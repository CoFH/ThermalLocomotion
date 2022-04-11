package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.UnderwaterMinecartEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import static cofh.thermal.core.ThermalCore.ENTITIES;
import static cofh.thermal.locomotion.init.TLocIDs.ID_UNDERWATER_CART;

public class TLocEntities {

    private TLocEntities() {

    }

    public static void register() {

        ENTITIES.register(ID_UNDERWATER_CART, () -> EntityType.Builder.<UnderwaterMinecartEntity>of(UnderwaterMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_UNDERWATER_CART));

        //ENTITIES.register(ID_SLIME_TNT_CART, () -> EntityType.Builder.<SlimeTNTMinecartEntity>of(SlimeTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_SLIME_TNT_CART));
        //ENTITIES.register(ID_REDSTONE_TNT_CART, () -> EntityType.Builder.<RedstoneTNTMinecartEntity>of(RedstoneTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_REDSTONE_TNT_CART));
        //ENTITIES.register(ID_GLOWSTONE_TNT_CART, () -> EntityType.Builder.<GlowstoneTNTMinecartEntity>of(GlowstoneTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_GLOWSTONE_TNT_CART));
        //ENTITIES.register(ID_ENDER_TNT_CART, () -> EntityType.Builder.<EnderTNTMinecartEntity>of(EnderTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_ENDER_TNT_CART));
        //
        //ENTITIES.register(ID_PHYTO_TNT_CART, () -> EntityType.Builder.<PhytoTNTMinecartEntity>of(PhytoTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_PHYTO_TNT_CART));
        //
        //ENTITIES.register(ID_FIRE_TNT_CART, () -> EntityType.Builder.<FireTNTMinecartEntity>of(FireTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_FIRE_TNT_CART));
        //ENTITIES.register(ID_EARTH_TNT_CART, () -> EntityType.Builder.<EarthTNTMinecartEntity>of(EarthTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_EARTH_TNT_CART));
        //ENTITIES.register(ID_ICE_TNT_CART, () -> EntityType.Builder.<IceTNTMinecartEntity>of(IceTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_ICE_TNT_CART));
        //ENTITIES.register(ID_LIGHTNING_TNT_CART, () -> EntityType.Builder.<LightningTNTMinecartEntity>of(LightningTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_LIGHTNING_TNT_CART));
        //
        //ENTITIES.register(ID_NUKE_TNT_CART, () -> EntityType.Builder.<NukeTNTMinecartEntity>of(NukeTNTMinecartEntity::new, EntityClassification.MISC).sized(0.98F, 0.7F).build(ID_NUKE_TNT_CART));
    }

}
