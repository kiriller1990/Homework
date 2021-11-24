package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.ActivityDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IActivityDiaryDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityDiaryService implements IActivityDiaryService {
    private final IActivityDiaryDAO activityDiaryDAO;
    private final UserHolder userHolder;
    private final IProfileService profileService

    public ActivityDiaryService(IActivityDiaryDAO activityDiaryDAO, UserHolder userHolder, IProfileService profileService) {
        this.activityDiaryDAO = activityDiaryDAO;
        this.userHolder = userHolder;
        this.profileService = profileService;
    }


    @Override
    public ActivityDiary save(ActivityDiaryDTO activityDiaryDTO) {
        ActivityDiary activityDiary = new ActivityDiary();
        activityDiary.setProfile(profileService.getProfile(activityDiaryDTO.getProfile().getId()));
        activityDiary.setActivityType(activityDiary.getActivityType());
        activityDiary.setCalories(activityDiaryDTO.getCalories());
        LocalDateTime createTime = new LocalDateTime.now();
        activityDiary.setCreateDate(createTime);
        activityDiary.setUpdateDate(createTime);
        activityDiaryDAO.save(activityDiary);
        return activityDiary;
    }

    @Override
    public ActiveByDateDto findAllActivityByDate(LocalDateTime start, LocalDateTime end,
                                                             Long id, Pageable pageable) {
        Page<Active> actives = activeDao.findAllByCreationDateBetweenAndProfileId(start, end, id, pageable);
        return
    }


    @Override
    public Active get(Long id) throws IllegalArgumentException {
        return activeDao.findById(id).orElseThrow();
    }

    @Override
    public void update(ActiveDto activeDto, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Active updatedActive = get(id);

        if (!updatedActive.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемая активность была изменена");
        } else {

            updatedActive.setName(activeDto.getName());
            updatedActive.setCalories(activeDto.getCalories());
            updatedActive.setProfile(activeDto.getProfile());

            LocalDateTime updateDate = LocalDateTime.now().withNano(0);
            updatedActive.setUpdateDate(updateDate);

            activeDao.save(updatedActive);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Active deletedActive = get(id);
        if (!deletedActive.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как удаляемая " +
                    "активность была изменена");
        } else {
            activeDao.deleteById(id);
        }
}
