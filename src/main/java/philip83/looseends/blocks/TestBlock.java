package philip83.looseends.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import philip83.looseends.LooseEnds;

public class TestBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public TestBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(POWERED, false));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(POWERED);
    }


    public void toggleState(BlockState state, World world, BlockPos pos) {
        Boolean isPowered = state.get(POWERED);
        world.setBlockState(pos, state.with(POWERED, !isPowered));

        world.notifyNeighborsOfStateChange(pos, this);
    }


    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            boolean flag = state.get(POWERED);
            if (flag != worldIn.isBlockPowered(pos)) {
                toggleState(state,worldIn,pos);
            }

        }

            LooseEnds.LOGGER.debug("Poke.");

    }
}


