package net.minecraft.data.worldgen.placement;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class OrePlacements {
   public static final Holder<PlacedFeature> ORE_MAGMA = PlacementUtils.m_206509_("ore_magma", OreFeatures.ORE_MAGMA, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(36))));
   public static final Holder<PlacedFeature> ORE_SOUL_SAND = PlacementUtils.m_206509_("ore_soul_sand", OreFeatures.ORE_SOUL_SAND, commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31))));
   public static final Holder<PlacedFeature> ORE_GOLD_DELTAS = PlacementUtils.m_206509_("ore_gold_deltas", OreFeatures.ORE_NETHER_GOLD, commonOrePlacement(20, PlacementUtils.RANGE_10_10));
   public static final Holder<PlacedFeature> ORE_QUARTZ_DELTAS = PlacementUtils.m_206509_("ore_quartz_deltas", OreFeatures.ORE_QUARTZ, commonOrePlacement(32, PlacementUtils.RANGE_10_10));
   public static final Holder<PlacedFeature> ORE_GOLD_NETHER = PlacementUtils.m_206509_("ore_gold_nether", OreFeatures.ORE_NETHER_GOLD, commonOrePlacement(10, PlacementUtils.RANGE_10_10));
   public static final Holder<PlacedFeature> ORE_QUARTZ_NETHER = PlacementUtils.m_206509_("ore_quartz_nether", OreFeatures.ORE_QUARTZ, commonOrePlacement(16, PlacementUtils.RANGE_10_10));
   public static final Holder<PlacedFeature> ORE_GRAVEL_NETHER = PlacementUtils.m_206509_("ore_gravel_nether", OreFeatures.ORE_GRAVEL_NETHER, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(41))));
   public static final Holder<PlacedFeature> ORE_BLACKSTONE = PlacementUtils.m_206509_("ore_blackstone", OreFeatures.ORE_BLACKSTONE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(31))));
   public static final Holder<PlacedFeature> ORE_DIRT = PlacementUtils.m_206509_("ore_dirt", OreFeatures.ORE_DIRT, commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(160))));
   public static final Holder<PlacedFeature> ORE_GRAVEL = PlacementUtils.m_206509_("ore_gravel", OreFeatures.ORE_GRAVEL, commonOrePlacement(14, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
   public static final Holder<PlacedFeature> ORE_GRANITE_UPPER = PlacementUtils.m_206509_("ore_granite_upper", OreFeatures.ORE_GRANITE, rareOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));
   public static final Holder<PlacedFeature> ORE_GRANITE_LOWER = PlacementUtils.m_206509_("ore_granite_lower", OreFeatures.ORE_GRANITE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
   public static final Holder<PlacedFeature> ORE_DIORITE_UPPER = PlacementUtils.m_206509_("ore_diorite_upper", OreFeatures.ORE_DIORITE, rareOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));
   public static final Holder<PlacedFeature> ORE_DIORITE_LOWER = PlacementUtils.m_206509_("ore_diorite_lower", OreFeatures.ORE_DIORITE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
   public static final Holder<PlacedFeature> ORE_ANDESITE_UPPER = PlacementUtils.m_206509_("ore_andesite_upper", OreFeatures.ORE_ANDESITE, rareOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));
   public static final Holder<PlacedFeature> ORE_ANDESITE_LOWER = PlacementUtils.m_206509_("ore_andesite_lower", OreFeatures.ORE_ANDESITE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
   public static final Holder<PlacedFeature> ORE_TUFF = PlacementUtils.m_206509_("ore_tuff", OreFeatures.ORE_TUFF, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0))));
   public static final Holder<PlacedFeature> ORE_COAL_UPPER = PlacementUtils.m_206509_("ore_coal_upper", OreFeatures.ORE_COAL, commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(136), VerticalAnchor.top())));
   public static final Holder<PlacedFeature> ORE_COAL_LOWER = PlacementUtils.m_206509_("ore_coal_lower", OreFeatures.ORE_COAL_BURIED, commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
   public static final Holder<PlacedFeature> ORE_IRON_UPPER = PlacementUtils.m_206509_("ore_iron_upper", OreFeatures.ORE_IRON, commonOrePlacement(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
   public static final Holder<PlacedFeature> ORE_IRON_MIDDLE = PlacementUtils.m_206509_("ore_iron_middle", OreFeatures.ORE_IRON, commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
   public static final Holder<PlacedFeature> ORE_IRON_SMALL = PlacementUtils.m_206509_("ore_iron_small", OreFeatures.ORE_IRON_SMALL, commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
   public static final Holder<PlacedFeature> ORE_GOLD_EXTRA = PlacementUtils.m_206509_("ore_gold_extra", OreFeatures.ORE_GOLD, commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(256))));
   public static final Holder<PlacedFeature> ORE_GOLD = PlacementUtils.m_206509_("ore_gold", OreFeatures.ORE_GOLD_BURIED, commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
   public static final Holder<PlacedFeature> ORE_GOLD_LOWER = PlacementUtils.m_206509_("ore_gold_lower", OreFeatures.ORE_GOLD_BURIED, orePlacement(CountPlacement.of(UniformInt.of(0, 1)), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))));
   public static final Holder<PlacedFeature> ORE_REDSTONE = PlacementUtils.m_206509_("ore_redstone", OreFeatures.ORE_REDSTONE, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15))));
   public static final Holder<PlacedFeature> ORE_REDSTONE_LOWER = PlacementUtils.m_206509_("ore_redstone_lower", OreFeatures.ORE_REDSTONE, commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))));
   public static final Holder<PlacedFeature> ORE_DIAMOND = PlacementUtils.m_206509_("ore_diamond", OreFeatures.ORE_DIAMOND_SMALL, commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
   public static final Holder<PlacedFeature> ORE_DIAMOND_LARGE = PlacementUtils.m_206509_("ore_diamond_large", OreFeatures.ORE_DIAMOND_LARGE, rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
   public static final Holder<PlacedFeature> ORE_DIAMOND_BURIED = PlacementUtils.m_206509_("ore_diamond_buried", OreFeatures.ORE_DIAMOND_BURIED, commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
   public static final Holder<PlacedFeature> ORE_LAPIS = PlacementUtils.m_206509_("ore_lapis", OreFeatures.ORE_LAPIS, commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
   public static final Holder<PlacedFeature> ORE_LAPIS_BURIED = PlacementUtils.m_206509_("ore_lapis_buried", OreFeatures.ORE_LAPIS_BURIED, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
   public static final Holder<PlacedFeature> ORE_INFESTED = PlacementUtils.m_206509_("ore_infested", OreFeatures.ORE_INFESTED, commonOrePlacement(14, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(63))));
   public static final Holder<PlacedFeature> ORE_EMERALD = PlacementUtils.m_206509_("ore_emerald", OreFeatures.ORE_EMERALD, commonOrePlacement(100, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))));
   public static final Holder<PlacedFeature> ORE_ANCIENT_DEBRIS_LARGE = PlacementUtils.m_206513_("ore_ancient_debris_large", OreFeatures.ORE_ANCIENT_DEBRIS_LARGE, InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(24)), BiomeFilter.biome());
   public static final Holder<PlacedFeature> ORE_ANCIENT_DEBRIS_SMALL = PlacementUtils.m_206513_("ore_debris_small", OreFeatures.ORE_ANCIENT_DEBRIS_SMALL, InSquarePlacement.spread(), PlacementUtils.RANGE_8_8, BiomeFilter.biome());
   public static final Holder<PlacedFeature> ORE_COPPER = PlacementUtils.m_206509_("ore_copper", OreFeatures.ORE_COPPPER_SMALL, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
   public static final Holder<PlacedFeature> ORE_COPPER_LARGE = PlacementUtils.m_206509_("ore_copper_large", OreFeatures.ORE_COPPER_LARGE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
   public static final Holder<PlacedFeature> ORE_CLAY = PlacementUtils.m_206509_("ore_clay", OreFeatures.ORE_CLAY, commonOrePlacement(46, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT));

   private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
      return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
   }

   private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
      return orePlacement(CountPlacement.of(p_195344_), p_195345_);
   }

   private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
      return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
   }
}