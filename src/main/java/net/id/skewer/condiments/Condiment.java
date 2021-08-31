package net.id.skewer.condiments;

import net.minecraft.entity.effect.StatusEffect;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface Condiment {
    @Nullable
    Collection<StatusEffect> getEffects();

    // add some other stuff for health / hunger, etc. Maybe some things are effected by cooking, maybe.
}
