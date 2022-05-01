package cofh.thermal.locomotion.entity;

import cofh.lib.entity.AbstractMinecartEntityCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.locomotion.init.TLocReferences.FLUID_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.FLUID_CART_ITEM;

public class FluidMinecartEntity extends AbstractMinecartEntityCoFH {

    public static final int BASE_CAPACITY = TANK_MEDIUM * 8;

    protected FluidStorageCoFH fluidStorage = new FluidStorageCoFH(BASE_CAPACITY);

    public FluidMinecartEntity(EntityType<? extends FluidMinecartEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public FluidMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(FLUID_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    public FluidMinecartEntity onPlaced(ItemStack stack) {

        super.onPlaced(stack);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        float holdingMod = getHoldingMod(enchantMap);
        fluidStorage.applyModifiers(holdingMod);

        if (stack.getTag() != null) {
            fluidStorage.read(stack.getTag());
        }
        return this;
    }

    @Override
    public ItemStack createItemStackTag(ItemStack stack) {

        fluidStorage.write(stack.getTag());

        return super.createItemStackTag(stack);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {

        super.readAdditionalSaveData(compound);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        float holdingMod = getHoldingMod(enchantMap);
        fluidStorage.applyModifiers(holdingMod);

        fluidStorage.read(compound);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {

        super.addAdditionalSaveData(compound);

        fluidStorage.write(compound);
    }

    @Override
    public ItemStack getCartItem() {

        return new ItemStack(FLUID_CART_ITEM);
    }

    @Override
    public Type getMinecartType() {

        return Type.CHEST;
    }

    // region CAPABILITIES
    protected LazyOptional<?> fluidCap = LazyOptional.empty();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (!fluidCap.isPresent() && fluidStorage.getCapacity() > 0) {
                fluidCap = LazyOptional.of(() -> fluidStorage);
            }
            return fluidCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {

        super.invalidateCaps();
        fluidCap.invalidate();
    }
    // endregion
}
