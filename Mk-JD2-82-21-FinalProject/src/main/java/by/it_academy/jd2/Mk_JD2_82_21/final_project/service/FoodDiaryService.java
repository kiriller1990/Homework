package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.CaloriesInDishCalculationDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.FoodDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.ICaloriesCalculationService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IFoodDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IFoodDiaryDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IProfileDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class FoodDiaryService implements IFoodDiaryService {
    private final IFoodDiaryDAO foodDiaryDAO;
    private final IProfileService profileService;
    private final ICaloriesCalculationService caloriesCalculationService;
    private final UserHolder userHolder;


    public FoodDiaryService(IFoodDiaryDAO foodDiaryDAO, IProfileService profileService,
                            ICaloriesCalculationService caloriesCalculationService,
                            UserHolder userHolder) {
        this.foodDiaryDAO = foodDiaryDAO;
        this.profileService = profileService;
        this.caloriesCalculationService = caloriesCalculationService;
        this.userHolder = userHolder;
    }

    @Override
    public void addFoodDiary(FoodDiary foodDiary,long id_profile) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(id_profile);
        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
            LocalDateTime timeStamp = LocalDateTime.now();
            foodDiary.setCreateDate(timeStamp);
            foodDiary.setUpdateDate(timeStamp);
            foodDiary.setProfile(profileService.getProfile(id_profile));
            if(foodDiary.getDish()!=null) {
                foodDiary.setDish(foodDiary.getDish());
            }
            if (foodDiary.getProduct()!=null) {
                foodDiary.setProduct(foodDiary.getProduct());
            }
            foodDiaryDAO.save(foodDiary);
        } else {
            throw new IllegalCallerException ("Добавлять дневник питания можно только в свой профиль");
        }
    }

    @Override
    public List<FoodDiary> getAllMealsInADay(long day, long id_profile) {
        LocalDateTime startDay = Instant.ofEpochMilli(day).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDay = startDay.plusDays(1);
        return foodDiaryDAO.findAllByProfileIdAndUpdateDateBetween(id_profile,startDay,endDay);
    }

    @Override
    public FoodDiaryDTO getFoodDiaryCard(long id_profile, long id_food) {
        FoodDiaryDTO foodDiaryDTO =countingCaloriesReceived(foodDiaryDAO.findFoodDiaryByProfileIdAndId(id_profile,id_food));
        return foodDiaryDTO;
    }


    @Override
    public Page<FoodDiary> getFoodDiaryList(Pageable pageable, long id_profile) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(id_profile);
        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
            return foodDiaryDAO.findALLyByProfileId(id_profile, pageable);
        } else {
            throw new IllegalCallerException ("Получать дневники питания может только по своему профилю");
        }

    }

    @Override
    public void updateFoodDiary(FoodDiary foodDiary, LocalDateTime dt_update, long id_profile, long id_food) {
        FoodDiary updateFoodDiary = foodDiaryDAO.findFoodDiaryByProfileIdAndId(id_profile,id_food);
        updateFoodDiary.setProfile(foodDiary.getProfile());
        updateFoodDiary.setEatingTimeType(foodDiary.getEatingTimeType());
        updateFoodDiary.setProduct(foodDiary.getProduct());
        updateFoodDiary.setDish(foodDiary.getDish());
        updateFoodDiary.setWeight(foodDiary.getWeight());
        foodDiaryDAO.save(updateFoodDiary);
    }

    @Override
    public void deleteFoodDiary(long id) {
        foodDiaryDAO.deleteById(id);
    }

    @Override
    public FoodDiaryDTO countingCaloriesReceived(FoodDiary foodDiary) {
        LocalDateTime updateDate = LocalDateTime.now();
        FoodDiaryDTO foodDiaryDTO = new FoodDiaryDTO();
        CaloriesInDishCalculationDTO caloriesCalculation = caloriesCalculationService.calculateCaloriesInDish(foodDiary);
        foodDiaryDTO.setId(foodDiary.getId());
        foodDiaryDTO.setProfile(foodDiary.getProfile());
        foodDiaryDTO.setEatingTimeType(foodDiary.getEatingTimeType());
        foodDiaryDTO.setDish(foodDiary.getDish());
        foodDiaryDTO.setWeight(foodDiary.getWeight());
        foodDiaryDTO.setCreateDate(updateDate);
        foodDiaryDTO.setUpdateDate(updateDate);
        foodDiaryDTO.setCaloriesReceived(caloriesCalculation.getCalories());
        foodDiaryDTO.setFatsReceived(caloriesCalculation.getFats());
        foodDiaryDTO.setProteinsReceived(caloriesCalculation.getProteins());
        foodDiaryDTO.setCarbohydratesReceived(caloriesCalculation.getCarbohydrates());
        return foodDiaryDTO;
    }


}
