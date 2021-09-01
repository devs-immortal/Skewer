package net.id.skewer.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.id.skewer.tag.CondimentTags;
import net.id.skewer.tag.SkewerItemTags;
import net.id.skewer.condiments.Condiment;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkewerItem extends MultiFoodItem {
    public SkewerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            player.giveItemStack(SkewerItems.SKEWER.getDefaultStack());
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public boolean canBeAdded(Item item){
        return SkewerItemTags.SKEWERABLE.contains(item);
    }

    @Override
    protected boolean canBeAdded(Condiment condiment) {
        return CondimentTags.SKEWERABLE.contains(condiment);
    }
}
