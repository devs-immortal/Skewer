package net.immortaldevs.skewer.component;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.SarRegistries;
import net.immortaldevs.sar.base.HungerModifier;
import net.immortaldevs.sar.base.SaturationModifierModifier;
import net.immortaldevs.sar.base.SimpleComponent;
import net.immortaldevs.skewer.component.food.SkeweredFoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.id;

public final class SkewerComponents {
    public static final KebabComponent KEBAB = new KebabComponent();

    public static final SkeweredFoodComponent SKEWERED_APPLE = new SkeweredFoodComponent(FoodComponents.APPLE);
    public static final SkeweredFoodComponent SKEWERED_BAKED_POTATO = new SkeweredFoodComponent(FoodComponents.BAKED_POTATO);
    public static final SkeweredFoodComponent SKEWERED_BEEF = new SkeweredFoodComponent(FoodComponents.BEEF);
    public static final SkeweredFoodComponent SKEWERED_CARROT = new SkeweredFoodComponent(FoodComponents.CARROT);
    public static final SkeweredFoodComponent SKEWERED_CHICKEN = new SkeweredFoodComponent(FoodComponents.CHICKEN);
    public static final SkeweredFoodComponent SKEWERED_CHORUS_FRUIT = new SkeweredFoodComponent(FoodComponents.CHORUS_FRUIT);
    public static final SkeweredFoodComponent SKEWERED_COD = new SkeweredFoodComponent(FoodComponents.COD);
    public static final SkeweredFoodComponent SKEWERED_COOKED_BEEF = new SkeweredFoodComponent(FoodComponents.COOKED_BEEF);
    public static final SkeweredFoodComponent SKEWERED_COOKED_CHICKEN = new SkeweredFoodComponent(FoodComponents.COOKED_CHICKEN);
    public static final SkeweredFoodComponent SKEWERED_COOKED_COD = new SkeweredFoodComponent(FoodComponents.COOKED_COD);
    public static final SkeweredFoodComponent SKEWERED_COOKED_MUTTON = new SkeweredFoodComponent(FoodComponents.COOKED_MUTTON);
    public static final SkeweredFoodComponent SKEWERED_COOKED_PORKCHOP = new SkeweredFoodComponent(FoodComponents.COOKED_PORKCHOP);
    public static final SkeweredFoodComponent SKEWERED_COOKED_RABBIT = new SkeweredFoodComponent(FoodComponents.COOKED_RABBIT);
    public static final SkeweredFoodComponent SKEWERED_COOKED_SALMON = new SkeweredFoodComponent(FoodComponents.COOKED_SALMON);
    public static final SkeweredFoodComponent SKEWERED_DRIED_KELP = new SkeweredFoodComponent(FoodComponents.DRIED_KELP);
    public static final SkeweredFoodComponent SKEWERED_GOLDEN_APPLE = new SkeweredFoodComponent(FoodComponents.GOLDEN_APPLE);
    public static final SkeweredFoodComponent SKEWERED_GOLDEN_CARROT = new SkeweredFoodComponent(FoodComponents.GOLDEN_CARROT);
    public static final SkeweredFoodComponent SKEWERED_MELON = new SkeweredFoodComponent(FoodComponents.MELON_SLICE);
    public static final SkeweredFoodComponent SKEWERED_MUTTON = new SkeweredFoodComponent(FoodComponents.MUTTON);
    public static final SkeweredFoodComponent SKEWERED_POISONOUS_POTATO = new SkeweredFoodComponent(FoodComponents.POISONOUS_POTATO);
    public static final SkeweredFoodComponent SKEWERED_PORKCHOP = new SkeweredFoodComponent(FoodComponents.PORKCHOP);
    public static final SkeweredFoodComponent SKEWERED_POTATO = new SkeweredFoodComponent(FoodComponents.POTATO);
    public static final SkeweredFoodComponent SKEWERED_PUFFERFISH = new SkeweredFoodComponent(FoodComponents.PUFFERFISH);
    public static final SkeweredFoodComponent SKEWERED_RABBIT = new SkeweredFoodComponent(FoodComponents.RABBIT);
    public static final SkeweredFoodComponent SKEWERED_ROTTEN_FLESH = new SkeweredFoodComponent(FoodComponents.ROTTEN_FLESH);
    public static final SkeweredFoodComponent SKEWERED_SALMON = new SkeweredFoodComponent(FoodComponents.SALMON);
    public static final SkeweredFoodComponent SKEWERED_SPIDER_EYE = new SkeweredFoodComponent(FoodComponents.SPIDER_EYE);
    public static final SkeweredFoodComponent SKEWERED_SWEET_BERRY = new SkeweredFoodComponent(FoodComponents.SWEET_BERRIES);
    public static final SkeweredFoodComponent SKEWERED_GLOW_BERRY = new SkeweredFoodComponent(FoodComponents.GLOW_BERRIES);
    public static final SkeweredFoodComponent SKEWERED_TROPICAL_FISH = new SkeweredFoodComponent(FoodComponents.TROPICAL_FISH);

    public static final Component KELP_GOO = new Component();
    public static final SimpleComponent AIOLI = new SimpleComponent(HungerModifier.add(2), SaturationModifierModifier.multiply(1.2f));
    public static final SimpleComponent CHILLI_SAUCE = new SimpleComponent(SaturationModifierModifier.multiply(1.35f));

    public static void init() {
        Registry.register(SarRegistries.COMPONENT, id("skewer"), KEBAB);

        Registry.register(SarRegistries.COMPONENT, id("skewered_apple"), SKEWERED_APPLE);
        Registry.register(SarRegistries.COMPONENT, id("skewered_baked_potato"), SKEWERED_BAKED_POTATO);
        Registry.register(SarRegistries.COMPONENT, id("skewered_beef"), SKEWERED_BEEF);
        Registry.register(SarRegistries.COMPONENT, id("skewered_carrot"), SKEWERED_CARROT);
        Registry.register(SarRegistries.COMPONENT, id("skewered_chicken"), SKEWERED_CHICKEN);
        Registry.register(SarRegistries.COMPONENT, id("skewered_chorus_fruit"), SKEWERED_CHORUS_FRUIT);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cod"), SKEWERED_COD);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_beef"), SKEWERED_COOKED_BEEF);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_chicken"), SKEWERED_COOKED_CHICKEN);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_cod"), SKEWERED_COOKED_COD);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_mutton"), SKEWERED_COOKED_MUTTON);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_porkchop"), SKEWERED_COOKED_PORKCHOP);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_rabbit"), SKEWERED_COOKED_RABBIT);
        Registry.register(SarRegistries.COMPONENT, id("skewered_cooked_salmon"), SKEWERED_COOKED_SALMON);
        Registry.register(SarRegistries.COMPONENT, id("skewered_dried_kelp"), SKEWERED_DRIED_KELP);
        Registry.register(SarRegistries.COMPONENT, id("skewered_golden_apple"), SKEWERED_GOLDEN_APPLE);
        Registry.register(SarRegistries.COMPONENT, id("skewered_golden_carrot"), SKEWERED_GOLDEN_CARROT);
        Registry.register(SarRegistries.COMPONENT, id("skewered_melon"), SKEWERED_MELON);
        Registry.register(SarRegistries.COMPONENT, id("skewered_mutton"), SKEWERED_MUTTON);
        Registry.register(SarRegistries.COMPONENT, id("skewered_poisonous_potato"), SKEWERED_POISONOUS_POTATO);
        Registry.register(SarRegistries.COMPONENT, id("skewered_porkchop"), SKEWERED_PORKCHOP);
        Registry.register(SarRegistries.COMPONENT, id("skewered_potato"), SKEWERED_POTATO);
        Registry.register(SarRegistries.COMPONENT, id("skewered_pufferfish"), SKEWERED_PUFFERFISH);
        Registry.register(SarRegistries.COMPONENT, id("skewered_rabbit"), SKEWERED_RABBIT);
        Registry.register(SarRegistries.COMPONENT, id("skewered_rotten_flesh"), SKEWERED_ROTTEN_FLESH);
        Registry.register(SarRegistries.COMPONENT, id("skewered_salmon"), SKEWERED_SALMON);
        Registry.register(SarRegistries.COMPONENT, id("skewered_spider_eye"), SKEWERED_SPIDER_EYE);
        Registry.register(SarRegistries.COMPONENT, id("skewered_sweet_berry"), SKEWERED_SWEET_BERRY);
        Registry.register(SarRegistries.COMPONENT, id("skewered_glow_berry"), SKEWERED_GLOW_BERRY);
        Registry.register(SarRegistries.COMPONENT, id("skewered_tropical_fish"), SKEWERED_TROPICAL_FISH);

        Registry.register(SarRegistries.COMPONENT, id("kelp_goo"), KELP_GOO);
        Registry.register(SarRegistries.COMPONENT, id("aioli"), AIOLI);
        Registry.register(SarRegistries.COMPONENT, id("chilli_sauce"), CHILLI_SAUCE);
    }
}
