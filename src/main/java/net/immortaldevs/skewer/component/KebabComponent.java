package net.immortaldevs.skewer.component;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.LarvalComponentData;
import net.immortaldevs.sar.api.SkeletalComponentData;
import net.immortaldevs.sar.api.ComponentCollection;

public class KebabComponent extends Component {
    @Override
    public void init(LarvalComponentData data) {
        data.loadChildren("foods");
        data.loadChildren("condiments");
    }

    public static void addFood(SkeletalComponentData kebab, Component food) {
        ComponentCollection children = kebab.getChildren("foods");
        int i = children.size();
        children.add(food);
        children.get(i).getOrCreateNbt().putDouble("offset", 0.125 * i);
    }

    public static void addCondiment(SkeletalComponentData kebab, Component condiment) {
        ComponentCollection children = kebab.getChildren("condiments");
        int i = children.size();
        children.add(condiment);
        children.get(i).getOrCreateNbt().putDouble("offset", 0.0625 * i);
    }
}
