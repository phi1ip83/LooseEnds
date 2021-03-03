package philip83.looseends.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;


public class TestBlock extends Block {
    public TestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return 15;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return 15;
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }
}
