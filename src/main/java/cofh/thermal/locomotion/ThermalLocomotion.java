package cofh.thermal.locomotion;

import cofh.thermal.locomotion.client.gui.EnergyMinecartScreen;
import cofh.thermal.locomotion.client.gui.FluidMinecartScreen;
import cofh.thermal.locomotion.client.renderer.entity.EnergyMinecartRenderer;
import cofh.thermal.locomotion.client.renderer.entity.FluidMinecartRenderer;
import cofh.thermal.locomotion.client.renderer.entity.UnderwaterMinecartRenderer;
import cofh.thermal.locomotion.client.renderer.entity.model.EnergyMinecartModel;
import cofh.thermal.locomotion.client.renderer.entity.model.FluidMinecartModel;
import cofh.thermal.locomotion.client.renderer.entity.model.UnderwaterMinecartModel;
import cofh.thermal.locomotion.init.registries.TLocBlocks;
import cofh.thermal.locomotion.init.registries.TLocContainers;
import cofh.thermal.locomotion.init.registries.TLocEntities;
import cofh.thermal.locomotion.init.registries.TLocItems;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.locomotion.init.registries.TLocContainers.ENERGY_CART_CONTAINER;
import static cofh.thermal.locomotion.init.registries.TLocContainers.FLUID_CART_CONTAINER;
import static cofh.thermal.locomotion.init.registries.TLocEntities.*;
import static cofh.thermal.locomotion.init.registries.TLocIDs.*;

@Mod (ID_THERMAL_LOCOMOTION)
public class ThermalLocomotion {

    public ThermalLocomotion(ModContainer modContainer, IEventBus modEventBus) {

        setFeatureFlags();

        modEventBus.addListener(this::entityLayerSetup);
        modEventBus.addListener(this::entityRendererSetup);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TLocBlocks.register();
        TLocItems.register();

        TLocContainers.register();
        TLocEntities.register();
    }

    private void setFeatureFlags() {

    }

    // region INITIALIZATION
    private void entityLayerSetup(final EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(UnderwaterMinecartModel.UNDERWATER_MINECART_LAYER, UnderwaterMinecartModel::createMesh);
        event.registerLayerDefinition(EnergyMinecartModel.ENERGY_MINECART_LAYER, EnergyMinecartModel::createMesh);
        event.registerLayerDefinition(FluidMinecartModel.FLUID_MINECART_LAYER, FluidMinecartModel::createMesh);
    }

    private void entityRendererSetup(final EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(UNDERWATER_CART.get(), UnderwaterMinecartRenderer::new);
        event.registerEntityRenderer(ENERGY_CART.get(), EnergyMinecartRenderer::new);
        event.registerEntityRenderer(FLUID_CART.get(), FluidMinecartRenderer::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        event.enqueueWork(this::registerGuiFactories);
        event.enqueueWork(this::registerRenderLayers);
    }
    // endregion

    // region HELPERS
    private void registerGuiFactories() {

        MenuScreens.register(ENERGY_CART_CONTAINER.get(), EnergyMinecartScreen::new);
        MenuScreens.register(FLUID_CART_CONTAINER.get(), FluidMinecartScreen::new);
    }

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
