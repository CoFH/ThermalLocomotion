package cofh.thermal.locomotion.common.item;

import cofh.core.util.ProxyUtils;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.core.util.helpers.FluidHelper;
import cofh.lib.api.item.IColorableItem;
import cofh.lib.api.item.IFluidContainerItem;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.locomotion.common.entity.FluidMinecart;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.core.util.helpers.AugmentableHelper.getPropertyWithDefault;
import static cofh.core.util.helpers.AugmentableHelper.setAttributeFromAugmentMax;
import static cofh.core.util.helpers.FluidHelper.addPotionTooltip;
import static cofh.lib.api.ContainerType.FLUID;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.StringHelper.*;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class FluidMinecartItem extends AugmentableMinecartItem implements IFluidContainerItem, IColorableItem {

    public FluidMinecartItem(Properties builder) {

        super(FluidMinecart::new, builder);
        setEnchantability(10);

        ProxyUtils.registerColorable(this);
    }

    @Override
    protected void tooltipDelegate(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        FluidStack fluid = getFluid(stack);
        if (!fluid.isEmpty()) {
            tooltip.add(StringHelper.getFluidName(fluid));
        }
        tooltip.add(isCreative(stack, FLUID)
                ? getTextComponent("info.cofh.infinite").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC)
                : getTextComponent(localize("info.cofh.amount") + ": " + format(fluid.getAmount()) + " / " + format(getCapacity(stack)) + " " + localize("info.cofh.unit_mb")));

        if (FluidHelper.hasPotionTag(fluid)) {
            tooltip.add(getEmptyLine());
            tooltip.add(getTextComponent(localize("info.cofh.effects") + ":"));
            addPotionTooltip(fluid, tooltip);
        }
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundTag augmentData) {

        CompoundTag subTag = container.getTagElement(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        setAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        setAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_FLUID_STORAGE);
        setAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_FLUID_CREATIVE);
    }

    // region IFluidContainerItem
    @Override
    public CompoundTag getOrCreateTankTag(ItemStack container) {

        CompoundTag tag = container.getOrCreateTag();
        if (!tag.contains(TAG_CAPACITY)) {
            new FluidStorageCoFH(FluidMinecart.BASE_CAPACITY).write(tag);
        }
        return container.getTag();
    }

    @Override
    public FluidStack getFluid(ItemStack container) {

        CompoundTag tag = getOrCreateTankTag(container);
        return FluidStack.loadFluidStackFromNBT(tag);
    }

    @Override
    public int getCapacity(ItemStack container) {

        CompoundTag tag = getOrCreateTankTag(container);
        float base = getPropertyWithDefault(container, TAG_AUGMENT_BASE_MOD, 1.0F);
        float mod = getPropertyWithDefault(container, TAG_AUGMENT_FLUID_STORAGE, 1.0F);
        return getMaxStored(container, Math.round(tag.getInt(TAG_CAPACITY) * mod * base));
    }
    // endregion

    // region IAugmentableItem
    @Override
    public void updateAugmentState(ItemStack container, List<ItemStack> augments) {

        container.getOrCreateTag().put(TAG_PROPERTIES, new CompoundTag());
        for (ItemStack augment : augments) {
            CompoundTag augmentData = AugmentDataHelper.getAugmentData(augment);
            if (augmentData == null) {
                continue;
            }
            setAttributesFromAugment(container, augmentData);
        }
        int fluidExcess = getFluidAmount(container) - getCapacity(container);
        if (fluidExcess > 0) {
            drain(container, fluidExcess, EXECUTE);
        }
    }
    // endregion

    // region IColorableItem
    @Override
    public int getColor(ItemStack item, int colorIndex) {

        if (colorIndex == 1) {
            return getFluidAmount(item) > 0 ? FluidHelper.color(getFluid(item)) : 0xFFFFFF;
        }
        return 0xFFFFFF;
    }
    // endregion
}
