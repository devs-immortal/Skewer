package net.id.skewer.condiments;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public interface Condiment {
    @Nullable FoodComponent getFoodComponent();

    default BiConsumer<PlayerEntity, Item> getOnConsumed(){
        return (player, item) -> {};
    }
}
