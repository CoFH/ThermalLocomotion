package cofh.thermal.locomotion.entity;

import cofh.lib.entity.AbstractTNTMinecartEntity;
import cofh.lib.util.AreaUtils;
import cofh.lib.util.Utils;
import cofh.thermal.core.entity.projectile.LightningGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.ID_LIGHTNING_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.LIGHTNING_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.LIGHTNING_TNT_CART_ITEM;

public class LightningTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public LightningTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public LightningTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(LIGHTNING_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return BLOCKS.get(ID_LIGHTNING_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.MINECART) : new ItemStack(LIGHTNING_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(world)) {
            BlockPos pos = this.getPosition();
            if (this.world.canSeeSky(pos)) {
                Utils.spawnLightningBolt(world, pos);
            }
            LightningGrenadeEntity.shockNearbyEntities(this, world, this.getPosition(), radius);
            AreaUtils.zapNearbyGround(this, world, this.getPosition(), radius, 0.05, 12);
            this.remove();
            this.entityDropItem(getCartItem());
        }
        this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

}
