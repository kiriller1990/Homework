package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IActivityDiaryDAO extends JpaRepository<ActivityDiary, Long> {
    Page<ActivityDiary> findActivityDiaryByProfileIdAndUpdateDateBetween (Pageable pageable, long idProfile,
                                                                          LocalDateTime startDate, LocalDateTime endDay);
}
