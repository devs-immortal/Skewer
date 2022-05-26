package net.immortaldevs.skewer.component;

import com.mojang.datafixers.util.Pair;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.ComponentCollection;
import net.immortaldevs.sar.api.LarvalComponentData;
import net.immortaldevs.sar.base.FoodStatusEffectModifier;
import net.immortaldevs.sar.base.HungerModifier;
import net.immortaldevs.sar.base.SaturationModifierModifier;
import net.immortaldevs.skewer.block.entity.PreparationTableBlockEntity.SkewerConstructionContext;
import net.immortaldevs.skewer.item.SkewerItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public class SkeweredFoodComponent extends Component {
    public final double outputModifier;
    protected final int hunger;
    protected final float saturationModifier;
    protected final @Nullable List<Pair<StatusEffectInstance, Float>> statusEffects;

    private SkeweredFoodComponent(int hunger,
                                  float saturation,
                                  double outputModifier,
                                  @Nullable List<Pair<StatusEffectInstance, Float>> effects) {
        this.hunger = hunger;
        this.saturationModifier = saturation;
        this.outputModifier = outputModifier;
        this.statusEffects = effects == null ? List.of() : effects;
    }

    @SafeVarargs
    public static SkeweredFoodComponent of(int hunger,
                                           float saturation,
                                           double outputModifier,
                                           Pair<StatusEffectInstance, Float>... statusEffects) {
        return new SkeweredFoodComponent(hunger, saturation, outputModifier, List.of(statusEffects));
    }

    public static SkeweredFoodComponent of(int hunger, float saturation, double outputModifier) {
        return new SkeweredFoodComponent(hunger, saturation, outputModifier, null);
    }

    public static SkeweredFoodComponent of(FoodComponent component, double outputModifier) {
        return new SkeweredFoodComponent(component.getHunger() + 3 >> 2,
                component.getSaturationModifier() * 0.25f,
                outputModifier,
                component.getStatusEffects());
    }

    public boolean applyTo(ComponentCollection collection,
                           SkewerConstructionContext context) {
        int i = collection.size();
        if (context.getSkewer().getItem() instanceof SkewerItem skewerItem
                && skewerItem.maxCapacity == i
                || i > 2) return false;

        this.calculateOutput(collection, context);
        collection.add(this);
        collection.get(i).getOrCreateNbt().putFloat("offset", 0.125f * i);
        if (!context.getPlayer().getAbilities().creativeMode) context.getFood().decrement(1);

        return true;
    }

    protected void calculateOutput(ComponentCollection collection,
                                   SkewerConstructionContext context) {
        if (!(context.getSkewer().getItem() instanceof SkewerItem skewerItem)) return;

        context.setOutputCount(context.getOutputCount() + Math.max(0.5,
                this.hunger / (this.saturationModifier * 4.0) / (2.0 - skewerItem.maxCapacity * 0.0625)));

        double outputModifier = this.outputModifier;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getComponent() == this) {
                outputModifier *= 2.0 - skewerItem.maxCapacity / 24.0;
                break;
            }
        }

        if (outputModifier >= 0.0) {
            context.setOutputModifier(outputModifier = context.getOutputModifier() * outputModifier);
            context.setOutputMultiplier(context.getOutputMultiplier() + outputModifier);
        }
    }

    public int getHunger() {
        return hunger;
    }

    public float getSaturationModifier() {
        return saturationModifier;
    }

    @Override
    public void init(LarvalComponentData data) {
        data.addModifier(HungerModifier.add(this.hunger));
        data.addModifier(SaturationModifierModifier.multiply(this.saturationModifier));
        if (this.statusEffects != null) {
            data.addModifier((FoodStatusEffectModifier) (stack, world, targetEntity, effects) ->
                    effects.addAll(SkeweredFoodComponent.this.statusEffects));
        }
    }
}
