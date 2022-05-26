package net.immortaldevs.skewer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.immortaldevs.skewer.block.entity.PreparationTableBlockEntity;
import net.immortaldevs.skewer.client.PreparationTableBlockEntityRenderer.ItemPositions.ItemPosition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;

import static net.minecraft.util.math.MathHelper.HALF_PI;

@Environment(EnvType.CLIENT)
public class PreparationTableBlockEntityRenderer implements BlockEntityRenderer<PreparationTableBlockEntity> {
    protected static final ItemPositions SKEWER_POSITIONS = new ItemPositions(
            new ItemPosition(1, 0.25f, 1.01171875f, 0.8f, HALF_PI, 0f, 0f),
            new ItemPosition(2, 0.25f, 1.03515625f, 0.8f, HALF_PI, 0f, 0.1f),
            new ItemPosition(5, 0.25f, 1.05859375f, 0.8f, HALF_PI, 0f, 0.2f));

    protected static final ItemPositions FOOD_POSITIONS = new ItemPositions(
            new ItemPosition(0, 0.5f, 1.01171875f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(1, 0.5f, 1.03515625f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(2, 0.5f, 1.05859375f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(3, 0.5f, 1.08203125f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(4, 0.5f, 1.10546875f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(5, 0.5f, 1.12890625f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(6, 0.5f, 1.15234375f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(7, 0.5f, 1.17578125f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(8, 0.5f, 1.19921875f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(9, 0.5f, 1.22265625f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(10, 0.5f, 1.24609375f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(11, 0.5f, 1.26953125f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(12, 0.5f, 1.29296875f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(13, 0.5f, 1.31640625f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(14, 0.5f, 1.33984375f, 0.5f, HALF_PI, 0f, 0f),
            new ItemPosition(15, 0.5f, 1.36328125f, 0.5f, HALF_PI, 0f, 0f));

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
        for (ItemPosition position : SKEWER_POSITIONS.positions) if (skewer.getCount() >= position.count) {
            renderItem(position, skewer, matrices, vertexConsumers, light, overlay);
        } else break;

        for (ItemPosition position : FOOD_POSITIONS.positions) {
            ItemStack stack = preparationTable.getFood(position.count);
            if (!stack.isEmpty()) renderItem(position, stack, matrices, vertexConsumers, light, overlay);
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

    protected static final class ItemPositions {
        public final ItemPosition[] positions;

        public ItemPositions(ItemPosition... positions) {
            this.positions = positions;
        }

        public static final class ItemPosition {
            public final int count;
            private final float x;
            private final float y;
            private final float z;
            private final Quaternion rotation;

            public ItemPosition(int count,
                                float posX,
                                float posY,
                                float posZ,
                                float rotationX,
                                float rotationY,
                                float rotationZ) {
                this.count = count;
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
}
