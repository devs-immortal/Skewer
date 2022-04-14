package net.immortaldevs.skewer.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public final class SkewerFoodComponents {
    public static final FoodComponent KEBAB = new FoodComponent.Builder().saturationModifier(1f).build();

    public static final FoodComponent GARLIC = new FoodComponent.Builder().alwaysEdible().hunger(1).saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 3), 0.8f).build();

    public static final FoodComponent KELP_GOO = new FoodComponent.Builder().build();
    public static final FoodComponent AIOLI = new FoodComponent.Builder().alwaysEdible().hunger(3).saturationModifier(1.8f).build();
    public static final FoodComponent CHILLI_SAUCE = new FoodComponent.Builder().alwaysEdible().hunger(2).saturationModifier(1.6f).build();
}
