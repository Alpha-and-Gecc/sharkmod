package net.minecraft.data.tags;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class BiomeTagsProvider extends TagsProvider<Biome> {
   public BiomeTagsProvider(DataGenerator p_211094_) {
      super(p_211094_, BuiltinRegistries.BIOME);
   }

   protected void addTags() {
      this.m_206424_(BiomeTags.f_207602_).m_211101_(Biomes.DEEP_FROZEN_OCEAN).m_211101_(Biomes.DEEP_COLD_OCEAN).m_211101_(Biomes.DEEP_OCEAN).m_211101_(Biomes.DEEP_LUKEWARM_OCEAN);
      this.m_206424_(BiomeTags.f_207603_).m_206428_(BiomeTags.f_207602_).m_211101_(Biomes.FROZEN_OCEAN).m_211101_(Biomes.OCEAN).m_211101_(Biomes.COLD_OCEAN).m_211101_(Biomes.LUKEWARM_OCEAN).m_211101_(Biomes.WARM_OCEAN);
      this.m_206424_(BiomeTags.f_207604_).m_211101_(Biomes.BEACH).m_211101_(Biomes.SNOWY_BEACH);
      this.m_206424_(BiomeTags.f_207605_).m_211101_(Biomes.RIVER).m_211101_(Biomes.FROZEN_RIVER);
      this.m_206424_(BiomeTags.f_207606_).m_211101_(Biomes.MEADOW).m_211101_(Biomes.FROZEN_PEAKS).m_211101_(Biomes.JAGGED_PEAKS).m_211101_(Biomes.STONY_PEAKS).m_211101_(Biomes.SNOWY_SLOPES);
      this.m_206424_(BiomeTags.f_207607_).m_211101_(Biomes.BADLANDS).m_211101_(Biomes.ERODED_BADLANDS).m_211101_(Biomes.WOODED_BADLANDS);
      this.m_206424_(BiomeTags.f_207608_).m_211101_(Biomes.WINDSWEPT_HILLS).m_211101_(Biomes.WINDSWEPT_FOREST).m_211101_(Biomes.WINDSWEPT_GRAVELLY_HILLS);
      this.m_206424_(BiomeTags.f_207609_).m_211101_(Biomes.TAIGA).m_211101_(Biomes.SNOWY_TAIGA).m_211101_(Biomes.OLD_GROWTH_PINE_TAIGA).m_211101_(Biomes.OLD_GROWTH_SPRUCE_TAIGA);
      this.m_206424_(BiomeTags.f_207610_).m_211101_(Biomes.BAMBOO_JUNGLE).m_211101_(Biomes.JUNGLE).m_211101_(Biomes.SPARSE_JUNGLE);
      this.m_206424_(BiomeTags.f_207611_).m_211101_(Biomes.FOREST).m_211101_(Biomes.FLOWER_FOREST).m_211101_(Biomes.BIRCH_FOREST).m_211101_(Biomes.OLD_GROWTH_BIRCH_FOREST).m_211101_(Biomes.DARK_FOREST).m_211101_(Biomes.GROVE);
      this.m_206424_(BiomeTags.f_207612_).m_211101_(Biomes.NETHER_WASTES).m_211101_(Biomes.BASALT_DELTAS).m_211101_(Biomes.SOUL_SAND_VALLEY).m_211101_(Biomes.CRIMSON_FOREST).m_211101_(Biomes.WARPED_FOREST);
      this.m_206424_(BiomeTags.f_207613_).m_206428_(BiomeTags.f_207604_);
      this.m_206424_(BiomeTags.f_207614_).m_211101_(Biomes.DESERT);
      this.m_206424_(BiomeTags.f_207615_).m_211101_(Biomes.SNOWY_TAIGA).m_211101_(Biomes.SNOWY_PLAINS).m_211101_(Biomes.SNOWY_SLOPES);
      this.m_206424_(BiomeTags.f_207616_).m_211101_(Biomes.BAMBOO_JUNGLE).m_211101_(Biomes.JUNGLE);
      this.m_206424_(BiomeTags.f_207617_).m_206428_(BiomeTags.f_207603_).m_206428_(BiomeTags.f_207605_).m_206428_(BiomeTags.f_207604_).m_206428_(BiomeTags.f_207606_).m_206428_(BiomeTags.f_207608_).m_206428_(BiomeTags.f_207609_).m_206428_(BiomeTags.f_207610_).m_206428_(BiomeTags.f_207611_).m_211101_(Biomes.STONY_SHORE);
      this.m_206424_(BiomeTags.f_207617_).m_211101_(Biomes.MUSHROOM_FIELDS).m_211101_(Biomes.ICE_SPIKES).m_211101_(Biomes.WINDSWEPT_SAVANNA).m_211101_(Biomes.DESERT).m_211101_(Biomes.SAVANNA).m_211101_(Biomes.SNOWY_PLAINS).m_211101_(Biomes.PLAINS).m_211101_(Biomes.SUNFLOWER_PLAINS).m_211101_(Biomes.SWAMP).m_211101_(Biomes.SAVANNA_PLATEAU).m_211101_(Biomes.DRIPSTONE_CAVES).m_211101_(Biomes.LUSH_CAVES);
      this.m_206424_(BiomeTags.f_207618_).m_206428_(BiomeTags.f_207607_);
      this.m_206424_(BiomeTags.f_207619_).m_206428_(BiomeTags.f_207602_);
      this.m_206424_(BiomeTags.f_207620_).m_211101_(Biomes.FROZEN_OCEAN).m_211101_(Biomes.COLD_OCEAN).m_211101_(Biomes.OCEAN).m_211101_(Biomes.DEEP_FROZEN_OCEAN).m_211101_(Biomes.DEEP_COLD_OCEAN).m_211101_(Biomes.DEEP_OCEAN);
      this.m_206424_(BiomeTags.f_207621_).m_211101_(Biomes.LUKEWARM_OCEAN).m_211101_(Biomes.WARM_OCEAN).m_211101_(Biomes.DEEP_LUKEWARM_OCEAN);
      this.m_206424_(BiomeTags.f_207622_).m_211101_(Biomes.DESERT).m_211101_(Biomes.PLAINS).m_211101_(Biomes.SAVANNA).m_211101_(Biomes.SNOWY_PLAINS).m_211101_(Biomes.TAIGA).m_206428_(BiomeTags.f_207606_).m_211101_(Biomes.GROVE);
      this.m_206424_(BiomeTags.f_207623_).m_211101_(Biomes.DESERT);
      this.m_206424_(BiomeTags.f_207624_).m_206428_(BiomeTags.f_207610_);
      this.m_206424_(BiomeTags.f_207625_).m_206428_(BiomeTags.f_207603_);
      this.m_206424_(BiomeTags.f_207626_).m_211101_(Biomes.SWAMP);
      this.m_206424_(BiomeTags.f_207627_).m_206428_(BiomeTags.f_207607_).m_206428_(BiomeTags.f_207608_).m_211101_(Biomes.SAVANNA_PLATEAU).m_211101_(Biomes.WINDSWEPT_SAVANNA).m_211101_(Biomes.STONY_SHORE).m_206428_(BiomeTags.f_207606_);
      this.m_206424_(BiomeTags.f_207586_).m_206428_(BiomeTags.f_207604_).m_206428_(BiomeTags.f_207605_).m_206428_(BiomeTags.f_207609_).m_206428_(BiomeTags.f_207611_).m_211101_(Biomes.MUSHROOM_FIELDS).m_211101_(Biomes.ICE_SPIKES).m_211101_(Biomes.DRIPSTONE_CAVES).m_211101_(Biomes.LUSH_CAVES).m_211101_(Biomes.SAVANNA).m_211101_(Biomes.SNOWY_PLAINS).m_211101_(Biomes.PLAINS).m_211101_(Biomes.SUNFLOWER_PLAINS);
      this.m_206424_(BiomeTags.f_207587_).m_206428_(BiomeTags.f_207604_);
      this.m_206424_(BiomeTags.f_207588_).m_206428_(BiomeTags.f_207603_);
      this.m_206424_(BiomeTags.f_207589_).m_211101_(Biomes.SWAMP);
      this.m_206424_(BiomeTags.f_207590_).m_211101_(Biomes.DESERT);
      this.m_206424_(BiomeTags.f_207591_).m_211101_(Biomes.PLAINS).m_211101_(Biomes.MEADOW);
      this.m_206424_(BiomeTags.f_207592_).m_211101_(Biomes.SAVANNA);
      this.m_206424_(BiomeTags.f_207593_).m_211101_(Biomes.SNOWY_PLAINS);
      this.m_206424_(BiomeTags.f_207594_).m_211101_(Biomes.TAIGA);
      this.m_206424_(BiomeTags.f_207595_).m_211101_(Biomes.DARK_FOREST);
      this.m_206424_(BiomeTags.f_207596_).m_211101_(Biomes.PLAINS).m_211101_(Biomes.SUNFLOWER_PLAINS).m_211101_(Biomes.SNOWY_PLAINS).m_211101_(Biomes.ICE_SPIKES).m_211101_(Biomes.DESERT).m_211101_(Biomes.FOREST).m_211101_(Biomes.FLOWER_FOREST).m_211101_(Biomes.BIRCH_FOREST).m_211101_(Biomes.DARK_FOREST).m_211101_(Biomes.OLD_GROWTH_BIRCH_FOREST).m_211101_(Biomes.OLD_GROWTH_PINE_TAIGA).m_211101_(Biomes.OLD_GROWTH_SPRUCE_TAIGA).m_211101_(Biomes.TAIGA).m_211101_(Biomes.SNOWY_TAIGA).m_211101_(Biomes.SAVANNA).m_211101_(Biomes.SAVANNA_PLATEAU).m_211101_(Biomes.WINDSWEPT_HILLS).m_211101_(Biomes.WINDSWEPT_GRAVELLY_HILLS).m_211101_(Biomes.WINDSWEPT_FOREST).m_211101_(Biomes.WINDSWEPT_SAVANNA).m_211101_(Biomes.JUNGLE).m_211101_(Biomes.SPARSE_JUNGLE).m_211101_(Biomes.BAMBOO_JUNGLE).m_211101_(Biomes.BADLANDS).m_211101_(Biomes.ERODED_BADLANDS).m_211101_(Biomes.WOODED_BADLANDS).m_211101_(Biomes.MEADOW).m_211101_(Biomes.GROVE).m_211101_(Biomes.SNOWY_SLOPES).m_211101_(Biomes.FROZEN_PEAKS).m_211101_(Biomes.JAGGED_PEAKS).m_211101_(Biomes.STONY_PEAKS).m_211101_(Biomes.MUSHROOM_FIELDS).m_211101_(Biomes.DRIPSTONE_CAVES).m_211101_(Biomes.LUSH_CAVES);
      this.m_206424_(BiomeTags.f_207597_).m_206428_(BiomeTags.f_207612_);
      this.m_206424_(BiomeTags.f_207598_).m_211101_(Biomes.SOUL_SAND_VALLEY);
      this.m_206424_(BiomeTags.f_207599_).m_211101_(Biomes.CRIMSON_FOREST).m_211101_(Biomes.NETHER_WASTES).m_211101_(Biomes.SOUL_SAND_VALLEY).m_211101_(Biomes.WARPED_FOREST);
      this.m_206424_(BiomeTags.f_207600_).m_206428_(BiomeTags.f_207612_);
      this.m_206424_(BiomeTags.f_207601_).m_211101_(Biomes.END_HIGHLANDS).m_211101_(Biomes.END_MIDLANDS);
   }

   public String getName() {
      return "Biome Tags";
   }
}