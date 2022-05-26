package net.immortaldevs.skewer.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.immortaldevs.skewer.block.SkewerBlocks;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.locate;

public final class SkewerItems {

    public static FabricItemSettings skewerSettings(int maxCount) {
        return new FabricItemSettings().food(SkewerFoodComponents.KEBAB).group(SkewerItemGroups.FOODS).maxCount(maxCount);
    }

    public static FabricItemSettings cookingItem() {
        return new FabricItemSettings().group(SkewerItemGroups.COOKING);
    }

    public static FabricItemSettings knife() {
        return new FabricItemSettings().group(SkewerItemGroups.COOKING).maxCount(1).rarity(Rarity.UNCOMMON);
    }

    public static final SkewerItem WOODEN_SKEWER = add("wooden_skewer", new SkewerItem(skewerSettings(32), 3, true));
    public static final SkewerItem COPPER_SKEWER = add("copper_skewer", new SkewerItem(skewerSettings(32), 3, false));
    public static final SkewerItem IRON_SKEWER = add("iron_skewer", new SkewerItem(skewerSettings(24), 4, false));
    public static final SkewerItem BAMBOO_SKEWER = add("bamboo_skewer", new SkewerItem(skewerSettings(24), 6, true));

    public static final Item KNIFE = add("knife", new KnifeItem(SkewerToolMaterials.METAL, 3, -2.1F, knife()));

    public static final Item GARLIC = add("garlic", new Item(new Settings().group(ItemGroup.FOOD).food(SkewerFoodComponents.GARLIC)));

    public static final StewItem KELP_GOO = add("kelp_goo", new StewItem(new Settings().group(SkewerItemGroups.FARMING).food(SkewerFoodComponents.KELP_GOO).maxCount(1)));
    public static final StewItem AIOLI = add("aioli", new StewItem(new Settings().group(SkewerItemGroups.FARMING).food(SkewerFoodComponents.AIOLI).maxCount(1)));
    public static final StewItem CHILLI_SAUCE = add("chilli_sauce", new StewItem(new Settings().group(SkewerItemGroups.FARMING).food(SkewerFoodComponents.CHILLI_SAUCE).maxCount(1)));

    public static final BlockItem PREP_TABLE_ITEM = add("prep_table", new BlockItem(SkewerBlocks.PREP_TABLE, cookingItem()));
    public static final BlockItem PREPARATION_TABLE = add("preparation_table", new BlockItem(SkewerBlocks.PREPARATION_TABLE, cookingItem()));

    public static void init() {
    }

    private static <T extends Item> T add(String id, T item) {
        return Registry.register(Registry.ITEM, locate(id), item);
    }
}
