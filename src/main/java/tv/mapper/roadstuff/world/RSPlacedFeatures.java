package tv.mapper.roadstuff.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.roadstuff.RoadStuff;
import tv.mapper.roadstuff.config.RSConfig;

import java.util.List;

public class RSPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, RoadStuff.MODID);

    public static final RegistryObject<PlacedFeature> BITUMEN_ORE_PLACED = PLACED_FEATURES.register("bitumen_ore",
            () -> new PlacedFeature(RSConfiguredFeatures.BITUMEN_ORE.getHolder().get(),
                    commonOrePlacement(RSConfig.BITUMEN_CHANCE.get(),
                            HeightRangePlacement.triangle(
                                    VerticalAnchor.absolute(RSConfig.BITUMEN_MIN_HEIGHT.get()),
                                    VerticalAnchor.absolute(RSConfig.BITUMEN_MAX_HEIGHT.get())
                            ))));

    public static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }
}
