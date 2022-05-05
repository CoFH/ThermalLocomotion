package cofh.thermal.locomotion;

import cofh.thermal.locomotion.client.renderer.entity.UnderwaterMinecartRenderer;
import cofh.thermal.locomotion.client.renderer.entity.model.UnderwaterMinecartModel;
import cofh.thermal.locomotion.init.TLocBlocks;
import cofh.thermal.locomotion.init.TLocEntities;
import cofh.thermal.locomotion.init.TLocItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.locomotion.init.TLocIDs.*;
import static cofh.thermal.locomotion.init.TLocReferences.UNDERWATER_CART_ENTITY;

@Mod (ID_THERMAL_LOCOMOTION)
public class ThermalLocomotion {

    public ThermalLocomotion() {

        setFeatureFlags();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::entityLayerSetup);
        modEventBus.addListener(this::entityRendererSetup);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TLocBlocks.register();
        TLocItems.register();

        TLocEntities.register();
    }

    private void setFeatureFlags() {

    }

    // region INITIALIZATION
    private void entityLayerSetup(final EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(UnderwaterMinecartModel.UNDERWATER_MINECART_LAYER, UnderwaterMinecartModel::createMesh);
    }

    private void entityRendererSetup(final EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(UNDERWATER_CART_ENTITY, UnderwaterMinecartRenderer::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        this.registerRenderLayers();
    }
    // endregion

    // region HELPERS
    private void registerRenderLayers() {

        RenderType cutout = RenderType.cutout();

        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_CROSSOVER_RAIL), cutout);

        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_PRISMARINE_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), cutout);

        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_LUMIUM_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), cutout);
    }
    // endregion
}
