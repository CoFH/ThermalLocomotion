package cofh.thermal.locomotion.entity;

import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.entity.AbstractMinecartEntityCoFH;
import cofh.thermal.lib.util.ThermalEnergyHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static cofh.thermal.locomotion.init.TLocReferences.ENERGY_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.ENERGY_CART_ITEM;

public class EnergyMinecartEntity extends AbstractMinecartEntityCoFH {

    public static final int BASE_CAPACITY = 8000000;
    public static final int BASE_XFER = 8000;

    protected EnergyStorageCoFH energyStorage = new EnergyStorageCoFH(BASE_CAPACITY, BASE_XFER);

    public EnergyMinecartEntity(EntityType<? extends EnergyMinecartEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public EnergyMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(ENERGY_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    public EnergyMinecartEntity onPlaced(ItemStack stack) {

        super.onPlaced(stack);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        float holdingMod = getHoldingMod(enchantMap);
        energyStorage.applyModifiers(holdingMod, 1.0F);

        if (stack.getTag() != null) {
            energyStorage.read(stack.getTag());
        }
        return this;
    }

    @Override
    public ItemStack createItemStackTag(ItemStack stack) {

        energyStorage.writeWithParams(stack.getTag());

        return super.createItemStackTag(stack);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {

        super.readAdditionalSaveData(compound);

        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
        float holdingMod = getHoldingMod(enchantMap);
        energyStorage.applyModifiers(holdingMod, 1.0F);

        energyStorage.read(compound);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {

        super.addAdditionalSaveData(compound);

        energyStorage.write(compound);
    }

    @Override
    public ItemStack getCartItem() {

        return new ItemStack(ENERGY_CART_ITEM);
    }

    @Override
    public Type getMinecartType() {

        return Type.CHEST;
    }

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
    protected void invalidateCaps() {

        super.invalidateCaps();
        energyCap.invalidate();
    }
    // endregion
}
