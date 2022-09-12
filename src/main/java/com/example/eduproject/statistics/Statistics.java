package com.example.eduproject.statistics;

import com.example.eduproject.eaten.EatenFood;
import com.example.eduproject.dish.Dish;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class Statistics {

    private float lipids = 0;
    private float carbs = 0;
    private float proteins = 0;
    private float kilocalories = 0;

    public Statistics(Map<EatenFood, Dish> eatenDishMap) {
        Set<EatenFood> eatenFoodSet = eatenDishMap.keySet();

        for (EatenFood eatenFood : eatenFoodSet) {
            this.lipids += eatenDishMap.get(eatenFood)
                    .getLipids()
                    * eatenFood.getMass() / 100.0;
            this.carbs += eatenDishMap.get(eatenFood)
                    .getCarbs()
                    * eatenFood.getMass() /100.0;
            this.proteins += eatenDishMap.get(eatenFood)
                    .getProteins()
                    * eatenFood.getMass() / 100.0;
            this.kilocalories += eatenDishMap.get(eatenFood)
                    .getKiloCalories()
                    * eatenFood.getMass() / 100.0;
        }
    }

}
