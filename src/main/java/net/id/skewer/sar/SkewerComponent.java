package net.id.skewer.sar;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.ComponentData;
import net.immortaldevs.sar.base.client.modifier.BakedModelModifier;
import net.id.skewer.client.sar.SkeweredFoodBakedModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.Random;

public class SkewerComponent extends Component {
    @Override
    public void init(ComponentData data) {
        NbtList foods = data.nbt().getList("items", NbtElement.COMPOUND_TYPE);
        Random random = new Random();

        for (int i = 0; i < foods.size(); i++) {
            ItemStack stack = ItemStack.fromNbt(foods.getCompound(i));

            data.addModifier((FoodModifier) consumer -> {
                FoodComponent foodComponent = stack.getItem().getFoodComponent();
                if (foodComponent != null) consumer.accept(foodComponent);
            });

            if (data.onClient()) {
                SkeweredFoodBakedModel model = new SkeweredFoodBakedModel(MinecraftClient.getInstance()
                        .getItemRenderer()
                        .getModel(stack, null, null, 0), i, random);

                data.addModifier((BakedModelModifier) consumer -> consumer.accept(model));
            }
        }
    }
}
