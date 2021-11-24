package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final IProfileService profileService;

    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(value = "/{id_profile}")
    public ResponseEntity<?> getProfile(@PathVariable("id_profile") Long id) {
        try {
            Profile profile = profileService.getProfile(id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (IllegalCallerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

        @GetMapping
        public ResponseEntity<?> getAll(@RequestParam(value = "page",required = false, defaultValue = "0")int page,
                    @RequestParam(value = "size",required = false, defaultValue = "30")int  size) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            Page<Profile> profilePage = profileService.getProfileList(pageable);
            if (!profilePage.isEmpty()) {
                return new ResponseEntity<>(profilePage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Список профилей пуст", HttpStatus.NOT_FOUND);
            }
        }

            @PostMapping
            public ResponseEntity<?> save(@RequestBody Profile profile){
                profileService.addProfile(profile);
                return new ResponseEntity<>("Профиль успешно создан", HttpStatus.CREATED);
            }

            @PutMapping (value = "/{id_profile}")
            public ResponseEntity<?> update(@PathVariable("id_profile") Long id,
                    @RequestBody Profile profile){
                profileService.updateProfile(profile,id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            @DeleteMapping (value = "/{id_profile}")
            public ResponseEntity<?> delete(@PathVariable("id_profile") Long id){
                profileService.deleteProfile(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
}
