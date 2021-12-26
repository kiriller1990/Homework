package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.CaloriesInDishCalculationDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.ICaloriesCalculationService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Ingredient;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaloriesCalculationService implements ICaloriesCalculationService {


    @Override
    public CaloriesInDishCalculationDTO calculateCaloriesInDish(FoodDiary foodDiary) {
        CaloriesInDishCalculationDTO caloriesInDishCalculationDTO = new CaloriesInDishCalculationDTO();
        double caloriesCount = 0.0;
        double proteinsCount = 0.0;
        double fatsCount = 0.0;
        double carbohydratesCount = 0.0;
        if(foodDiary.getProduct()!=null) {
            //рассчет КБЖУ для продукта, если в приеме пищи был чистый продукт
            Product product = foodDiary.getProduct();

            //вес съеденного продукта
            double mealWeight = foodDiary.getWeight();

            //Вес продукта для которого рассчитан КБЖУ
            double weightInProduct = product.getWeight();

            //получение КБЖУ в продукте
            double caloriesInProduct = product.getCalories();
            double proteinsInProduct = product.getProteins();
            double fatsInProduct = product.getFats();
            double carbohydratesInProduct = product.getCarbohydrates();

            //КБЖУ в 1 грамме продукта
            double caloriesInOneGram= caloriesInProduct  / mealWeight;
            double proteinsInOneGram = proteinsInProduct/mealWeight;
            double fatsInOneGram = fatsInProduct/mealWeight;
            double carbohydratesInOneGram = carbohydratesInProduct/mealWeight;

            // Рассчет КБЖУ приема пищи
            double productCaloriesInDish = caloriesInOneGram * weightInProduct;
            double productProteinsInDish = proteinsInOneGram * weightInProduct;
            double productFatsInDish = fatsInOneGram * weightInProduct;
            double productCarbohydratesInDish = carbohydratesInOneGram * weightInProduct;

            caloriesCount = productCaloriesInDish;
            proteinsCount = productProteinsInDish;
            fatsCount = productFatsInDish;
            carbohydratesCount = productCarbohydratesInDish;

        }else if(foodDiary.getDish()!= null) {
            List<Ingredient> ingredients = foodDiary.getDish().getIngredients();

            //Рассчет КБЖУ для каждого продукта в блюде
            for(int i=0;i<=(ingredients.size()-1);i++){
                //получение продукта в блюде
                    Product productInDish = ingredients.get(i).getProduct();
                    //получение КБЖУ в продукте
                    double caloriesInProduct = productInDish.getCalories();
                    double proteinsInProduct = productInDish.getProteins();
                    double fatsInProduct = productInDish.getFats();
                    double carbohydratesInProduct = productInDish.getCarbohydrates();

                    //вес продукта
                    double productWeight = productInDish.getWeight();

                    //КБЖУ в 1 грамме продукта
                    double caloriesInOneGram = caloriesInProduct / productWeight;
                    double proteinsInOneGram = proteinsInProduct / productWeight;
                    double fatsInOneGram = fatsInProduct / productWeight;
                    double carbohydratesInOneGram = carbohydratesInProduct / productWeight;

                    //Вес продукта в блюде
                    double weightProductInDish = ingredients.get(i).getWeight();
                // Рассчет КБЖУ продукта в блюде
                double productCaloriesInDish = caloriesInOneGram * weightProductInDish;
                double productProteinsInDish = proteinsInOneGram * weightProductInDish;
                double productFatsInDish = fatsInOneGram * weightProductInDish;
                double productCarbohydratesInDish = carbohydratesInOneGram * weightProductInDish;

                //Добавление в общий счетчик КБЖУ блюда
                caloriesCount += productCaloriesInDish;
                proteinsCount += productProteinsInDish;
                fatsCount += productFatsInDish;
                carbohydratesCount += productCarbohydratesInDish;
            }
        }
        //Добавление КБЖУ в ДТО
        caloriesInDishCalculationDTO.setCalories(caloriesCount);
        caloriesInDishCalculationDTO.setProteins(proteinsCount);
        caloriesInDishCalculationDTO.setFats(fatsCount);
        caloriesInDishCalculationDTO.setCarbohydrates(carbohydratesCount);

        return caloriesInDishCalculationDTO;
    }


}
