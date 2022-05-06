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

public class EnergyMinecartModel<T extends Entity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation ENERGY_MINECART_LAYER = new ModelLayerLocation(new ResourceLocation("thermal:energy_minecart"), "main");

    private final ModelPart root;

    public EnergyMinecartModel(ModelPart root) {

        this.root = root;
    }

    public static LayerDefinition createMesh() {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("case", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-8.0F, -12.0F, -10.0F, 16.0F, 3.0F, 20.0F)
                        .texOffs(0, 23).addBox(-8.0F, -3.0F, -10.0F, 16.0F, 3.0F, 20.0F)
                        .texOffs(0, 46).addBox(-8.0F, -6.0F, -10.0F, 16.0F, 3.0F, 8.0F)
                        .texOffs(0, 46).addBox(-8.0F, -6.0F, 2.0F, 16.0F, 3.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, (float) (Math.PI / 2F), 0.0F));


        partdefinition.addOrReplaceChild("core", CubeListBuilder.create()
                        .texOffs(72, 37).addBox(-6.0F, -12.0F, -8.0F, 12.0F, 11.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, (float) (Math.PI / 2F), 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public ModelPart root() {

        return this.root;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

}
