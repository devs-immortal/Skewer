package net.immortaldevs.skewer.sar;

import net.immortaldevs.sar.api.Modifier;
import net.immortaldevs.sar.api.ModifierMap;
import net.minecraft.item.FoodComponent;

import java.util.function.Consumer;

public interface FoodModifier extends Modifier {
    void apply(Consumer<FoodComponent> consumer);

    @Override
    default void register(ModifierMap modifierMap) {
        modifierMap.merge(FoodModifier.class, this, (a, b) -> consumer -> {
            a.apply(consumer);
            b.apply(consumer);
        });
    }
}
