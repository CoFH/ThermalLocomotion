package cofh.thermal.locomotion.client.renderer.entity.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class UnderwaterMinecartModel<T extends Entity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation UNDERWATER_MINECART_LAYER = new ModelLayerLocation(new ResourceLocation("thermal:underwater_minecart"), "main");

    private final ModelPart root;

    public UnderwaterMinecartModel(ModelPart root) {

        this.root = root;
    }

    public static LayerDefinition createMesh() {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("cart", CubeListBuilder.create()
                        .texOffs(0, 42).addBox(-8, -5, -10, 16, 10, 20)
                        .texOffs(56, 56).addBox(-6, -5, -8, 12, 8, 16),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, (float) (Math.PI / 2F), 0.0F));

        partdefinition.addOrReplaceChild("dome", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-8, -20, -10, 16, 15, 20),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, (float) (Math.PI / 2F), 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {

        return this.root;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

}
