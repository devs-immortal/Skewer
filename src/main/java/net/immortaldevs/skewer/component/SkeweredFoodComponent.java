package net.immortaldevs.skewer.component;

import com.mojang.datafixers.util.Pair;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.LarvalComponentData;
import net.immortaldevs.sar.base.FoodStatusEffectModifier;
import net.immortaldevs.sar.base.HungerModifier;
import net.immortaldevs.sar.base.SaturationModifierModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;

import java.util.List;

public class SkeweredFoodComponent extends Component {
    protected final int hunger;
    protected final float saturationModifier;
    protected final List<Pair<StatusEffectInstance, Float>> statusEffects;

    public SkeweredFoodComponent(FoodComponent foodComponent) {
        this.hunger = foodComponent.getHunger() / 4;
        this.saturationModifier = foodComponent.getSaturationModifier();
        this.statusEffects = foodComponent.getStatusEffects();
    }

    @Override
    public void init(LarvalComponentData data) {
        data.addModifier(HungerModifier.add(this.hunger));
        data.addModifier(SaturationModifierModifier.multiply(this.saturationModifier));
        data.addModifier((FoodStatusEffectModifier) (stack, world, targetEntity, effects) ->
                effects.addAll(SkeweredFoodComponent.this.statusEffects));
    }
}
