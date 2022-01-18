package net.id.skewer.mixin;

import net.id.skewer.items.FoodComponentExt;
import net.id.skewer.items.SkewerItem;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {
    @Inject(method = "eat",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/item/FoodComponent;getHunger()I"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void eat(Item item, ItemStack stack, CallbackInfo ci, FoodComponent foodComponent) {
        if (item instanceof SkewerItem) {
            ((FoodComponentExt) foodComponent).skewer$init(stack);
        }
    }
}
