package cofh.thermal.locomotion.common.entity;

import cofh.core.common.entity.AbstractMinecartCoFH;
import cofh.lib.util.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.registries.TLocEntities.UNDERWATER_CART;
import static cofh.thermal.locomotion.init.registries.TLocIDs.ID_UNDERWATER_CART;

public class UnderwaterMinecart extends AbstractMinecartCoFH {

    public static final int AIR_SUPPLY_MAX = 4800;

    protected int airSupply = AIR_SUPPLY_MAX;
    protected int respirationFactor = 1;

    public UnderwaterMinecart(EntityType<? extends UnderwaterMinecart> type, Level worldIn) {

        super(type, worldIn);
    }

    public UnderwaterMinecart(Level worldIn, double posX, double posY, double posZ) {

        super(UNDERWATER_CART.get(), worldIn, posX, posY, posZ);
    }

    public UnderwaterMinecart onPlaced(ItemStack stack) {

        super.onPlaced(stack);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        if (enchantMap.containsKey(Enchantments.RESPIRATION)) {
            int encRespiration = enchantMap.get(Enchantments.RESPIRATION);
            this.respirationFactor = Math.max(1, encRespiration + 1);
        }
        return this;
    }

    @Override
    protected Item getDropItem() {

        return ITEMS.get(ID_UNDERWATER_CART);
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
    public void readAdditionalSaveData(CompoundTag compound) {

        super.readAdditionalSaveData(compound);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        if (enchantMap.containsKey(Enchantments.RESPIRATION)) {
            int encRespiration = enchantMap.get(Enchantments.RESPIRATION);
            this.respirationFactor = Math.max(1, encRespiration + 1);
        }
    }

    @Override
    protected boolean updateInWaterStateAndDoFluidPushing() {

        if (this.getVehicle() instanceof Boat) {
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
    public InteractionResult interact(Player player, InteractionHand hand) {

        if (player.isSecondaryUseActive() || this.isVehicle()) {
            return InteractionResult.PASS;
        } else if (!this.level.isClientSide) {
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
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
    public ItemStack getPickResult() {

        return new ItemStack(ITEMS.get(ID_UNDERWATER_CART));
    }

    @Override
    public Type getMinecartType() {

        return AbstractMinecart.Type.RIDEABLE;
    }

}
