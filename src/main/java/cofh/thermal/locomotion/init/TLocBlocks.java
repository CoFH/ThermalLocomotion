package cofh.thermal.locomotion.init;

import cofh.lib.block.impl.rails.*;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Rarity;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.locomotion.init.TLocIDs.*;
import static net.minecraft.block.AbstractBlock.Properties.of;

public class TLocBlocks {

    private TLocBlocks() {

    }

    public static void register() {

        registerBlock(ID_CROSSOVER_RAIL, () -> new CrossoverRailBlock(of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), ID_THERMAL_LOCOMOTION);

        registerBlock(ID_PRISMARINE_RAIL, () -> new RailBlockWL(of(Material.METAL, MaterialColor.NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))), ID_THERMAL_LOCOMOTION);
        registerBlock(ID_PRISMARINE_CROSSOVER_RAIL, () -> new CrossoverRailBlockWL(of(Material.METAL, MaterialColor.NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))), ID_THERMAL_LOCOMOTION);
        registerBlock(ID_PRISMARINE_POWERED_RAIL, () -> new PoweredRailBlockWL(of(Material.METAL, MaterialColor.NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8)), true), ID_THERMAL_LOCOMOTION);
        registerBlock(ID_PRISMARINE_ACTIVATOR_RAIL, () -> new PoweredRailBlockWL(of(Material.METAL, MaterialColor.NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))), ID_THERMAL_LOCOMOTION);
        registerBlock(ID_PRISMARINE_DETECTOR_RAIL, () -> new DetectorRailBlockWL(of(Material.METAL, MaterialColor.NONE).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(8))), ID_THERMAL_LOCOMOTION);

        registerBlock(ID_LUMIUM_RAIL, () -> new RailBlockCoFH(of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION);
        registerBlock(ID_LUMIUM_CROSSOVER_RAIL, () -> new CrossoverRailBlock(of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION);
        registerBlock(ID_LUMIUM_POWERED_RAIL, () -> new PoweredRailBlockCoFH(of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15)), true), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION);
        registerBlock(ID_LUMIUM_ACTIVATOR_RAIL, () -> new PoweredRailBlockCoFH(of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION);
        registerBlock(ID_LUMIUM_DETECTOR_RAIL, () -> new DetectorRailBlockCoFH(of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL).lightLevel(lightValue(15))), Rarity.UNCOMMON, ID_THERMAL_LOCOMOTION);
    }

}
