package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IProfileDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.CheckUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IProfileService {
    private final IProfileDAO profileDAO;
    private final UserHolder userHolder;
    private final CheckUtil checkUtil;
    private final IUserService userService;


    public ProfileService(IProfileDAO profileDAO, UserHolder userHolder, CheckUtil checkUtil, IUserService userService) {
        this.profileDAO = profileDAO;
        this.userHolder = userHolder;
        this.checkUtil = checkUtil;
        this.userService = userService;
    }

    @Override
    public void addProfile(Profile profile) {
        Profile addedProfile = profile;
        User user = userHolder.getUser();
        long userId = user.getId();
        if(profileDAO.findProfileByUserId(userId)!= null) {
            throw new IllegalArgumentException("У данного пользователя уже есть профиль");
        }
        addedProfile.setUser(user);
        LocalDateTime createTime = LocalDateTime.now();
        addedProfile.setCreateDate(createTime);
        addedProfile.setUpdateDate(createTime);
        profileDAO.save(addedProfile);
    }

    @Override
    public Profile getProfile(long profileId) {
        User user = userHolder.getUser();
        Profile profile =  profileDAO.findById(profileId).orElseThrow(()-> new IllegalArgumentException("По данному ID профиль не найден"));
        if (profile.getUser() == user || checkUtil.isAdminRoleCheck()) {
            return profile;
        } else {
            throw new IllegalArgumentException ("Вы можете посмотреть только свой профиль");
        }

    }

    @Override
    public Page<Profile> getProfileList(Pageable pageable) {
        if(checkUtil.isAdminRoleCheck()) {
            return profileDAO.findAll(pageable);
        } else {
            throw new IllegalArgumentException("Только Администратор может просматривать список пользователей");
        }
    }

    @Override
    public void updateProfile(Profile profile, long id) {
        Profile updatableProfile = profileDAO.getById(id);
        User profileUser = updatableProfile.getUser();
        User user =userHolder.getUser();
        if (user == profileUser || checkUtil.isAdminRoleCheck()) {
            updatableProfile.setUser(user);
            updatableProfile.setHeight(profile.getHeight());
            updatableProfile.setWeight(profile.getWeight());
            updatableProfile.setWeightGoal(profile.getWeightGoal());
            updatableProfile.setDateOfBirthday(profile.getDateOfBirthday());
            updatableProfile.setSex(profile.getSex());
            updatableProfile.setLifeStyle(profile.getLifeStyle());
            updatableProfile.setGoal(profile.getGoal());
            LocalDateTime updateDate = LocalDateTime.now();
            updatableProfile.setCreateDate(profile.getCreateDate());
            updatableProfile.setUpdateDate(updateDate);
            profileDAO.save(updatableProfile);

        } else {
            throw new IllegalArgumentException ("Только администратор может обновлять чужой профиль");
        }
    }

    @Override
    public void deleteProfile(long id) {
        User user = userHolder.getUser();
        Profile profile =  profileDAO.findById(id).orElseThrow(()-> new IllegalArgumentException("По данному ID профиль не найден"));
        if (profile.getUser()== user || user.getRole().equals(ERole.ROLE_ADMIN)) {
            profileDAO.deleteById(id);
        } else {
            throw new IllegalArgumentException ("Вы можете удалить только свой профиль");
        }
    }


}
