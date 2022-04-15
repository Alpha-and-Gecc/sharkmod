package net.minecraft.data.worldgen.features;

import java.util.List;
import java.util.Random;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FeatureUtils {
   public static Holder<? extends ConfiguredFeature<?, ?>> m_206469_() {
      List<Holder<? extends ConfiguredFeature<?, ?>>> list = List.of(AquaticFeatures.KELP, CaveFeatures.MOSS_PATCH_BONEMEAL, EndFeatures.CHORUS_PLANT, MiscOverworldFeatures.SPRING_LAVA_OVERWORLD, NetherFeatures.BASALT_BLOBS, OreFeatures.ORE_ANCIENT_DEBRIS_LARGE, PileFeatures.PILE_HAY, TreeFeatures.AZALEA_TREE, VegetationFeatures.TREES_OLD_GROWTH_PINE_TAIGA);
      return Util.getRandom(list, new Random());
   }

   private static BlockPredicate simplePatchPredicate(List<Block> p_195009_) {
      BlockPredicate blockpredicate;
      if (!p_195009_.isEmpty()) {
         blockpredicate = BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(p_195009_, new BlockPos(0, -1, 0)));
      } else {
         blockpredicate = BlockPredicate.ONLY_IN_AIR_PREDICATE;
      }

      return blockpredicate;
   }

   public static RandomPatchConfiguration m_206470_(int p_206471_, Holder<PlacedFeature> p_206472_) {
      return new RandomPatchConfiguration(p_206471_, 7, 3, p_206472_);
   }

   public static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration m_206480_(F p_206481_, FC p_206482_, List<Block> p_206483_, int p_206484_) {
      return m_206470_(p_206484_, PlacementUtils.m_206498_(p_206481_, p_206482_, simplePatchPredicate(p_206483_)));
   }

   public static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration m_206476_(F p_206477_, FC p_206478_, List<Block> p_206479_) {
      return m_206480_(p_206477_, p_206478_, p_206479_, 96);
   }

   public static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration m_206473_(F p_206474_, FC p_206475_) {
      return m_206480_(p_206474_, p_206475_, List.of(), 96);
   }

   public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> m_206485_(String p_206486_, Feature<NoneFeatureConfiguration> p_206487_) {
      return m_206488_(p_206486_, p_206487_, FeatureConfiguration.NONE);
   }

   public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> m_206488_(String p_206489_, F p_206490_, FC p_206491_) {
      return BuiltinRegistries.m_206380_(BuiltinRegistries.CONFIGURED_FEATURE, p_206489_, new ConfiguredFeature<>(p_206490_, p_206491_));
   }
}