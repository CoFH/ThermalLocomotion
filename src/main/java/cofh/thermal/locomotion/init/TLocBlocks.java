package cofh.thermal.locomotion.init;

import cofh.lib.block.CrossoverRailBlock;
import cofh.lib.block.DetectorRailBlockCoFH;
import cofh.lib.block.PoweredRailBlockCoFH;
import cofh.lib.block.RailBlockCoFH;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_LOCOMOTION;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.lib.common.ThermalCreativeTabs.blocksTab;
import static cofh.thermal.locomotion.init.TLocIDs.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.material.MapColor.NONE;

public class TLocBlocks {

    private TLocBlocks() {

    }

    public static void register() {

        blocksTab(registerBlock(ID_CROSSOVER_RAIL, () -> new CrossoverRailBlock(of().noCollission().strength(0.7F).sound(SoundType.METAL)), ID_THERMAL_LOCOMOTION));

        blocksTab(registerBlock(ID_PRISMARINE_RAIL, () -> new RailBlockCoFH(of().mapColor(NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))) {

            @Override
            public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {

                return state.getValue(WATERLOGGED) ? maxSpeed * 1.5F : maxSpeed;
            }
        }, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_PRISMARINE_CROSSOVER_RAIL, () -> new CrossoverRailBlock(of().mapColor(NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))) {

            @Override
            public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {

                return state.getValue(WATERLOGGED) ? maxSpeed * 1.5F : maxSpeed;
            }
        }, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_PRISMARINE_POWERED_RAIL, () -> new PoweredRailBlockCoFH(of().mapColor(NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8)), true) {

            @Override
            public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {

                return state.getValue(WATERLOGGED) ? maxSpeed * 1.5F : maxSpeed;
            }
        }, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_PRISMARINE_ACTIVATOR_RAIL, () -> new PoweredRailBlockCoFH(of().mapColor(NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))) {

            @Override
            public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {

                return state.getValue(WATERLOGGED) ? maxSpeed * 1.5F : maxSpeed;
            }
        }, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_PRISMARINE_DETECTOR_RAIL, () -> new DetectorRailBlockCoFH(of().mapColor(NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))) {

            @Override
            public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {

                return state.getValue(WATERLOGGED) ? maxSpeed * 1.5F : maxSpeed;
            }
        }, ID_THERMAL_LOCOMOTION));

        blocksTab(registerBlock(ID_LUMIUM_RAIL, () -> new RailBlockCoFH(of().noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_LUMIUM_CROSSOVER_RAIL, () -> new CrossoverRailBlock(of().noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_LUMIUM_POWERED_RAIL, () -> new PoweredRailBlockCoFH(of().noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15)), true), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_LUMIUM_ACTIVATOR_RAIL, () -> new PoweredRailBlockCoFH(of().noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION));
        blocksTab(registerBlock(ID_LUMIUM_DETECTOR_RAIL, () -> new DetectorRailBlockCoFH(of().noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION));
    }

}
