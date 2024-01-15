package tv.mapper.roadstuff.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.roadstuff.RoadStuff;
import tv.mapper.roadstuff.world.level.block.RSBlockRegistry;

public class ModItemGroups
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RoadStuff.MODID);

    public static RegistryObject<CreativeModeTab> ROADSTUFF = CREATIVE_MODE_TABS.register("roadstuff_group", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(RSBlockRegistry.TRAFFIC_CONE_BLOCKS.get(DyeColor.ORANGE).get()))
            .title(Component.translatable("itemGroup.roadstuff_group"))
            .build()
    );

    public static void init()
    {
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}