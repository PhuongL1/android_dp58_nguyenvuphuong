package com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model.FoodItem;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model.IngredientItem;

import java.util.ArrayList;

public class FoodViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<FoodItem>> foodListLiveData = new MutableLiveData<>();
    private final MutableLiveData<FoodItem> selectedFoodLiveData = new MutableLiveData<>();

    private final ArrayList<FoodItem> foodList = new ArrayList<>();

    public FoodViewModel() {
        initData();
    }

    private void initData() {
        foodList.add(new FoodItem(
                1,
                "Telor ceplok",
                "Simple fried egg with warm rice and seasoning.",
                "9 mins",
                "Easy",
                "1 people",
                4.3f,
                R.drawable.food_egg_d7,
                false,
                createEggIngredients()
        ));

        foodList.add(new FoodItem(
                2,
                "Salad vegetarian",
                "Fresh vegetable salad with healthy dressing.",
                "10 mins",
                "Easy",
                "2 people",
                4.3f,
                R.drawable.food_salad_d7,
                false,
                createSaladIngredients()
        ));

        foodList.add(new FoodItem(
                3,
                "Toast with egg",
                "Crispy toast served with egg and herbs.",
                "30 mins",
                "Medium",
                "1 people",
                4.3f,
                R.drawable.food_toast_d7,
                false,
                createToastIngredients()
        ));

        foodList.add(new FoodItem(
                4,
                "Salmon sauce",
                "Fresh salmon served with special creamy sauce.",
                "45 mins",
                "Hard",
                "3 people",
                4.3f,
                R.drawable.food_salmon_d7,
                false,
                createSalmonIngredients()
        ));

        foodList.add(new FoodItem(
                5,
                "Mushroom soup",
                "Hot mushroom soup with creamy texture.",
                "15 mins",
                "Easy",
                "2 people",
                4.3f,
                R.drawable.food_soup_d7,
                false,
                createSoupIngredients()
        ));

        foodList.add(new FoodItem(
                6,
                "Gourmet dessert",
                "Elegant layered mousse cake with fresh fruit toppings, including blueberries and kiwi.",
                "30 mins",
                "Medium",
                "4 people",
                4.8f,
                R.drawable.food_dessert_d7,
                false,
                createDessertIngredients()
        ));

        foodListLiveData.setValue(foodList);

        if (!foodList.isEmpty()) {
            selectedFoodLiveData.setValue(foodList.get(0));
        }
    }

    public LiveData<ArrayList<FoodItem>> getFoodListLiveData() {
        return foodListLiveData;
    }

    public LiveData<FoodItem> getSelectedFoodLiveData() {
        return selectedFoodLiveData;
    }

    public void selectFood(FoodItem foodItem) {
        selectedFoodLiveData.setValue(foodItem);
    }

    public void toggleFavorite(int foodId) {
        FoodItem selectedFood = null;

        for (FoodItem item : foodList) {
            if (item.getId() == foodId) {
                item.setFavorite(!item.isFavorite());
                selectedFood = item;
                break;
            }
        }

        foodListLiveData.setValue(foodList);

        if (selectedFood != null) {
            selectedFoodLiveData.setValue(selectedFood);
        }
    }

    private ArrayList<String> createNutrition(String n1, String n2, String n3) {
        ArrayList<String> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        return list;
    }
    private ArrayList<IngredientItem> createEggIngredients() {
        ArrayList<IngredientItem> list = new ArrayList<>();

        list.add(new IngredientItem(
                "Egg",
                "2 pcs",
                R.drawable.food_egg_1_d7,
                createNutrition("Protein", "Fat", "Vitamin")
        ));

        list.add(new IngredientItem(
                "Rice",
                "150 gr",
                R.drawable.food_rice_d7,
                createNutrition("Carb", "Fiber", "Energy")
        ));

        list.add(new IngredientItem(
                "Tomato",
                "50 gr",
                R.drawable.food_tomato_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        return list;
    }

    private ArrayList<IngredientItem> createSaladIngredients() {
        ArrayList<IngredientItem> list = new ArrayList<>();

        list.add(new IngredientItem(
                "Lettuce",
                "100 gr",
                R.drawable.food_lettuce_d7,
                createNutrition("Fiber", "Vitamin", "Carb")
        ));

        list.add(new IngredientItem(
                "Tomato",
                "80 gr",
                R.drawable.food_tomato_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        list.add(new IngredientItem(
                "Carrot",
                "60 gr",
                R.drawable.food_carrot_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        return list;
    }
    private ArrayList<IngredientItem> createToastIngredients() {
        ArrayList<IngredientItem> list = new ArrayList<>();

        list.add(new IngredientItem(
                "Toast",
                "2 slices",
                R.drawable.food_toast_1_d7,
                createNutrition("Carb", "Sugar", "Fiber")
        ));

        list.add(new IngredientItem(
                "Egg",
                "1 pcs",
                R.drawable.food_egg_d7,
                createNutrition("Protein", "Fat", "Vitamin")
        ));

        list.add(new IngredientItem(
                "Cheese",
                "30 gr",
                R.drawable.food_cheese_d7,
                createNutrition("Fat", "Protein", "Calcium")
        ));

        return list;
    }
    private ArrayList<IngredientItem> createSalmonIngredients() {
        ArrayList<IngredientItem> list = new ArrayList<>();

        list.add(new IngredientItem(
                "Salmon",
                "300 gr",
                R.drawable.food_salmon_1_d7,
                createNutrition("Protein", "Fat", "Omega")
        ));

        list.add(new IngredientItem(
                "Cream sauce",
                "100 gr",
                R.drawable.food_cream_d7,
                createNutrition("Fat", "Protein", "Sugar")
        ));

        list.add(new IngredientItem(
                "Lemon",
                "30 gr",
                R.drawable.food_lemon_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        return list;
    }
    private ArrayList<IngredientItem> createSoupIngredients() {
        ArrayList<IngredientItem> list = new ArrayList<>();

        list.add(new IngredientItem(
                "Mushroom",
                "200 gr",
                R.drawable.food_mushroom_d7,
                createNutrition("Protein", "Fiber", "Vitamin")
        ));

        list.add(new IngredientItem(
                "Cream",
                "120 gr",
                R.drawable.food_cream_d7,
                createNutrition("Fat", "Protein", "Sugar")
        ));

        list.add(new IngredientItem(
                "Onion",
                "50 gr",
                R.drawable.food_onion_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        return list;
    }
    private ArrayList<IngredientItem> createDessertIngredients() {
        ArrayList<IngredientItem> list = new ArrayList<>();

        list.add(new IngredientItem(
                "Blueberry",
                "100 gr",
                R.drawable.food_blueberry_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        list.add(new IngredientItem(
                "Kiwi",
                "80 gr",
                R.drawable.food_kiwi_d7,
                createNutrition("Vitamin", "Fiber", "Carb")
        ));

        list.add(new IngredientItem(
                "Cream",
                "120 gr",
                R.drawable.food_cream_d7,
                createNutrition("Fat", "Protein", "Sugar")
        ));

        list.add(new IngredientItem(
                "Cake base",
                "150 gr",
                R.drawable.food_cake_d7,
                createNutrition("Carb", "Sugar", "Fat")
        ));

        list.add(new IngredientItem(
                "Milk",
                "100 ml",
                R.drawable.food_milk_d7,
                createNutrition("Protein", "Fat", "Calcium")
        ));

        return list;
    }
}