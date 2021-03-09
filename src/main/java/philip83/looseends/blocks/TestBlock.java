package philip83.looseends.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import philip83.looseends.LooseEnds;

import java.util.HashSet;
import java.util.Set;

public class TestBlock extends Block {
    private static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final BooleanProperty TRIGGERED = BooleanProperty.create("triggered");

    public TestBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(POWERED, false).with(TRIGGERED, false));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(POWERED).add(TRIGGERED);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
    }




    private void safeBreak(BlockState state, World world, BlockPos pos){
        Set<BlockPos> toActivate = new HashSet<>();
        toActivate.add(pos);

        updateTouching(pos, world, toActivate);

        toActivate.forEach((blockPos) ->{
            world.setBlockState(blockPos, state.with(POWERED, false).with(TRIGGERED, false));

            world.notifyNeighborsOfStateChange(blockPos,this.getBlock());

        });
    }
    private void toggleState(BlockState state, World world, BlockPos pos) {
        Set<BlockPos> toActivate = new HashSet<>();
        toActivate.add(pos);

        updateTouching(pos, world, toActivate);

        Boolean isPowered = state.get(POWERED);

        toActivate.forEach((blockPos) ->{
            if(isPowered){
                world.setBlockState(blockPos, state.with(POWERED, false).with(TRIGGERED, false));
            } else{
                world.setBlockState(blockPos, state.with(POWERED, true).with(TRIGGERED, true));
            }

            world.notifyNeighborsOfStateChange(blockPos,this.getBlock());

        });

        world.setBlockState(pos, state.with(POWERED, !isPowered).with(TRIGGERED, false));
    }

    private void updateTouching(BlockPos pos, World world,  Set<BlockPos> previous){
        BlockPos temp;

        temp = pos.down();
        if(world.getBlockState(temp).getBlock() == this.getBlock() && !previous.contains(temp)){
            previous.add(temp);
            updateTouching(temp, world, previous);
        }
        temp = pos.up();
        if(world.getBlockState(temp).getBlock() == this.getBlock() && !previous.contains(temp)){
            previous.add(temp);
            updateTouching(temp, world, previous);
        }
        temp = pos.north();
        if(world.getBlockState(temp).getBlock() == this.getBlock() && !previous.contains(temp)){
            previous.add(temp);
            updateTouching(temp, world, previous);
        }
        temp = pos.south();
        if(world.getBlockState(temp).getBlock() == this.getBlock() && !previous.contains(temp)){
            previous.add(temp);
            updateTouching(temp, world, previous);
        }
        temp = pos.east();
        if(world.getBlockState(temp).getBlock() == this.getBlock() && !previous.contains(temp)){
            previous.add(temp);
            updateTouching(temp, world, previous);
        }
        temp = pos.west();
        if(world.getBlockState(temp).getBlock() == this.getBlock() && !previous.contains(temp)){
            previous.add(temp);
            updateTouching(temp, world, previous);
        }

    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            boolean flag = state.get(POWERED);
            if (flag != worldIn.isBlockPowered(pos) && blockIn != this.getBlock() && !worldIn.getBlockState(pos).get(TRIGGERED)) {
                toggleState(state, worldIn, pos);
            }
            if(isMoving){
                LooseEnds.LOGGER.debug("Push!");
            }

        }

    }
}


