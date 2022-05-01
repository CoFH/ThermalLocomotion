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
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

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

    public UnderwaterMinecartEntity onPlaced(ItemStack stack) {

        super.onPlaced(stack);

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
        if (Utils.isServerWorld(level)) {
            if (!this.wasTouchingWater) {
                airSupply = Math.min(airSupply + 40, AIR_SUPPLY_MAX);
            } else {
                List<Entity> passengers = getPassengers();
                if (!passengers.isEmpty() && airSupply > 0) {
                    passengers.forEach((e) -> {
                        if (e.getAirSupply() < e.getMaxAirSupply()) {
                            e.setAirSupply(e.getAirSupply() + 1);
                            if (random.nextInt(respirationFactor) <= 0) {
                                --airSupply;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {

        super.readAdditionalSaveData(compound);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        if (enchantMap.containsKey(Enchantments.RESPIRATION)) {
            int encRespiration = enchantMap.get(Enchantments.RESPIRATION);
            this.respirationFactor = Math.max(1, encRespiration + 1);
        }
    }

    // TODO: 1.16 alteration.
    @Override
    protected boolean updateInWaterStateAndDoFluidPushing() {

        if (this.getVehicle() instanceof BoatEntity) {
            this.wasTouchingWater = false;
        } else if (this.updateFluidHeightAndDoFluidPushing(FluidTags.WATER, 0.014D)) {
            if (!this.wasTouchingWater && !this.firstTick) {
                this.doWaterSplashEffect();
            }
            this.fallDistance = 0.0F;
            this.wasTouchingWater = true;
            this.clearFire();
            // this.eyesInWater = this.areEyesInFluid(FluidTags.WATER);
        } else {
            this.wasTouchingWater = false;
        }
        return this.wasTouchingWater;
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
    public ActionResultType interact(PlayerEntity player, Hand hand) {

        ActionResultType ret = super.interact(player, hand);
        if (ret.consumesAction()) return ret;
        if (player.isSecondaryUseActive()) {
            return ActionResultType.PASS;
        } else if (this.isVehicle()) {
            return ActionResultType.PASS;
        } else if (!this.level.isClientSide) {
            return player.startRiding(this) ? ActionResultType.CONSUME : ActionResultType.PASS;
        } else {
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public void activateMinecart(int x, int y, int z, boolean receivingPower) {

        if (receivingPower) {
            if (this.isVehicle()) {
                this.ejectPassengers();
            }
            if (this.getHurtTime() == 0) {
                this.setHurtDir(-this.getHurtDir());
                this.setHurtTime(10);
                this.setDamage(50.0F);
                this.markHurt();
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
