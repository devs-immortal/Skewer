package net.immortaldevs.skewer.sar;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.immortaldevs.skewer.client.sar.SkeweredFoodModelTransformer;
import net.immortaldevs.skewer.condiments.Condiment;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.ComponentData;
import net.immortaldevs.sar.base.client.DynamicBakedModel;
import net.immortaldevs.sar.base.client.modifier.ParticleSpriteModifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SkewerComponent extends Component {
    @Override
    public void init(ComponentData data) {
        NbtList foods = data.nbt().getList("items", NbtElement.COMPOUND_TYPE);
        NbtList condiments = data.nbt().getList("condiments", NbtElement.COMPOUND_TYPE);
        Random random = new Random(221027696472227851L);

        List<Supplier<Sprite>> spriteSuppliers = new ArrayList<>();

        if (data.onClient() && foods.size() != 0) {
            data.addModifier((ParticleSpriteModifier) stack ->
                    spriteSuppliers.get(random.nextInt(spriteSuppliers.size())).get());
        }


        for (int i = 0; i < foods.size(); i++) {
            NbtCompound food = foods.getCompound(i);
            food.put("Count", NbtInt.of(1));
            ItemStack stack = ItemStack.fromNbt(food);

            data.addModifier((FoodModifier) consumer -> {
                FoodComponent foodComponent = stack.getItem().getFoodComponent();
                if (foodComponent != null) consumer.accept(foodComponent);
            });

            if (data.onClient()) {
                BakedModel bakedModel = MinecraftClient.getInstance()
                        .getItemRenderer()
                        .getModel(stack, null, null, 0);

                spriteSuppliers.add(bakedModel instanceof DynamicBakedModel dynamicBakedModel
                        ? () -> dynamicBakedModel.getParticleSprite(stack)
                        : bakedModel::getParticleSprite);

                data.addModifier(SkeweredFoodModelTransformer.transform(i, random, stack,
                        ((FabricBakedModel) bakedModel)::emitItemQuads));
            }
        }

        // TODO this adds condiment info, but they don't render.
        for (int i = 0; i < condiments.size(); i++) {
            Condiment condiment = Condiment.fromNbt(condiments.getCompound(i));
            data.addModifier((FoodModifier) consumer ->{
                FoodComponent foodComponent = condiment.getFoodComponent();
                consumer.accept(foodComponent);
            });
        }
    }
}
