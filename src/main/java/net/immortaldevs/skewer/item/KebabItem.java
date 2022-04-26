package net.immortaldevs.skewer.item;

import net.immortaldevs.sar.api.SkeletalComponentData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KebabItem extends Item {
    public static final int MAX_SKEWER_CAPACITY = 6;

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

    /**
     * Display skewered items on the tooltip
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!context.isAdvanced()) return;
        SkeletalComponentData kebab = stack.getComponent("kebab");
        if (kebab == null) return;

        SkeletalComponentData.Children foods = kebab.getChildren("foods");
        for (int i = 0; i < foods.size(); i++) {
            tooltip.add(new LiteralText(foods.get(i).getComponent().getId().toString()));
        }
    }
}
