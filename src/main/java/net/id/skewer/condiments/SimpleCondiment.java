package net.id.skewer.condiments;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class SimpleCondiment implements Condiment{
    private final FoodComponent foodComponent;
    private final BiConsumer<PlayerEntity, Item> onConsumed;

    public SimpleCondiment(FoodComponent foodComponent, BiConsumer<PlayerEntity, Item> onConsumed){
        this.foodComponent = foodComponent;
        this.onConsumed = onConsumed;
    }

    public SimpleCondiment(FoodComponent foodComponent){
        this(foodComponent, (p, i) -> {});
    }

    @NotNull
    @Override
    public FoodComponent getFoodComponent() {
        return foodComponent;
    }

    @NotNull
    @Override
    public BiConsumer<PlayerEntity, Item> getOnConsumed() {
        return onConsumed;
    }
}
