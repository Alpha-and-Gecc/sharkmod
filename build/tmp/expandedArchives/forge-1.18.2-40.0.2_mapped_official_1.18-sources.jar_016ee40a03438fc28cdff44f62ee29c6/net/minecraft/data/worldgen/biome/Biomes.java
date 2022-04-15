package net.minecraft.data.worldgen.biome;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public abstract class Biomes {
   private static void m_206463_(ResourceKey<Biome> p_206464_, Biome p_206465_) {
      BuiltinRegistries.m_206384_(BuiltinRegistries.BIOME, p_206464_, p_206465_);
   }

   public static Holder<Biome> m_206462_() {
      return BuiltinRegistries.BIOME.m_206081_(net.minecraft.world.level.biome.Biomes.PLAINS);
   }

   static {
      m_206463_(net.minecraft.world.level.biome.Biomes.THE_VOID, OverworldBiomes.theVoid());
      m_206463_(net.minecraft.world.level.biome.Biomes.PLAINS, OverworldBiomes.plains(false, false, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.SUNFLOWER_PLAINS, OverworldBiomes.plains(true, false, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.SNOWY_PLAINS, OverworldBiomes.plains(false, true, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.ICE_SPIKES, OverworldBiomes.plains(false, true, true));
      m_206463_(net.minecraft.world.level.biome.Biomes.DESERT, OverworldBiomes.desert());
      m_206463_(net.minecraft.world.level.biome.Biomes.SWAMP, OverworldBiomes.swamp());
      m_206463_(net.minecraft.world.level.biome.Biomes.FOREST, OverworldBiomes.forest(false, false, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.FLOWER_FOREST, OverworldBiomes.forest(false, false, true));
      m_206463_(net.minecraft.world.level.biome.Biomes.BIRCH_FOREST, OverworldBiomes.forest(true, false, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.DARK_FOREST, OverworldBiomes.darkForest());
      m_206463_(net.minecraft.world.level.biome.Biomes.OLD_GROWTH_BIRCH_FOREST, OverworldBiomes.forest(true, true, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.OLD_GROWTH_PINE_TAIGA, OverworldBiomes.oldGrowthTaiga(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.OLD_GROWTH_SPRUCE_TAIGA, OverworldBiomes.oldGrowthTaiga(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.TAIGA, OverworldBiomes.taiga(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.SNOWY_TAIGA, OverworldBiomes.taiga(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.SAVANNA, OverworldBiomes.savanna(false, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.SAVANNA_PLATEAU, OverworldBiomes.savanna(false, true));
      m_206463_(net.minecraft.world.level.biome.Biomes.WINDSWEPT_HILLS, OverworldBiomes.windsweptHills(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.WINDSWEPT_GRAVELLY_HILLS, OverworldBiomes.windsweptHills(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.WINDSWEPT_FOREST, OverworldBiomes.windsweptHills(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.WINDSWEPT_SAVANNA, OverworldBiomes.savanna(true, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.JUNGLE, OverworldBiomes.jungle());
      m_206463_(net.minecraft.world.level.biome.Biomes.SPARSE_JUNGLE, OverworldBiomes.sparseJungle());
      m_206463_(net.minecraft.world.level.biome.Biomes.BAMBOO_JUNGLE, OverworldBiomes.bambooJungle());
      m_206463_(net.minecraft.world.level.biome.Biomes.BADLANDS, OverworldBiomes.badlands(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.ERODED_BADLANDS, OverworldBiomes.badlands(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.WOODED_BADLANDS, OverworldBiomes.badlands(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.MEADOW, OverworldBiomes.meadow());
      m_206463_(net.minecraft.world.level.biome.Biomes.GROVE, OverworldBiomes.grove());
      m_206463_(net.minecraft.world.level.biome.Biomes.SNOWY_SLOPES, OverworldBiomes.snowySlopes());
      m_206463_(net.minecraft.world.level.biome.Biomes.FROZEN_PEAKS, OverworldBiomes.frozenPeaks());
      m_206463_(net.minecraft.world.level.biome.Biomes.JAGGED_PEAKS, OverworldBiomes.jaggedPeaks());
      m_206463_(net.minecraft.world.level.biome.Biomes.STONY_PEAKS, OverworldBiomes.stonyPeaks());
      m_206463_(net.minecraft.world.level.biome.Biomes.RIVER, OverworldBiomes.river(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.FROZEN_RIVER, OverworldBiomes.river(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.BEACH, OverworldBiomes.beach(false, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.SNOWY_BEACH, OverworldBiomes.beach(true, false));
      m_206463_(net.minecraft.world.level.biome.Biomes.STONY_SHORE, OverworldBiomes.beach(false, true));
      m_206463_(net.minecraft.world.level.biome.Biomes.WARM_OCEAN, OverworldBiomes.warmOcean());
      m_206463_(net.minecraft.world.level.biome.Biomes.LUKEWARM_OCEAN, OverworldBiomes.lukeWarmOcean(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.DEEP_LUKEWARM_OCEAN, OverworldBiomes.lukeWarmOcean(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.OCEAN, OverworldBiomes.ocean(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.DEEP_OCEAN, OverworldBiomes.ocean(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.COLD_OCEAN, OverworldBiomes.coldOcean(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.DEEP_COLD_OCEAN, OverworldBiomes.coldOcean(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.FROZEN_OCEAN, OverworldBiomes.frozenOcean(false));
      m_206463_(net.minecraft.world.level.biome.Biomes.DEEP_FROZEN_OCEAN, OverworldBiomes.frozenOcean(true));
      m_206463_(net.minecraft.world.level.biome.Biomes.MUSHROOM_FIELDS, OverworldBiomes.mushroomFields());
      m_206463_(net.minecraft.world.level.biome.Biomes.DRIPSTONE_CAVES, OverworldBiomes.dripstoneCaves());
      m_206463_(net.minecraft.world.level.biome.Biomes.LUSH_CAVES, OverworldBiomes.lushCaves());
      m_206463_(net.minecraft.world.level.biome.Biomes.NETHER_WASTES, NetherBiomes.netherWastes());
      m_206463_(net.minecraft.world.level.biome.Biomes.WARPED_FOREST, NetherBiomes.warpedForest());
      m_206463_(net.minecraft.world.level.biome.Biomes.CRIMSON_FOREST, NetherBiomes.crimsonForest());
      m_206463_(net.minecraft.world.level.biome.Biomes.SOUL_SAND_VALLEY, NetherBiomes.soulSandValley());
      m_206463_(net.minecraft.world.level.biome.Biomes.BASALT_DELTAS, NetherBiomes.basaltDeltas());
      m_206463_(net.minecraft.world.level.biome.Biomes.THE_END, EndBiomes.theEnd());
      m_206463_(net.minecraft.world.level.biome.Biomes.END_HIGHLANDS, EndBiomes.endHighlands());
      m_206463_(net.minecraft.world.level.biome.Biomes.END_MIDLANDS, EndBiomes.endMidlands());
      m_206463_(net.minecraft.world.level.biome.Biomes.SMALL_END_ISLANDS, EndBiomes.smallEndIslands());
      m_206463_(net.minecraft.world.level.biome.Biomes.END_BARRENS, EndBiomes.endBarrens());
   }
}