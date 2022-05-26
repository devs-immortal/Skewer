package net.immortaldevs.skewer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.immortaldevs.skewer.block.entity.PreparationTableBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;

import static net.minecraft.util.math.MathHelper.HALF_PI;
import static net.minecraft.util.math.MathHelper.PI;

@Environment(EnvType.CLIENT)
public class PreparationTableBlockEntityRenderer implements BlockEntityRenderer<PreparationTableBlockEntity> {
    protected static final ItemPosition SKEWER_1
            = new ItemPosition(0.25, 1.01171875, 0.8, HALF_PI, 0f, PI / 4);
    protected static final ItemPosition SKEWER_2
            = new ItemPosition(0.25, 1.03515625, 0.8, HALF_PI, 0f, PI / 4 + 0.1f);
    protected static final ItemPosition SKEWER_5
            = new ItemPosition(0.25, 1.05859375, 0.8, HALF_PI, 0f, PI / 4 - 0.07f);

    protected static final ItemPosition[] FOOD_POSITIONS = {
            new ItemPosition(0.5, 1.01171875, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.03515625, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.05859375, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.08203125, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.10546875, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.12890625, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.15234375, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.17578125, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.19921875, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.22265625, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.24609375, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.26953125, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.29296875, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.31640625, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.33984375, 0.5, HALF_PI, 0f, 0f),
            new ItemPosition(0.5, 1.36328125, 0.5, HALF_PI, 0f, 0f)
    };

    public PreparationTableBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(PreparationTableBlockEntity preparationTable,
                       float tickDelta,
                       MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       int overlay) {
        ItemStack skewer = preparationTable.getSkewer();
        if (skewer.getCount() >= 1) renderItem(SKEWER_1, skewer, matrices, vertexConsumers, light, overlay);
        if (skewer.getCount() >= 2) renderItem(SKEWER_2, skewer, matrices, vertexConsumers, light, overlay);
        if (skewer.getCount() >= 5) renderItem(SKEWER_5, skewer, matrices, vertexConsumers, light, overlay);

        int n = 0;
        for (int i = 0; i < 16; i++) {
            ItemStack stack = preparationTable.getFood(i);
            if (!stack.isEmpty()) renderItem(FOOD_POSITIONS[n++], stack, matrices, vertexConsumers, light, overlay);
        }
    }

    protected static void renderItem(ItemPosition position,
                                     ItemStack stack,
                                     MatrixStack matrices,
                                     VertexConsumerProvider vertexConsumers,
                                     int light,
                                     int overlay) {
        matrices.push();
        position.apply(matrices);
        matrices.scale(0.375f, 0.375f, 0.375f);

        MinecraftClient.getInstance()
                .getItemRenderer()
                .renderItem(stack,
                        ModelTransformation.Mode.FIXED,
                        light,
                        overlay,
                        matrices,
                        vertexConsumers,
                        0);

        matrices.pop();
    }

    public static final class ItemPosition {
        private final double x;
        private final double y;
        private final double z;
        private final Quaternion rotation;

        public ItemPosition(double posX,
                            double posY,
                            double posZ,
                            float rotationX,
                            float rotationY,
                            float rotationZ) {
            this.x = posX;
            this.y = posY;
            this.z = posZ;
            this.rotation = Quaternion.fromEulerXyz(rotationX, rotationY, rotationZ);
        }

        public void apply(MatrixStack matrices) {
            matrices.translate(x, y, z);
            matrices.multiply(rotation);
        }
    }
}
