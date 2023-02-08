package cofh.thermal.locomotion.item;

import cofh.core.entity.AbstractMinecartCoFH;
import cofh.core.item.IAugmentableItem;
import cofh.core.item.MinecartItemCoFH;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntSupplier;

public class AugmentableMinecartItem extends MinecartItemCoFH implements IAugmentableItem {

    protected IntSupplier numSlots = () -> 0;
    protected BiPredicate<ItemStack, List<ItemStack>> augValidator = (e, f) -> true;

    public AugmentableMinecartItem(IMinecartFactory<? extends AbstractMinecartCoFH> factory, Properties builder) {

        super(factory, builder);
    }

    public AugmentableMinecartItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public AugmentableMinecartItem setAugValidator(BiPredicate<ItemStack, List<ItemStack>> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    // region IAugmentableItem
    @Override
    public int getAugmentSlots(ItemStack augmentable) {

        return numSlots.getAsInt();
    }

    @Override
    public boolean validAugment(ItemStack augmentable, ItemStack augment, List<ItemStack> augments) {

        return augValidator.test(augment, augments);
    }

    @Override
    public void updateAugmentState(ItemStack augmentable, List<ItemStack> augments) {

    }
    // endregion
}
