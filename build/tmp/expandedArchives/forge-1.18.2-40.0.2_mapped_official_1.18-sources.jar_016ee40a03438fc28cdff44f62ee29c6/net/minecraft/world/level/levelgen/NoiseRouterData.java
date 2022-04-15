package net.minecraft.world.level.levelgen;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class NoiseRouterData {
   private static final float f_209440_ = 0.08F;
   private static final double f_209441_ = 1.5D;
   private static final double f_209442_ = 1.5D;
   private static final double f_209443_ = 1.5625D;
   private static final DensityFunction f_209444_ = DensityFunctions.m_208264_(10.0D);
   private static final DensityFunction f_209445_ = DensityFunctions.m_208263_();
   private static final ResourceKey<DensityFunction> f_209446_ = m_209536_("zero");
   private static final ResourceKey<DensityFunction> f_209447_ = m_209536_("y");
   private static final ResourceKey<DensityFunction> f_209448_ = m_209536_("shift_x");
   private static final ResourceKey<DensityFunction> f_209449_ = m_209536_("shift_z");
   private static final ResourceKey<DensityFunction> f_209450_ = m_209536_("overworld/base_3d_noise");
   private static final ResourceKey<DensityFunction> f_209451_ = m_209536_("overworld/continents");
   private static final ResourceKey<DensityFunction> f_209452_ = m_209536_("overworld/erosion");
   private static final ResourceKey<DensityFunction> f_209453_ = m_209536_("overworld/ridges");
   private static final ResourceKey<DensityFunction> f_209454_ = m_209536_("overworld/factor");
   private static final ResourceKey<DensityFunction> f_209455_ = m_209536_("overworld/depth");
   private static final ResourceKey<DensityFunction> f_209456_ = m_209536_("overworld/sloped_cheese");
   private static final ResourceKey<DensityFunction> f_209457_ = m_209536_("overworld_large_biomes/continents");
   private static final ResourceKey<DensityFunction> f_209458_ = m_209536_("overworld_large_biomes/erosion");
   private static final ResourceKey<DensityFunction> f_209459_ = m_209536_("overworld_large_biomes/factor");
   private static final ResourceKey<DensityFunction> f_209460_ = m_209536_("overworld_large_biomes/depth");
   private static final ResourceKey<DensityFunction> f_209461_ = m_209536_("overworld_large_biomes/sloped_cheese");
   private static final ResourceKey<DensityFunction> f_209462_ = m_209536_("end/sloped_cheese");
   private static final ResourceKey<DensityFunction> f_209463_ = m_209536_("overworld/caves/spaghetti_roughness_function");
   private static final ResourceKey<DensityFunction> f_209464_ = m_209536_("overworld/caves/entrances");
   private static final ResourceKey<DensityFunction> f_209465_ = m_209536_("overworld/caves/noodle");
   private static final ResourceKey<DensityFunction> f_209437_ = m_209536_("overworld/caves/pillars");
   private static final ResourceKey<DensityFunction> f_209438_ = m_209536_("overworld/caves/spaghetti_2d_thickness_modulator");
   private static final ResourceKey<DensityFunction> f_209439_ = m_209536_("overworld/caves/spaghetti_2d");

   protected static NoiseRouterWithOnlyNoises m_212277_(NoiseSettings p_212278_, boolean p_212279_) {
      return m_212282_(p_212278_, p_212279_);
   }

   private static ResourceKey<DensityFunction> m_209536_(String p_209537_) {
      return ResourceKey.create(Registry.f_211074_, new ResourceLocation(p_209537_));
   }

   public static Holder<? extends DensityFunction> m_209468_() {
      m_209544_(f_209446_, DensityFunctions.m_208263_());
      int i = DimensionType.MIN_Y * 2;
      int j = DimensionType.MAX_Y * 2;
      m_209544_(f_209447_, DensityFunctions.m_208266_(i, j, (double)i, (double)j));
      DensityFunction densityfunction = m_209544_(f_209448_, DensityFunctions.m_208361_(DensityFunctions.m_208373_(DensityFunctions.m_208366_(m_209542_(Noises.SHIFT)))));
      DensityFunction densityfunction1 = m_209544_(f_209449_, DensityFunctions.m_208361_(DensityFunctions.m_208373_(DensityFunctions.m_208378_(m_209542_(Noises.SHIFT)))));
      m_209544_(f_209450_, BlendedNoise.f_210615_);
      DensityFunction densityfunction2 = m_209544_(f_209451_, DensityFunctions.m_208361_(DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.CONTINENTALNESS))));
      DensityFunction densityfunction3 = m_209544_(f_209452_, DensityFunctions.m_208361_(DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.EROSION))));
      DensityFunction densityfunction4 = m_209544_(f_209453_, DensityFunctions.m_208361_(DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.RIDGE))));
      DensityFunction densityfunction5 = DensityFunctions.m_208368_(m_209542_(Noises.JAGGED), 1500.0D, 0.0D);
      DensityFunction densityfunction6 = m_209488_(densityfunction2, densityfunction3, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.OFFSET, -0.81D, 2.5D, DensityFunctions.m_208372_());
      DensityFunction densityfunction7 = m_209544_(f_209454_, m_209488_(densityfunction2, densityfunction3, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.FACTOR, 0.0D, 8.0D, f_209444_));
      DensityFunction densityfunction8 = m_209544_(f_209455_, DensityFunctions.m_208293_(DensityFunctions.m_208266_(-64, 320, 1.5D, -1.5D), densityfunction6));
      m_209544_(f_209456_, m_209481_(densityfunction2, densityfunction3, densityfunction4, densityfunction7, densityfunction8, densityfunction5));
      DensityFunction densityfunction9 = m_209544_(f_209457_, DensityFunctions.m_208361_(DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.CONTINENTALNESS_LARGE))));
      DensityFunction densityfunction10 = m_209544_(f_209458_, DensityFunctions.m_208361_(DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.EROSION_LARGE))));
      DensityFunction densityfunction11 = m_209488_(densityfunction9, densityfunction10, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.OFFSET, -0.81D, 2.5D, DensityFunctions.m_208372_());
      DensityFunction densityfunction12 = m_209544_(f_209459_, m_209488_(densityfunction9, densityfunction10, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.FACTOR, 0.0D, 8.0D, f_209444_));
      DensityFunction densityfunction13 = m_209544_(f_209460_, DensityFunctions.m_208293_(DensityFunctions.m_208266_(-64, 320, 1.5D, -1.5D), densityfunction11));
      m_209544_(f_209461_, m_209481_(densityfunction9, densityfunction10, densityfunction4, densityfunction12, densityfunction13, densityfunction5));
      m_209544_(f_209462_, DensityFunctions.m_208293_(DensityFunctions.m_208271_(0L), m_209552_(f_209450_)));
      m_209544_(f_209463_, m_209547_());
      m_209544_(f_209438_, DensityFunctions.m_208380_(DensityFunctions.m_208336_(m_209542_(Noises.SPAGHETTI_2D_THICKNESS), 2.0D, 1.0D, -0.6D, -1.3D)));
      m_209544_(f_209439_, m_209561_());
      m_209544_(f_209464_, m_209554_());
      m_209544_(f_209465_, m_209557_());
      m_209544_(f_209437_, m_209560_());
      return BuiltinRegistries.f_211085_.m_203611_().iterator().next();
   }

   private static DensityFunction m_209544_(ResourceKey<DensityFunction> p_209545_, DensityFunction p_209546_) {
      return new DensityFunctions.HolderHolder(BuiltinRegistries.m_206384_(BuiltinRegistries.f_211085_, p_209545_, p_209546_));
   }

   private static Holder<NormalNoise.NoiseParameters> m_209542_(ResourceKey<NormalNoise.NoiseParameters> p_209543_) {
      return BuiltinRegistries.NOISE.m_206081_(p_209543_);
   }

   private static DensityFunction m_209552_(ResourceKey<DensityFunction> p_209553_) {
      return new DensityFunctions.HolderHolder(BuiltinRegistries.f_211085_.m_206081_(p_209553_));
   }

   private static DensityFunction m_209481_(DensityFunction p_209482_, DensityFunction p_209483_, DensityFunction p_209484_, DensityFunction p_209485_, DensityFunction p_209486_, DensityFunction p_209487_) {
      DensityFunction densityfunction = m_209488_(p_209482_, p_209483_, p_209484_, DensityFunctions.TerrainShaperSpline.SplineType.JAGGEDNESS, 0.0D, 1.28D, f_209445_);
      DensityFunction densityfunction1 = DensityFunctions.m_208363_(densityfunction, p_209487_.m_208232_());
      DensityFunction densityfunction2 = m_212271_(p_209485_, DensityFunctions.m_208293_(p_209486_, densityfunction1));
      return DensityFunctions.m_208293_(densityfunction2, m_209552_(f_209450_));
   }

   private static DensityFunction m_209547_() {
      DensityFunction densityfunction = DensityFunctions.m_208322_(m_209542_(Noises.SPAGHETTI_ROUGHNESS));
      DensityFunction densityfunction1 = DensityFunctions.m_208327_(m_209542_(Noises.SPAGHETTI_ROUGHNESS_MODULATOR), 0.0D, -0.1D);
      return DensityFunctions.m_208380_(DensityFunctions.m_208363_(densityfunction1, DensityFunctions.m_208293_(densityfunction.m_208229_(), DensityFunctions.m_208264_(-0.4D))));
   }

   private static DensityFunction m_209554_() {
      DensityFunction densityfunction = DensityFunctions.m_208380_(DensityFunctions.m_208368_(m_209542_(Noises.SPAGHETTI_3D_RARITY), 2.0D, 1.0D));
      DensityFunction densityfunction1 = DensityFunctions.m_208327_(m_209542_(Noises.SPAGHETTI_3D_THICKNESS), -0.065D, -0.088D);
      DensityFunction densityfunction2 = DensityFunctions.m_208315_(densityfunction, m_209542_(Noises.SPAGHETTI_3D_1), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE1);
      DensityFunction densityfunction3 = DensityFunctions.m_208315_(densityfunction, m_209542_(Noises.SPAGHETTI_3D_2), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE1);
      DensityFunction densityfunction4 = DensityFunctions.m_208293_(DensityFunctions.m_208382_(densityfunction2, densityfunction3), densityfunction1).m_208220_(-1.0D, 1.0D);
      DensityFunction densityfunction5 = m_209552_(f_209463_);
      DensityFunction densityfunction6 = DensityFunctions.m_208368_(m_209542_(Noises.CAVE_ENTRANCE), 0.75D, 0.5D);
      DensityFunction densityfunction7 = DensityFunctions.m_208293_(DensityFunctions.m_208293_(densityfunction6, DensityFunctions.m_208264_(0.37D)), DensityFunctions.m_208266_(-10, 30, 0.3D, 0.0D));
      return DensityFunctions.m_208380_(DensityFunctions.m_208375_(densityfunction7, DensityFunctions.m_208293_(densityfunction5, densityfunction4)));
   }

   private static DensityFunction m_209557_() {
      DensityFunction densityfunction = m_209552_(f_209447_);
      int i = -64;
      int j = -60;
      int k = 320;
      DensityFunction densityfunction1 = m_209471_(densityfunction, DensityFunctions.m_208368_(m_209542_(Noises.NOODLE), 1.0D, 1.0D), -60, 320, -1);
      DensityFunction densityfunction2 = m_209471_(densityfunction, DensityFunctions.m_208336_(m_209542_(Noises.NOODLE_THICKNESS), 1.0D, 1.0D, -0.05D, -0.1D), -60, 320, 0);
      double d0 = 2.6666666666666665D;
      DensityFunction densityfunction3 = m_209471_(densityfunction, DensityFunctions.m_208368_(m_209542_(Noises.NOODLE_RIDGE_A), 2.6666666666666665D, 2.6666666666666665D), -60, 320, 0);
      DensityFunction densityfunction4 = m_209471_(densityfunction, DensityFunctions.m_208368_(m_209542_(Noises.NOODLE_RIDGE_B), 2.6666666666666665D, 2.6666666666666665D), -60, 320, 0);
      DensityFunction densityfunction5 = DensityFunctions.m_208363_(DensityFunctions.m_208264_(1.5D), DensityFunctions.m_208382_(densityfunction3.m_208229_(), densityfunction4.m_208229_()));
      return DensityFunctions.m_208287_(densityfunction1, -1000000.0D, 0.0D, DensityFunctions.m_208264_(64.0D), DensityFunctions.m_208293_(densityfunction2, densityfunction5));
   }

   private static DensityFunction m_209560_() {
      double d0 = 25.0D;
      double d1 = 0.3D;
      DensityFunction densityfunction = DensityFunctions.m_208368_(m_209542_(Noises.PILLAR), 25.0D, 0.3D);
      DensityFunction densityfunction1 = DensityFunctions.m_208327_(m_209542_(Noises.PILLAR_RARENESS), 0.0D, -2.0D);
      DensityFunction densityfunction2 = DensityFunctions.m_208327_(m_209542_(Noises.PILLAR_THICKNESS), 0.0D, 1.1D);
      DensityFunction densityfunction3 = DensityFunctions.m_208293_(DensityFunctions.m_208363_(densityfunction, DensityFunctions.m_208264_(2.0D)), densityfunction1);
      return DensityFunctions.m_208380_(DensityFunctions.m_208363_(densityfunction3, densityfunction2.m_208231_()));
   }

   private static DensityFunction m_209561_() {
      DensityFunction densityfunction = DensityFunctions.m_208368_(m_209542_(Noises.SPAGHETTI_2D_MODULATOR), 2.0D, 1.0D);
      DensityFunction densityfunction1 = DensityFunctions.m_208315_(densityfunction, m_209542_(Noises.SPAGHETTI_2D), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE2);
      DensityFunction densityfunction2 = DensityFunctions.m_208331_(m_209542_(Noises.SPAGHETTI_2D_ELEVATION), 0.0D, (double)Math.floorDiv(-64, 8), 8.0D);
      DensityFunction densityfunction3 = m_209552_(f_209438_);
      DensityFunction densityfunction4 = DensityFunctions.m_208293_(densityfunction2, DensityFunctions.m_208266_(-64, 320, 8.0D, -40.0D)).m_208229_();
      DensityFunction densityfunction5 = DensityFunctions.m_208293_(densityfunction4, densityfunction3).m_208231_();
      double d0 = 0.083D;
      DensityFunction densityfunction6 = DensityFunctions.m_208293_(densityfunction1, DensityFunctions.m_208363_(DensityFunctions.m_208264_(0.083D), densityfunction3));
      return DensityFunctions.m_208382_(densityfunction6, densityfunction5).m_208220_(-1.0D, 1.0D);
   }

   private static DensityFunction m_209469_(DensityFunction p_209470_) {
      DensityFunction densityfunction = m_209552_(f_209439_);
      DensityFunction densityfunction1 = m_209552_(f_209463_);
      DensityFunction densityfunction2 = DensityFunctions.m_208324_(m_209542_(Noises.CAVE_LAYER), 8.0D);
      DensityFunction densityfunction3 = DensityFunctions.m_208363_(DensityFunctions.m_208264_(4.0D), densityfunction2.m_208230_());
      DensityFunction densityfunction4 = DensityFunctions.m_208324_(m_209542_(Noises.CAVE_CHEESE), 0.6666666666666666D);
      DensityFunction densityfunction5 = DensityFunctions.m_208293_(DensityFunctions.m_208293_(DensityFunctions.m_208264_(0.27D), densityfunction4).m_208220_(-1.0D, 1.0D), DensityFunctions.m_208293_(DensityFunctions.m_208264_(1.5D), DensityFunctions.m_208363_(DensityFunctions.m_208264_(-0.64D), p_209470_)).m_208220_(0.0D, 0.5D));
      DensityFunction densityfunction6 = DensityFunctions.m_208293_(densityfunction3, densityfunction5);
      DensityFunction densityfunction7 = DensityFunctions.m_208375_(DensityFunctions.m_208375_(densityfunction6, m_209552_(f_209464_)), DensityFunctions.m_208293_(densityfunction, densityfunction1));
      DensityFunction densityfunction8 = m_209552_(f_209437_);
      DensityFunction densityfunction9 = DensityFunctions.m_208287_(densityfunction8, -1000000.0D, 0.03D, DensityFunctions.m_208264_(-1000000.0D), densityfunction8);
      return DensityFunctions.m_208382_(densityfunction7, densityfunction9);
   }

   private static DensityFunction m_212274_(NoiseSettings p_212275_, DensityFunction p_212276_) {
      DensityFunction densityfunction = DensityFunctions.m_208319_(p_212275_, p_212276_);
      DensityFunction densityfunction1 = DensityFunctions.m_208389_(densityfunction);
      return DensityFunctions.m_208363_(DensityFunctions.m_208281_(densityfunction1), DensityFunctions.m_208264_(0.64D)).m_208234_();
   }

   private static NoiseRouterWithOnlyNoises m_212282_(NoiseSettings p_212283_, boolean p_212284_) {
      DensityFunction densityfunction = DensityFunctions.m_208324_(m_209542_(Noises.AQUIFER_BARRIER), 0.5D);
      DensityFunction densityfunction1 = DensityFunctions.m_208324_(m_209542_(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
      DensityFunction densityfunction2 = DensityFunctions.m_208324_(m_209542_(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
      DensityFunction densityfunction3 = DensityFunctions.m_208322_(m_209542_(Noises.AQUIFER_LAVA));
      DensityFunction densityfunction4 = m_209552_(f_209448_);
      DensityFunction densityfunction5 = m_209552_(f_209449_);
      DensityFunction densityfunction6 = DensityFunctions.m_208296_(densityfunction4, densityfunction5, 0.25D, m_209542_(p_212284_ ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE));
      DensityFunction densityfunction7 = DensityFunctions.m_208296_(densityfunction4, densityfunction5, 0.25D, m_209542_(p_212284_ ? Noises.VEGETATION_LARGE : Noises.VEGETATION));
      DensityFunction densityfunction8 = m_209552_(p_212284_ ? f_209459_ : f_209454_);
      DensityFunction densityfunction9 = m_209552_(p_212284_ ? f_209460_ : f_209455_);
      DensityFunction densityfunction10 = m_212271_(DensityFunctions.m_208373_(densityfunction8), densityfunction9);
      DensityFunction densityfunction11 = m_209552_(p_212284_ ? f_209461_ : f_209456_);
      DensityFunction densityfunction12 = DensityFunctions.m_208375_(densityfunction11, DensityFunctions.m_208363_(DensityFunctions.m_208264_(5.0D), m_209552_(f_209464_)));
      DensityFunction densityfunction13 = DensityFunctions.m_208287_(densityfunction11, -1000000.0D, 1.5625D, densityfunction12, m_209469_(densityfunction11));
      DensityFunction densityfunction14 = DensityFunctions.m_208375_(m_212274_(p_212283_, densityfunction13), m_209552_(f_209465_));
      DensityFunction densityfunction15 = m_209552_(f_209447_);
      int i = p_212283_.minY();
      int j = Stream.of(OreVeinifier.VeinType.values()).mapToInt((p_212286_) -> {
         return p_212286_.f_209674_;
      }).min().orElse(i);
      int k = Stream.of(OreVeinifier.VeinType.values()).mapToInt((p_212281_) -> {
         return p_212281_.f_209675_;
      }).max().orElse(i);
      DensityFunction densityfunction16 = m_209471_(densityfunction15, DensityFunctions.m_208368_(m_209542_(Noises.ORE_VEININESS), 1.5D, 1.5D), j, k, 0);
      float f = 4.0F;
      DensityFunction densityfunction17 = m_209471_(densityfunction15, DensityFunctions.m_208368_(m_209542_(Noises.ORE_VEIN_A), 4.0D, 4.0D), j, k, 0).m_208229_();
      DensityFunction densityfunction18 = m_209471_(densityfunction15, DensityFunctions.m_208368_(m_209542_(Noises.ORE_VEIN_B), 4.0D, 4.0D), j, k, 0).m_208229_();
      DensityFunction densityfunction19 = DensityFunctions.m_208293_(DensityFunctions.m_208264_((double)-0.08F), DensityFunctions.m_208382_(densityfunction17, densityfunction18));
      DensityFunction densityfunction20 = DensityFunctions.m_208322_(m_209542_(Noises.ORE_GAP));
      return new NoiseRouterWithOnlyNoises(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6, densityfunction7, m_209552_(p_212284_ ? f_209457_ : f_209451_), m_209552_(p_212284_ ? f_209458_ : f_209452_), m_209552_(p_212284_ ? f_209460_ : f_209455_), m_209552_(f_209453_), densityfunction10, densityfunction14, densityfunction16, densityfunction19, densityfunction20);
   }

   private static NoiseRouterWithOnlyNoises m_212287_(NoiseSettings p_212288_) {
      DensityFunction densityfunction = m_209552_(f_209448_);
      DensityFunction densityfunction1 = m_209552_(f_209449_);
      DensityFunction densityfunction2 = DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.TEMPERATURE));
      DensityFunction densityfunction3 = DensityFunctions.m_208296_(densityfunction, densityfunction1, 0.25D, m_209542_(Noises.VEGETATION));
      DensityFunction densityfunction4 = m_212271_(DensityFunctions.m_208373_(m_209552_(f_209454_)), m_209552_(f_209455_));
      DensityFunction densityfunction5 = m_212274_(p_212288_, m_209552_(f_209456_));
      return new NoiseRouterWithOnlyNoises(DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), densityfunction2, densityfunction3, m_209552_(f_209451_), m_209552_(f_209452_), m_209552_(f_209455_), m_209552_(f_209453_), densityfunction4, densityfunction5, DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_());
   }

   protected static NoiseRouterWithOnlyNoises m_209548_(NoiseSettings p_209549_) {
      return m_212287_(p_209549_);
   }

   protected static NoiseRouterWithOnlyNoises m_209555_(NoiseSettings p_209556_) {
      return m_212287_(p_209556_);
   }

   protected static NoiseRouterWithOnlyNoises m_209558_(NoiseSettings p_209559_) {
      DensityFunction densityfunction = DensityFunctions.m_208373_(DensityFunctions.m_208271_(0L));
      DensityFunction densityfunction1 = m_212274_(p_209559_, m_209552_(f_209462_));
      return new NoiseRouterWithOnlyNoises(DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), densityfunction, densityfunction1, DensityFunctions.m_208263_(), DensityFunctions.m_208263_(), DensityFunctions.m_208263_());
   }

   private static NormalNoise m_209524_(PositionalRandomFactory p_209525_, Registry<NormalNoise.NoiseParameters> p_209526_, Holder<NormalNoise.NoiseParameters> p_209527_) {
      return Noises.m_209647_(p_209525_, p_209527_.m_203543_().flatMap(p_209526_::m_203636_).orElse(p_209527_));
   }

   public static NoiseRouter m_209502_(NoiseSettings p_209503_, long p_209504_, Registry<NormalNoise.NoiseParameters> p_209505_, WorldgenRandom.Algorithm p_209506_, NoiseRouterWithOnlyNoises p_209507_) {
      boolean flag = p_209506_ == WorldgenRandom.Algorithm.LEGACY;
      PositionalRandomFactory positionalrandomfactory = p_209506_.newInstance(p_209504_).forkPositional();
      Map<DensityFunction, DensityFunction> map = new HashMap<>();
      DensityFunction.Visitor densityfunction$visitor = (p_209535_) -> {
         if (p_209535_ instanceof DensityFunctions.Noise) {
            DensityFunctions.Noise densityfunctions$noise = (DensityFunctions.Noise)p_209535_;
            Holder<NormalNoise.NoiseParameters> holder2 = densityfunctions$noise.f_208786_();
            return new DensityFunctions.Noise(holder2, m_209524_(positionalrandomfactory, p_209505_, holder2), densityfunctions$noise.f_208788_(), densityfunctions$noise.f_208789_());
         } else if (p_209535_ instanceof DensityFunctions.ShiftNoise) {
            DensityFunctions.ShiftNoise densityfunctions$shiftnoise = (DensityFunctions.ShiftNoise)p_209535_;
            Holder<NormalNoise.NoiseParameters> holder3 = densityfunctions$shiftnoise.m_207183_();
            NormalNoise normalnoise1;
            if (flag) {
               normalnoise1 = NormalNoise.create(positionalrandomfactory.fromHashOf(Noises.SHIFT.location()), new NormalNoise.NoiseParameters(0, 0.0D));
            } else {
               normalnoise1 = m_209524_(positionalrandomfactory, p_209505_, holder3);
            }

            return densityfunctions$shiftnoise.m_207360_(normalnoise1);
         } else if (p_209535_ instanceof DensityFunctions.ShiftedNoise) {
            DensityFunctions.ShiftedNoise densityfunctions$shiftednoise = (DensityFunctions.ShiftedNoise)p_209535_;
            if (flag) {
               Holder<NormalNoise.NoiseParameters> holder = densityfunctions$shiftednoise.f_208929_();
               if (Objects.equals(holder.m_203543_(), Optional.of(Noises.TEMPERATURE))) {
                  NormalNoise normalnoise2 = NormalNoise.createLegacyNetherBiome(p_209506_.newInstance(p_209504_), new NormalNoise.NoiseParameters(-7, 1.0D, 1.0D));
                  return new DensityFunctions.ShiftedNoise(densityfunctions$shiftednoise.f_208924_(), densityfunctions$shiftednoise.f_208925_(), densityfunctions$shiftednoise.f_208926_(), densityfunctions$shiftednoise.f_208927_(), densityfunctions$shiftednoise.f_208928_(), holder, normalnoise2);
               }

               if (Objects.equals(holder.m_203543_(), Optional.of(Noises.VEGETATION))) {
                  NormalNoise normalnoise = NormalNoise.createLegacyNetherBiome(p_209506_.newInstance(p_209504_ + 1L), new NormalNoise.NoiseParameters(-7, 1.0D, 1.0D));
                  return new DensityFunctions.ShiftedNoise(densityfunctions$shiftednoise.f_208924_(), densityfunctions$shiftednoise.f_208925_(), densityfunctions$shiftednoise.f_208926_(), densityfunctions$shiftednoise.f_208927_(), densityfunctions$shiftednoise.f_208928_(), holder, normalnoise);
               }
            }

            Holder<NormalNoise.NoiseParameters> holder1 = densityfunctions$shiftednoise.f_208929_();
            return new DensityFunctions.ShiftedNoise(densityfunctions$shiftednoise.f_208924_(), densityfunctions$shiftednoise.f_208925_(), densityfunctions$shiftednoise.f_208926_(), densityfunctions$shiftednoise.f_208927_(), densityfunctions$shiftednoise.f_208928_(), holder1, m_209524_(positionalrandomfactory, p_209505_, holder1));
         } else if (p_209535_ instanceof DensityFunctions.WeirdScaledSampler) {
            DensityFunctions.WeirdScaledSampler densityfunctions$weirdscaledsampler = (DensityFunctions.WeirdScaledSampler)p_209535_;
            return new DensityFunctions.WeirdScaledSampler(densityfunctions$weirdscaledsampler.m_207189_(), densityfunctions$weirdscaledsampler.f_208426_(), m_209524_(positionalrandomfactory, p_209505_, densityfunctions$weirdscaledsampler.f_208426_()), densityfunctions$weirdscaledsampler.f_208428_());
         } else if (p_209535_ instanceof BlendedNoise) {
            return flag ? new BlendedNoise(p_209506_.newInstance(p_209504_), p_209503_.noiseSamplingSettings(), p_209503_.getCellWidth(), p_209503_.getCellHeight()) : new BlendedNoise(positionalrandomfactory.fromHashOf(new ResourceLocation("terrain")), p_209503_.noiseSamplingSettings(), p_209503_.getCellWidth(), p_209503_.getCellHeight());
         } else if (p_209535_ instanceof DensityFunctions.EndIslandDensityFunction) {
            return new DensityFunctions.EndIslandDensityFunction(p_209504_);
         } else if (p_209535_ instanceof DensityFunctions.TerrainShaperSpline) {
            DensityFunctions.TerrainShaperSpline densityfunctions$terrainshaperspline = (DensityFunctions.TerrainShaperSpline)p_209535_;
            TerrainShaper terrainshaper = p_209503_.terrainShaper();
            return new DensityFunctions.TerrainShaperSpline(densityfunctions$terrainshaperspline.f_208995_(), densityfunctions$terrainshaperspline.f_208996_(), densityfunctions$terrainshaperspline.f_208997_(), terrainshaper, densityfunctions$terrainshaperspline.f_208999_(), densityfunctions$terrainshaperspline.m_207402_(), densityfunctions$terrainshaperspline.m_207401_());
         } else if (p_209535_ instanceof DensityFunctions.Slide) {
            DensityFunctions.Slide densityfunctions$slide = (DensityFunctions.Slide)p_209535_;
            return new DensityFunctions.Slide(p_209503_, densityfunctions$slide.m_207189_());
         } else {
            return p_209535_;
         }
      };
      DensityFunction.Visitor densityfunction$visitor1 = (p_209541_) -> {
         return map.computeIfAbsent(p_209541_, densityfunction$visitor);
      };
      NoiseRouterWithOnlyNoises noiserouterwithonlynoises = p_209507_.m_209603_(densityfunction$visitor1);
      PositionalRandomFactory positionalrandomfactory1 = positionalrandomfactory.fromHashOf(new ResourceLocation("aquifer")).forkPositional();
      PositionalRandomFactory positionalrandomfactory2 = positionalrandomfactory.fromHashOf(new ResourceLocation("ore")).forkPositional();
      return new NoiseRouter(noiserouterwithonlynoises.f_209568_(), noiserouterwithonlynoises.f_209569_(), noiserouterwithonlynoises.f_209570_(), noiserouterwithonlynoises.f_209571_(), positionalrandomfactory1, positionalrandomfactory2, noiserouterwithonlynoises.f_209572_(), noiserouterwithonlynoises.f_209573_(), noiserouterwithonlynoises.f_209574_(), noiserouterwithonlynoises.f_209575_(), noiserouterwithonlynoises.f_209576_(), noiserouterwithonlynoises.f_209577_(), noiserouterwithonlynoises.f_209578_(), noiserouterwithonlynoises.f_209579_(), noiserouterwithonlynoises.f_209580_(), noiserouterwithonlynoises.f_209581_(), noiserouterwithonlynoises.f_209582_(), (new OverworldBiomeBuilder()).spawnTarget());
   }

   private static DensityFunction m_209488_(DensityFunction p_209489_, DensityFunction p_209490_, DensityFunction p_209491_, DensityFunctions.TerrainShaperSpline.SplineType p_209492_, double p_209493_, double p_209494_, DensityFunction p_209495_) {
      DensityFunction densityfunction = DensityFunctions.m_208305_(p_209489_, p_209490_, p_209491_, p_209492_, p_209493_, p_209494_);
      DensityFunction densityfunction1 = DensityFunctions.m_208301_(DensityFunctions.m_208360_(), p_209495_, densityfunction);
      return DensityFunctions.m_208361_(DensityFunctions.m_208373_(densityfunction1));
   }

   private static DensityFunction m_212271_(DensityFunction p_212272_, DensityFunction p_212273_) {
      DensityFunction densityfunction = DensityFunctions.m_208363_(p_212273_, p_212272_);
      return DensityFunctions.m_208363_(DensityFunctions.m_208264_(4.0D), densityfunction.m_208233_());
   }

   private static DensityFunction m_209471_(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
      return DensityFunctions.m_208281_(DensityFunctions.m_208287_(p_209472_, (double)p_209474_, (double)(p_209475_ + 1), p_209473_, DensityFunctions.m_208264_((double)p_209476_)));
   }

   protected static double m_209498_(NoiseSettings p_209499_, double p_209500_, double p_209501_) {
      double d0 = (double)((int)p_209501_ / p_209499_.getCellHeight() - p_209499_.getMinCellY());
      p_209500_ = p_209499_.topSlideSettings().m_209638_(p_209500_, (double)p_209499_.getCellCountY() - d0);
      return p_209499_.bottomSlideSettings().m_209638_(p_209500_, d0);
   }

   protected static double m_209508_(NoiseSettings p_209509_, DensityFunction p_209510_, int p_209511_, int p_209512_) {
      for(int i = p_209509_.getMinCellY() + p_209509_.getCellCountY(); i >= p_209509_.getMinCellY(); --i) {
         int j = i * p_209509_.getCellHeight();
         double d0 = -0.703125D;
         double d1 = p_209510_.m_207386_(new DensityFunction.SinglePointContext(p_209511_, j, p_209512_)) + -0.703125D;
         double d2 = Mth.clamp(d1, -64.0D, 64.0D);
         d2 = m_209498_(p_209509_, d2, (double)j);
         if (d2 > 0.390625D) {
            return (double)j;
         }
      }

      return 2.147483647E9D;
   }

   protected static final class QuantizedSpaghettiRarity {
      protected static double m_209563_(double p_209564_) {
         if (p_209564_ < -0.75D) {
            return 0.5D;
         } else if (p_209564_ < -0.5D) {
            return 0.75D;
         } else if (p_209564_ < 0.5D) {
            return 1.0D;
         } else {
            return p_209564_ < 0.75D ? 2.0D : 3.0D;
         }
      }

      protected static double m_209565_(double p_209566_) {
         if (p_209566_ < -0.5D) {
            return 0.75D;
         } else if (p_209566_ < 0.0D) {
            return 1.0D;
         } else {
            return p_209566_ < 0.5D ? 1.5D : 2.0D;
         }
      }
   }
}