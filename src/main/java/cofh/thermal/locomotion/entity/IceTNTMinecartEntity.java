package cofh.thermal.locomotion.entity;

import cofh.lib.entity.AbstractTNTMinecartEntity;
import cofh.lib.util.AreaUtils;
import cofh.lib.util.Utils;
import cofh.thermal.core.entity.projectile.IceGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalConfig.permanentLava;
import static cofh.thermal.lib.common.ThermalConfig.permanentWater;
import static cofh.thermal.lib.common.ThermalIDs.ID_ICE_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.ICE_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.ICE_TNT_CART_ITEM;

public class IceTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public IceTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public IceTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(ICE_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return BLOCKS.get(ID_ICE_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.MINECART) : new ItemStack(ICE_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(level)) {
            IceGrenadeEntity.affectNearbyEntities(this, level, this.blockPosition(), radius, null);
            AreaUtils.freezeSpecial(this, level, this.blockPosition(), radius, true, true);
            AreaUtils.freezeNearbyGround(this, level, this.blockPosition(), radius);
            AreaUtils.freezeAllWater(this, level, this.blockPosition(), radius, permanentWater);
            AreaUtils.freezeAllLava(this, level, this.blockPosition(), radius, permanentLava);
            makeAreaOfEffectCloud();
            this.remove();
            this.spawnAtLocation(getCartItem());
        }
        this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 1.0D, 0.0D, 0.0D);
        this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
    }

    private void makeAreaOfEffectCloud() {

        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(level, getX(), getY() + 0.5D, getZ());
        cloud.setRadius(1);
        cloud.setParticle(ParticleTypes.ITEM_SNOWBALL);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((radius - cloud.getRadius()) / (float) cloud.getDuration());

        level.addFreshEntity(cloud);
    }

}
