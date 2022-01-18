package net.immortaldevs.skewer.mixin;

import com.mojang.datafixers.util.Pair;
import net.immortaldevs.skewer.items.SkewerFoodComponents;
import net.immortaldevs.sar.base.ModifierUtils;
import net.immortaldevs.skewer.items.FoodComponentExt;
import net.immortaldevs.skewer.sar.FoodModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"ConstantConditions", "unused"})
@Mixin(FoodComponent.class)
public abstract class FoodComponentMixin implements FoodComponentExt {
    private FoodComponentMixin(int hunger, float saturationModifier, boolean meat, boolean alwaysEdible, boolean snack, List<Pair<StatusEffectInstance, Float>> statusEffects) {
    }

    @Override
    public void skewer$init(ItemStack stack) {
    }

    static {
        SkewerFoodComponents.SUPPLIER = () -> (FoodComponent) (Object) new FoodComponentMixin(0, 0f, false, false, false, List.of()) {
            private final List<FoodComponent> children = new ArrayList<>();

            @Override
            public void skewer$init(ItemStack stack) {
                ModifierUtils.acceptModifier(stack, FoodModifier.class, foodModifier ->
                        foodModifier.apply(this.children::add));
            }

            public int getHunger() {
                int ret = 0;
                for (FoodComponent child : this.children) {
                    ret += child.getHunger();
                }

                return ret;
            }

            public float getSaturationModifier() {
                float ret = 0;
                for (FoodComponent child : this.children) {
                    ret += child.getSaturationModifier();
                }

                return ret;
            }

            public boolean isMeat() {
                for (FoodComponent child : this.children) {
                    if (child.isMeat()) return true;
                }

                return false;
            }

            public boolean isAlwaysEdible() {
                return false;
            }

            public boolean isSnack() {
                return false;
            }

            public List<Pair<StatusEffectInstance, Float>> getStatusEffects() {
                List<Pair<StatusEffectInstance, Float>> ret = new ArrayList<>();
                for (FoodComponent child : this.children) {
                    ret.addAll(child.getStatusEffects());
                }

                return ret;
            }
        };
    }
}
