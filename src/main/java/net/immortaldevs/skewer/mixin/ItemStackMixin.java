package net.immortaldevs.skewer.mixin;

import net.immortaldevs.divineintervention.injection.ModifyOperand;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.SkeletalComponentData;
import net.immortaldevs.skewer.component.KebabComponent;
import net.immortaldevs.skewer.component.SkewerComponents;
import net.immortaldevs.skewer.component.food.SkewerableFoods;
import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("unused")
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract void decrement(int amount);

    @ModifyOperand(method = "onClicked",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;onClicked(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Lnet/minecraft/screen/slot/Slot;Lnet/minecraft/util/ClickType;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/inventory/StackReference;)Z"))
    private boolean onClicked(boolean value, ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (cursorStackReference.get().getCount() > 2) return value;
        Component component = SkewerableFoods.get(this.getItem());
        if (component == null) return value;

        if (cursorStackReference.get().getItem() == Items.STICK) {
            cursorStackReference.set(new ItemStack(SkewerItems.KEBAB,
                    cursorStackReference.get().getCount()));
        }

        if (cursorStackReference.get().getItem() == SkewerItems.KEBAB) {
            SkeletalComponentData kebab = cursorStackReference.get().getComponent("kebeb");
            if (kebab == null) {
                kebab = cursorStackReference.get().getOrCreateComponent("kebab", SkewerComponents.KEBAB);
                kebab.getOrCreateNbt().putDouble("posY", -0.0625);
            }

            KebabComponent.addFood(kebab, component);
            if (!player.isCreative()) this.decrement(1);
            return true;
        }

        return value;
    }
}
