package net.id.skewer.mixin;

import net.id.skewer.items.MultiFoodItem;
import net.immortaldevs.sar.base.ItemStackExt;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackExt {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "isFood",
            at = @At("HEAD"),
            cancellable = true)
    private void isFood(CallbackInfoReturnable<Boolean> cir) {
        if (this.getItem() instanceof MultiFoodItem) {
            cir.setReturnValue(MultiFoodItem.hasFood((ItemStack) (Object) this));
        }
    }
}
