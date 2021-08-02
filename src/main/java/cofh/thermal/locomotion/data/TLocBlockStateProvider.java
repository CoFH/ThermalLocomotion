package cofh.thermal.locomotion.data;

import cofh.lib.data.BlockStateProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TLocBlockStateProvider extends BlockStateProviderCoFH {

    public TLocBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {

        super(gen, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

    }

}
