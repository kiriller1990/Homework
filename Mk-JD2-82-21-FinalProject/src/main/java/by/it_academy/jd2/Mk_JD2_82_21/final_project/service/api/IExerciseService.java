package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Exercise;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;

import java.util.List;

public interface IExerciseService {
    public void addExercise(Exercise exercise);
    public Exercise getExercise(long exerciseId);
    public List<Exercise> getExerciseList();
    void updateExercise(Exercise exercise, long id);
    void deleteExercise(long id);
}
