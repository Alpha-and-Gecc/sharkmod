package net.minecraft.world.level.levelgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public record NoiseGeneratorSettings(NoiseSettings noiseSettings, BlockState defaultBlock, BlockState defaultFluid, NoiseRouterWithOnlyNoises f_209353_, SurfaceRules.RuleSource surfaceRule, int seaLevel, boolean disableMobGeneration, boolean aquifersEnabled, boolean oreVeinsEnabled, boolean f_209354_) {
   public static final Codec<NoiseGeneratorSettings> DIRECT_CODEC = RecordCodecBuilder.create((p_64475_) -> {
      return p_64475_.group(NoiseSettings.CODEC.fieldOf("noise").forGetter(NoiseGeneratorSettings::noiseSettings), BlockState.CODEC.fieldOf("default_block").forGetter(NoiseGeneratorSettings::defaultBlock), BlockState.CODEC.fieldOf("default_fluid").forGetter(NoiseGeneratorSettings::defaultFluid), NoiseRouterWithOnlyNoises.f_209567_.fieldOf("noise_router").forGetter(NoiseGeneratorSettings::f_209353_), SurfaceRules.RuleSource.CODEC.fieldOf("surface_rule").forGetter(NoiseGeneratorSettings::surfaceRule), Codec.INT.fieldOf("sea_level").forGetter(NoiseGeneratorSettings::seaLevel), Codec.BOOL.fieldOf("disable_mob_generation").forGetter(NoiseGeneratorSettings::disableMobGeneration), Codec.BOOL.fieldOf("aquifers_enabled").forGetter(NoiseGeneratorSettings::isAquifersEnabled), Codec.BOOL.fieldOf("ore_veins_enabled").forGetter(NoiseGeneratorSettings::m_209369_), Codec.BOOL.fieldOf("legacy_random_source").forGetter(NoiseGeneratorSettings::f_209354_)).apply(p_64475_, NoiseGeneratorSettings::new);
   });
   public static final Codec<Holder<NoiseGeneratorSettings>> CODEC = RegistryFileCodec.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, DIRECT_CODEC);
   public static final ResourceKey<NoiseGeneratorSettings> OVERWORLD = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("overworld"));
   public static final ResourceKey<NoiseGeneratorSettings> LARGE_BIOMES = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("large_biomes"));
   public static final ResourceKey<NoiseGeneratorSettings> AMPLIFIED = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("amplified"));
   public static final ResourceKey<NoiseGeneratorSettings> NETHER = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("nether"));
   public static final ResourceKey<NoiseGeneratorSettings> END = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("end"));
   public static final ResourceKey<NoiseGeneratorSettings> CAVES = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("caves"));
   public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLANDS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation("floating_islands"));

   public boolean isAquifersEnabled() {
      return this.aquifersEnabled;
   }

   public boolean m_209369_() {
      return this.oreVeinsEnabled;
   }

   public WorldgenRandom.Algorithm getRandomSource() {
      return this.f_209354_ ? WorldgenRandom.Algorithm.LEGACY : WorldgenRandom.Algorithm.XOROSHIRO;
   }

   public NoiseRouter m_209366_(Registry<NormalNoise.NoiseParameters> p_209367_, long p_209368_) {
      return NoiseRouterData.m_209502_(this.noiseSettings, p_209368_, p_209367_, this.getRandomSource(), this.f_209353_);
   }

   public static void register(ResourceKey<NoiseGeneratorSettings> p_198263_, NoiseGeneratorSettings p_198264_) {
      BuiltinRegistries.m_206388_(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, p_198263_.location(), p_198264_);
   }

   public static Holder<NoiseGeneratorSettings> m_204599_() {
      return BuiltinRegistries.NOISE_GENERATOR_SETTINGS.m_203611_().iterator().next();
   }

   public static NoiseGeneratorSettings end() {
      return new NoiseGeneratorSettings(NoiseSettings.f_209631_, Blocks.END_STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(), NoiseRouterData.m_209558_(NoiseSettings.f_209631_), SurfaceRuleData.end(), 0, true, false, false, true);
   }

   public static NoiseGeneratorSettings nether() {
      return new NoiseGeneratorSettings(NoiseSettings.f_209630_, Blocks.NETHERRACK.defaultBlockState(), Blocks.LAVA.defaultBlockState(), NoiseRouterData.m_209555_(NoiseSettings.f_209630_), SurfaceRuleData.nether(), 32, false, false, false, true);
   }

   public static NoiseGeneratorSettings overworld(boolean p_198266_, boolean p_198267_) {
      NoiseSettings noisesettings = NoiseSettings.m_212307_(p_198266_);
      return new NoiseGeneratorSettings(noisesettings, Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.m_212277_(noisesettings, p_198267_), SurfaceRuleData.overworld(), 63, false, true, true, false);
   }

   public static NoiseGeneratorSettings caves() {
      return new NoiseGeneratorSettings(NoiseSettings.f_209632_, Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.m_209548_(NoiseSettings.f_209632_), SurfaceRuleData.overworldLike(false, true, true), 32, false, false, false, true);
   }

   public static NoiseGeneratorSettings floatingIslands() {
      return new NoiseGeneratorSettings(NoiseSettings.f_209633_, Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.m_209548_(NoiseSettings.f_209633_), SurfaceRuleData.overworldLike(false, false, false), -64, false, false, false, true);
   }

   static {
      register(OVERWORLD, overworld(false, false));
      register(LARGE_BIOMES, overworld(false, true));
      register(AMPLIFIED, overworld(true, false));
      register(NETHER, nether());
      register(END, end());
      register(CAVES, caves());
      register(FLOATING_ISLANDS, floatingIslands());
   }
}