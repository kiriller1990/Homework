package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IActivityDiaryDAO extends JpaRepository<ActivityDiary, Long> {
    List<ActivityDiary> findActivityDiaryByProfileIdAndDateOfActivityBetween(long id_profile,
                                                                                 LocalDate dt_start, LocalDate dt_end);
}
