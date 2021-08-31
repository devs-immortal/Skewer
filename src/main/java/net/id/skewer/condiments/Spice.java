package net.id.skewer.condiments;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class Spice implements Condiment{
    private final FoodComponent foodComponent;
    private final BiConsumer<PlayerEntity, Item> onConsumed;

    public Spice(FoodComponent foodComponent, BiConsumer<PlayerEntity, Item> onConsumed){
        this.foodComponent = foodComponent;
        this.onConsumed = onConsumed;
    }

    public Spice(FoodComponent foodComponent){
        this(foodComponent, (p, i) -> {});
    }

    @Override
    public @Nullable FoodComponent getFoodComponent() {
        return foodComponent;
    }

    @Override
    public BiConsumer<PlayerEntity, Item> getOnConsumed() {
        return onConsumed;
    }
}
