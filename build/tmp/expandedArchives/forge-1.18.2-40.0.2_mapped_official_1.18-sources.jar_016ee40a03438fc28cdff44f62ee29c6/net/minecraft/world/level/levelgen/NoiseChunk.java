package net.minecraft.world.level.levelgen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableList.Builder;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.longs.Long2IntMap;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.material.MaterialRuleList;

public class NoiseChunk implements DensityFunction.ContextProvider, DensityFunction.FunctionContext {
   private final NoiseSettings noiseSettings;
   final int cellCountXZ;
   final int cellCountY;
   final int cellNoiseMinY;
   private final int firstCellX;
   private final int firstCellZ;
   final int firstNoiseX;
   final int firstNoiseZ;
   final List<NoiseChunk.NoiseInterpolator> interpolators;
   final List<NoiseChunk.CacheAllInCell> f_209160_;
   private final Map<DensityFunction, DensityFunction> f_209161_ = new HashMap<>();
   private final Long2IntMap preliminarySurfaceLevel = new Long2IntOpenHashMap();
   private final Aquifer aquifer;
   private final DensityFunction f_209162_;
   private final NoiseChunk.BlockStateFiller f_209163_;
   private final Blender blender;
   private final NoiseChunk.FlatCache f_209164_;
   private final NoiseChunk.FlatCache f_209165_;
   private final DensityFunctions.BeardifierOrMarker f_209166_;
   private long f_209167_ = ChunkPos.INVALID_CHUNK_POS;
   private Blender.BlendingOutput f_209168_ = new Blender.BlendingOutput(1.0D, 0.0D);
   final int f_209169_;
   final int f_209170_;
   final int f_209171_;
   boolean f_209172_;
   boolean f_209173_;
   private int f_209150_;
   int f_209151_;
   private int f_209152_;
   int f_209153_;
   int f_209154_;
   int f_209155_;
   long f_209156_;
   long f_209157_;
   int f_209158_;
   private final DensityFunction.ContextProvider f_209159_ = new DensityFunction.ContextProvider() {
      public DensityFunction.FunctionContext m_207263_(int p_209253_) {
         NoiseChunk.this.f_209151_ = (p_209253_ + NoiseChunk.this.cellNoiseMinY) * NoiseChunk.this.f_209171_;
         ++NoiseChunk.this.f_209156_;
         NoiseChunk.this.f_209154_ = 0;
         NoiseChunk.this.f_209158_ = p_209253_;
         return NoiseChunk.this;
      }

      public void m_207207_(double[] p_209255_, DensityFunction p_209256_) {
         for(int i2 = 0; i2 < NoiseChunk.this.cellCountY + 1; ++i2) {
            NoiseChunk.this.f_209151_ = (i2 + NoiseChunk.this.cellNoiseMinY) * NoiseChunk.this.f_209171_;
            ++NoiseChunk.this.f_209156_;
            NoiseChunk.this.f_209154_ = 0;
            NoiseChunk.this.f_209158_ = i2;
            p_209255_[i2] = p_209256_.m_207386_(NoiseChunk.this);
         }

      }
   };

   public static NoiseChunk m_209206_(ChunkAccess p_209207_, NoiseRouter p_209208_, Supplier<DensityFunctions.BeardifierOrMarker> p_209209_, NoiseGeneratorSettings p_209210_, Aquifer.FluidPicker p_209211_, Blender p_209212_) {
      ChunkPos chunkpos = p_209207_.getPos();
      NoiseSettings noisesettings = p_209210_.noiseSettings();
      int i = Math.max(noisesettings.minY(), p_209207_.getMinBuildHeight());
      int j = Math.min(noisesettings.minY() + noisesettings.height(), p_209207_.getMaxBuildHeight());
      int k = Mth.intFloorDiv(i, noisesettings.getCellHeight());
      int l = Mth.intFloorDiv(j - i, noisesettings.getCellHeight());
      return new NoiseChunk(16 / noisesettings.getCellWidth(), l, k, p_209208_, chunkpos.getMinBlockX(), chunkpos.getMinBlockZ(), p_209209_.get(), p_209210_, p_209211_, p_209212_);
   }

   public static NoiseChunk m_209194_(int p_209195_, int p_209196_, int p_209197_, int p_209198_, NoiseRouter p_209199_, NoiseGeneratorSettings p_209200_, Aquifer.FluidPicker p_209201_) {
      return new NoiseChunk(1, p_209198_, p_209197_, p_209199_, p_209195_, p_209196_, DensityFunctions.BeardifierMarker.INSTANCE, p_209200_, p_209201_, Blender.empty());
   }

   private NoiseChunk(int p_209175_, int p_209176_, int p_209177_, NoiseRouter p_209178_, int p_209179_, int p_209180_, DensityFunctions.BeardifierOrMarker p_209181_, NoiseGeneratorSettings p_209182_, Aquifer.FluidPicker p_209183_, Blender p_209184_) {
      this.noiseSettings = p_209182_.noiseSettings();
      this.cellCountXZ = p_209175_;
      this.cellCountY = p_209176_;
      this.cellNoiseMinY = p_209177_;
      this.f_209170_ = this.noiseSettings.getCellWidth();
      this.f_209171_ = this.noiseSettings.getCellHeight();
      this.firstCellX = Math.floorDiv(p_209179_, this.f_209170_);
      this.firstCellZ = Math.floorDiv(p_209180_, this.f_209170_);
      this.interpolators = Lists.newArrayList();
      this.f_209160_ = Lists.newArrayList();
      this.firstNoiseX = QuartPos.fromBlock(p_209179_);
      this.firstNoiseZ = QuartPos.fromBlock(p_209180_);
      this.f_209169_ = QuartPos.fromBlock(p_209175_ * this.f_209170_);
      this.blender = p_209184_;
      this.f_209166_ = p_209181_;
      this.f_209164_ = new NoiseChunk.FlatCache(new NoiseChunk.BlendAlpha(), false);
      this.f_209165_ = new NoiseChunk.FlatCache(new NoiseChunk.BlendOffset(), false);

      for(int i = 0; i <= this.f_209169_; ++i) {
         int j = this.firstNoiseX + i;
         int k = QuartPos.toBlock(j);

         for(int l = 0; l <= this.f_209169_; ++l) {
            int i1 = this.firstNoiseZ + l;
            int j1 = QuartPos.toBlock(i1);
            Blender.BlendingOutput blender$blendingoutput = p_209184_.m_207242_(k, j1);
            this.f_209164_.f_209327_[i][l] = blender$blendingoutput.f_209729_();
            this.f_209165_.f_209327_[i][l] = blender$blendingoutput.f_209730_();
         }
      }

      if (!p_209182_.isAquifersEnabled()) {
         this.aquifer = Aquifer.createDisabled(p_209183_);
      } else {
         int k1 = SectionPos.blockToSectionCoord(p_209179_);
         int l1 = SectionPos.blockToSectionCoord(p_209180_);
         this.aquifer = Aquifer.m_208160_(this, new ChunkPos(k1, l1), p_209178_.f_209378_(), p_209178_.f_209379_(), p_209178_.f_209380_(), p_209178_.f_209381_(), p_209178_.f_209382_(), p_209177_ * this.f_209171_, p_209176_ * this.f_209171_, p_209183_);
      }

      Builder<NoiseChunk.BlockStateFiller> builder = ImmutableList.builder();
      DensityFunction densityfunction = DensityFunctions.m_208387_(DensityFunctions.m_208293_(p_209178_.f_209391_(), DensityFunctions.BeardifierMarker.INSTANCE)).m_207456_(this::m_209213_);
      builder.add((p_209217_) -> {
         return this.aquifer.m_207104_(p_209217_, densityfunction.m_207386_(p_209217_));
      });
      if (p_209182_.m_209369_()) {
         builder.add(OreVeinifier.m_209667_(p_209178_.f_209392_().m_207456_(this::m_209213_), p_209178_.f_209393_().m_207456_(this::m_209213_), p_209178_.f_209394_().m_207456_(this::m_209213_), p_209178_.f_209383_()));
      }

      this.f_209163_ = new MaterialRuleList(builder.build());
      this.f_209162_ = p_209178_.f_209390_().m_207456_(this::m_209213_);
   }

   protected Climate.Sampler m_209218_(NoiseRouter p_209219_) {
      return new Climate.Sampler(p_209219_.f_209384_().m_207456_(this::m_209213_), p_209219_.f_209385_().m_207456_(this::m_209213_), p_209219_.f_209386_().m_207456_(this::m_209213_), p_209219_.f_209387_().m_207456_(this::m_209213_), p_209219_.f_209388_().m_207456_(this::m_209213_), p_209219_.f_209389_().m_207456_(this::m_209213_), p_209219_.f_209395_());
   }

   @Nullable
   protected BlockState m_209247_() {
      return this.f_209163_.m_207387_(this);
   }

   public int m_207115_() {
      return this.f_209150_ + this.f_209153_;
   }

   public int m_207114_() {
      return this.f_209151_ + this.f_209154_;
   }

   public int m_207113_() {
      return this.f_209152_ + this.f_209155_;
   }

   public int preliminarySurfaceLevel(int p_198257_, int p_198258_) {
      return this.preliminarySurfaceLevel.computeIfAbsent(ChunkPos.asLong(QuartPos.fromBlock(p_198257_), QuartPos.fromBlock(p_198258_)), this::computePreliminarySurfaceLevel);
   }

   private int computePreliminarySurfaceLevel(long p_198250_) {
      int i = ChunkPos.getX(p_198250_);
      int j = ChunkPos.getZ(p_198250_);
      return (int)NoiseRouterData.m_209508_(this.noiseSettings, this.f_209162_, QuartPos.toBlock(i), QuartPos.toBlock(j));
   }

   public Blender m_207434_() {
      return this.blender;
   }

   private void m_209220_(boolean p_209221_, int p_209222_) {
      this.f_209150_ = p_209222_ * this.f_209170_;
      this.f_209153_ = 0;

      for(int i = 0; i < this.cellCountXZ + 1; ++i) {
         int j = this.firstCellZ + i;
         this.f_209152_ = j * this.f_209170_;
         this.f_209155_ = 0;
         ++this.f_209157_;

         for(NoiseChunk.NoiseInterpolator noisechunk$noiseinterpolator : this.interpolators) {
            double[] adouble = (p_209221_ ? noisechunk$noiseinterpolator.slice0 : noisechunk$noiseinterpolator.slice1)[i];
            noisechunk$noiseinterpolator.m_207362_(adouble, this.f_209159_);
         }
      }

      ++this.f_209157_;
   }

   public void initializeForFirstCellX() {
      if (this.f_209172_) {
         throw new IllegalStateException("Staring interpolation twice");
      } else {
         this.f_209172_ = true;
         this.f_209156_ = 0L;
         this.m_209220_(true, this.firstCellX);
      }
   }

   public void advanceCellX(int p_188750_) {
      this.m_209220_(false, this.firstCellX + p_188750_ + 1);
      this.f_209150_ = (this.firstCellX + p_188750_) * this.f_209170_;
   }

   public NoiseChunk m_207263_(int p_209240_) {
      int i = Math.floorMod(p_209240_, this.f_209170_);
      int j = Math.floorDiv(p_209240_, this.f_209170_);
      int k = Math.floorMod(j, this.f_209170_);
      int l = this.f_209171_ - 1 - Math.floorDiv(j, this.f_209170_);
      this.f_209153_ = k;
      this.f_209154_ = l;
      this.f_209155_ = i;
      this.f_209158_ = p_209240_;
      return this;
   }

   public void m_207207_(double[] p_209224_, DensityFunction p_209225_) {
      this.f_209158_ = 0;

      for(int i = this.f_209171_ - 1; i >= 0; --i) {
         this.f_209154_ = i;

         for(int j = 0; j < this.f_209170_; ++j) {
            this.f_209153_ = j;

            for(int k = 0; k < this.f_209170_; ++k) {
               this.f_209155_ = k;
               p_209224_[this.f_209158_++] = p_209225_.m_207386_(this);
            }
         }
      }

   }

   public void selectCellYZ(int p_188811_, int p_188812_) {
      this.interpolators.forEach((p_209205_) -> {
         p_209205_.selectCellYZ(p_188811_, p_188812_);
      });
      this.f_209173_ = true;
      this.f_209151_ = (p_188811_ + this.cellNoiseMinY) * this.f_209171_;
      this.f_209152_ = (this.firstCellZ + p_188812_) * this.f_209170_;
      ++this.f_209157_;

      for(NoiseChunk.CacheAllInCell noisechunk$cacheallincell : this.f_209160_) {
         noisechunk$cacheallincell.f_209297_.m_207362_(noisechunk$cacheallincell.f_209298_, this);
      }

      ++this.f_209157_;
      this.f_209173_ = false;
   }

   public void m_209191_(int p_209192_, double p_209193_) {
      this.f_209154_ = p_209192_ - this.f_209151_;
      this.interpolators.forEach((p_209238_) -> {
         p_209238_.updateForY(p_209193_);
      });
   }

   public void m_209230_(int p_209231_, double p_209232_) {
      this.f_209153_ = p_209231_ - this.f_209150_;
      this.interpolators.forEach((p_209229_) -> {
         p_209229_.updateForX(p_209232_);
      });
   }

   public void m_209241_(int p_209242_, double p_209243_) {
      this.f_209155_ = p_209242_ - this.f_209152_;
      ++this.f_209156_;
      this.interpolators.forEach((p_209188_) -> {
         p_209188_.updateForZ(p_209243_);
      });
   }

   public void m_209248_() {
      if (!this.f_209172_) {
         throw new IllegalStateException("Staring interpolation twice");
      } else {
         this.f_209172_ = false;
      }
   }

   public void swapSlices() {
      this.interpolators.forEach(NoiseChunk.NoiseInterpolator::swapSlices);
   }

   public Aquifer aquifer() {
      return this.aquifer;
   }

   Blender.BlendingOutput m_209244_(int p_209245_, int p_209246_) {
      long i = ChunkPos.asLong(p_209245_, p_209246_);
      if (this.f_209167_ == i) {
         return this.f_209168_;
      } else {
         this.f_209167_ = i;
         Blender.BlendingOutput blender$blendingoutput = this.blender.m_207242_(p_209245_, p_209246_);
         this.f_209168_ = blender$blendingoutput;
         return blender$blendingoutput;
      }
   }

   protected DensityFunction m_209213_(DensityFunction p_209214_) {
      return this.f_209161_.computeIfAbsent(p_209214_, this::m_209233_);
   }

   private DensityFunction m_209233_(DensityFunction p_209234_) {
      if (p_209234_ instanceof DensityFunctions.Marker) {
         DensityFunctions.Marker densityfunctions$marker = (DensityFunctions.Marker)p_209234_;
         Object object;
         switch(densityfunctions$marker.m_207136_()) {
         case Interpolated:
            object = new NoiseChunk.NoiseInterpolator(densityfunctions$marker.m_207056_());
            break;
         case FlatCache:
            object = new NoiseChunk.FlatCache(densityfunctions$marker.m_207056_(), true);
            break;
         case Cache2D:
            object = new NoiseChunk.Cache2D(densityfunctions$marker.m_207056_());
            break;
         case CacheOnce:
            object = new NoiseChunk.CacheOnce(densityfunctions$marker.m_207056_());
            break;
         case CacheAllInCell:
            object = new NoiseChunk.CacheAllInCell(densityfunctions$marker.m_207056_());
            break;
         default:
            throw new IncompatibleClassChangeError();
         }

         return (DensityFunction)object;
      } else {
         if (this.blender != Blender.empty()) {
            if (p_209234_ == DensityFunctions.BlendAlpha.INSTANCE) {
               return this.f_209164_;
            }

            if (p_209234_ == DensityFunctions.BlendOffset.INSTANCE) {
               return this.f_209165_;
            }
         }

         if (p_209234_ == DensityFunctions.BeardifierMarker.INSTANCE) {
            return this.f_209166_;
         } else if (p_209234_ instanceof DensityFunctions.HolderHolder) {
            DensityFunctions.HolderHolder densityfunctions$holderholder = (DensityFunctions.HolderHolder)p_209234_;
            return densityfunctions$holderholder.f_208636_().m_203334_();
         } else {
            return p_209234_;
         }
      }
   }

   class BlendAlpha implements NoiseChunk.NoiseChunkDensityFunction {
      public DensityFunction m_207056_() {
         return DensityFunctions.BlendAlpha.INSTANCE;
      }

      public double m_207386_(DensityFunction.FunctionContext p_209264_) {
         return NoiseChunk.this.m_209244_(p_209264_.m_207115_(), p_209264_.m_207113_()).f_209729_();
      }

      public void m_207362_(double[] p_209266_, DensityFunction.ContextProvider p_209267_) {
         p_209267_.m_207207_(p_209266_, this);
      }

      public double m_207402_() {
         return 0.0D;
      }

      public double m_207401_() {
         return 1.0D;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return DensityFunctions.BlendAlpha.f_208528_;
      }
   }

   class BlendOffset implements NoiseChunk.NoiseChunkDensityFunction {
      public DensityFunction m_207056_() {
         return DensityFunctions.BlendOffset.INSTANCE;
      }

      public double m_207386_(DensityFunction.FunctionContext p_209276_) {
         return NoiseChunk.this.m_209244_(p_209276_.m_207115_(), p_209276_.m_207113_()).f_209730_();
      }

      public void m_207362_(double[] p_209278_, DensityFunction.ContextProvider p_209279_) {
         p_209279_.m_207207_(p_209278_, this);
      }

      public double m_207402_() {
         return Double.NEGATIVE_INFINITY;
      }

      public double m_207401_() {
         return Double.POSITIVE_INFINITY;
      }

      public Codec<? extends DensityFunction> m_207500_() {
         return DensityFunctions.BlendOffset.f_208565_;
      }
   }

   @FunctionalInterface
   public interface BlockStateFiller {
      @Nullable
      BlockState m_207387_(DensityFunction.FunctionContext p_209283_);
   }

   static class Cache2D implements DensityFunctions.MarkerOrMarked, NoiseChunk.NoiseChunkDensityFunction {
      private final DensityFunction f_209284_;
      private long f_209285_ = ChunkPos.INVALID_CHUNK_POS;
      private double f_209286_;

      Cache2D(DensityFunction p_209288_) {
         this.f_209284_ = p_209288_;
      }

      public double m_207386_(DensityFunction.FunctionContext p_209290_) {
         int i = p_209290_.m_207115_();
         int j = p_209290_.m_207113_();
         long k = ChunkPos.asLong(i, j);
         if (this.f_209285_ == k) {
            return this.f_209286_;
         } else {
            this.f_209285_ = k;
            double d0 = this.f_209284_.m_207386_(p_209290_);
            this.f_209286_ = d0;
            return d0;
         }
      }

      public void m_207362_(double[] p_209292_, DensityFunction.ContextProvider p_209293_) {
         this.f_209284_.m_207362_(p_209292_, p_209293_);
      }

      public DensityFunction m_207056_() {
         return this.f_209284_;
      }

      public DensityFunctions.Marker.Type m_207136_() {
         return DensityFunctions.Marker.Type.Cache2D;
      }
   }

   class CacheAllInCell implements DensityFunctions.MarkerOrMarked, NoiseChunk.NoiseChunkDensityFunction {
      final DensityFunction f_209297_;
      final double[] f_209298_;

      CacheAllInCell(DensityFunction p_209301_) {
         this.f_209297_ = p_209301_;
         this.f_209298_ = new double[NoiseChunk.this.f_209170_ * NoiseChunk.this.f_209170_ * NoiseChunk.this.f_209171_];
         NoiseChunk.this.f_209160_.add(this);
      }

      public double m_207386_(DensityFunction.FunctionContext p_209303_) {
         if (p_209303_ != NoiseChunk.this) {
            return this.f_209297_.m_207386_(p_209303_);
         } else if (!NoiseChunk.this.f_209172_) {
            throw new IllegalStateException("Trying to sample interpolator outside the interpolation loop");
         } else {
            int i = NoiseChunk.this.f_209153_;
            int j = NoiseChunk.this.f_209154_;
            int k = NoiseChunk.this.f_209155_;
            return i >= 0 && j >= 0 && k >= 0 && i < NoiseChunk.this.f_209170_ && j < NoiseChunk.this.f_209171_ && k < NoiseChunk.this.f_209170_ ? this.f_209298_[((NoiseChunk.this.f_209171_ - 1 - j) * NoiseChunk.this.f_209170_ + i) * NoiseChunk.this.f_209170_ + k] : this.f_209297_.m_207386_(p_209303_);
         }
      }

      public void m_207362_(double[] p_209305_, DensityFunction.ContextProvider p_209306_) {
         p_209306_.m_207207_(p_209305_, this);
      }

      public DensityFunction m_207056_() {
         return this.f_209297_;
      }

      public DensityFunctions.Marker.Type m_207136_() {
         return DensityFunctions.Marker.Type.CacheAllInCell;
      }
   }

   class CacheOnce implements DensityFunctions.MarkerOrMarked, NoiseChunk.NoiseChunkDensityFunction {
      private final DensityFunction f_209310_;
      private long f_209311_;
      private long f_209312_;
      private double f_209313_;
      @Nullable
      private double[] f_209314_;

      CacheOnce(DensityFunction p_209317_) {
         this.f_209310_ = p_209317_;
      }

      public double m_207386_(DensityFunction.FunctionContext p_209319_) {
         if (p_209319_ != NoiseChunk.this) {
            return this.f_209310_.m_207386_(p_209319_);
         } else if (this.f_209314_ != null && this.f_209312_ == NoiseChunk.this.f_209157_) {
            return this.f_209314_[NoiseChunk.this.f_209158_];
         } else if (this.f_209311_ == NoiseChunk.this.f_209156_) {
            return this.f_209313_;
         } else {
            this.f_209311_ = NoiseChunk.this.f_209156_;
            double d0 = this.f_209310_.m_207386_(p_209319_);
            this.f_209313_ = d0;
            return d0;
         }
      }

      public void m_207362_(double[] p_209321_, DensityFunction.ContextProvider p_209322_) {
         if (this.f_209314_ != null && this.f_209312_ == NoiseChunk.this.f_209157_) {
            System.arraycopy(this.f_209314_, 0, p_209321_, 0, p_209321_.length);
         } else {
            this.m_207056_().m_207362_(p_209321_, p_209322_);
            if (this.f_209314_ != null && this.f_209314_.length == p_209321_.length) {
               System.arraycopy(p_209321_, 0, this.f_209314_, 0, p_209321_.length);
            } else {
               this.f_209314_ = (double[])p_209321_.clone();
            }

            this.f_209312_ = NoiseChunk.this.f_209157_;
         }
      }

      public DensityFunction m_207056_() {
         return this.f_209310_;
      }

      public DensityFunctions.Marker.Type m_207136_() {
         return DensityFunctions.Marker.Type.CacheOnce;
      }
   }

   class FlatCache implements DensityFunctions.MarkerOrMarked, NoiseChunk.NoiseChunkDensityFunction {
      private final DensityFunction f_209326_;
      final double[][] f_209327_;

      FlatCache(DensityFunction p_209330_, boolean p_209331_) {
         this.f_209326_ = p_209330_;
         this.f_209327_ = new double[NoiseChunk.this.f_209169_ + 1][NoiseChunk.this.f_209169_ + 1];
         if (p_209331_) {
            for(int i = 0; i <= NoiseChunk.this.f_209169_; ++i) {
               int j = NoiseChunk.this.firstNoiseX + i;
               int k = QuartPos.toBlock(j);

               for(int l = 0; l <= NoiseChunk.this.f_209169_; ++l) {
                  int i1 = NoiseChunk.this.firstNoiseZ + l;
                  int j1 = QuartPos.toBlock(i1);
                  this.f_209327_[i][l] = p_209330_.m_207386_(new DensityFunction.SinglePointContext(k, 0, j1));
               }
            }
         }

      }

      public double m_207386_(DensityFunction.FunctionContext p_209333_) {
         int i = QuartPos.fromBlock(p_209333_.m_207115_());
         int j = QuartPos.fromBlock(p_209333_.m_207113_());
         int k = i - NoiseChunk.this.firstNoiseX;
         int l = j - NoiseChunk.this.firstNoiseZ;
         int i1 = this.f_209327_.length;
         return k >= 0 && l >= 0 && k < i1 && l < i1 ? this.f_209327_[k][l] : this.f_209326_.m_207386_(p_209333_);
      }

      public void m_207362_(double[] p_209335_, DensityFunction.ContextProvider p_209336_) {
         p_209336_.m_207207_(p_209335_, this);
      }

      public DensityFunction m_207056_() {
         return this.f_209326_;
      }

      public DensityFunctions.Marker.Type m_207136_() {
         return DensityFunctions.Marker.Type.FlatCache;
      }
   }

   interface NoiseChunkDensityFunction extends DensityFunction {
      DensityFunction m_207056_();

      default DensityFunction m_207456_(DensityFunction.Visitor p_209341_) {
         return this.m_207056_().m_207456_(p_209341_);
      }

      default double m_207402_() {
         return this.m_207056_().m_207402_();
      }

      default double m_207401_() {
         return this.m_207056_().m_207401_();
      }
   }

   public class NoiseInterpolator implements DensityFunctions.MarkerOrMarked, NoiseChunk.NoiseChunkDensityFunction {
      double[][] slice0;
      double[][] slice1;
      private final DensityFunction noiseFiller;
      private double noise000;
      private double noise001;
      private double noise100;
      private double noise101;
      private double noise010;
      private double noise011;
      private double noise110;
      private double noise111;
      private double valueXZ00;
      private double valueXZ10;
      private double valueXZ01;
      private double valueXZ11;
      private double valueZ0;
      private double valueZ1;
      private double value;

      NoiseInterpolator(DensityFunction p_209345_) {
         this.noiseFiller = p_209345_;
         this.slice0 = this.allocateSlice(NoiseChunk.this.cellCountY, NoiseChunk.this.cellCountXZ);
         this.slice1 = this.allocateSlice(NoiseChunk.this.cellCountY, NoiseChunk.this.cellCountXZ);
         NoiseChunk.this.interpolators.add(this);
      }

      private double[][] allocateSlice(int p_188855_, int p_188856_) {
         int i = p_188856_ + 1;
         int j = p_188855_ + 1;
         double[][] adouble = new double[i][j];

         for(int k = 0; k < i; ++k) {
            adouble[k] = new double[j];
         }

         return adouble;
      }

      void selectCellYZ(int p_188864_, int p_188865_) {
         this.noise000 = this.slice0[p_188865_][p_188864_];
         this.noise001 = this.slice0[p_188865_ + 1][p_188864_];
         this.noise100 = this.slice1[p_188865_][p_188864_];
         this.noise101 = this.slice1[p_188865_ + 1][p_188864_];
         this.noise010 = this.slice0[p_188865_][p_188864_ + 1];
         this.noise011 = this.slice0[p_188865_ + 1][p_188864_ + 1];
         this.noise110 = this.slice1[p_188865_][p_188864_ + 1];
         this.noise111 = this.slice1[p_188865_ + 1][p_188864_ + 1];
      }

      void updateForY(double p_188851_) {
         this.valueXZ00 = Mth.lerp(p_188851_, this.noise000, this.noise010);
         this.valueXZ10 = Mth.lerp(p_188851_, this.noise100, this.noise110);
         this.valueXZ01 = Mth.lerp(p_188851_, this.noise001, this.noise011);
         this.valueXZ11 = Mth.lerp(p_188851_, this.noise101, this.noise111);
      }

      void updateForX(double p_188862_) {
         this.valueZ0 = Mth.lerp(p_188862_, this.valueXZ00, this.valueXZ10);
         this.valueZ1 = Mth.lerp(p_188862_, this.valueXZ01, this.valueXZ11);
      }

      void updateForZ(double p_188867_) {
         this.value = Mth.lerp(p_188867_, this.valueZ0, this.valueZ1);
      }

      public double m_207386_(DensityFunction.FunctionContext p_209347_) {
         if (p_209347_ != NoiseChunk.this) {
            return this.noiseFiller.m_207386_(p_209347_);
         } else if (!NoiseChunk.this.f_209172_) {
            throw new IllegalStateException("Trying to sample interpolator outside the interpolation loop");
         } else {
            return NoiseChunk.this.f_209173_ ? Mth.lerp3((double)NoiseChunk.this.f_209153_ / (double)NoiseChunk.this.f_209170_, (double)NoiseChunk.this.f_209154_ / (double)NoiseChunk.this.f_209171_, (double)NoiseChunk.this.f_209155_ / (double)NoiseChunk.this.f_209170_, this.noise000, this.noise100, this.noise010, this.noise110, this.noise001, this.noise101, this.noise011, this.noise111) : this.value;
         }
      }

      public void m_207362_(double[] p_209349_, DensityFunction.ContextProvider p_209350_) {
         if (NoiseChunk.this.f_209173_) {
            p_209350_.m_207207_(p_209349_, this);
         } else {
            this.m_207056_().m_207362_(p_209349_, p_209350_);
         }
      }

      public DensityFunction m_207056_() {
         return this.noiseFiller;
      }

      private void swapSlices() {
         double[][] adouble = this.slice0;
         this.slice0 = this.slice1;
         this.slice1 = adouble;
      }

      public DensityFunctions.Marker.Type m_207136_() {
         return DensityFunctions.Marker.Type.Interpolated;
      }
   }
}