package cofh.thermal.locomotion.entity;

import cofh.core.util.helpers.AugmentDataHelper;
import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.Utils;
import cofh.thermal.lib.entity.AugmentableMinecart;
import cofh.thermal.lib.util.ThermalEnergyHelper;
import cofh.thermal.locomotion.inventory.container.EnergyMinecartContainer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Predicate;

import static cofh.core.util.helpers.AugmentableHelper.getAttributeModWithDefault;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.config.ThermalCoreConfig.storageAugments;
import static cofh.thermal.lib.common.ThermalAugmentRules.ENERGY_STORAGE_VALIDATOR;
import static cofh.thermal.locomotion.init.TLocEntities.ENERGY_CART;
import static cofh.thermal.locomotion.init.TLocIDs.ID_ENERGY_CART;

public class EnergyMinecart extends AugmentableMinecart implements MenuProvider {

    public static final int BASE_CAPACITY = 1000000;
    public static final int BASE_XFER = 1000;

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(1, ThermalEnergyHelper::hasEnergyHandlerCap);
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH(1, ThermalEnergyHelper::hasEnergyHandlerCap);

    protected EnergyStorageCoFH energyStorage = new EnergyStorageCoFH(BASE_CAPACITY, BASE_XFER);

    public EnergyMinecart(EntityType<? extends EnergyMinecart> type, Level worldIn) {

        super(type, worldIn);

        inventory.addSlot(inputSlot);
        inventory.addSlot(outputSlot);

        this.entityData.set(ENERGY_STORED, 0);

        addAugmentSlots(storageAugments);
    }

    public EnergyMinecart(Level worldIn, double posX, double posY, double posZ) {

        super(ENERGY_CART.get(), worldIn, posX, posY, posZ);

        inventory.addSlot(inputSlot);
        inventory.addSlot(outputSlot);

        this.entityData.set(ENERGY_STORED, 0);

        addAugmentSlots(storageAugments);
    }

    protected void handleEnergy() {

        if (!inputSlot.isEmpty()) {
            int maxTransfer = Math.min(energyStorage.getMaxReceive(), energyStorage.getSpace());
            inputSlot.getItemStack()
                    .getCapability(ThermalEnergyHelper.getBaseEnergySystem(), null)
                    .ifPresent(c -> energyStorage.receiveEnergy(c.extractEnergy(maxTransfer, false), false));
        }
        if (!outputSlot.isEmpty()) {
            int maxTransfer = Math.min(energyStorage.getMaxExtract(), energyStorage.getEnergyStored());
            outputSlot.getItemStack()
                    .getCapability(ThermalEnergyHelper.getBaseEnergySystem(), null)
                    .ifPresent(c -> energyStorage.extractEnergy(c.receiveEnergy(maxTransfer, false), false));
        }
    }

    public EnergyStorageCoFH getEnergyStorage() {

        return energyStorage;
    }

    @Override
    protected void defineSynchedData() {

        super.defineSynchedData();
        this.entityData.define(ENERGY_STORED, 0);
    }

    @Override
    public EnergyMinecart onPlaced(ItemStack stack) {

        if (stack.getTag() != null) {
            energyStorage.read(stack.getTag());
        }
        super.onPlaced(stack);
        return this;
    }

    @Override
    public void tick() {

        if (Utils.isServerWorld(level)) {
            handleEnergy();
            entityData.set(ENERGY_STORED, energyStorage.getEnergyStored());
        } else {
            energyStorage.setEnergyStored(entityData.get(ENERGY_STORED));
        }
        super.tick();
    }

    @Override
    protected Item getDropItem() {

        return ITEMS.get(ID_ENERGY_CART);
    }

    @Override
    public ItemStack createItemStackTag(ItemStack stack) {

        energyStorage.writeWithParams(stack.getOrCreateTag());
        return super.createItemStackTag(stack);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {

        super.readAdditionalSaveData(compound);
        energyStorage.read(compound);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {

        super.addAdditionalSaveData(compound);
        energyStorage.write(compound);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {

        if (player.isSecondaryUseActive() || this.isVehicle()) {
            return InteractionResult.PASS;
        } else if (!Utils.isClientWorld(level)) {
            Utils.openEntityScreen((ServerPlayer) player, this, this);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack getPickResult() {

        return new ItemStack(ITEMS.get(ID_ENERGY_CART));
    }

    @Override
    public Type getMinecartType() {

        return Type.CHEST;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new EnergyMinecartContainer(i, inventory, this);
    }

    // region AUGMENTS
    @Override
    protected Predicate<ItemStack> augValidator() {

        return item -> AugmentDataHelper.hasAugmentData(item) && ENERGY_STORAGE_VALIDATOR.test(item, getAugmentsAsList());
    }

    @Override
    protected void finalizeAttributes(Map<Enchantment, Integer> enchantmentMap) {

        float holdingMod = getHoldingMod(enchantmentMap);
        float baseMod = getAttributeModWithDefault(augmentNBT, TAG_AUGMENT_BASE_MOD, 1.0F);
        float energyStorageMod = holdingMod * baseMod * getAttributeModWithDefault(augmentNBT, TAG_AUGMENT_RF_STORAGE, 1.0F);
        float energyXferMod = baseMod * getAttributeModWithDefault(augmentNBT, TAG_AUGMENT_RF_XFER, 1.0F);

        energyStorage.applyModifiers(energyStorageMod, energyXferMod).setCreative(() -> creativeEnergy);
    }
    // endregion

    // region CAPABILITIES
    protected LazyOptional<?> energyCap = LazyOptional.empty();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == ThermalEnergyHelper.getBaseEnergySystem()) {
            if (!energyCap.isPresent() && energyStorage.getCapacity() > 0) {
                energyCap = LazyOptional.of(() -> energyStorage);
            }
            return energyCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {

        super.invalidateCaps();
        energyCap.invalidate();
    }
    // endregion
}
