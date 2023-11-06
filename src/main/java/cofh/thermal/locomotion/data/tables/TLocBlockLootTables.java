package cofh.thermal.locomotion.data.tables;

import cofh.lib.data.loot.BlockLootSubProviderCoFH;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocBlockLootTables extends BlockLootSubProviderCoFH {

    @Override
    protected void generate() {

        var regBlocks = BLOCKS;
        var regItems = ITEMS;

        createSimpleDropTable(regBlocks.get(ID_CROSSOVER_RAIL));

        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_CROSSOVER_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_POWERED_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_ACTIVATOR_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_DETECTOR_RAIL));

        createSimpleDropTable(regBlocks.get(ID_LUMIUM_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_CROSSOVER_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_POWERED_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_ACTIVATOR_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_DETECTOR_RAIL));
    }

}
