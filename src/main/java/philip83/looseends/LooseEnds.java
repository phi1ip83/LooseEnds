package philip83.looseends;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import philip83.looseends.setup.Client;
import philip83.looseends.setup.ModSetup;
import philip83.looseends.setup.init.ItemInit;


@Mod(LooseEnds.MODID)
public class LooseEnds {
    public static final String MODID = "looseends";
    public static final Logger LOGGER = LogManager.getLogger();

    public LooseEnds() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ModSetup::init);
        bus.addListener(Client::init);

        ItemInit.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }


}
