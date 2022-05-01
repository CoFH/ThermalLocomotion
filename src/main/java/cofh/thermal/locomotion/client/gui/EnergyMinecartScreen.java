package cofh.thermal.locomotion.client.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.thermal.locomotion.inventory.container.EnergyMinecartContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.generatePanelInfo;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class EnergyMinecartScreen extends ContainerScreenCoFH<EnergyMinecartContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/energy_minecart.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public EnergyMinecartScreen(EnergyMinecartContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, titleIn);

        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.energy_minecart");
        name = "energy_minecart";
    }

    @Override
    public void init() {

        super.init();

        // TODO Energy Storage
    }

}
