package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import static cofh.thermal.core.ThermalCore.ENTITIES;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocEntities {

    private TLocEntities() {

    }

    public static void register() {

        ENTITIES.register(ID_UNDERWATER_CART, () -> EntityType.Builder.<UnderwaterMinecartEntity>create(UnderwaterMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_UNDERWATER_CART));

        ENTITIES.register(ID_SLIME_TNT_CART, () -> EntityType.Builder.<SlimeTNTMinecartEntity>create(SlimeTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_SLIME_TNT_CART));
        ENTITIES.register(ID_REDSTONE_TNT_CART, () -> EntityType.Builder.<RedstoneTNTMinecartEntity>create(RedstoneTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_REDSTONE_TNT_CART));
        ENTITIES.register(ID_GLOWSTONE_TNT_CART, () -> EntityType.Builder.<GlowstoneTNTMinecartEntity>create(GlowstoneTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_GLOWSTONE_TNT_CART));
        ENTITIES.register(ID_ENDER_TNT_CART, () -> EntityType.Builder.<EnderTNTMinecartEntity>create(EnderTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_ENDER_TNT_CART));

        ENTITIES.register(ID_PHYTO_TNT_CART, () -> EntityType.Builder.<PhytoTNTMinecartEntity>create(PhytoTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_PHYTO_TNT_CART));

        ENTITIES.register(ID_FIRE_TNT_CART, () -> EntityType.Builder.<FireTNTMinecartEntity>create(FireTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_FIRE_TNT_CART));
        ENTITIES.register(ID_EARTH_TNT_CART, () -> EntityType.Builder.<EarthTNTMinecartEntity>create(EarthTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_EARTH_TNT_CART));
        ENTITIES.register(ID_ICE_TNT_CART, () -> EntityType.Builder.<IceTNTMinecartEntity>create(IceTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_ICE_TNT_CART));
        ENTITIES.register(ID_LIGHTNING_TNT_CART, () -> EntityType.Builder.<LightningTNTMinecartEntity>create(LightningTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_LIGHTNING_TNT_CART));

        ENTITIES.register(ID_NUKE_TNT_CART, () -> EntityType.Builder.<NukeTNTMinecartEntity>create(NukeTNTMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_NUKE_TNT_CART));
    }

}
