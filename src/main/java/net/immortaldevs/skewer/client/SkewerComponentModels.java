package net.immortaldevs.skewer.client;

import net.immortaldevs.sar.base.client.ComponentModel;
import net.immortaldevs.sar.base.client.LoadedModelComponentModel;
import net.immortaldevs.skewer.component.SkewerCondimentComponents;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;

@SuppressWarnings("unused")
public final class SkewerComponentModels {
    public static final ComponentModel SKEWERED_APPLE = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_APPLE);
    public static final ComponentModel SKEWERED_BAKED_POTATO = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_BAKED_POTATO);
    public static final ComponentModel SKEWERED_BEETROOT = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_BEETROOT);
    public static final ComponentModel SKEWERED_CARROT = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_CARROT);
    public static final ComponentModel SKEWERED_CHORUS_FRUIT = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_CHORUS_FRUIT);
    public static final ComponentModel SKEWERED_COOKED_BEEF = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_BEEF);
    public static final ComponentModel SKEWERED_COOKED_CHICKEN = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_CHICKEN);
    public static final ComponentModel SKEWERED_COOKED_COD = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_COD);
    public static final ComponentModel SKEWERED_COOKED_MUTTON = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_MUTTON);
    public static final ComponentModel SKEWERED_COOKED_PORKCHOP = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_PORKCHOP);
    public static final ComponentModel SKEWERED_COOKED_RABBIT = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_RABBIT);
    public static final ComponentModel SKEWERED_COOKED_SALMON = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_COOKED_SALMON);
    public static final ComponentModel SKEWERED_DRIED_KELP = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_DRIED_KELP);
    public static final ComponentModel SKEWERED_GOLDEN_APPLE = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_GOLDEN_APPLE);
    public static final ComponentModel SKEWERED_GOLDEN_CARROT = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_GOLDEN_CARROT);
    public static final ComponentModel SKEWERED_MELON = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_MELON);
    public static final ComponentModel SKEWERED_POISONOUS_POTATO = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_POISONOUS_POTATO);
    public static final ComponentModel SKEWERED_ROTTEN_FLESH = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_ROTTEN_FLESH);
    public static final ComponentModel SKEWERED_SPIDER_EYE = new SkeweredFoodComponentModel(SkeweredFoodComponents.SKEWERED_SPIDER_EYE);

    public static final ComponentModel KELP_GOO = new LoadedModelComponentModel(SkewerCondimentComponents.KELP_GOO);
    public static final ComponentModel AIOLI = new LoadedModelComponentModel(SkewerCondimentComponents.AIOLI);
    public static final ComponentModel CHILLI_SAUCE = new LoadedModelComponentModel(SkewerCondimentComponents.CHILLI_SAUCE);

    public static void init() {
    }
}
