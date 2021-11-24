package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao;


import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileDAO extends JpaRepository<Profile, Long> {
}
