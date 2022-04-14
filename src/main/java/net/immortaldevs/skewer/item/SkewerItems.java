package net.immortaldevs.skewer.item;

import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.id;

public final class SkewerItems {
    public static final KebabItem KEBAB = new KebabItem(new Settings().food(SkewerFoodComponents.KEBAB));

    public static final Item GARLIC = new Item(new Settings().group(ItemGroup.FOOD).food(SkewerFoodComponents.GARLIC));

    public static final StewItem KELP_GOO = new StewItem(new Settings().group(SkewerItemGroups.CONDIMENTS).food(SkewerFoodComponents.KELP_GOO).maxCount(1));
    public static final StewItem AIOLI = new StewItem(new Settings().group(SkewerItemGroups.CONDIMENTS).food(SkewerFoodComponents.AIOLI).maxCount(1));
    public static final StewItem CHILLI_SAUCE = new StewItem(new Settings().group(SkewerItemGroups.CONDIMENTS).food(SkewerFoodComponents.CHILLI_SAUCE).maxCount(1));

    public static void init() {
        Registry.register(Registry.ITEM, id("kebab"), KEBAB);

        Registry.register(Registry.ITEM, id("garlic"), GARLIC);

        Registry.register(Registry.ITEM, id("kelp_goo"), KELP_GOO);
        Registry.register(Registry.ITEM, id("aioli"), AIOLI);
        Registry.register(Registry.ITEM, id("chilli_sauce"), CHILLI_SAUCE);
    }
}
