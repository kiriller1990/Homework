package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import org.springframework.data.domain.Page;

public class WeightMeasurementsByDateDTO {
    private Page<WeightMeasurements> weightMeasurements;

    public WeightMeasurementsByDateDTO() {
    }

    public Page<WeightMeasurements> getWeightMeasurements() {
        return weightMeasurements;
    }

    public void setWeightMeasurements(Page<WeightMeasurements> weightMeasurements) {
        this.weightMeasurements = weightMeasurements;
    }
}
