package cofh.thermal.locomotion.entity;

import cofh.lib.entity.AbstractMinecartEntityCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

import static cofh.lib.util.constants.NBTTags.TAG_CART_DATA;
import static cofh.thermal.locomotion.init.TLocReferences.UNDERWATER_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.UNDERWATER_CART_ITEM;

public class UnderwaterMinecartEntity extends AbstractMinecartEntityCoFH {

    public static final int AIR_SUPPLY_MAX = 4800;

    protected int airSupply = AIR_SUPPLY_MAX;
    protected int respirationFactor = 1;

    public UnderwaterMinecartEntity(EntityType<? extends UnderwaterMinecartEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public UnderwaterMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(UNDERWATER_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    public UnderwaterMinecartEntity setEnchantments(ListNBT enchantments) {

        super.setEnchantments(enchantments);
        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);

        if (enchantMap.containsKey(Enchantments.RESPIRATION)) {
            int encRespiration = enchantMap.get(Enchantments.RESPIRATION);
            this.respirationFactor = Math.max(1, encRespiration + 1);
        }
        return this;
    }

    @Override
    public void tick() {

        super.tick();
        if (Utils.isServerWorld(world)) {
            if (!this.inWater) {
                airSupply = Math.min(airSupply + 40, AIR_SUPPLY_MAX);
            } else {
                List<Entity> passengers = getPassengers();
                if (!passengers.isEmpty() && airSupply > 0) {
                    passengers.forEach((e) -> {
                        if (e.getAir() < e.getMaxAir()) {
                            e.setAir(e.getAir() + 1);
                            if (rand.nextInt(respirationFactor) <= 0) {
                                --airSupply;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {

        super.readAdditional(compound);

        respirationFactor = compound.getInt(TAG_CART_DATA);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

        super.writeAdditional(compound);

        compound.putInt(TAG_CART_DATA, respirationFactor);
    }

    // TODO: 1.16 alteration.
    @Override
    protected boolean func_233566_aG_() {

        if (this.getRidingEntity() instanceof BoatEntity) {
            this.inWater = false;
        } else if (this.handleFluidAcceleration(FluidTags.WATER, 0.014D)) {
            if (!this.inWater && !this.firstUpdate) {
                this.doWaterSplashEffect();
            }
            this.fallDistance = 0.0F;
            this.inWater = true;
            this.extinguish();
            // this.eyesInWater = this.areEyesInFluid(FluidTags.WATER);
        } else {
            this.inWater = false;
        }
        return this.inWater;
    }

    //    @Override
    //    public boolean areEyesInFluid(ITag<Fluid> tag) {
    //
    //        if (this.getRidingEntity() instanceof BoatEntity) {
    //            return false;
    //        } else {
    //            double eyePos = this.getPosY() + 1.0D;
    //            BlockPos pos = new BlockPos(this.getPosX(), eyePos, this.getPosZ());
    //            return this.world.getFluidState(pos).isEntityInside(world, pos, this, eyePos, tag, true);
    //        }
    //    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {

        return true;
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {

        ActionResultType ret = super.processInitialInteract(player, hand);
        if (ret.isSuccessOrConsume()) return ret;
        if (player.isSecondaryUseActive()) {
            return ActionResultType.PASS;
        } else if (this.isBeingRidden()) {
            return ActionResultType.PASS;
        } else if (!this.world.isRemote) {
            return player.startRiding(this) ? ActionResultType.CONSUME : ActionResultType.PASS;
        } else {
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {

        if (receivingPower) {
            if (this.isBeingRidden()) {
                this.removePassengers();
            }
            if (this.getRollingAmplitude() == 0) {
                this.setRollingDirection(-this.getRollingDirection());
                this.setRollingAmplitude(10);
                this.setDamage(50.0F);
                this.markVelocityChanged();
            }
        }
    }

    @Override
    public ItemStack getCartItem() {

        return new ItemStack(UNDERWATER_CART_ITEM);
    }

    @Override
    public Type getMinecartType() {

        return AbstractMinecartEntity.Type.RIDEABLE;
    }

}
