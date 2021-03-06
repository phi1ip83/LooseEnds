package philip83.looseends.setup.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import philip83.looseends.LooseEnds;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LooseEnds.MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()));


    //blocks
    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK = ITEMS.register("example_block", () ->new BlockItem(BlockInit.EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> TEST_BLOCK = ITEMS.register("test_block", ()-> new BlockItem(BlockInit.TEST_BLOCK.get(), new Item.Properties()));
}
