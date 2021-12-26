package by.it_academy.jd2.Mk_JD2_82_21.final_project.utils;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class OptimisticLock  {
    private final UserHolder userHolder;
    private final IProductService productService;
    private final IProfileService profileService;
    private final IDishService dishService;
    private final TimeConverter timeConverter;
    private final IWeightMeasurementsService weightMeasurementsService;
    private final IFoodDiaryService foodDiaryService;
    private final IActivityDiaryService activityDiaryService;


    public OptimisticLock(UserHolder userHolder, IProductService productService, IProfileService profileService,
                          IDishService dishService, TimeConverter timeConverter,
                          IWeightMeasurementsService weightMeasurementsService, IFoodDiaryService foodDiaryService,
                          IActivityDiaryService activityDiaryService) {
        this.userHolder = userHolder;
        this.productService = productService;
        this.profileService = profileService;
        this.dishService = dishService;
        this.timeConverter = timeConverter;
        this.weightMeasurementsService = weightMeasurementsService;
        this.foodDiaryService = foodDiaryService;
        this.activityDiaryService = activityDiaryService;
    }

    public boolean userOptimisticLock (long dtUpdate) {
        LocalDateTime userUpdateTime = userHolder.getUser().getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(userUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка обновления не удалась,т.к в процессе обновления " +
                    "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

    public boolean productOptimisticLock (long dtUpdate, long id) {
        LocalDateTime productUpdateTime = productService.getProduct(id).getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(productUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка не удалась,т.к в процессе выполнения запроса " +
                    "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

    public boolean profileOptimisticLock (long dtUpdate, long id) {
        LocalDateTime profileUpdateTime = profileService.getProfile(id).getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(profileUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка не удалась,т.к в процессе выполнения запроса " +
                    "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

    public boolean dishOptimisticLock (long dtUpdate, long id) {
        LocalDateTime dishUpdateTime = dishService.getDish(id).getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(dishUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка не удалась,т.к в процессе выполнения запроса " +
                    "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

    public boolean weightMeasurementsOptimisticLock (long dtUpdate, long id) {
        LocalDateTime weightMeasurementsUpdateTime = weightMeasurementsService.getWeightMeasurementsCard(id).getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(weightMeasurementsUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка не удалась,т.к в процессе выполнения запроса " +
                    "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

    public boolean foodDiaryOptimisticLock (long idFood, long idProfile, long dtUpdate) {
        LocalDateTime FoodDiaryUpdateTime = foodDiaryService.getFoodDiaryCard(idProfile, idFood).getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(FoodDiaryUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка не удалась,т.к в процессе выполнения запроса " +
                    "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

    public boolean activityDiaryOptimisticLock (long dtUpdate, long idActivity) {
        LocalDateTime activityDiaryUpdateTime = activityDiaryService.getActivityDiaryCard(idActivity).
                                                                                        getUpdateDate();
        long dataBaseUpdateTimeInMills = timeConverter.convertFromLocalDateTimeToMills(activityDiaryUpdateTime);
        if (dtUpdate == dataBaseUpdateTimeInMills || dtUpdate == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Попытка не удалась,т.к в процессе выполнения запроса " +
                        "другой пользователь успел внести изменения, пожалуйста попробуйте еще раз");
        }
    }

}
