package net.immortaldevs.skewer.client;

import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.ComponentData;
import net.immortaldevs.sar.base.client.LoadedModelComponentModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class SkeweredFoodComponentModel extends LoadedModelComponentModel {
    public SkeweredFoodComponentModel(Component component) {
        super(component);
    }

    @Override
    public void itemRender(@NotNull ComponentData data,
                           @NotNull VertexConsumerProvider vertexConsumers,
                           @NotNull ItemStack stack,
                           @NotNull MatrixStack matrices,
                           @NotNull ModelTransformation.Mode renderMode,
                           int light,
                           int overlay) {
        translate: {
            NbtCompound nbt = data.getNbt();
            if (nbt == null) break translate;
            double offset = nbt.getDouble("offset");
            matrices.translate(offset - (3.75 / 16.0), offset - (3.75 / 16.0), 0.0);
        }

        super.itemRender(data, vertexConsumers, stack, matrices, renderMode, light, overlay);
    }
}
