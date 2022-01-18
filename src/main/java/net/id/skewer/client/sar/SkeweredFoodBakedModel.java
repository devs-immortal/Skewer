package net.id.skewer.client.sar;

import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.util.Random;
import java.util.function.Supplier;

public class SkeweredFoodBakedModel extends ForwardingBakedModel {
    private final float offset;
    private final float z;
    private final Quaternion quaternion;

    public SkeweredFoodBakedModel(BakedModel wrapped, int offset, Random random) {
        this.wrapped = wrapped;
        this.offset = offset * 0.0425f + random.nextFloat(0.495f, 0.505f);
        this.z = random.nextFloat(0.48f, 0.52f);
        this.quaternion = new Quaternion(0, 1.570796f, 0, false);
        this.quaternion.hamiltonProduct(new Quaternion(
                random.nextFloat(-0.05f, 0.05f) - 0.785398f,
                random.nextFloat(-0.05f, 0.05f),
                random.nextFloat(-3.141f, 3.141f), false));
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        context.pushTransform(quad -> {
            Vec3f work = new Vec3f();

            for (int i = 0; i < 4; i++) {
                quad.copyPos(i, work);
                work.add(-0.5f, -0.5f, -0.5f);
                work.rotate(quaternion);
                work.scale(0.75f);
                work.add(offset, offset, z);
                quad.pos(i, work);
            }

            return true;
        });

        super.emitItemQuads(stack, randomSupplier, context);
        context.popTransform();

    }
}
