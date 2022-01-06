package net.id.skewer.condiments;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public interface Condiment {
    @NotNull
    FoodComponent getFoodComponent();

    @NotNull
    default BiConsumer<PlayerEntity, Item> getOnConsumed(){
        return (p, i) -> {};
    }
}
