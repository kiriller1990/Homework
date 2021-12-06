package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IProfileDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IProfileService {
    private final IProfileDAO profileDAO;
    private final UserHolder userHolder;


    public ProfileService(IProfileDAO profileDAO, UserHolder userHolder) {
        this.profileDAO = profileDAO;
        this.userHolder = userHolder;
    }

    @Override
    public void addProfile(Profile profile) {
        User user = userHolder.getUser();
        profile.setUser(user);
        LocalDateTime createTime = LocalDateTime.now();
        profile.setUpdateDate(createTime);
        profile.setUpdateDate(createTime);
        profileDAO.save(profile);

    }

    @Override
    public Profile getProfile(long profileId) {
        User user = userHolder.getUser();
        Profile profile =  profileDAO.findById(profileId).orElseThrow(()-> new IllegalArgumentException("По данному ID профиль не найден"));
        if (profile.getUser()== user || user.getRole().equals(ERole.ROLE_ADMIN)) {
            return profile;
        } else {
            throw new IllegalCallerException ("Вы можете посмотреть только свой профиль");
        }

    }

    @Override
    public Page<Profile> getProfileList(Pageable pageable) {
        return profileDAO.findAll(pageable);
    }

    @Override
    public void updateProfile(Profile profile, long id) {
        User user =userHolder.getUser();
        Profile updatableProfile = getProfile(id);
        if (profile.getUser()== user || user.getRole().equals(ERole.ROLE_ADMIN)) {
            updatableProfile.setUser(user);
            updatableProfile.setHeight(profile.getHeight());
            updatableProfile.setWeight(profile.getWeight());
            updatableProfile.setWeightGoal(profile.getWeightGoal());
            updatableProfile.setDateOfBirthday(profile.getDateOfBirthday());
            updatableProfile.setSex(profile.getSex());
            updatableProfile.setLifeStyle(profile.getLifeStyle());
            updatableProfile.setGoal(profile.getGoal());
            LocalDateTime createDate = LocalDateTime.now();
            updatableProfile.setCreateDate(createDate);
            updatableProfile.setUpdateDate(createDate);
            profileDAO.save(updatableProfile);

        } else {
            throw new IllegalCallerException ("Вы можете обновить только свой профиль");
        }
    }

    @Override
    public void deleteProfile(long id) {
        User user = userHolder.getUser();
        Profile profile =  profileDAO.findById(id).orElseThrow(()-> new IllegalArgumentException("По данному ID профиль не найден"));
        if (profile.getUser()== user || user.getRole().equals(ERole.ROLE_ADMIN)) {
            profileDAO.deleteById(id);
        } else {
            throw new IllegalCallerException ("Вы можете удалить только свой профиль");
        }
    }
}
