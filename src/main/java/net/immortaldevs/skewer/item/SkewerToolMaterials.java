package net.immortaldevs.skewer.item;

import net.immortaldevs.skewer.mixin.ToolMaterialsAccessor;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public final class SkewerToolMaterials {
    public static final ToolMaterial METAL = ToolMaterialsAccessor.callInit("SKEWER_METAL", -1,
            2, 200, 5.0F, 2.5F, 20,
            () -> Ingredient.ofItems(Items.IRON_INGOT));
}
