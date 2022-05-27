package net.immortaldevs.skewer.component;

import net.immortaldevs.sar.api.ComponentCollection;
import net.immortaldevs.sar.api.Modifier;
import net.immortaldevs.sar.base.SimpleComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;

public class CondimentComponent extends SimpleComponent {
    public CondimentComponent(Modifier... modifiers) {
        super(modifiers);
    }

    public boolean applyTo(ComponentCollection collection,
                           ItemStack condiment,
                           ItemStack kebab,
                           PlayerEntity player,
                           Hand hand) {
        int i = collection.size();

        collection.add(this);
        collection.get(i).getOrCreateNbt().putDouble("offset", 0.0625 * i);
        Item remainder = condiment.getItem().getRecipeRemainder();
        if (remainder != null) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(condiment, player, remainder.getDefaultStack()));
        } else if (!player.getAbilities().creativeMode) condiment.decrement(1);

        return true;
    }
}
