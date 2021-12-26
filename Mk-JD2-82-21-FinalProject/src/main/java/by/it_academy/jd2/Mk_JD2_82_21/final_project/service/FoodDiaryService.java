package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.CaloriesInDishCalculationDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.FoodDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.*;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodDiaryService implements IFoodDiaryService {
    private final IFoodDiaryDAO foodDiaryDAO;
    private final IProfileService profileService;
    private final ICaloriesCalculationService caloriesCalculationService;
    private final UserHolder userHolder;
    private final IDishService dishService;
    private final IProductService productService;


    public FoodDiaryService(IFoodDiaryDAO foodDiaryDAO, IProfileService profileService,
                            ICaloriesCalculationService caloriesCalculationService,
                            UserHolder userHolder, IDishService dishService, IProductService productService) {
        this.foodDiaryDAO = foodDiaryDAO;
        this.profileService = profileService;
        this.caloriesCalculationService = caloriesCalculationService;
        this.userHolder = userHolder;
        this.dishService = dishService;
        this.productService = productService;
    }

    @Override
    public FoodDiary addFoodDiary(FoodDiary foodDiary,long idProfile) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(idProfile);
        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
            LocalDateTime timeStamp = LocalDateTime.now();
            foodDiary.setCreateDate(timeStamp);
            foodDiary.setUpdateDate(timeStamp);
            foodDiary.setProfile(profileService.getProfile(idProfile));
            if(foodDiary.getDish()!=null) {
                foodDiary.setDish(dishService.getDish(foodDiary.getDish().getId()));
            }
            if (foodDiary.getProduct()!= null) {
                foodDiary.setProduct(productService.getProduct(foodDiary.getProduct().getId()));
            }
           return foodDiaryDAO.save(foodDiary);
        } else {
            throw new IllegalArgumentException ("Добавлять дневник питания можно только в свой профиль");
        }
    }

    @Override
    public List<FoodDiaryDTO> getAllMealsInADay(long day, long idProfile) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(idProfile);
        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
            LocalDateTime startDay = Instant.ofEpochMilli(day).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime endDay = startDay.plusDays(1);
            List<FoodDiary> foodDiariesInDay = foodDiaryDAO.findAllByProfileIdAndUpdateDateBetween(idProfile, startDay, endDay);
            List<FoodDiaryDTO> foodDiariesInDayDTO = countingCaloriesReceivedForDay(foodDiariesInDay);
            return foodDiariesInDayDTO;
        } else {
            throw new IllegalArgumentException("Вы можете просматривать только свои дневники питания");
        }
    }

    @Override
    public FoodDiaryDTO getFoodDiaryCard(long idProfile, long idFood) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(idProfile);
        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
            FoodDiaryDTO foodDiaryDTO =countingCaloriesReceived(foodDiaryDAO.findById(idFood).orElseThrow(()->
                new IllegalArgumentException ("По данному ID запись в дневнике питания не найдена")));
            return foodDiaryDTO;
        } else {
            throw new IllegalArgumentException("Вы можете просматривать только свои дневники питания");
        }
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
    public void updateFoodDiary(FoodDiary foodDiary, long idProfile, long idFood) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(idProfile);

        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
            FoodDiary foodDiaryToUpdate = foodDiaryDAO.findById(idFood).orElseThrow(()->
                    new IllegalArgumentException ("По данному ID запись в дневнике питания не найдена"));
            foodDiaryToUpdate.setProfile(profileService.getProfile(idProfile));
            if(foodDiary.getProduct()!=null) {
                foodDiaryToUpdate.setProduct(productService.getProduct(foodDiary.getProduct().getId()));
            }
            if (foodDiary.getDish() != null) {
                foodDiary.setDish(dishService.getDish(foodDiary.getDish().getId()));
            }
            foodDiaryToUpdate.setEatingTimeType(foodDiary.getEatingTimeType());
            foodDiaryToUpdate.setWeight(foodDiary.getWeight());
            foodDiaryToUpdate.setUpdateDate(LocalDateTime.now());
            foodDiaryDAO.save(foodDiaryToUpdate);
        }else {
            throw new IllegalArgumentException("Вы можете обновлять только свои дневники питания");
        }
    }

    @Override
    public void deleteFoodDiary( long idProfile, long idFood) {
        User user = userHolder.getUser();
        Profile profile = profileService.getProfile(idProfile);

        if(profile.getUser().getId() == user.getId() || user.getRole().equals(ERole.ROLE_ADMIN)) {
        foodDiaryDAO.deleteById(idFood);
        }else {
            throw new IllegalArgumentException("Вы можете удалить только свой дневник питания");
        }
    }

    public List<FoodDiaryDTO> countingCaloriesReceivedForDay (List<FoodDiary> foodDiaries) {
        List<FoodDiaryDTO> foodDiaryDTOList = new ArrayList<>();
        for (FoodDiary foodDiary : foodDiaries)  {
               FoodDiaryDTO foodDiaryDTO = countingCaloriesReceived(foodDiary);
               foodDiaryDTOList.add(foodDiaryDTO);
        }
        return  foodDiaryDTOList;
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
        foodDiaryDTO.setProduct(foodDiary.getProduct());
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
