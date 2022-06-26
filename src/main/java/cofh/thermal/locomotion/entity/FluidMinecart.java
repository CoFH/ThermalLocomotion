//package cofh.thermal.locomotion.entity;
//
//import cofh.core.entity.AbstractMinecartCoFH;
//import cofh.lib.fluid.FluidStorageCoFH;
//import net.minecraft.core.Direction;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.fluids.FluidStack;
//import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import java.util.Map;
//
//import static cofh.lib.util.Constants.TANK_MEDIUM;
//import static cofh.thermal.locomotion.init.TLocReferences.FLUID_CART_ENTITY;
//import static cofh.thermal.locomotion.init.TLocReferences.FLUID_CART_ITEM;
//
//public class FluidMinecart extends AbstractMinecartCoFH {
//
//    public static final int BASE_CAPACITY = TANK_MEDIUM * 8;
//
//    protected FluidStorageCoFH fluidStorage = new FluidStorageCoFH(BASE_CAPACITY);
//
//    public FluidMinecart(EntityType<? extends FluidMinecart> type, Level worldIn) {
//
//        super(type, worldIn);
//    }
//
//    public FluidMinecart(Level worldIn, double posX, double posY, double posZ) {
//
//        super(FLUID_CART_ENTITY, worldIn, posX, posY, posZ);
//    }
//
//    public FluidMinecart onPlaced(ItemStack stack) {
//
//        super.onPlaced(stack);
//
//        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
//        float holdingMod = getHoldingMod(enchantMap);
//        fluidStorage.applyModifiers(holdingMod);
//
//        if (stack.getTag() != null) {
//            fluidStorage.read(stack.getTag());
//        }
//        return this;
//    }
//
//    public FluidStack getFluidStack() {
//
//        return fluidStorage.getFluidStack();
//    }
//
//    @Override
//    public ItemStack createItemStackTag(ItemStack stack) {
//
//        fluidStorage.write(stack.getOrCreateTag());
//
//        return super.createItemStackTag(stack);
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundTag compound) {
//
//        super.readAdditionalSaveData(compound);
//
//        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.deserializeEnchantments(enchantments);
//        float holdingMod = getHoldingMod(enchantMap);
//        fluidStorage.applyModifiers(holdingMod);
//
//        fluidStorage.read(compound);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundTag compound) {
//
//        super.addAdditionalSaveData(compound);
//
//        fluidStorage.write(compound);
//    }
//
//    @Override
//    public ItemStack getPickResult() {
//
//        return new ItemStack(FLUID_CART_ITEM);
//    }
//
//    @Override
//    public Type getMinecartType() {
//
//        return Type.CHEST;
//    }
//
//    // region CAPABILITIES
//    protected LazyOptional<?> fluidCap = LazyOptional.empty();
//
//    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//
//        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
//            if (!fluidCap.isPresent() && fluidStorage.getCapacity() > 0) {
//                fluidCap = LazyOptional.of(() -> fluidStorage);
//            }
//            return fluidCap.cast();
//        }
//        return super.getCapability(cap, side);
//    }
//
//    @Override
//    public void invalidateCaps() {
//
//        super.invalidateCaps();
//        fluidCap.invalidate();
//    }
//    // endregion
//}
