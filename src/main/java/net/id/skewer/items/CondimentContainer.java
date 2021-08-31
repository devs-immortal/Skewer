package net.id.skewer.items;

import net.minecraft.item.Item;
import net.id.skewer.condiments.Condiment;
import org.jetbrains.annotations.Nullable;

public class CondimentContainer extends Item {
    private final Condiment condiment;

    public CondimentContainer(Settings settings, @Nullable Condiment condiment) {
        super(settings);
        this.condiment = condiment;
    }

    @Nullable
    public Condiment getCondiment(){
        return condiment;
    }
}
