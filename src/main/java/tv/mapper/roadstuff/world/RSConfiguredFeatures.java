package tv.mapper.roadstuff.world;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.roadstuff.RoadStuff;
import tv.mapper.roadstuff.config.RSConfig;
import tv.mapper.roadstuff.world.level.block.RSBlockRegistry;

import java.util.List;
import java.util.function.Supplier;

public class RSConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, RoadStuff.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> BITUMEN_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), RSBlockRegistry.BITUMEN_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), RSBlockRegistry.BITUMEN_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BITUMEN_ORE = CONFIGURED_FEATURES.register("bitumen_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BITUMEN_ORES.get(), RSConfig.BITUMEN_SIZE.get())));
}
