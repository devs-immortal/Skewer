package net.id.skewer.items;

import net.immortaldevs.sar.base.ItemStackExt;
import net.immortaldevs.sar.base.RootComponentData;
import net.id.skewer.sar.FoodModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// maybe rename to FoodStackItem or similar.
public class MultiFoodItem extends Item {
    public MultiFoodItem(Settings settings) {
        super(settings);
    }

    public static boolean hasFood(ItemStack stack) {
        RootComponentData rootComponentData = ((ItemStackExt) (Object) stack).sar$getComponentRoot();
        return rootComponentData != null
                && rootComponentData.modifierMap.contains(FoodModifier.class);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (hasFood(stack)) return super.use(world, user, hand);
        return TypedActionResult.pass(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        return user instanceof PlayerEntity playerEntity && playerEntity.getAbilities().creativeMode
                ? result : Items.AIR.getDefaultStack();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return hasFood(stack) ? super.getUseAction(stack) : UseAction.NONE;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 48;
    }

    @Nullable
    @Override
    public FoodComponent getFoodComponent() {
        return SkewerFoodComponents.SUPPLIER.get();
    }
}
