package cofh.thermal.locomotion.entity;

import cofh.lib.entity.AbstractTNTMinecartEntity;
import cofh.lib.util.Utils;
import cofh.thermal.core.entity.projectile.NukeGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.ID_NUKE_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.NUKE_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.NUKE_TNT_CART_ITEM;

public class NukeTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public NukeTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public NukeTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(NUKE_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return BLOCKS.get(ID_NUKE_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.AIR) : new ItemStack(NUKE_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(level)) {
            level.setBlockAndUpdate(this.blockPosition(), Blocks.AIR.defaultBlockState());
            NukeGrenadeEntity.affectNearbyEntities(this, level, this.blockPosition(), radius * 3, null);
            NukeGrenadeEntity.destroyBlocks(this, level, this.blockPosition(), radius * 2);
            level.explode(this, this.getX(), this.getY(), this.getZ(), (float) NukeGrenadeEntity.explosionStrength * 2, !this.isInWater(), NukeGrenadeEntity.explosionsDestroyBlocks ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            this.remove();
        }
        this.level.addParticle(ParticleTypes.FLASH, this.getX(), this.getY(), this.getZ(), 1.0D, 0.0D, 0.0D);
        this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
    }

}
