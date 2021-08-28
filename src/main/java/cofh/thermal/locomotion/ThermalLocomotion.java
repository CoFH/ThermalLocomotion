package cofh.thermal.locomotion;

import cofh.lib.client.renderer.entity.TNTMinecartRendererCoFH;
import cofh.thermal.core.client.renderer.entity.UnderwaterMinecartRenderer;
import cofh.thermal.locomotion.init.TLocBlocks;
import cofh.thermal.locomotion.init.TLocEntities;
import cofh.thermal.locomotion.init.TLocItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.locomotion.init.TLocIDs.*;
import static cofh.thermal.locomotion.init.TLocReferences.*;

@Mod(ID_THERMAL_LOCOMOTION)
public class ThermalLocomotion {

    public ThermalLocomotion() {

        setFeatureFlags();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TLocBlocks.register();
        TLocItems.register();

        TLocEntities.register();
    }

    private void setFeatureFlags() {

    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        this.registerRenderLayers();
        this.registerEntityRenderingHandlers();
    }
    // endregion

    // region HELPERS
    private void registerRenderLayers() {

        RenderType cutout = RenderType.cutout();

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_CROSSOVER_RAIL), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), cutout);
    }

    private void registerEntityRenderingHandlers() {

        RenderingRegistry.registerEntityRenderingHandler(UNDERWATER_CART_ENTITY, UnderwaterMinecartRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(SLIME_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
        RenderingRegistry.registerEntityRenderingHandler(REDSTONE_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
        RenderingRegistry.registerEntityRenderingHandler(GLOWSTONE_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
        RenderingRegistry.registerEntityRenderingHandler(ENDER_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);

        RenderingRegistry.registerEntityRenderingHandler(PHYTO_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);

        RenderingRegistry.registerEntityRenderingHandler(FIRE_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
        RenderingRegistry.registerEntityRenderingHandler(EARTH_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
        RenderingRegistry.registerEntityRenderingHandler(ICE_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
        RenderingRegistry.registerEntityRenderingHandler(LIGHTNING_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);

        RenderingRegistry.registerEntityRenderingHandler(NUKE_TNT_CART_ENTITY, TNTMinecartRendererCoFH::new);
    }
    // endregion
}
