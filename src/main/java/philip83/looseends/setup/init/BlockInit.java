package philip83.looseends.setup.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import philip83.looseends.LooseEnds;
import philip83.looseends.blocks.TestBlock;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LooseEnds.MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", ()-> new Block(AbstractBlock.Properties.create(Material.IRON)));

    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", ()-> new TestBlock(AbstractBlock.Properties.create(Material.IRON)));
}
