package net.immortaldevs.skewer.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class KebabItem extends Item {
    public KebabItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        return user instanceof PlayerEntity player && player.getAbilities().creativeMode
                ? result
                : new ItemStack(Items.STICK);
    }
}
