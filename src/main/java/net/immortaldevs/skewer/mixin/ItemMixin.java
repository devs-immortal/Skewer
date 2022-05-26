package net.immortaldevs.skewer.mixin;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.SkeletalComponentData;
import net.immortaldevs.skewer.component.KebabComponent;
import net.immortaldevs.skewer.component.SkewerComponents;
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
    /**
     * Allows items to be put onto skewers when clicked with a skewer
     * in the inventory
     * @param stack The ItemStack of this item (the food, presumably)
     * @param otherStack The ItemStack of the skewer
     * @param cursorStackReference Also the skewer, but mutable. 🤷‍♀️
     */
    @Inject(method = "onClicked", at = @At("RETURN"), cancellable = true)
    public void onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> cir) {
        if (cursorStackReference.get().getCount() > 1) return;
        cir.setReturnValue(
                cir.getReturnValue()
                || skewer$tryAddFoodToSkewer(stack, cursorStackReference, player)
                || skewer$tryAddCondimentToSkewer(stack, cursorStackReference, player)
        );
    }

    // Pretty messy. Some duplicated code too.

    @Unique
    private boolean skewer$tryAddFoodToSkewer(ItemStack stack, StackReference cursorStackReference, PlayerEntity player) {
        Component component = SkeweredFoodComponents.get((Item) (Object) this);
        if (component == null) return false;

        if (cursorStackReference.get().getItem() instanceof SkewerItem skewer) {
            // Move to SkewerItem or something
            SkeletalComponentData kebab = cursorStackReference.get().getComponent("kebab");
            if (kebab == null) {
                kebab = cursorStackReference.get().getOrCreateComponent("kebab", SkewerComponents.KEBAB);
                kebab.getOrCreateNbt().putDouble("posY", -0.0625);
            }
            if (kebab.getChildren("foods").size() < skewer.maxCapacity) {
                KebabComponent.addFood(kebab, component);
                if (!player.isCreative()) stack.decrement(1);
                return true;
            }
        }
        return false;
    }

    @Unique
    private boolean skewer$tryAddCondimentToSkewer(ItemStack stack, StackReference cursorStackReference, PlayerEntity player) {
        Component component = SkewerCondimentComponents.fromItem((Item) (Object) this);
        if (component == null) return false;

        if (cursorStackReference.get().getItem() instanceof SkewerItem skewer) {
            SkeletalComponentData kebab = cursorStackReference.get().getComponent("kebab");
            if (kebab == null) {
                kebab = cursorStackReference.get().getOrCreateComponent("kebab", SkewerComponents.KEBAB);
                kebab.getOrCreateNbt().putDouble("posY", -0.0625);
            }
            if (kebab.getChildren("condiments").size() < skewer.maxCapacity) {
                KebabComponent.addCondiment(kebab, component);
                /*
                add "turn this into a bowl" here
                 */
                if (!player.isCreative()) stack.decrement(1);
                return true;
            }
        }
        return false;
    }
}
