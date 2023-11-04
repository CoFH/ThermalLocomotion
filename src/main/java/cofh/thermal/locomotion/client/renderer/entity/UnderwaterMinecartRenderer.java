package cofh.thermal.locomotion.client.renderer.entity;

import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.locomotion.client.renderer.entity.model.UnderwaterMinecartModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.phys.Vec3;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class UnderwaterMinecartRenderer<T extends AbstractMinecart> extends EntityRenderer<T> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ID_THERMAL + ":textures/entity/underwater_minecart.png");
    protected final EntityModel<T> model;

    public UnderwaterMinecartRenderer(EntityRendererProvider.Context ctx) {

        super(ctx);
        this.shadowRadius = 0.7F;
        this.model = new UnderwaterMinecartModel<>(ctx.getModelSet().bakeLayer(UnderwaterMinecartModel.UNDERWATER_MINECART_LAYER));
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn) {

        super.render(entityIn, entityYaw, partialTicks, poseStackIn, bufferIn, packedLightIn);

        poseStackIn.pushPose();
        long i = (long) entityIn.getId() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f = (((float) (i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f1 = (((float) (i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f2 = (((float) (i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        poseStackIn.translate(f, f1, f2);
        double d0 = Mth.lerp(partialTicks, entityIn.xOld, entityIn.getX());
        double d1 = Mth.lerp(partialTicks, entityIn.yOld, entityIn.getY());
        double d2 = Mth.lerp(partialTicks, entityIn.zOld, entityIn.getZ());
        Vec3 vec3 = entityIn.getPos(d0, d1, d2);
        float f3 = Mth.lerp(partialTicks, entityIn.xRotO, entityIn.xRot);
        if (vec3 != null) {
            Vec3 vec31 = entityIn.getPosOffs(d0, d1, d2, 0.3F);
            Vec3 vec32 = entityIn.getPosOffs(d0, d1, d2, -0.3F);
            if (vec31 == null) {
                vec31 = vec3;
            }
            if (vec32 == null) {
                vec32 = vec3;
            }
            poseStackIn.translate(vec3.x - d0, (vec31.y + vec32.y) / 2.0D - d1, vec3.z - d2);
            Vec3 vec33 = vec32.add(-vec31.x, -vec31.y, -vec31.z);
            if (vec33.length() != 0.0D) {
                vec33 = vec33.normalize();
                entityYaw = (float) (Math.atan2(vec33.z, vec33.x) * 180.0D / Math.PI);
                f3 = (float) (Math.atan(vec33.y) * 73.0D);
            }
        }
        poseStackIn.translate(0.0D, 0.375D, 0.0D);
        poseStackIn.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        poseStackIn.mulPose(Axis.ZP.rotationDegrees(-f3));
        float f5 = (float) entityIn.getHurtTime() - partialTicks;
        float f6 = entityIn.getDamage() - partialTicks;
        if (f6 < 0.0F) {
            f6 = 0.0F;
        }
        if (f5 > 0.0F) {
            poseStackIn.mulPose(Axis.XP.rotationDegrees(MathHelper.sin(f5) * f5 * f6 / 10.0F * (float) entityIn.getHurtDir()));
        }
        //        int j = entityIn.getDisplayOffset();
        //        BlockState blockstate = entityIn.getDisplayBlockState();
        //        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
        //            poseStackIn.pushPose();
        //            poseStackIn.scale(0.75F, 0.75F, 0.75F);
        //            poseStackIn.translate(-0.5D, (float) (j - 8) / 16.0F, 0.5D);
        //            poseStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        //            this.renderBlockState(entityIn, partialTicks, blockstate, poseStackIn, bufferIn, packedLightIn);
        //            poseStackIn.popPose();
        //        }
        poseStackIn.scale(-1.0F, -1.0F, 1.0F);
        this.model.setupAnim(entityIn, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(poseStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {

        return TEXTURE;
    }

    //    protected void renderBlockState(T entityIn, float partialTicks, BlockState stateIn, PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn) {
    //
    //        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(stateIn, poseStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
    //    }

}
