package net.id.skewer.mixin;

import com.mojang.datafixers.util.Pair;
import net.id.skewer.items.FoodComponentExt;
import net.id.skewer.items.MultiFoodItem;
import net.id.skewer.sar.FoodModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

import static net.immortaldevs.sar.base.ModifierUtils.acceptModifier;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    protected ItemStack activeItemStack;

    @Inject(method = "shouldSpawnConsumptionEffects",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/item/FoodComponent;isSnack()Z"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void shouldSpawnConsumptionEffects(CallbackInfoReturnable<Boolean> cir, int i, FoodComponent foodComponent) {
        if (this.activeItemStack.getItem() instanceof MultiFoodItem) {
            ((FoodComponentExt) foodComponent).skewer$init(this.activeItemStack);
        }
    }

    @Inject(method = "applyFoodEffects",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/List;iterator()Ljava/util/Iterator;",
                    ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity, CallbackInfo ci, Item item, List<Pair<StatusEffectInstance, Float>> list) {
        if (item instanceof MultiFoodItem) {
            acceptModifier(stack, FoodModifier.class, foodModifier ->
                    foodModifier.apply(foodComponent -> list.addAll(foodComponent.getStatusEffects())));
        }
    }
}
