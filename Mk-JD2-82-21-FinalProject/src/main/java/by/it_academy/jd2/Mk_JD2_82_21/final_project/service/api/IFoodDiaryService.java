package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.FoodDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IFoodDiaryService {
    public void addFoodDiary(FoodDiary foodDiary,long id_profile);
    public FoodDiaryDTO getFoodDiaryCard(long id_profile, long id_food);
    public Page<FoodDiary> getFoodDiaryList(Pageable pageable, long id_profile);
    public void updateFoodDiary(FoodDiary foodDiary, LocalDateTime dt_update, long id_profile, long id_food);
    public void deleteFoodDiary(long id);
    public List<FoodDiary> getAllMealsInADay(long day, long id_profile);
    public FoodDiaryDTO countingCaloriesReceived (FoodDiary foodDiary);
}
