package net.id.skewer.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class SkewerItem extends MultiFoodItem{
    public SkewerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        return user instanceof PlayerEntity playerEntity && playerEntity.getAbilities().creativeMode
                ? result : Items.STICK.getDefaultStack();
    }

}
