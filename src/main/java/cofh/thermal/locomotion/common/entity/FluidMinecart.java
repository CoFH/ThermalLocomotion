package cofh.thermal.locomotion.common.entity;

import cofh.core.common.fluid.PotionFluid;
import cofh.core.util.filter.FilterRegistry;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.core.util.helpers.FluidHelper;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.lib.util.Utils;
import cofh.thermal.lib.common.entity.AugmentableMinecart;
import cofh.thermal.locomotion.common.inventory.FluidMinecartMenu;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static cofh.core.util.helpers.AugmentableHelper.getAttributeModString;
import static cofh.core.util.helpers.AugmentableHelper.getAttributeModWithDefault;
import static cofh.lib.util.Constants.*;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.config.ThermalCoreConfig.storageAugments;
import static cofh.thermal.lib.util.ThermalAugmentRules.createAllowValidator;
import static cofh.thermal.locomotion.init.registries.TLocEntities.FLUID_CART;
import static cofh.thermal.locomotion.init.registries.TLocIDs.ID_FLUID_CART;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE;

public class FluidMinecart extends AugmentableMinecart implements MenuProvider {

    public static final BiPredicate<ItemStack, List<ItemStack>> AUG_VALIDATOR = createAllowValidator(TAG_AUGMENT_TYPE_UPGRADE, TAG_AUGMENT_TYPE_FLUID, TAG_AUGMENT_TYPE_FILTER);

    public static final int BASE_CAPACITY = TANK_MEDIUM * 4;

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(1, (item) -> FluidHelper.hasFluidHandlerCap(item) || item.getItem() == Items.POTION);
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH(1, FluidHelper::hasFluidHandlerCap);

    protected FluidStorageCoFH tank = new FluidStorageCoFH(BASE_CAPACITY, fluid -> filter.valid(fluid));

    public FluidMinecart(EntityType<? extends FluidMinecart> type, Level worldIn) {

        super(type, worldIn);

        inventory.addSlot(inputSlot);
        inventory.addSlot(outputSlot);

        addAugmentSlots(storageAugments);
    }

    public FluidMinecart(Level worldIn, double posX, double posY, double posZ) {

        super(FLUID_CART.get(), worldIn, posX, posY, posZ);

        inventory.addSlot(inputSlot);
        inventory.addSlot(outputSlot);

        addAugmentSlots(storageAugments);
    }

    protected void handleFluid() {

        if (!inputSlot.isEmpty()) {
            ItemStack inputStack = inputSlot.getItemStack();
            if (inputStack.getItem() == Items.POTION) {
                FluidStack potion = PotionFluid.getPotionFluidFromItem(BOTTLE_VOLUME, inputStack);
                if (tank.fill(potion, SIMULATE) == BOTTLE_VOLUME) {
                    tank.fill(potion, EXECUTE);
                    inputSlot.setItemStack(new ItemStack(Items.GLASS_BOTTLE));
                }
            } else {
                inputStack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM, null).ifPresent(c -> {
                    int toFill = tank.fill(new FluidStack(c.getFluidInTank(0), BUCKET_VOLUME), SIMULATE);
                    if (toFill > 0) {
                        tank.fill(c.drain(toFill, EXECUTE), EXECUTE);
                        inputSlot.setItemStack(c.getContainer());
                    }
                });
            }
        }
        if (!outputSlot.isEmpty()) {
            outputSlot.getItemStack().getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM, null).ifPresent(c -> {
                tank.drain(c.fill(new FluidStack(tank.getFluidStack(), Math.min(tank.getAmount(), BUCKET_VOLUME)), EXECUTE), EXECUTE);
                outputSlot.setItemStack(c.getContainer());
            });
        }
    }

    public FluidStorageCoFH getTank() {

        return tank;
    }

    @Override
    protected void defineSynchedData() {

        super.defineSynchedData();
        this.entityData.define(FLUID_STACK_STORED, FluidStack.EMPTY);
        this.entityData.define(FLUID_STORED, 0);
    }

    @Override
    public FluidMinecart onPlaced(ItemStack stack) {

        if (stack.getTag() != null) {
            tank.read(stack.getTag());
        }
        super.onPlaced(stack);
        return this;
    }

    @Override
    public void tick() {

        if (Utils.isServerWorld(level)) {
            handleFluid();
            entityData.set(FLUID_STACK_STORED, tank.getFluidStack());
            entityData.set(FLUID_STORED, tank.getStored());
        } else {
            tank.setFluidStack(entityData.get(FLUID_STACK_STORED));
            if (!tank.isEmpty()) {
                tank.getFluidStack().setAmount(entityData.get(FLUID_STORED));
            }
        }
        super.tick();
    }

    @Override
    protected Item getDropItem() {

        return ITEMS.get(ID_FLUID_CART);
    }

    @Override
    public ItemStack createItemStackTag(ItemStack stack) {

        tank.write(stack.getOrCreateTag());
        return super.createItemStackTag(stack);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {

        super.readAdditionalSaveData(compound);
        tank.read(compound);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {

        super.addAdditionalSaveData(compound);
        tank.write(compound);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {

        if (player.isSecondaryUseActive() || this.isVehicle()) {
            return InteractionResult.PASS;
        } else if (!Utils.isClientWorld(level)) {
            if (attemptFluidHandlerInteraction(player, hand)) {
                return InteractionResult.SUCCESS;
            }
            Utils.openEntityScreen((ServerPlayer) player, this, this);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack getPickResult() {

        return new ItemStack(ITEMS.get(ID_FLUID_CART));
    }

    @Override
    public Type getMinecartType() {

        return Type.CHEST;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new FluidMinecartMenu(i, inventory, this);
    }

    // region AUGMENTS
    @Override
    protected Predicate<ItemStack> augValidator() {

        return item -> AugmentDataHelper.hasAugmentData(item) && AUG_VALIDATOR.test(item, getAugmentsAsList());
    }

    @Override
    protected void finalizeAttributes(Map<Enchantment, Integer> enchantmentMap) {

        float holdingMod = getHoldingMod(enchantmentMap);
        float baseMod = getAttributeModWithDefault(augmentNBT, TAG_AUGMENT_BASE_MOD, 1.0F);
        float fluidStorageMod = holdingMod * baseMod * getAttributeModWithDefault(augmentNBT, TAG_AUGMENT_FLUID_STORAGE, 1.0F);

        tank.applyModifiers(fluidStorageMod).setCreative(() -> creativeTanks);

        CompoundTag filterNBT = filter.write(new CompoundTag());
        filter = FilterRegistry.getFilter(getAttributeModString(augmentNBT, TAG_FILTER_TYPE), filterNBT, this);
    }
    // endregion

    // region CAPABILITIES
    protected LazyOptional<?> fluidCap = LazyOptional.empty();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            if (!fluidCap.isPresent() && tank.getCapacity() > 0) {
                fluidCap = LazyOptional.of(() -> tank);
            }
            return fluidCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {

        super.invalidateCaps();
        fluidCap.invalidate();
    }
    // endregion
}
