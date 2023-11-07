package cofh.thermal.locomotion.client.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.ElementTexture;
import cofh.core.client.gui.element.panel.AugmentPanel;
import cofh.core.common.network.packet.server.FilterableGuiTogglePacket;
import cofh.core.util.filter.IFilterable;
import cofh.core.util.helpers.FilterHelper;
import cofh.thermal.locomotion.common.inventory.FluidMinecartContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;

import java.util.Collections;

import static cofh.core.util.helpers.GuiHelper.*;
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

        // Filter Tab
        addElement(new ElementTexture(this, 4, -21)
                .setUV(24, 0)
                .setSize(24, 21)
                .setTexture(TAB_TOP, 48, 32)
                .setVisible(() -> FilterHelper.hasFilter((IFilterable) menu.cart)));

        addElement(new ElementTexture(this, 8, -17) {

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

                FilterableGuiTogglePacket.openFilterGui(menu.cart);
                return true;
            }
        }
                .setSize(16, 16)
                .setTexture(NAV_FILTER, 16, 16)
                .setTooltipFactory((element, mouseX, mouseY) -> menu.cart.getFilter() instanceof MenuProvider menuProvider ? Collections.singletonList(menuProvider.getDisplayName()) : Collections.emptyList())
                .setVisible(() -> FilterHelper.hasFilter((IFilterable) menu.cart)));
    }

}
