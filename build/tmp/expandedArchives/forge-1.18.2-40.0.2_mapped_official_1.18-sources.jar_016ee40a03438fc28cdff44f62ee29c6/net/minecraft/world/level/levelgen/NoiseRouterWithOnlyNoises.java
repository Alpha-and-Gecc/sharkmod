package net.minecraft.world.level.levelgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Function;

public record NoiseRouterWithOnlyNoises(DensityFunction f_209568_, DensityFunction f_209569_, DensityFunction f_209570_, DensityFunction f_209571_, DensityFunction f_209572_, DensityFunction f_209573_, DensityFunction f_209574_, DensityFunction f_209575_, DensityFunction f_209576_, DensityFunction f_209577_, DensityFunction f_209578_, DensityFunction f_209579_, DensityFunction f_209580_, DensityFunction f_209581_, DensityFunction f_209582_) {
   public static final Codec<NoiseRouterWithOnlyNoises> f_209567_ = RecordCodecBuilder.create((p_209602_) -> {
      return p_209602_.group(m_209605_("barrier", NoiseRouterWithOnlyNoises::f_209568_), m_209605_("fluid_level_floodedness", NoiseRouterWithOnlyNoises::f_209569_), m_209605_("fluid_level_spread", NoiseRouterWithOnlyNoises::f_209570_), m_209605_("lava", NoiseRouterWithOnlyNoises::f_209571_), m_209605_("temperature", NoiseRouterWithOnlyNoises::f_209572_), m_209605_("vegetation", NoiseRouterWithOnlyNoises::f_209573_), m_209605_("continents", NoiseRouterWithOnlyNoises::f_209574_), m_209605_("erosion", NoiseRouterWithOnlyNoises::f_209575_), m_209605_("depth", NoiseRouterWithOnlyNoises::f_209576_), m_209605_("ridges", NoiseRouterWithOnlyNoises::f_209577_), m_209605_("initial_density_without_jaggedness", NoiseRouterWithOnlyNoises::f_209578_), m_209605_("final_density", NoiseRouterWithOnlyNoises::f_209579_), m_209605_("vein_toggle", NoiseRouterWithOnlyNoises::f_209580_), m_209605_("vein_ridged", NoiseRouterWithOnlyNoises::f_209581_), m_209605_("vein_gap", NoiseRouterWithOnlyNoises::f_209582_)).apply(p_209602_, NoiseRouterWithOnlyNoises::new);
   });

   private static RecordCodecBuilder<NoiseRouterWithOnlyNoises, DensityFunction> m_209605_(String p_209606_, Function<NoiseRouterWithOnlyNoises, DensityFunction> p_209607_) {
      return DensityFunction.f_208218_.fieldOf(p_209606_).forGetter(p_209607_);
   }

   public NoiseRouterWithOnlyNoises m_209603_(DensityFunction.Visitor p_209604_) {
      return new NoiseRouterWithOnlyNoises(this.f_209568_.m_207456_(p_209604_), this.f_209569_.m_207456_(p_209604_), this.f_209570_.m_207456_(p_209604_), this.f_209571_.m_207456_(p_209604_), this.f_209572_.m_207456_(p_209604_), this.f_209573_.m_207456_(p_209604_), this.f_209574_.m_207456_(p_209604_), this.f_209575_.m_207456_(p_209604_), this.f_209576_.m_207456_(p_209604_), this.f_209577_.m_207456_(p_209604_), this.f_209578_.m_207456_(p_209604_), this.f_209579_.m_207456_(p_209604_), this.f_209580_.m_207456_(p_209604_), this.f_209581_.m_207456_(p_209604_), this.f_209582_.m_207456_(p_209604_));
   }
}