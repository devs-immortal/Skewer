package net.immortaldevs.skewer.item;

import net.immortaldevs.sar.api.ComponentCollection;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkewerItem extends Item {
    public final int maxCapacity;
    public final boolean breaks;

    public SkewerItem(Settings settings, int maxCapacity, boolean breaks) {
        super(settings);
        this.maxCapacity = maxCapacity;
        this.breaks = breaks;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        if(user instanceof PlayerEntity player && player.getAbilities().creativeMode) {
            return result;
        }
        else {
            return breaks ? result : new ItemStack(this);
        }


    }

    /*
     * No, you don't get to chew on the stick.
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.hasComponents("foods")) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        } else {
            return TypedActionResult.fail(stack);
        }
    }

    /*
     * Display skewered items on the tooltip
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!context.isAdvanced()) return;

        ComponentCollection foods = stack.getComponents("foods");
        for (var food : foods) {
            tooltip.add(new LiteralText(food
                    .getComponent()
                    .getId()
                    .toString()).formatted(Formatting.DARK_GRAY));
        }

        ComponentCollection condiments = stack.getComponents("condiments");
        for (var condiment : condiments) {
            tooltip.add(new LiteralText(condiment
                    .getComponent()
                    .getId()
                    .toString()).formatted(Formatting.DARK_GRAY));
        }
    }
}
