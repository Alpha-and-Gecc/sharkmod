package net.minecraft.data.worldgen.features;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class AquaticFeatures {
   public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SEAGRASS_SHORT = FeatureUtils.m_206488_("seagrass_short", Feature.SEAGRASS, new ProbabilityFeatureConfiguration(0.3F));
   public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SEAGRASS_SLIGHTLY_LESS_SHORT = FeatureUtils.m_206488_("seagrass_slightly_less_short", Feature.SEAGRASS, new ProbabilityFeatureConfiguration(0.4F));
   public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SEAGRASS_MID = FeatureUtils.m_206488_("seagrass_mid", Feature.SEAGRASS, new ProbabilityFeatureConfiguration(0.6F));
   public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SEAGRASS_TALL = FeatureUtils.m_206488_("seagrass_tall", Feature.SEAGRASS, new ProbabilityFeatureConfiguration(0.8F));
   public static final Holder<ConfiguredFeature<CountConfiguration, ?>> SEA_PICKLE = FeatureUtils.m_206488_("sea_pickle", Feature.SEA_PICKLE, new CountConfiguration(20));
   public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> SEAGRASS_SIMPLE = FeatureUtils.m_206488_("seagrass_simple", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SEAGRASS)));
   public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> KELP = FeatureUtils.m_206485_("kelp", Feature.KELP);
   public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> WARM_OCEAN_VEGETATION = FeatureUtils.m_206488_("warm_ocean_vegetation", Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.m_205809_(PlacementUtils.m_206502_(Feature.CORAL_TREE, FeatureConfiguration.NONE), PlacementUtils.m_206502_(Feature.CORAL_CLAW, FeatureConfiguration.NONE), PlacementUtils.m_206502_(Feature.CORAL_MUSHROOM, FeatureConfiguration.NONE))));
}