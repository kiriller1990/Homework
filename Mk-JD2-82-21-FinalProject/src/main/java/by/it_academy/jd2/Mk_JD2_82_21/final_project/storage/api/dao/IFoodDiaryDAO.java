package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IFoodDiaryDAO extends JpaRepository<FoodDiary, Long> {
    Page<FoodDiary> findALLyByProfileId (long id_profile, Pageable pageable);
    FoodDiary findFoodDiaryByProfileIdAndId(long id_profile, long id_food);
    List<FoodDiary> findAllByProfileIdAndUpdateTimeBetween(Long profileId, LocalDateTime startDay, LocalDateTime endDay);

}
