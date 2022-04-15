package net.minecraft.world.level.levelgen;

import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.Double2DoubleFunction;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.CubicSpline;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.ToFloatFunction;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import org.slf4j.Logger;

public final class DensityFunctions {
   private static final Codec<DensityFunction> f_208259_ = Registry.f_211076_.byNameCodec().dispatch(DensityFunction::m_207500_, Function.identity());
   protected static final double f_208257_ = 1000000.0D;
   static final Codec<Double> f_208260_ = Codec.doubleRange(-1000000.0D, 1000000.0D);
   public static final Codec<DensityFunction> f_208258_ = Codec.either(f_208260_, f_208259_).xmap((p_208274_) -> {
      return p_208274_.map(DensityFunctions::m_208264_, Function.identity());
   }, (p_208392_) -> {
      if (p_208392_ instanceof DensityFunctions.Constant) {
         DensityFunctions.Constant densityfunctions$constant = (DensityFunctions.Constant)p_208392_;
         return Either.left(densityfunctions$constant.f_208607_());
      } else {
         return Either.right(p_208392_);
      }
   });

   public static Codec<? extends DensityFunction> m_208342_(Registry<Codec<? extends DensityFunction>> p_208343_) {
      m_208344_(p_208343_, "blend_alpha", DensityFunctions.BlendAlpha.f_208528_);
      m_208344_(p_208343_, "blend_offset", DensityFunctions.BlendOffset.f_208565_);
      m_208344_(p_208343_, "beardifier", DensityFunctions.BeardifierMarker.f_208524_);
      m_208344_(p_208343_, "old_blended_noise", BlendedNoise.f_210616_);

      for(DensityFunctions.Marker.Type densityfunctions$marker$type : DensityFunctions.Marker.Type.values()) {
         m_208344_(p_208343_, densityfunctions$marker$type.getSerializedName(), densityfunctions$marker$type.f_208731_);
      }

      m_208344_(p_208343_, "noise", DensityFunctions.Noise.f_208785_);
      m_208344_(p_208343_, "end_islands", DensityFunctions.EndIslandDensityFunction.f_208626_);
      m_208344_(p_208343_, "weird_scaled_sampler", DensityFunctions.WeirdScaledSampler.f_208424_);
      m_208344_(p_208343_, "shifted_noise", DensityFunctions.ShiftedNoise.f_208923_);
      m_208344_(p_208343_, "range_choice", DensityFunctions.RangeChoice.f_208822_);
      m_208344_(p_208343_, "shift_a", DensityFunctions.ShiftA.f_208878_);
      m_208344_(p_208343_, "shift_b", DensityFunctions.ShiftB.f_208898_);
      m_208344_(p_208343_, "shift", DensityFunctions.Shift.f_208858_);
      m_208344_(p_208343_, "blend_density", DensityFunctions.BlendDensity.f_208547_);
      m_208344_(p_208343_, "clamp", DensityFunctions.Clamp.f_208583_);

      for(DensityFunctions.Mapped.Type densityfunctions$mapped$type : DensityFunctions.Mapped.Type.values()) {
         m_208344_(p_208343_, densityfunctions$mapped$type.getSerializedName(), densityfunctions$mapped$type.f_208691_);
      }

      m_208344_(p_208343_, "slide", DensityFunctions.Slide.f_208971_);

      for(DensityFunctions.TwoArgumentSimpleFunction.Type densityfunctions$twoargumentsimplefunction$type : DensityFunctions.TwoArgumentSimpleFunction.Type.values()) {
         m_208344_(p_208343_, densityfunctions$twoargumentsimplefunction$type.getSerializedName(), densityfunctions$twoargumentsimplefunction$type.f_209082_);
      }

      m_208344_(p_208343_, "spline", DensityFunctions.Spline.f_211701_);
      m_208344_(p_208343_, "terrain_shaper_spline", DensityFunctions.TerrainShaperSpline.f_208994_);
      m_208344_(p_208343_, "constant", DensityFunctions.Constant.f_208608_);
      return m_208344_(p_208343_, "y_clamped_gradient", DensityFunctions.YClampedGradient.f_208480_);
   }

   private static Codec<? extends DensityFunction> m_208344_(Registry<Codec<? extends DensityFunction>> p_208345_, String p_208346_, Codec<? extends DensityFunction> p_208347_) {
      return Registry.register(p_208345_, p_208346_, p_208347_);
   }

   static <A, O> Codec<O> m_208275_(Codec<A> p_208276_, Function<A, O> p_208277_, Function<O, A> p_208278_) {
      return p_208276_.fieldOf("argument").xmap(p_208277_, p_208278_).codec();
   }

   static <O> Codec<O> m_208352_(Function<DensityFunction, O> p_208353_, Function<O, DensityFunction> p_208354_) {
      return m_208275_(DensityFunction.f_208218_, p_208353_, p_208354_);
   }

   static <O> Codec<O> m_208348_(BiFunction<DensityFunction, DensityFunction, O> p_208349_, Function<O, DensityFunction> p_208350_, Function<O, DensityFunction> p_208351_) {
      return RecordCodecBuilder.create((p_208359_) -> {
         return p_208359_.group(DensityFunction.f_208218_.fieldOf("argument1").forGetter(p_208350_), DensityFunction.f_208218_.fieldOf("argument2").forGetter(p_208351_)).apply(p_208359_, p_208349_);
      });
   }

   static <O> Codec<O> m_208279_(MapCodec<O> p_208280_) {
      return p_208280_.codec();
   }

   private DensityFunctions() {
   }

   public static DensityFunction m_208281_(DensityFunction p_208282_) {
      return new DensityFunctions.Marker(DensityFunctions.Marker.Type.Interpolated, p_208282_);
   }

   public static DensityFunction m_208361_(DensityFunction p_208362_) {
      return new DensityFunctions.Marker(DensityFunctions.Marker.Type.FlatCache, p_208362_);
   }

   public static DensityFunction m_208373_(DensityFunction p_208374_) {
      return new DensityFunctions.Marker(DensityFunctions.Marker.Type.Cache2D, p_208374_);
   }

   public static DensityFunction m_208380_(DensityFunction p_208381_) {
      return new DensityFunctions.Marker(DensityFunctions.Marker.Type.CacheOnce, p_208381_);
   }

   public static DensityFunction m_208387_(DensityFunction p_208388_) {
      return new DensityFunctions.Marker(DensityFunctions.Marker.Type.CacheAllInCell, p_208388_);
   }

   public static DensityFunction m_208336_(Holder<NormalNoise.NoiseParameters> p_208337_, @Deprecated double p_208338_, double p_208339_, double p_208340_, double p_208341_) {
      return m_208283_(new DensityFunctions.Noise(p_208337_, (NormalNoise)null, p_208338_, p_208339_), p_208340_, p_208341_);
   }

   public static DensityFunction m_208331_(Holder<NormalNoise.NoiseParameters> p_208332_, double p_208333_, double p_208334_, double p_208335_) {
      return m_208336_(p_208332_, 1.0D, p_208333_, p_208334_, p_208335_);
   }

   public static DensityFunction m_208327_(Holder<NormalNoise.NoiseParameters> p_208328_, double p_208329_, double p_208330_) {
      return m_208336_(p_208328_, 1.0D, 1.0D, p_208329_, p_208330_);
   }

   public static DensityFunction m_208296_(DensityFunction p_208297_, DensityFunction p_208298_, double p_208299_, Holder<NormalNoise.NoiseParameters> p_208300_) {
      return new DensityFunctions.ShiftedNoise(p_208297_, m_208263_(), p_208298_, p_208299_, 0.0D, p_208300_, (NormalNoise)null);
   }

   public static DensityFunction m_208322_(Holder<NormalNoise.NoiseParameters> p_208323_) {
      return m_208368_(p_208323_, 1.0D, 1.0D);
   }

   public static DensityFunction m_208368_(Holder<NormalNoise.NoiseParameters> p_208369_, double p_208370_, double p_208371_) {
      return new DensityFunctions.Noise(p_208369_, (NormalNoise)null, p_208370_, p_208371_);
   }

   public static DensityFunction m_208324_(Holder<NormalNoise.NoiseParameters> p_208325_, double p_208326_) {
      return m_208368_(p_208325_, 1.0D, p_208326_);
   }

   public static DensityFunction m_208287_(DensityFunction p_208288_, double p_208289_, double p_208290_, DensityFunction p_208291_, DensityFunction p_208292_) {
      return new DensityFunctions.RangeChoice(p_208288_, p_208289_, p_208290_, p_208291_, p_208292_);
   }

   public static DensityFunction m_208366_(Holder<NormalNoise.NoiseParameters> p_208367_) {
      return new DensityFunctions.ShiftA(p_208367_, (NormalNoise)null);
   }

   public static DensityFunction m_208378_(Holder<NormalNoise.NoiseParameters> p_208379_) {
      return new DensityFunctions.ShiftB(p_208379_, (NormalNoise)null);
   }

   public static DensityFunction m_208385_(Holder<NormalNoise.NoiseParameters> p_208386_) {
      return new DensityFunctions.Shift(p_208386_, (NormalNoise)null);
   }

   public static DensityFunction m_208389_(DensityFunction p_208390_) {
      return new DensityFunctions.BlendDensity(p_208390_);
   }

   public static DensityFunction m_208271_(long p_208272_) {
      return new DensityFunctions.EndIslandDensityFunction(p_208272_);
   }

   public static DensityFunction m_208315_(DensityFunction p_208316_, Holder<NormalNoise.NoiseParameters> p_208317_, DensityFunctions.WeirdScaledSampler.RarityValueMapper p_208318_) {
      return new DensityFunctions.WeirdScaledSampler(p_208316_, p_208317_, (NormalNoise)null, p_208318_);
   }

   public static DensityFunction m_208319_(NoiseSettings p_208320_, DensityFunction p_208321_) {
      return new DensityFunctions.Slide(p_208320_, p_208321_);
   }

   public static DensityFunction m_208293_(DensityFunction p_208294_, DensityFunction p_208295_) {
      return DensityFunctions.TwoArgumentSimpleFunction.m_209073_(DensityFunctions.TwoArgumentSimpleFunction.Type.ADD, p_208294_, p_208295_);
   }

   public static DensityFunction m_208363_(DensityFunction p_208364_, DensityFunction p_208365_) {
      return DensityFunctions.TwoArgumentSimpleFunction.m_209073_(DensityFunctions.TwoArgumentSimpleFunction.Type.MUL, p_208364_, p_208365_);
   }

   public static DensityFunction m_208375_(DensityFunction p_208376_, DensityFunction p_208377_) {
      return DensityFunctions.TwoArgumentSimpleFunction.m_209073_(DensityFunctions.TwoArgumentSimpleFunction.Type.MIN, p_208376_, p_208377_);
   }

   public static DensityFunction m_208382_(DensityFunction p_208383_, DensityFunction p_208384_) {
      return DensityFunctions.TwoArgumentSimpleFunction.m_209073_(DensityFunctions.TwoArgumentSimpleFunction.Type.MAX, p_208383_, p_208384_);
   }

   public static DensityFunction m_208305_(DensityFunction p_208306_, DensityFunction p_208307_, DensityFunction p_208308_, DensityFunctions.TerrainShaperSpline.SplineType p_208309_, double p_208310_, double p_208311_) {
      return new DensityFunctions.TerrainShaperSpline(p_208306_, p_208307_, p_208308_, (TerrainShaper)null, p_208309_, p_208310_, p_208311_);
   }

   public static DensityFunction m_208263_() {
      return DensityFunctions.Constant.f_208609_;
   }

   public static DensityFunction m_208264_(double p_208265_) {
      return new DensityFunctions.Constant(p_208265_);
   }

   public static DensityFunction m_208266_(int p_208267_, int p_208268_, double p_208269_, double p_208270_) {
      return new DensityFunctions.YClampedGradient(p_208267_, p_208268_, p_208269_, p_208270_);
   }

   public static DensityFunction m_208312_(DensityFunction p_208313_, DensityFunctions.Mapped.Type p_208314_) {
      return DensityFunctions.Mapped.m_208671_(p_208314_, p_208313_);
   }

   private static DensityFunction m_208283_(DensityFunction p_208284_, double p_208285_, double p_208286_) {
      double d0 = (p_208285_ + p_208286_) * 0.5D;
      double d1 = (p_208286_ - p_208285_) * 0.5D;
      return m_208293_(m_208264_(d0), m_208363_(m_208264_(d1), p_208284_));
   }

   public static DensityFunction m_208360_() {
      return DensityFunctions.BlendAlpha.INSTANCE;
   }

   public static DensityFunction m_208372_() {
      return DensityFunctions.BlendOffset.INSTANCE;
   }

   public static DensityFunction m_208301_(DensityFunction p_208302_, DensityFunction p_208303_, DensityFunction p_208304_) {
      DensityFunction densityfunction = m_208380_(p_208302_);
      DensityFunction densityfunction1 = m_208293_(m_208363_(densityfunction, m_208264_(-1.0D)), m_208264_(1.0D));
      return m_208293_(m_208363_(p_208303_, densityfunction1), m_208363_(p_208304_, densityfunction));
   }

   static record Ap2(DensityFunctions.TwoArgumentSimpleFunction.Type f_208397_, DensityFunction f_208398_, DensityFunction f_208399_, double f_208400_, double f_208401_) implements DensityFunctions.TwoArgumentSimpleFunction {
      public double m_207386_(DensityFunction.FunctionContext p_208410_) {
         double d0 = this.f_208398_.m_207386_(p_208410_);
         double d1;
         switch(this.f_208397_) {
         case ADD:
            d1 = d0 + this.f_208399_.m_207386_(p_208410_);
            break;
         case MAX:
            d1 = d0 > this.f_208399_.m_207401_() ? d0 : Math.max(d0, this.f_208399_.m_207386_(p_208410_));
            break;
         case MIN:
            d1 = d0 < this.f_208399_.m_207402_() ? d0 : Math.min(d0, this.f_208399_.m_207386_(p_208410_));
            break;
         case MUL:
            d1 = d0 == 0.0D ? 0.0D : d0 * this.f_208399_.m_207386_(p_208410_);
            break;
         default:
            throw new IncompatibleClassChangeError();
         }

         return d1;
      }

      public void m_207362_(double[] p_208414_, DensityFunction.ContextProvider p_208415_) {
         this.f_208398_.m_207362_(p_208414_, p_208415_);
         switch(this.f_208397_) {
         case ADD:
            double[] adouble = new double[p_208414_.length];
            this.f_208399_.m_207362_(adouble, p_208415_);

            for(int k = 0; k < p_208414_.length; ++k) {
               p_208414_[k] += adouble[k];
            }
            break;
         case MAX:
            double d3 = this.f_208399_.m_207401_();

            for(int l = 0; l < p_208414_.length; ++l) {
               double d4 = p_208414_[l];
               p_208414_[l] = d4 > d3 ? d4 : Math.max(d4, this.f_208399_.m_207386_(p_208415_.m_207263_(l)));
            }
            break;
         case MIN:
            double d2 = this.f_208399_.m_207402_();

            for(int j = 0; j < p_208414_.length; ++j) {
               double d1 = p_208414_[j];
               p_208414_[j] = d1 < d2 ? d1 : Math.min(d1, this.f_208399_.m_207386_(p_208415_.m_207263_(j)));
            }
            break;
         case MUL:
            for(int i = 0; i < p_208414_.length; ++i) {
               double d0 = p_208414_[i];
               p_208414_[i] = d0 == 0.0D ? 0.0D : d0 * this.f_208399_.m_207386_(p_208415_.m_207263_(i));
            }
         }

      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208412_) {
         return p_208412_.apply(DensityFunctions.TwoArgumentSimpleFunction.m_209073_(this.f_208397_, this.f_208398_.m_207456_(p_208412_), this.f_208399_.m_207456_(p_208412_)));
      }

      public double m_207402_() {
         return this.f_208400_;
      }

      public double m_207401_() {
         return this.f_208401_;
      }

      public DensityFunctions.TwoArgumentSimpleFunction.Type m_207119_() {
         return this.f_208397_;
      }

      public DensityFunction m_207185_() {
         return this.f_208398_;
      }

      public DensityFunction m_207190_() {
         return this.f_208399_;
      }
   }

   protected static enum BeardifierMarker implements DensityFunctions.BeardifierOrMarker {
      INSTANCE;

      public double m_207386_(DensityFunction.FunctionContext p_208515_) {
         return 0.0D;
      }

      public void m_207362_(double[] p_208517_, DensityFunction.ContextProvider p_208518_) {
         Arrays.fill(p_208517_, 0.0D);
      }

      public double m_207402_() {
         return 0.0D;
      }

      public double m_207401_() {
         return 0.0D;
      }
   }

   public interface BeardifierOrMarker extends DensityFunction.SimpleFunction {
      Codec<DensityFunction> f_208524_ = Codec.unit(DensityFunctions.BeardifierMarker.INSTANCE);

      default Codec<? extends DensityFunction> m_207500_() {
         return f_208524_;
      }
   }

   protected static enum BlendAlpha implements DensityFunction.SimpleFunction {
      INSTANCE;

      public static final Codec<DensityFunction> f_208528_ = Codec.unit(INSTANCE);

      public double m_207386_(DensityFunction.FunctionContext p_208536_) {
         return 1.0D;
      }

      public void m_207362_(double[] p_208538_, DensityFunction.ContextProvider p_208539_) {
         Arrays.fill(p_208538_, 1.0D);
      }

      public double m_207402_() {
         return 1.0D;
      }

      public double m_207401_() {
         return 1.0D;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208528_;
      }
   }

   static record BlendDensity(DensityFunction f_208546_) implements DensityFunctions.TransformerWithContext {
      static final Codec<DensityFunctions.BlendDensity> f_208547_ = DensityFunctions.m_208352_(DensityFunctions.BlendDensity::new, DensityFunctions.BlendDensity::m_207189_);

      public double m_207219_(DensityFunction.FunctionContext p_208553_, double p_208554_) {
         return p_208553_.m_207434_().m_207103_(p_208553_, p_208554_);
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208556_) {
         return p_208556_.apply(new DensityFunctions.BlendDensity(this.f_208546_.m_207456_(p_208556_)));
      }

      public double m_207402_() {
         return Double.NEGATIVE_INFINITY;
      }

      public double m_207401_() {
         return Double.POSITIVE_INFINITY;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208547_;
      }

      public DensityFunction m_207189_() {
         return this.f_208546_;
      }
   }

   protected static enum BlendOffset implements DensityFunction.SimpleFunction {
      INSTANCE;

      public static final Codec<DensityFunction> f_208565_ = Codec.unit(INSTANCE);

      public double m_207386_(DensityFunction.FunctionContext p_208573_) {
         return 0.0D;
      }

      public void m_207362_(double[] p_208575_, DensityFunction.ContextProvider p_208576_) {
         Arrays.fill(p_208575_, 0.0D);
      }

      public double m_207402_() {
         return 0.0D;
      }

      public double m_207401_() {
         return 0.0D;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208565_;
      }
   }

   protected static record Clamp(DensityFunction f_208584_, double f_208585_, double f_208586_) implements DensityFunctions.PureTransformer {
      private static final MapCodec<DensityFunctions.Clamp> f_208587_ = RecordCodecBuilder.mapCodec((p_208597_) -> {
         return p_208597_.group(DensityFunction.f_208216_.fieldOf("input").forGetter(DensityFunctions.Clamp::m_207305_), DensityFunctions.f_208260_.fieldOf("min").forGetter(DensityFunctions.Clamp::m_207402_), DensityFunctions.f_208260_.fieldOf("max").forGetter(DensityFunctions.Clamp::m_207401_)).apply(p_208597_, DensityFunctions.Clamp::new);
      });
      public static final Codec<DensityFunctions.Clamp> f_208583_ = DensityFunctions.m_208279_(f_208587_);

      public double m_207382_(double p_208595_) {
         return Mth.clamp(p_208595_, this.f_208585_, this.f_208586_);
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208599_) {
         return new DensityFunctions.Clamp(this.f_208584_.m_207456_(p_208599_), this.f_208585_, this.f_208586_);
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208583_;
      }

      public DensityFunction m_207305_() {
         return this.f_208584_;
      }

      public double m_207402_() {
         return this.f_208585_;
      }

      public double m_207401_() {
         return this.f_208586_;
      }
   }

   static record Constant(double f_208607_) implements DensityFunction.SimpleFunction {
      static final Codec<DensityFunctions.Constant> f_208608_ = DensityFunctions.m_208275_(DensityFunctions.f_208260_, DensityFunctions.Constant::new, DensityFunctions.Constant::f_208607_);
      static final DensityFunctions.Constant f_208609_ = new DensityFunctions.Constant(0.0D);

      public double m_207386_(DensityFunction.FunctionContext p_208615_) {
         return this.f_208607_;
      }

      public void m_207362_(double[] p_208617_, DensityFunction.ContextProvider p_208618_) {
         Arrays.fill(p_208617_, this.f_208607_);
      }

      public double m_207402_() {
         return this.f_208607_;
      }

      public double m_207401_() {
         return this.f_208607_;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208608_;
      }
   }

   protected static final class EndIslandDensityFunction implements DensityFunction.SimpleFunction {
      public static final Codec<DensityFunctions.EndIslandDensityFunction> f_208626_ = Codec.unit(new DensityFunctions.EndIslandDensityFunction(0L));
      final SimplexNoise f_208627_;

      public EndIslandDensityFunction(long p_208630_) {
         RandomSource randomsource = new LegacyRandomSource(p_208630_);
         randomsource.consumeCount(17292);
         this.f_208627_ = new SimplexNoise(randomsource);
      }

      public double m_207386_(DensityFunction.FunctionContext p_208633_) {
         return ((double)TheEndBiomeSource.getHeightValue(this.f_208627_, p_208633_.m_207115_() / 8, p_208633_.m_207113_() / 8) - 8.0D) / 128.0D;
      }

      public double m_207402_() {
         return -0.84375D;
      }

      public double m_207401_() {
         return 0.5625D;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208626_;
      }
   }

   protected static record HolderHolder(Holder<DensityFunction> f_208636_) implements DensityFunction {
      public double m_207386_(DensityFunction.FunctionContext p_208641_) {
         return this.f_208636_.m_203334_().m_207386_(p_208641_);
      }

      public void m_207362_(double[] p_208645_, DensityFunction.ContextProvider p_208646_) {
         this.f_208636_.m_203334_().m_207362_(p_208645_, p_208646_);
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208643_) {
         return p_208643_.apply(new DensityFunctions.HolderHolder(new Holder.Direct<>(this.f_208636_.m_203334_().m_207456_(p_208643_))));
      }

      public double m_207402_() {
         return this.f_208636_.m_203334_().m_207402_();
      }

      public double m_207401_() {
         return this.f_208636_.m_203334_().m_207401_();
      }

      public Codec<? extends DensityFunction> m_207500_() {
         throw new UnsupportedOperationException("Calling .codec() on HolderHolder");
      }
   }

   protected static record Mapped(DensityFunctions.Mapped.Type f_208654_, DensityFunction f_208655_, double f_208656_, double f_208657_) implements DensityFunctions.PureTransformer {
      public static DensityFunctions.Mapped m_208671_(DensityFunctions.Mapped.Type p_208672_, DensityFunction p_208673_) {
         double d0 = p_208673_.m_207402_();
         double d1 = m_208668_(p_208672_, d0);
         double d2 = m_208668_(p_208672_, p_208673_.m_207401_());
         return p_208672_ != DensityFunctions.Mapped.Type.ABS && p_208672_ != DensityFunctions.Mapped.Type.SQUARE ? new DensityFunctions.Mapped(p_208672_, p_208673_, d1, d2) : new DensityFunctions.Mapped(p_208672_, p_208673_, Math.max(0.0D, d0), Math.max(d1, d2));
      }

      private static double m_208668_(DensityFunctions.Mapped.Type p_208669_, double p_208670_) {
         double d1;
         switch(p_208669_) {
         case ABS:
            d1 = Math.abs(p_208670_);
            break;
         case SQUARE:
            d1 = p_208670_ * p_208670_;
            break;
         case CUBE:
            d1 = p_208670_ * p_208670_ * p_208670_;
            break;
         case HALF_NEGATIVE:
            d1 = p_208670_ > 0.0D ? p_208670_ : p_208670_ * 0.5D;
            break;
         case QUARTER_NEGATIVE:
            d1 = p_208670_ > 0.0D ? p_208670_ : p_208670_ * 0.25D;
            break;
         case SQUEEZE:
            double d0 = Mth.clamp(p_208670_, -1.0D, 1.0D);
            d1 = d0 / 2.0D - d0 * d0 * d0 / 24.0D;
            break;
         default:
            throw new IncompatibleClassChangeError();
         }

         return d1;
      }

      public double m_207382_(double p_208665_) {
         return m_208668_(this.f_208654_, p_208665_);
      }

      public DensityFunctions.Mapped m_207456_(DensityFunction.Visitor p_208677_) {
         return m_208671_(this.f_208654_, this.f_208655_.m_207456_(p_208677_));
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return this.f_208654_.f_208691_;
      }

      public DensityFunction m_207305_() {
         return this.f_208655_;
      }

      public double m_207402_() {
         return this.f_208656_;
      }

      public double m_207401_() {
         return this.f_208657_;
      }

      static enum Type implements StringRepresentable {
         ABS("abs"),
         SQUARE("square"),
         CUBE("cube"),
         HALF_NEGATIVE("half_negative"),
         QUARTER_NEGATIVE("quarter_negative"),
         SQUEEZE("squeeze");

         private final String f_208690_;
         final Codec<DensityFunctions.Mapped> f_208691_ = DensityFunctions.m_208352_((p_208700_) -> {
            return DensityFunctions.Mapped.m_208671_(this, p_208700_);
         }, DensityFunctions.Mapped::m_207305_);

         private Type(String p_208697_) {
            this.f_208690_ = p_208697_;
         }

         public String getSerializedName() {
            return this.f_208690_;
         }
      }
   }

   protected static record Marker(DensityFunctions.Marker.Type f_208705_, DensityFunction f_208706_) implements DensityFunctions.MarkerOrMarked {
      public double m_207386_(DensityFunction.FunctionContext p_208712_) {
         return this.f_208706_.m_207386_(p_208712_);
      }

      public void m_207362_(double[] p_208716_, DensityFunction.ContextProvider p_208717_) {
         this.f_208706_.m_207362_(p_208716_, p_208717_);
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208714_) {
         return p_208714_.apply(new DensityFunctions.Marker(this.f_208705_, this.f_208706_.m_207456_(p_208714_)));
      }

      public double m_207402_() {
         return this.f_208706_.m_207402_();
      }

      public double m_207401_() {
         return this.f_208706_.m_207401_();
      }

      public DensityFunctions.Marker.Type m_207136_() {
         return this.f_208705_;
      }

      public DensityFunction m_207056_() {
         return this.f_208706_;
      }

      static enum Type implements StringRepresentable {
         Interpolated("interpolated"),
         FlatCache("flat_cache"),
         Cache2D("cache_2d"),
         CacheOnce("cache_once"),
         CacheAllInCell("cache_all_in_cell");

         private final String f_208730_;
         final Codec<DensityFunctions.MarkerOrMarked> f_208731_ = DensityFunctions.m_208352_((p_208740_) -> {
            return new DensityFunctions.Marker(this, p_208740_);
         }, DensityFunctions.MarkerOrMarked::m_207056_);

         private Type(String p_208737_) {
            this.f_208730_ = p_208737_;
         }

         public String getSerializedName() {
            return this.f_208730_;
         }
      }
   }

   public interface MarkerOrMarked extends DensityFunction {
      DensityFunctions.Marker.Type m_207136_();

      DensityFunction m_207056_();

      default Codec<? extends DensityFunction> m_207500_() {
         return this.m_207136_().f_208731_;
      }
   }

   static record MulOrAdd(DensityFunctions.MulOrAdd.Type f_208746_, DensityFunction f_208747_, double f_208748_, double f_208749_, double f_208750_) implements DensityFunctions.TwoArgumentSimpleFunction, DensityFunctions.PureTransformer {
      public DensityFunctions.TwoArgumentSimpleFunction.Type m_207119_() {
         return this.f_208746_ == DensityFunctions.MulOrAdd.Type.MUL ? DensityFunctions.TwoArgumentSimpleFunction.Type.MUL : DensityFunctions.TwoArgumentSimpleFunction.Type.ADD;
      }

      public DensityFunction m_207185_() {
         return DensityFunctions.m_208264_(this.f_208750_);
      }

      public DensityFunction m_207190_() {
         return this.f_208747_;
      }

      public double m_207382_(double p_208759_) {
         double d0;
         switch(this.f_208746_) {
         case MUL:
            d0 = p_208759_ * this.f_208750_;
            break;
         case ADD:
            d0 = p_208759_ + this.f_208750_;
            break;
         default:
            throw new IncompatibleClassChangeError();
         }

         return d0;
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208761_) {
         DensityFunction densityfunction = this.f_208747_.m_207456_(p_208761_);
         double d0 = densityfunction.m_207402_();
         double d1 = densityfunction.m_207401_();
         double d2;
         double d3;
         if (this.f_208746_ == DensityFunctions.MulOrAdd.Type.ADD) {
            d2 = d0 + this.f_208750_;
            d3 = d1 + this.f_208750_;
         } else if (this.f_208750_ >= 0.0D) {
            d2 = d0 * this.f_208750_;
            d3 = d1 * this.f_208750_;
         } else {
            d2 = d1 * this.f_208750_;
            d3 = d0 * this.f_208750_;
         }

         return new DensityFunctions.MulOrAdd(this.f_208746_, densityfunction, d2, d3, this.f_208750_);
      }

      public DensityFunction m_207305_() {
         return this.f_208747_;
      }

      public double m_207402_() {
         return this.f_208748_;
      }

      public double m_207401_() {
         return this.f_208749_;
      }

      static enum Type {
         MUL,
         ADD;
      }
   }

   protected static record Noise(Holder<NormalNoise.NoiseParameters> f_208786_, @Nullable NormalNoise f_208787_, double f_208788_, double f_208789_) implements DensityFunction.SimpleFunction {
      public static final MapCodec<DensityFunctions.Noise> f_208784_ = RecordCodecBuilder.mapCodec((p_208798_) -> {
         return p_208798_.group(NormalNoise.NoiseParameters.CODEC.fieldOf("noise").forGetter(DensityFunctions.Noise::f_208786_), Codec.DOUBLE.fieldOf("xz_scale").forGetter(DensityFunctions.Noise::f_208788_), Codec.DOUBLE.fieldOf("y_scale").forGetter(DensityFunctions.Noise::f_208789_)).apply(p_208798_, DensityFunctions.Noise::m_208801_);
      });
      public static final Codec<DensityFunctions.Noise> f_208785_ = DensityFunctions.m_208279_(f_208784_);

      public static DensityFunctions.Noise m_208801_(Holder<NormalNoise.NoiseParameters> p_208802_, @Deprecated double p_208803_, double p_208804_) {
         return new DensityFunctions.Noise(p_208802_, (NormalNoise)null, p_208803_, p_208804_);
      }

      public double m_207386_(DensityFunction.FunctionContext p_208800_) {
         return this.f_208787_ == null ? 0.0D : this.f_208787_.getValue((double)p_208800_.m_207115_() * this.f_208788_, (double)p_208800_.m_207114_() * this.f_208789_, (double)p_208800_.m_207113_() * this.f_208788_);
      }

      public double m_207402_() {
         return -this.m_207401_();
      }

      public double m_207401_() {
         return this.f_208787_ == null ? 2.0D : this.f_208787_.m_210630_();
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208785_;
      }
   }

   interface PureTransformer extends DensityFunction {
      DensityFunction m_207305_();

      default double m_207386_(DensityFunction.FunctionContext p_208817_) {
         return this.m_207382_(this.m_207305_().m_207386_(p_208817_));
      }

      default void m_207362_(double[] p_208819_, DensityFunction.ContextProvider p_208820_) {
         this.m_207305_().m_207362_(p_208819_, p_208820_);

         for(int i = 0; i < p_208819_.length; ++i) {
            p_208819_[i] = this.m_207382_(p_208819_[i]);
         }

      }

      double m_207382_(double p_208815_);
   }

   static record RangeChoice(DensityFunction f_208823_, double f_208824_, double f_208825_, DensityFunction f_208826_, DensityFunction f_208827_) implements DensityFunction {
      public static final MapCodec<DensityFunctions.RangeChoice> f_208821_ = RecordCodecBuilder.mapCodec((p_208837_) -> {
         return p_208837_.group(DensityFunction.f_208218_.fieldOf("input").forGetter(DensityFunctions.RangeChoice::f_208823_), DensityFunctions.f_208260_.fieldOf("min_inclusive").forGetter(DensityFunctions.RangeChoice::f_208824_), DensityFunctions.f_208260_.fieldOf("max_exclusive").forGetter(DensityFunctions.RangeChoice::f_208825_), DensityFunction.f_208218_.fieldOf("when_in_range").forGetter(DensityFunctions.RangeChoice::f_208826_), DensityFunction.f_208218_.fieldOf("when_out_of_range").forGetter(DensityFunctions.RangeChoice::f_208827_)).apply(p_208837_, DensityFunctions.RangeChoice::new);
      });
      public static final Codec<DensityFunctions.RangeChoice> f_208822_ = DensityFunctions.m_208279_(f_208821_);

      public double m_207386_(DensityFunction.FunctionContext p_208839_) {
         double d0 = this.f_208823_.m_207386_(p_208839_);
         return d0 >= this.f_208824_ && d0 < this.f_208825_ ? this.f_208826_.m_207386_(p_208839_) : this.f_208827_.m_207386_(p_208839_);
      }

      public void m_207362_(double[] p_208843_, DensityFunction.ContextProvider p_208844_) {
         this.f_208823_.m_207362_(p_208843_, p_208844_);

         for(int i = 0; i < p_208843_.length; ++i) {
            double d0 = p_208843_[i];
            if (d0 >= this.f_208824_ && d0 < this.f_208825_) {
               p_208843_[i] = this.f_208826_.m_207386_(p_208844_.m_207263_(i));
            } else {
               p_208843_[i] = this.f_208827_.m_207386_(p_208844_.m_207263_(i));
            }
         }

      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208841_) {
         return p_208841_.apply(new DensityFunctions.RangeChoice(this.f_208823_.m_207456_(p_208841_), this.f_208824_, this.f_208825_, this.f_208826_.m_207456_(p_208841_), this.f_208827_.m_207456_(p_208841_)));
      }

      public double m_207402_() {
         return Math.min(this.f_208826_.m_207402_(), this.f_208827_.m_207402_());
      }

      public double m_207401_() {
         return Math.max(this.f_208826_.m_207401_(), this.f_208827_.m_207401_());
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208822_;
      }
   }

   static record Shift(Holder<NormalNoise.NoiseParameters> f_208856_, @Nullable NormalNoise f_208857_) implements DensityFunctions.ShiftNoise {
      static final Codec<DensityFunctions.Shift> f_208858_ = DensityFunctions.m_208275_(NormalNoise.NoiseParameters.CODEC, (p_208868_) -> {
         return new DensityFunctions.Shift(p_208868_, (NormalNoise)null);
      }, DensityFunctions.Shift::m_207183_);

      public double m_207386_(DensityFunction.FunctionContext p_208864_) {
         return this.m_208917_((double)p_208864_.m_207115_(), (double)p_208864_.m_207114_(), (double)p_208864_.m_207113_());
      }

      public DensityFunctions.ShiftNoise m_207360_(NormalNoise p_208866_) {
         return new DensityFunctions.Shift(this.f_208856_, p_208866_);
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208858_;
      }

      public Holder<NormalNoise.NoiseParameters> m_207183_() {
         return this.f_208856_;
      }

      @Nullable
      public NormalNoise m_207488_() {
         return this.f_208857_;
      }
   }

   protected static record ShiftA(Holder<NormalNoise.NoiseParameters> f_208876_, @Nullable NormalNoise f_208877_) implements DensityFunctions.ShiftNoise {
      static final Codec<DensityFunctions.ShiftA> f_208878_ = DensityFunctions.m_208275_(NormalNoise.NoiseParameters.CODEC, (p_208888_) -> {
         return new DensityFunctions.ShiftA(p_208888_, (NormalNoise)null);
      }, DensityFunctions.ShiftA::m_207183_);

      public double m_207386_(DensityFunction.FunctionContext p_208884_) {
         return this.m_208917_((double)p_208884_.m_207115_(), 0.0D, (double)p_208884_.m_207113_());
      }

      public DensityFunctions.ShiftNoise m_207360_(NormalNoise p_208886_) {
         return new DensityFunctions.ShiftA(this.f_208876_, p_208886_);
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208878_;
      }

      public Holder<NormalNoise.NoiseParameters> m_207183_() {
         return this.f_208876_;
      }

      @Nullable
      public NormalNoise m_207488_() {
         return this.f_208877_;
      }
   }

   protected static record ShiftB(Holder<NormalNoise.NoiseParameters> f_208896_, @Nullable NormalNoise f_208897_) implements DensityFunctions.ShiftNoise {
      static final Codec<DensityFunctions.ShiftB> f_208898_ = DensityFunctions.m_208275_(NormalNoise.NoiseParameters.CODEC, (p_208908_) -> {
         return new DensityFunctions.ShiftB(p_208908_, (NormalNoise)null);
      }, DensityFunctions.ShiftB::m_207183_);

      public double m_207386_(DensityFunction.FunctionContext p_208904_) {
         return this.m_208917_((double)p_208904_.m_207113_(), (double)p_208904_.m_207115_(), 0.0D);
      }

      public DensityFunctions.ShiftNoise m_207360_(NormalNoise p_208906_) {
         return new DensityFunctions.ShiftB(this.f_208896_, p_208906_);
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208898_;
      }

      public Holder<NormalNoise.NoiseParameters> m_207183_() {
         return this.f_208896_;
      }

      @Nullable
      public NormalNoise m_207488_() {
         return this.f_208897_;
      }
   }

   interface ShiftNoise extends DensityFunction.SimpleFunction {
      Holder<NormalNoise.NoiseParameters> m_207183_();

      @Nullable
      NormalNoise m_207488_();

      default double m_207402_() {
         return -this.m_207401_();
      }

      default double m_207401_() {
         NormalNoise normalnoise = this.m_207488_();
         return (normalnoise == null ? 2.0D : normalnoise.m_210630_()) * 4.0D;
      }

      default double m_208917_(double p_208918_, double p_208919_, double p_208920_) {
         NormalNoise normalnoise = this.m_207488_();
         return normalnoise == null ? 0.0D : normalnoise.getValue(p_208918_ * 0.25D, p_208919_ * 0.25D, p_208920_ * 0.25D) * 4.0D;
      }

      DensityFunctions.ShiftNoise m_207360_(NormalNoise p_208921_);
   }

   protected static record ShiftedNoise(DensityFunction f_208924_, DensityFunction f_208925_, DensityFunction f_208926_, double f_208927_, double f_208928_, Holder<NormalNoise.NoiseParameters> f_208929_, @Nullable NormalNoise f_208930_) implements DensityFunction {
      private static final MapCodec<DensityFunctions.ShiftedNoise> f_208931_ = RecordCodecBuilder.mapCodec((p_208943_) -> {
         return p_208943_.group(DensityFunction.f_208218_.fieldOf("shift_x").forGetter(DensityFunctions.ShiftedNoise::f_208924_), DensityFunction.f_208218_.fieldOf("shift_y").forGetter(DensityFunctions.ShiftedNoise::f_208925_), DensityFunction.f_208218_.fieldOf("shift_z").forGetter(DensityFunctions.ShiftedNoise::f_208926_), Codec.DOUBLE.fieldOf("xz_scale").forGetter(DensityFunctions.ShiftedNoise::f_208927_), Codec.DOUBLE.fieldOf("y_scale").forGetter(DensityFunctions.ShiftedNoise::f_208928_), NormalNoise.NoiseParameters.CODEC.fieldOf("noise").forGetter(DensityFunctions.ShiftedNoise::f_208929_)).apply(p_208943_, DensityFunctions.ShiftedNoise::m_208948_);
      });
      public static final Codec<DensityFunctions.ShiftedNoise> f_208923_ = DensityFunctions.m_208279_(f_208931_);

      public static DensityFunctions.ShiftedNoise m_208948_(DensityFunction p_208949_, DensityFunction p_208950_, DensityFunction p_208951_, double p_208952_, double p_208953_, Holder<NormalNoise.NoiseParameters> p_208954_) {
         return new DensityFunctions.ShiftedNoise(p_208949_, p_208950_, p_208951_, p_208952_, p_208953_, p_208954_, (NormalNoise)null);
      }

      public double m_207386_(DensityFunction.FunctionContext p_208945_) {
         if (this.f_208930_ == null) {
            return 0.0D;
         } else {
            double d0 = (double)p_208945_.m_207115_() * this.f_208927_ + this.f_208924_.m_207386_(p_208945_);
            double d1 = (double)p_208945_.m_207114_() * this.f_208928_ + this.f_208925_.m_207386_(p_208945_);
            double d2 = (double)p_208945_.m_207113_() * this.f_208927_ + this.f_208926_.m_207386_(p_208945_);
            return this.f_208930_.getValue(d0, d1, d2);
         }
      }

      public void m_207362_(double[] p_208956_, DensityFunction.ContextProvider p_208957_) {
         p_208957_.m_207207_(p_208956_, this);
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208947_) {
         return p_208947_.apply(new DensityFunctions.ShiftedNoise(this.f_208924_.m_207456_(p_208947_), this.f_208925_.m_207456_(p_208947_), this.f_208926_.m_207456_(p_208947_), this.f_208927_, this.f_208928_, this.f_208929_, this.f_208930_));
      }

      public double m_207402_() {
         return -this.m_207401_();
      }

      public double m_207401_() {
         return this.f_208930_ == null ? 2.0D : this.f_208930_.m_210630_();
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208923_;
      }
   }

   protected static record Slide(@Nullable NoiseSettings f_208972_, DensityFunction f_208973_) implements DensityFunctions.TransformerWithContext {
      public static final Codec<DensityFunctions.Slide> f_208971_ = DensityFunctions.m_208352_((p_208985_) -> {
         return new DensityFunctions.Slide((NoiseSettings)null, p_208985_);
      }, DensityFunctions.Slide::m_207189_);

      public double m_207219_(DensityFunction.FunctionContext p_208980_, double p_208981_) {
         return this.f_208972_ == null ? p_208981_ : NoiseRouterData.m_209498_(this.f_208972_, p_208981_, (double)p_208980_.m_207114_());
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208983_) {
         return p_208983_.apply(new DensityFunctions.Slide(this.f_208972_, this.f_208973_.m_207456_(p_208983_)));
      }

      public double m_207402_() {
         return this.f_208972_ == null ? this.f_208973_.m_207402_() : Math.min(this.f_208973_.m_207402_(), Math.min(this.f_208972_.bottomSlideSettings().target(), this.f_208972_.topSlideSettings().target()));
      }

      public double m_207401_() {
         return this.f_208972_ == null ? this.f_208973_.m_207401_() : Math.max(this.f_208973_.m_207401_(), Math.max(this.f_208972_.bottomSlideSettings().target(), this.f_208972_.topSlideSettings().target()));
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208971_;
      }

      public DensityFunction m_207189_() {
         return this.f_208973_;
      }
   }

   public static record Spline(CubicSpline<TerrainShaper.PointCustom> f_211702_, double f_211703_, double f_211704_) implements DensityFunction {
      private static final MapCodec<DensityFunctions.Spline> f_211705_ = RecordCodecBuilder.mapCodec((p_211713_) -> {
         return p_211713_.group(TerrainShaper.f_211598_.fieldOf("spline").forGetter(DensityFunctions.Spline::f_211702_), DensityFunctions.f_208260_.fieldOf("min_value").forGetter(DensityFunctions.Spline::m_207402_), DensityFunctions.f_208260_.fieldOf("max_value").forGetter(DensityFunctions.Spline::m_207401_)).apply(p_211713_, DensityFunctions.Spline::new);
      });
      public static final Codec<DensityFunctions.Spline> f_211701_ = DensityFunctions.m_208279_(f_211705_);

      public double m_207386_(DensityFunction.FunctionContext p_211715_) {
         return Mth.clamp((double)this.f_211702_.apply(TerrainShaper.m_211599_(p_211715_)), this.f_211703_, this.f_211704_);
      }

      public void m_207362_(double[] p_211722_, DensityFunction.ContextProvider p_211723_) {
         p_211723_.m_207207_(p_211722_, this);
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_211717_) {
         return p_211717_.apply(new DensityFunctions.Spline(this.f_211702_.m_211396_((p_211720_) -> {
            Object object;
            if (p_211720_ instanceof TerrainShaper.CoordinateCustom) {
               TerrainShaper.CoordinateCustom terrainshaper$coordinatecustom = (TerrainShaper.CoordinateCustom)p_211720_;
               object = terrainshaper$coordinatecustom.m_211611_(p_211717_);
            } else {
               object = p_211720_;
            }

            return (ToFloatFunction<TerrainShaper.PointCustom>)object;
         }), this.f_211703_, this.f_211704_));
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_211701_;
      }

      public double m_207402_() {
         return this.f_211703_;
      }

      public double m_207401_() {
         return this.f_211704_;
      }
   }

   /** @deprecated */
   @Deprecated
   public static record TerrainShaperSpline(DensityFunction f_208995_, DensityFunction f_208996_, DensityFunction f_208997_, @Nullable TerrainShaper f_208998_, DensityFunctions.TerrainShaperSpline.SplineType f_208999_, double f_209000_, double f_209001_) implements DensityFunction {
      private static final MapCodec<DensityFunctions.TerrainShaperSpline> f_209002_ = RecordCodecBuilder.mapCodec((p_209014_) -> {
         return p_209014_.group(DensityFunction.f_208218_.fieldOf("continentalness").forGetter(DensityFunctions.TerrainShaperSpline::f_208995_), DensityFunction.f_208218_.fieldOf("erosion").forGetter(DensityFunctions.TerrainShaperSpline::f_208996_), DensityFunction.f_208218_.fieldOf("weirdness").forGetter(DensityFunctions.TerrainShaperSpline::f_208997_), DensityFunctions.TerrainShaperSpline.SplineType.f_209046_.fieldOf("spline").forGetter(DensityFunctions.TerrainShaperSpline::f_208999_), DensityFunctions.f_208260_.fieldOf("min_value").forGetter(DensityFunctions.TerrainShaperSpline::m_207402_), DensityFunctions.f_208260_.fieldOf("max_value").forGetter(DensityFunctions.TerrainShaperSpline::m_207401_)).apply(p_209014_, DensityFunctions.TerrainShaperSpline::m_209019_);
      });
      public static final Codec<DensityFunctions.TerrainShaperSpline> f_208994_ = DensityFunctions.m_208279_(f_209002_);

      public static DensityFunctions.TerrainShaperSpline m_209019_(DensityFunction p_209020_, DensityFunction p_209021_, DensityFunction p_209022_, DensityFunctions.TerrainShaperSpline.SplineType p_209023_, double p_209024_, double p_209025_) {
         return new DensityFunctions.TerrainShaperSpline(p_209020_, p_209021_, p_209022_, (TerrainShaper)null, p_209023_, p_209024_, p_209025_);
      }

      public double m_207386_(DensityFunction.FunctionContext p_209016_) {
         return this.f_208998_ == null ? 0.0D : Mth.clamp((double)this.f_208999_.f_209049_.m_209040_(this.f_208998_, TerrainShaper.makePoint((float)this.f_208995_.m_207386_(p_209016_), (float)this.f_208996_.m_207386_(p_209016_), (float)this.f_208997_.m_207386_(p_209016_))), this.f_209000_, this.f_209001_);
      }

      public void m_207362_(double[] p_209027_, DensityFunction.ContextProvider p_209028_) {
         for(int i = 0; i < p_209027_.length; ++i) {
            p_209027_[i] = this.m_207386_(p_209028_.m_207263_(i));
         }

      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_209018_) {
         return p_209018_.apply(new DensityFunctions.TerrainShaperSpline(this.f_208995_.m_207456_(p_209018_), this.f_208996_.m_207456_(p_209018_), this.f_208997_.m_207456_(p_209018_), this.f_208998_, this.f_208999_, this.f_209000_, this.f_209001_));
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208994_;
      }

      public double m_207402_() {
         return this.f_209000_;
      }

      public double m_207401_() {
         return this.f_209001_;
      }

      interface Spline {
         float m_209040_(TerrainShaper p_209041_, TerrainShaper.Point p_209042_);
      }

      public static enum SplineType implements StringRepresentable {
         OFFSET("offset", TerrainShaper::offset),
         FACTOR("factor", TerrainShaper::factor),
         JAGGEDNESS("jaggedness", TerrainShaper::jaggedness);

         private static final Map<String, DensityFunctions.TerrainShaperSpline.SplineType> f_209047_ = Arrays.stream(values()).collect(Collectors.toMap(DensityFunctions.TerrainShaperSpline.SplineType::getSerializedName, (p_209059_) -> {
            return p_209059_;
         }));
         public static final Codec<DensityFunctions.TerrainShaperSpline.SplineType> f_209046_ = StringRepresentable.fromEnum(DensityFunctions.TerrainShaperSpline.SplineType::values, f_209047_::get);
         private final String f_209048_;
         final DensityFunctions.TerrainShaperSpline.Spline f_209049_;

         private SplineType(String p_209055_, DensityFunctions.TerrainShaperSpline.Spline p_209056_) {
            this.f_209048_ = p_209055_;
            this.f_209049_ = p_209056_;
         }

         public String getSerializedName() {
            return this.f_209048_;
         }
      }
   }

   interface TransformerWithContext extends DensityFunction {
      DensityFunction m_207189_();

      default double m_207386_(DensityFunction.FunctionContext p_209065_) {
         return this.m_207219_(p_209065_, this.m_207189_().m_207386_(p_209065_));
      }

      default void m_207362_(double[] p_209069_, DensityFunction.ContextProvider p_209070_) {
         this.m_207189_().m_207362_(p_209069_, p_209070_);

         for(int i = 0; i < p_209069_.length; ++i) {
            p_209069_[i] = this.m_207219_(p_209070_.m_207263_(i), p_209069_[i]);
         }

      }

      double m_207219_(DensityFunction.FunctionContext p_209066_, double p_209067_);
   }

   interface TwoArgumentSimpleFunction extends DensityFunction {
      Logger f_209071_ = LogUtils.getLogger();

      static DensityFunctions.TwoArgumentSimpleFunction m_209073_(DensityFunctions.TwoArgumentSimpleFunction.Type p_209074_, DensityFunction p_209075_, DensityFunction p_209076_) {
         double d0 = p_209075_.m_207402_();
         double d1 = p_209076_.m_207402_();
         double d2 = p_209075_.m_207401_();
         double d3 = p_209076_.m_207401_();
         if (p_209074_ == DensityFunctions.TwoArgumentSimpleFunction.Type.MIN || p_209074_ == DensityFunctions.TwoArgumentSimpleFunction.Type.MAX) {
            boolean flag = d0 >= d3;
            boolean flag1 = d1 >= d2;
            if (flag || flag1) {
               f_209071_.warn("Creating a " + p_209074_ + " function between two non-overlapping inputs: " + p_209075_ + " and " + p_209076_);
            }
         }

         double d6;
         switch(p_209074_) {
         case ADD:
            d6 = d0 + d1;
            break;
         case MAX:
            d6 = Math.max(d0, d1);
            break;
         case MIN:
            d6 = Math.min(d0, d1);
            break;
         case MUL:
            d6 = d0 > 0.0D && d1 > 0.0D ? d0 * d1 : (d2 < 0.0D && d3 < 0.0D ? d2 * d3 : Math.min(d0 * d3, d2 * d1));
            break;
         default:
            throw new IncompatibleClassChangeError();
         }

         double d5 = d6;
         switch(p_209074_) {
         case ADD:
            d6 = d2 + d3;
            break;
         case MAX:
            d6 = Math.max(d2, d3);
            break;
         case MIN:
            d6 = Math.min(d2, d3);
            break;
         case MUL:
            d6 = d0 > 0.0D && d1 > 0.0D ? d2 * d3 : (d2 < 0.0D && d3 < 0.0D ? d0 * d1 : Math.max(d0 * d1, d2 * d3));
            break;
         default:
            throw new IncompatibleClassChangeError();
         }

         double d4 = d6;
         if (p_209074_ == DensityFunctions.TwoArgumentSimpleFunction.Type.MUL || p_209074_ == DensityFunctions.TwoArgumentSimpleFunction.Type.ADD) {
            if (p_209075_ instanceof DensityFunctions.Constant) {
               DensityFunctions.Constant densityfunctions$constant1 = (DensityFunctions.Constant)p_209075_;
               return new DensityFunctions.MulOrAdd(p_209074_ == DensityFunctions.TwoArgumentSimpleFunction.Type.ADD ? DensityFunctions.MulOrAdd.Type.ADD : DensityFunctions.MulOrAdd.Type.MUL, p_209076_, d5, d4, densityfunctions$constant1.f_208607_);
            }

            if (p_209076_ instanceof DensityFunctions.Constant) {
               DensityFunctions.Constant densityfunctions$constant = (DensityFunctions.Constant)p_209076_;
               return new DensityFunctions.MulOrAdd(p_209074_ == DensityFunctions.TwoArgumentSimpleFunction.Type.ADD ? DensityFunctions.MulOrAdd.Type.ADD : DensityFunctions.MulOrAdd.Type.MUL, p_209075_, d5, d4, densityfunctions$constant.f_208607_);
            }
         }

         return new DensityFunctions.Ap2(p_209074_, p_209075_, p_209076_, d5, d4);
      }

      DensityFunctions.TwoArgumentSimpleFunction.Type m_207119_();

      DensityFunction m_207185_();

      DensityFunction m_207190_();

      default Codec<? extends DensityFunction> m_207500_() {
         return this.m_207119_().f_209082_;
      }

      public static enum Type implements StringRepresentable {
         ADD("add"),
         MUL("mul"),
         MIN("min"),
         MAX("max");

         final Codec<DensityFunctions.TwoArgumentSimpleFunction> f_209082_ = DensityFunctions.m_208348_((p_209092_, p_209093_) -> {
            return DensityFunctions.TwoArgumentSimpleFunction.m_209073_(this, p_209092_, p_209093_);
         }, DensityFunctions.TwoArgumentSimpleFunction::m_207185_, DensityFunctions.TwoArgumentSimpleFunction::m_207190_);
         private final String f_209083_;

         private Type(String p_209089_) {
            this.f_209083_ = p_209089_;
         }

         public String getSerializedName() {
            return this.f_209083_;
         }
      }
   }

   protected static record WeirdScaledSampler(DensityFunction f_208425_, Holder<NormalNoise.NoiseParameters> f_208426_, @Nullable NormalNoise f_208427_, DensityFunctions.WeirdScaledSampler.RarityValueMapper f_208428_) implements DensityFunctions.TransformerWithContext {
      private static final MapCodec<DensityFunctions.WeirdScaledSampler> f_208429_ = RecordCodecBuilder.mapCodec((p_208438_) -> {
         return p_208438_.group(DensityFunction.f_208218_.fieldOf("input").forGetter(DensityFunctions.WeirdScaledSampler::m_207189_), NormalNoise.NoiseParameters.CODEC.fieldOf("noise").forGetter(DensityFunctions.WeirdScaledSampler::f_208426_), DensityFunctions.WeirdScaledSampler.RarityValueMapper.f_208460_.fieldOf("rarity_value_mapper").forGetter(DensityFunctions.WeirdScaledSampler::f_208428_)).apply(p_208438_, DensityFunctions.WeirdScaledSampler::m_208444_);
      });
      public static final Codec<DensityFunctions.WeirdScaledSampler> f_208424_ = DensityFunctions.m_208279_(f_208429_);

      public static DensityFunctions.WeirdScaledSampler m_208444_(DensityFunction p_208445_, Holder<NormalNoise.NoiseParameters> p_208446_, DensityFunctions.WeirdScaledSampler.RarityValueMapper p_208447_) {
         return new DensityFunctions.WeirdScaledSampler(p_208445_, p_208446_, (NormalNoise)null, p_208447_);
      }

      public double m_207219_(DensityFunction.FunctionContext p_208440_, double p_208441_) {
         if (this.f_208427_ == null) {
            return 0.0D;
         } else {
            double d0 = this.f_208428_.f_208463_.get(p_208441_);
            return d0 * Math.abs(this.f_208427_.getValue((double)p_208440_.m_207115_() / d0, (double)p_208440_.m_207114_() / d0, (double)p_208440_.m_207113_() / d0));
         }
      }

      public DensityFunction m_207456_(DensityFunction.Visitor p_208443_) {
         this.f_208425_.m_207456_(p_208443_);
         return p_208443_.apply(new DensityFunctions.WeirdScaledSampler(this.f_208425_.m_207456_(p_208443_), this.f_208426_, this.f_208427_, this.f_208428_));
      }

      public double m_207402_() {
         return 0.0D;
      }

      public double m_207401_() {
         return this.f_208428_.f_208464_ * (this.f_208427_ == null ? 2.0D : this.f_208427_.m_210630_());
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208424_;
      }

      public DensityFunction m_207189_() {
         return this.f_208425_;
      }

      public static enum RarityValueMapper implements StringRepresentable {
         TYPE1("type_1", NoiseRouterData.QuantizedSpaghettiRarity::m_209565_, 2.0D),
         TYPE2("type_2", NoiseRouterData.QuantizedSpaghettiRarity::m_209563_, 3.0D);

         private static final Map<String, DensityFunctions.WeirdScaledSampler.RarityValueMapper> f_208461_ = Arrays.stream(values()).collect(Collectors.toMap(DensityFunctions.WeirdScaledSampler.RarityValueMapper::getSerializedName, (p_208475_) -> {
            return p_208475_;
         }));
         public static final Codec<DensityFunctions.WeirdScaledSampler.RarityValueMapper> f_208460_ = StringRepresentable.fromEnum(DensityFunctions.WeirdScaledSampler.RarityValueMapper::values, f_208461_::get);
         private final String f_208462_;
         final Double2DoubleFunction f_208463_;
         final double f_208464_;

         private RarityValueMapper(String p_208470_, Double2DoubleFunction p_208471_, double p_208472_) {
            this.f_208462_ = p_208470_;
            this.f_208463_ = p_208471_;
            this.f_208464_ = p_208472_;
         }

         public String getSerializedName() {
            return this.f_208462_;
         }
      }
   }

   static record YClampedGradient(int f_208481_, int f_208482_, double f_208483_, double f_208484_) implements DensityFunction.SimpleFunction {
      private static final MapCodec<DensityFunctions.YClampedGradient> f_208485_ = RecordCodecBuilder.mapCodec((p_208494_) -> {
         return p_208494_.group(Codec.intRange(DimensionType.MIN_Y * 2, DimensionType.MAX_Y * 2).fieldOf("from_y").forGetter(DensityFunctions.YClampedGradient::f_208481_), Codec.intRange(DimensionType.MIN_Y * 2, DimensionType.MAX_Y * 2).fieldOf("to_y").forGetter(DensityFunctions.YClampedGradient::f_208482_), DensityFunctions.f_208260_.fieldOf("from_value").forGetter(DensityFunctions.YClampedGradient::f_208483_), DensityFunctions.f_208260_.fieldOf("to_value").forGetter(DensityFunctions.YClampedGradient::f_208484_)).apply(p_208494_, DensityFunctions.YClampedGradient::new);
      });
      public static final Codec<DensityFunctions.YClampedGradient> f_208480_ = DensityFunctions.m_208279_(f_208485_);

      public double m_207386_(DensityFunction.FunctionContext p_208496_) {
         return Mth.clampedMap((double)p_208496_.m_207114_(), (double)this.f_208481_, (double)this.f_208482_, this.f_208483_, this.f_208484_);
      }

      public double m_207402_() {
         return Math.min(this.f_208483_, this.f_208484_);
      }

      public double m_207401_() {
         return Math.max(this.f_208483_, this.f_208484_);
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return f_208480_;
      }
   }
}