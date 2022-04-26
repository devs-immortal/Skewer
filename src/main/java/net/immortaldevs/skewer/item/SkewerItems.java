package net.immortaldevs.skewer.item;

import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.locate;

public final class SkewerItems {
    public static final SkewerItem WOODEN_SKEWER = add("wooden_skewer", new SkewerItem(new Settings().food(SkewerFoodComponents.KEBAB), 4));
    public static final SkewerItem BAMBOO_SKEWER = add("bamboo_skewer", new SkewerItem(new Settings().group(SkewerItemGroups.FOODS).food(SkewerFoodComponents.KEBAB), 6));
    public static final SkewerItem METAL_SKEWER = add("metal_skewer", new SkewerItem(new Settings().group(SkewerItemGroups.FOODS).food(SkewerFoodComponents.KEBAB), 10));

    public static final Item GARLIC = add("garlic", new Item(new Settings().group(ItemGroup.FOOD).food(SkewerFoodComponents.GARLIC)));

    public static final StewItem KELP_GOO = add("kelp_goo", new StewItem(new Settings().group(SkewerItemGroups.CONDIMENTS).food(SkewerFoodComponents.KELP_GOO).maxCount(1)));
    public static final StewItem AIOLI = add("aioli", new StewItem(new Settings().group(SkewerItemGroups.CONDIMENTS).food(SkewerFoodComponents.AIOLI).maxCount(1)));
    public static final StewItem CHILLI_SAUCE = add("chilli_sauce", new StewItem(new Settings().group(SkewerItemGroups.CONDIMENTS).food(SkewerFoodComponents.CHILLI_SAUCE).maxCount(1)));

    public static void init() {
    }

    private static <T extends Item> T add(String id, T item) {
        return Registry.register(Registry.ITEM, locate(id), item);
    }
}
