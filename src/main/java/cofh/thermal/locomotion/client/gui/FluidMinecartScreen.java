package cofh.thermal.locomotion.client.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.panel.AugmentPanel;
import cofh.thermal.locomotion.inventory.container.FluidMinecartContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.createMediumFluidStorage;
import static cofh.core.util.helpers.GuiHelper.generatePanelInfo;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class FluidMinecartScreen extends ContainerScreenCoFH<FluidMinecartContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/fluid_minecart.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public FluidMinecartScreen(FluidMinecartContainer container, Inventory inv, Component titleIn) {

        super(container, inv, titleIn);

        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.fluid_minecart");
    }

    @Override
    public void init() {

        super.init();

        if (menu.getAugmentSlots().size() > 0) {
            addPanel(new AugmentPanel(this, menu::getNumAugmentSlots, menu.getAugmentSlots()));
        }
        addElement(createMediumFluidStorage(this, 80, 22, menu.cart.getTank()));
    }

}
