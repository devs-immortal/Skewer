package net.immortaldevs.skewer.component;

import com.mojang.datafixers.util.Pair;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.LarvalComponentData;
import net.immortaldevs.sar.base.FoodStatusEffectModifier;
import net.immortaldevs.sar.base.HungerModifier;
import net.immortaldevs.sar.base.SaturationModifierModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SkeweredFoodComponent extends Component {
    protected final int hunger;
    protected final float saturationModifier;
    protected final List<Pair<StatusEffectInstance, Float>> statusEffects;

    private SkeweredFoodComponent(int hunger, float saturation, Optional<List<Pair<StatusEffectInstance, Float>>> effects) {
        this.hunger = hunger;
        this.saturationModifier = saturation;
        this.statusEffects = effects.orElse(new ArrayList<>());
    }

    @SafeVarargs
    public static SkeweredFoodComponent of(int hunger, float saturation, @NotNull Pair<StatusEffectInstance, Float> ... statusEffects) {
        return new SkeweredFoodComponent(hunger, saturation, Optional.of(List.of(statusEffects)));
    }

    public static SkeweredFoodComponent of(int hunger, float saturation) {
        return new SkeweredFoodComponent(hunger, saturation, Optional.empty());
    }

    public static SkeweredFoodComponent of(FoodComponent component) {
        return new SkeweredFoodComponent(component.getHunger(), component.getSaturationModifier(), Optional.ofNullable(component.getStatusEffects()));
    }

    @Override
    public void init(LarvalComponentData data) {
        data.addModifier(HungerModifier.add(this.hunger));
        data.addModifier(SaturationModifierModifier.multiply(this.saturationModifier));
        data.addModifier((FoodStatusEffectModifier) (stack, world, targetEntity, effects) ->
                effects.addAll(SkeweredFoodComponent.this.statusEffects));
    }
}
