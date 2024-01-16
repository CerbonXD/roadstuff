package tv.mapper.roadstuff;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mapper.roadstuff.network.RSNetwork;
import tv.mapper.roadstuff.proxy.ClientProxy;
import tv.mapper.roadstuff.proxy.IProxy;
import tv.mapper.roadstuff.proxy.ServerProxy;
import tv.mapper.roadstuff.util.AsphaltPaintMap;
import tv.mapper.roadstuff.util.ConcretePaintMap;
import tv.mapper.roadstuff.world.item.ModItemGroups;
import tv.mapper.roadstuff.world.item.RSItemRegistry;
import tv.mapper.roadstuff.world.level.block.RSBlockRegistry;

@Mod(RoadStuff.MODID)
public class RoadStuff
{
    public static final String MODID = "roadstuff";
    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static AsphaltPaintMap asphaltMap;
    public static ConcretePaintMap concreteMap;

    public static AsphaltPaintMap asphaltSlopeMap;
    public static ConcretePaintMap concreteSlopeMap;

    public static long clickInterval = System.currentTimeMillis();

    public static final Logger LOGGER = LogManager.getLogger();

    public RoadStuff()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RSBlockRegistry.init();
        RSItemRegistry.init();
        ModItemGroups.init();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::serverSetup);

        modEventBus.addListener(this::addToCreativeTab);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("RoadStuff setup started!");
        proxy.setup(event);

        RSNetwork.registerNetworkPackets();

        asphaltMap = new AsphaltPaintMap(false);
        concreteMap = new ConcretePaintMap(false);
        asphaltSlopeMap = new AsphaltPaintMap(true);
        concreteSlopeMap = new ConcretePaintMap(true);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        // LOGGER.info("RoadStuff client setup");
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event)
    {
        // LOGGER.info("RoadStuff server setup");
    }

    private void addToCreativeTab(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTab() == ModItemGroups.ROADSTUFF.get())
            RSItemRegistry.ITEMS.getEntries().forEach(event::accept);
    }
}