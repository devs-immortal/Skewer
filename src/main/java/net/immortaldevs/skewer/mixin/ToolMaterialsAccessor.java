package net.immortaldevs.skewer.mixin;

import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public interface ToolMaterialsAccessor {
    @SuppressWarnings("unused")
    @Invoker(value = "<init>")
    static ToolMaterials callInit(String id, int ordinal, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        throw new Error();
    }
}
