package net.id.skewer.condiments;

import net.id.skewer.registry.SkewerRegistries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public interface Condiment {
    @NotNull
    FoodComponent getFoodComponent();

    @NotNull
    default BiConsumer<PlayerEntity, Item> getOnConsumed(){
        return (p, i) -> {};
    }

    static Condiment fromNbt(NbtCompound nbt){
        return SkewerRegistries.CONDIMENT.get(new Identifier(nbt.getString("id")));
    }
}
