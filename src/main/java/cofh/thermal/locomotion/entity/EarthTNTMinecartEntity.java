package cofh.thermal.locomotion.entity;

import cofh.lib.entity.AbstractTNTMinecartEntity;
import cofh.lib.util.Utils;
import cofh.thermal.core.entity.projectile.EarthGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.ID_EARTH_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.EARTH_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.EARTH_TNT_CART_ITEM;

public class EarthTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public EarthTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public EarthTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(EARTH_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return BLOCKS.get(ID_EARTH_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.MINECART) : new ItemStack(EARTH_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(level)) {
            EarthGrenadeEntity.sunderNearbyEntities(this, level, this.blockPosition(), radius);
            EarthGrenadeEntity.breakBlocks(this, level, this.blockPosition(), radius - 1, null);
            this.remove();
            this.spawnAtLocation(getCartItem());
        }
        this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 1.0D, 0.0D, 0.0D);
        this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
    }

}
