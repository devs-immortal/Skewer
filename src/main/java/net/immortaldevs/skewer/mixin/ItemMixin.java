package net.immortaldevs.skewer.mixin;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.ComponentCollection;
import net.immortaldevs.skewer.component.SkewerCondimentComponents;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;
import net.immortaldevs.skewer.item.SkewerItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    /*
     * Allows items to be put onto skewers when clicked with a skewer
     * in the inventory
     */
    @Inject(method = "onClicked", at = @At("RETURN"), cancellable = true)
    public void onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> cir) {
        if (cursorStackReference.get().getCount() > 1) return;
        cir.setReturnValue(cir.getReturnValue() || skewer$tryAddToSkewer(stack, cursorStackReference, player));
    }

    @Unique
    private boolean skewer$tryAddToSkewer(ItemStack stack,
                                          StackReference cursorStackReference,
                                          PlayerEntity player) {
        boolean food;
        Component component = SkeweredFoodComponents.get((Item) (Object) this);
        if (!(food = component != null)) component = SkewerCondimentComponents.get((Item) (Object) this);
        if (component == null) return false;

        if (cursorStackReference.get().getItem() instanceof SkewerItem skewer) {
            ComponentCollection target = cursorStackReference.get().getComponents(food ? "foods" : "condiments");
            if (target.size() < skewer.maxCapacity) {
                int i = target.size();
                target.add(component);
                if (food) target.get(i).getOrCreateNbt().putDouble("offset", 0.125 * i);
                if (!player.isCreative()) stack.decrement(1);
                return true;
            }
        }
        return false;
    }
}
