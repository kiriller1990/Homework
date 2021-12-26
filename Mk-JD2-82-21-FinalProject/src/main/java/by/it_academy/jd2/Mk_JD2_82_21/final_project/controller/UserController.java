package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.LoginDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.UserDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.JwtProvider;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.OptimisticLock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final IAuthService authService;
    private final JwtProvider jwtProvider;
    private final OptimisticLock optimisticLock;

    public UserController(IUserService userService, IAuthService authService, JwtProvider jwtProvider,
                          OptimisticLock optimisticLock) {
        this.userService = userService;
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.optimisticLock = optimisticLock;
    }

    @PostMapping (value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = this.authService.getByLoginAndPassword(loginDTO);
        if(user!=null){
            String token = "Bearer " + jwtProvider.generateToken(loginDTO.getLogin());
            UserDTO userDTO = new UserDTO();
            userDTO.setUser(user);
            userDTO.setToken("Bearer "+token);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
            return new ResponseEntity<>("Пользователь с таким логином и паролем не найден, пожалуйста, " +
                    "проверьте правильность ввода логина и пароля или зарегистрирйтесь!",HttpStatus.UNAUTHORIZED);
        }

    @PostMapping (value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        if (this.authService.getByLogin(user.getLogin()) != null) {
            return new ResponseEntity<>("Этот логин уже используется", HttpStatus.CONFLICT);
        } else {
            userService.addUser(user);
            return new ResponseEntity<>("Пользователь успешно зарегистрирован",HttpStatus.CREATED);
        }

    }

    @PutMapping (value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @PathVariable("dt_update") Long dtUpdate,
                                        @RequestBody User user){
        try {
             optimisticLock.userOptimisticLock(dtUpdate);
             userService.updateUser(user, id);
             return new ResponseEntity<>("Пользователь успешно обновлен",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam (value = "page",required = false, defaultValue = "0")int page,
                                    @RequestParam(value = "size",required = false, defaultValue = "30")int size){
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            Page<User> users = userService.getUserList(pageable);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
}
