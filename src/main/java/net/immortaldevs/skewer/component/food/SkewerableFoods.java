package net.immortaldevs.skewer.component.food;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.skewer.component.SkewerComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public final class SkewerableFoods {
    private static final Reference2ReferenceMap<Item, Component> FOODS =
            new Reference2ReferenceOpenHashMap<>();

    public static void register(Item item, Component component) {
        FOODS.put(item, component);
    }

    public static Component get(Item item) {
        return FOODS.get(item);
    }

    public static void init() {
        register(Items.APPLE, SkewerComponents.SKEWERED_APPLE);
        register(Items.BAKED_POTATO, SkewerComponents.SKEWERED_BAKED_POTATO);
        register(Items.BEEF, SkewerComponents.SKEWERED_BEEF);
        register(Items.CARROT, SkewerComponents.SKEWERED_CARROT);
        register(Items.CHICKEN, SkewerComponents.SKEWERED_CHICKEN);
        register(Items.CHORUS_FRUIT, SkewerComponents.SKEWERED_CHORUS_FRUIT);
        register(Items.COD, SkewerComponents.SKEWERED_COD);
        register(Items.COOKED_BEEF, SkewerComponents.SKEWERED_COOKED_BEEF);
        register(Items.COOKED_CHICKEN, SkewerComponents.SKEWERED_COOKED_CHICKEN);
        register(Items.COOKED_COD, SkewerComponents.SKEWERED_COOKED_COD);
        register(Items.COOKED_MUTTON, SkewerComponents.SKEWERED_COOKED_MUTTON);
        register(Items.COOKED_PORKCHOP, SkewerComponents.SKEWERED_COOKED_PORKCHOP);
        register(Items.COOKED_RABBIT, SkewerComponents.SKEWERED_COOKED_RABBIT);
        register(Items.COOKED_SALMON, SkewerComponents.SKEWERED_COOKED_SALMON);
        register(Items.DRIED_KELP, SkewerComponents.SKEWERED_DRIED_KELP);
        register(Items.GOLDEN_APPLE, SkewerComponents.SKEWERED_GOLDEN_APPLE);
        register(Items.GOLDEN_CARROT, SkewerComponents.SKEWERED_GOLDEN_CARROT);
        register(Items.MELON, SkewerComponents.SKEWERED_MELON);
        register(Items.MUTTON, SkewerComponents.SKEWERED_MUTTON);
        register(Items.POISONOUS_POTATO, SkewerComponents.SKEWERED_POISONOUS_POTATO);
        register(Items.PORKCHOP, SkewerComponents.SKEWERED_PORKCHOP);
        register(Items.POTATO, SkewerComponents.SKEWERED_POTATO);
        register(Items.PUFFERFISH, SkewerComponents.SKEWERED_PUFFERFISH);
        register(Items.RABBIT, SkewerComponents.SKEWERED_RABBIT);
        register(Items.ROTTEN_FLESH, SkewerComponents.SKEWERED_ROTTEN_FLESH);
        register(Items.SALMON, SkewerComponents.SKEWERED_SALMON);
        register(Items.SPIDER_EYE, SkewerComponents.SKEWERED_SPIDER_EYE);
        register(Items.SWEET_BERRIES, SkewerComponents.SKEWERED_SWEET_BERRY);
        register(Items.GLOW_BERRIES, SkewerComponents.SKEWERED_GLOW_BERRY);
        register(Items.TROPICAL_FISH, SkewerComponents.SKEWERED_TROPICAL_FISH);
    }
}
