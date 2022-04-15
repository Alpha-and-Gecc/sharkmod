package net.minecraft.world.level.levelgen;

import com.mojang.serialization.Codec;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.level.levelgen.blending.Blender;

public interface DensityFunction {
   Codec<DensityFunction> f_208216_ = DensityFunctions.f_208258_;
   Codec<Holder<DensityFunction>> f_208217_ = RegistryFileCodec.create(Registry.f_211074_, f_208216_);
   Codec<DensityFunction> f_208218_ = f_208217_.xmap(DensityFunctions.HolderHolder::new, (p_208226_) -> {
      if (p_208226_ instanceof DensityFunctions.HolderHolder) {
         DensityFunctions.HolderHolder densityfunctions$holderholder = (DensityFunctions.HolderHolder)p_208226_;
         return densityfunctions$holderholder.f_208636_();
      } else {
         return new Holder.Direct<>(p_208226_);
      }
   });

   double m_207386_(DensityFunction.FunctionContext p_208223_);

   void m_207362_(double[] p_208227_, DensityFunction.ContextProvider p_208228_);

   DensityFunction m_207456_(DensityFunction.Visitor p_208224_);

   double m_207402_();

   double m_207401_();

   Codec<? extends DensityFunction> m_207500_();

   default DensityFunction m_208220_(double p_208221_, double p_208222_) {
      return new DensityFunctions.Clamp(this, p_208221_, p_208222_);
   }

   default DensityFunction m_208229_() {
      return DensityFunctions.m_208312_(this, DensityFunctions.Mapped.Type.ABS);
   }

   default DensityFunction m_208230_() {
      return DensityFunctions.m_208312_(this, DensityFunctions.Mapped.Type.SQUARE);
   }

   default DensityFunction m_208231_() {
      return DensityFunctions.m_208312_(this, DensityFunctions.Mapped.Type.CUBE);
   }

   default DensityFunction m_208232_() {
      return DensityFunctions.m_208312_(this, DensityFunctions.Mapped.Type.HALF_NEGATIVE);
   }

   default DensityFunction m_208233_() {
      return DensityFunctions.m_208312_(this, DensityFunctions.Mapped.Type.QUARTER_NEGATIVE);
   }

   default DensityFunction m_208234_() {
      return DensityFunctions.m_208312_(this, DensityFunctions.Mapped.Type.SQUEEZE);
   }

   public interface ContextProvider {
      DensityFunction.FunctionContext m_207263_(int p_208235_);

      void m_207207_(double[] p_208236_, DensityFunction p_208237_);
   }

   public interface FunctionContext {
      int m_207115_();

      int m_207114_();

      int m_207113_();

      default Blender m_207434_() {
         return Blender.empty();
      }
   }

   public interface SimpleFunction extends DensityFunction {
      default void m_207362_(double[] p_208241_, DensityFunction.ContextProvider p_208242_) {
         p_208242_.m_207207_(p_208241_, this);
      }

      default DensityFunction m_207456_(DensityFunction.Visitor p_208239_) {
         return p_208239_.apply(this);
      }
   }

   public static record SinglePointContext(int f_208243_, int f_208244_, int f_208245_) implements DensityFunction.FunctionContext {
      public int m_207115_() {
         return this.f_208243_;
      }

      public int m_207114_() {
         return this.f_208244_;
      }

      public int m_207113_() {
         return this.f_208245_;
      }
   }

   public interface Visitor extends Function<DensityFunction, DensityFunction> {
   }
}