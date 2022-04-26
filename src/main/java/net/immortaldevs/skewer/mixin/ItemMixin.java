package net.immortaldevs.skewer.mixin;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.SkeletalComponentData;
import net.immortaldevs.skewer.component.KebabComponent;
import net.immortaldevs.skewer.component.SkewerComponents;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;
import net.immortaldevs.skewer.item.SkewerItem;
import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    /**
     * Allows items to be put onto sticks and skewers when clicked with a stick or skewer
     * in the inventory
     * @param stack The ItemStack of this item (the food, presumably)
     * @param otherStack The ItemStack of the skewer / stick
     * @param cursorStackReference Also the skewer / stick, but mutable. 🤷‍♀️
     */
    @Inject(method = "onClicked", at = @At("RETURN"), cancellable = true)
    public void onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> cir) {
        if (cursorStackReference.get().getCount() > 1) return;
        Component component = SkeweredFoodComponents.fromItem((Item) (Object) this);
        if (component == null) return;

        if (cursorStackReference.get().getItem() == Items.STICK) {
            cursorStackReference.set(new ItemStack(SkewerItems.WOODEN_SKEWER,
                    cursorStackReference.get().getCount()));
        }

        if (cursorStackReference.get().getItem() instanceof SkewerItem skewer) {
            SkeletalComponentData kebab = cursorStackReference.get().getComponent("kebab");
            if (kebab == null) {
                kebab = cursorStackReference.get().getOrCreateComponent("kebab", SkewerComponents.KEBAB);
                kebab.getOrCreateNbt().putDouble("posY", -0.0625);
            }
            if (kebab.getChildren("foods").size() < skewer.maxCapacity) {
                KebabComponent.addFood(kebab, component);
                if (!player.isCreative()) stack.decrement(1);
                cir.setReturnValue(true);
            }
        }
    }
}
