package com.example.calco1.Model;

import java.io.Serializable;

public class FoodModel implements Serializable {
    private String foodName;
    private String calories;

    public FoodModel() {
    }

    public FoodModel(String foodName, String calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
