package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.CaloriesInDishCalculationDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.FoodDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;

public interface ICaloriesCalculationService {
    CaloriesInDishCalculationDTO calculateCaloriesInDish (FoodDiary foodDiary);
}
