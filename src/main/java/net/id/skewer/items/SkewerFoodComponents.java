package net.id.skewer.items;

import net.minecraft.item.FoodComponent;

public class SkewerFoodComponents {
    public static final FoodComponent GARLIC = new FoodComponent.Builder().build();
    public static final FoodComponent AIOLI = new FoodComponent.Builder().saturationModifier(1).build();
    public static final FoodComponent EMPTY = new FoodComponent.Builder().build();
}
