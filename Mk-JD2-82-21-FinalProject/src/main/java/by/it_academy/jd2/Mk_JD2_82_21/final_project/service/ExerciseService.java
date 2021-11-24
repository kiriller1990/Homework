package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IExerciseService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IExerciseDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements IExerciseService {
    private static IExerciseDAO exerciseDAO;

    public ExerciseService(IExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public void addExercise(Exercise exercise) {
        exerciseDAO.save(exercise);
    }

    @Override
    public Exercise getExercise(long exerciseId) {
        return exerciseDAO.findById(exerciseId).orElseThrow(()->
                new IllegalArgumentException("По данному ID упражнения не найдены"));
    }

    @Override
    public List<Exercise> getExerciseList() {
        return exerciseDAO.findAll();
    }

    @Override
    public void updateExercise(Exercise exercise, long id) {
            Exercise exerciseUpdate = getExercise(id);
            exerciseUpdate.setExerciseName(exercise.getExerciseName());
            exerciseUpdate.setCalories(exercise.getCalories());
            exerciseUpdate.setProfile(exercise.getProfile());
            exerciseDAO.save(exerciseUpdate);
    }

    @Override
    public void deleteExercise(long id) {
            exerciseDAO.deleteById(id);
    }
}

