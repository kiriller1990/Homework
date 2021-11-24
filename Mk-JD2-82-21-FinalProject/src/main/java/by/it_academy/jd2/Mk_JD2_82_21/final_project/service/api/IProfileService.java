package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Ingredient;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProfileService {
    public void addProfile(Profile profile);
    public Profile getProfile(long profileId);
    public Page<Profile> getProfileList(Pageable pageable);
    void updateProfile(Profile profile, long id);
    void deleteProfile(long id);
}
