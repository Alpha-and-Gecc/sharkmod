package net.minecraft.world.level.levelgen.flat;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.LayerConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.slf4j.Logger;

public class FlatLevelGeneratorSettings {
   private static final Logger LOGGER = LogUtils.getLogger();
   public static final Codec<FlatLevelGeneratorSettings> CODEC = RecordCodecBuilder.<FlatLevelGeneratorSettings>create((p_209800_) -> {
      return p_209800_.group(RegistryOps.m_206832_(Registry.BIOME_REGISTRY).forGetter((p_161916_) -> {
         return p_161916_.biomes;
      }), RegistryCodecs.m_206277_(Registry.f_211073_).optionalFieldOf("structure_overrides").forGetter((p_209812_) -> {
         return p_209812_.f_209788_;
      }), FlatLayerInfo.CODEC.listOf().fieldOf("layers").forGetter(FlatLevelGeneratorSettings::getLayersInfo), Codec.BOOL.fieldOf("lakes").orElse(false).forGetter((p_161912_) -> {
         return p_161912_.addLakes;
      }), Codec.BOOL.fieldOf("features").orElse(false).forGetter((p_209809_) -> {
         return p_209809_.decoration;
      }), Biome.CODEC.optionalFieldOf("biome").orElseGet(Optional::empty).forGetter((p_209807_) -> {
         return Optional.of(p_209807_.biome);
      })).apply(p_209800_, FlatLevelGeneratorSettings::new);
   }).comapFlatMap(FlatLevelGeneratorSettings::validateHeight, Function.identity()).stable();
   private final Registry<Biome> biomes;
   private final Optional<HolderSet<StructureSet>> f_209788_;
   private final List<FlatLayerInfo> layersInfo = Lists.newArrayList();
   private Holder<Biome> biome;
   private final List<BlockState> layers;
   private boolean voidGen;
   private boolean decoration;
   private boolean addLakes;

   private static DataResult<FlatLevelGeneratorSettings> validateHeight(FlatLevelGeneratorSettings p_161906_) {
      int i = p_161906_.layersInfo.stream().mapToInt(FlatLayerInfo::getHeight).sum();
      return i > DimensionType.Y_SIZE ? DataResult.error("Sum of layer heights is > " + DimensionType.Y_SIZE, p_161906_) : DataResult.success(p_161906_);
   }

   private FlatLevelGeneratorSettings(Registry<Biome> p_209790_, Optional<HolderSet<StructureSet>> p_209791_, List<FlatLayerInfo> p_209792_, boolean p_209793_, boolean p_209794_, Optional<Holder<Biome>> p_209795_) {
      this(p_209791_, p_209790_);
      if (p_209793_) {
         this.setAddLakes();
      }

      if (p_209794_) {
         this.setDecoration();
      }

      this.layersInfo.addAll(p_209792_);
      this.updateLayers();
      if (p_209795_.isEmpty()) {
         LOGGER.error("Unknown biome, defaulting to plains");
         this.biome = p_209790_.m_203538_(Biomes.PLAINS);
      } else {
         this.biome = p_209795_.get();
      }

   }

   public FlatLevelGeneratorSettings(Optional<HolderSet<StructureSet>> p_209797_, Registry<Biome> p_209798_) {
      this.biomes = p_209798_;
      this.f_209788_ = p_209797_;
      this.biome = p_209798_.m_203538_(Biomes.PLAINS);
      this.layers = Lists.newArrayList();
   }

   public FlatLevelGeneratorSettings m_209803_(List<FlatLayerInfo> p_209804_, Optional<HolderSet<StructureSet>> p_209805_) {
      FlatLevelGeneratorSettings flatlevelgeneratorsettings = new FlatLevelGeneratorSettings(p_209805_, this.biomes);

      for(FlatLayerInfo flatlayerinfo : p_209804_) {
         flatlevelgeneratorsettings.layersInfo.add(new FlatLayerInfo(flatlayerinfo.getHeight(), flatlayerinfo.getBlockState().getBlock()));
         flatlevelgeneratorsettings.updateLayers();
      }

      flatlevelgeneratorsettings.m_204918_(this.biome);
      if (this.decoration) {
         flatlevelgeneratorsettings.setDecoration();
      }

      if (this.addLakes) {
         flatlevelgeneratorsettings.setAddLakes();
      }

      return flatlevelgeneratorsettings;
   }

   public void setDecoration() {
      this.decoration = true;
   }

   public void setAddLakes() {
      this.addLakes = true;
   }

   public Holder<Biome> m_204920_() {
      Biome biome = this.m_204921_().m_203334_();
      BiomeGenerationSettings biomegenerationsettings = biome.getGenerationSettings();
      BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
      if (this.addLakes) {
         biomegenerationsettings$builder.m_204201_(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND);
         biomegenerationsettings$builder.m_204201_(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_SURFACE);
      }

      boolean flag = (!this.voidGen || this.biome.m_203565_(Biomes.THE_VOID)) && this.decoration;
      if (flag) {
         List<HolderSet<PlacedFeature>> list = biomegenerationsettings.features();

         for(int i = 0; i < list.size(); ++i) {
            if (i != GenerationStep.Decoration.UNDERGROUND_STRUCTURES.ordinal() && i != GenerationStep.Decoration.SURFACE_STRUCTURES.ordinal()) {
               for(Holder<PlacedFeature> holder : list.get(i)) {
                  biomegenerationsettings$builder.m_204193_(i, holder);
               }
            }
         }
      }

      List<BlockState> list1 = this.getLayers();

      for(int j = 0; j < list1.size(); ++j) {
         BlockState blockstate = list1.get(j);
         if (!Heightmap.Types.MOTION_BLOCKING.isOpaque().test(blockstate)) {
            list1.set(j, (BlockState)null);
            biomegenerationsettings$builder.m_204201_(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, PlacementUtils.m_206502_(Feature.FILL_LAYER, new LayerConfiguration(j, blockstate)));
         }
      }

      return Holder.m_205709_(Biome.BiomeBuilder.m_204185_(biome).generationSettings(biomegenerationsettings$builder.build()).build().setRegistryName(biome.getRegistryName()));
   }

   public Optional<HolderSet<StructureSet>> m_209810_() {
      return this.f_209788_;
   }

   public Holder<Biome> m_204921_() {
      return this.biome;
   }

   public void m_204918_(Holder<Biome> p_204919_) {
      this.biome = p_204919_;
   }

   public List<FlatLayerInfo> getLayersInfo() {
      return this.layersInfo;
   }

   public List<BlockState> getLayers() {
      return this.layers;
   }

   public void updateLayers() {
      this.layers.clear();

      for(FlatLayerInfo flatlayerinfo : this.layersInfo) {
         for(int i = 0; i < flatlayerinfo.getHeight(); ++i) {
            this.layers.add(flatlayerinfo.getBlockState());
         }
      }

      this.voidGen = this.layers.stream().allMatch((p_209802_) -> {
         return p_209802_.is(Blocks.AIR);
      });
   }

   public static FlatLevelGeneratorSettings m_211734_(Registry<Biome> p_211735_, Registry<StructureSet> p_211736_) {
      HolderSet<StructureSet> holderset = HolderSet.m_205809_(p_211736_.m_206081_(BuiltinStructureSets.f_209836_), p_211736_.m_206081_(BuiltinStructureSets.f_209820_));
      FlatLevelGeneratorSettings flatlevelgeneratorsettings = new FlatLevelGeneratorSettings(Optional.of(holderset), p_211735_);
      flatlevelgeneratorsettings.biome = p_211735_.m_203538_(Biomes.PLAINS);
      flatlevelgeneratorsettings.getLayersInfo().add(new FlatLayerInfo(1, Blocks.BEDROCK));
      flatlevelgeneratorsettings.getLayersInfo().add(new FlatLayerInfo(2, Blocks.DIRT));
      flatlevelgeneratorsettings.getLayersInfo().add(new FlatLayerInfo(1, Blocks.GRASS_BLOCK));
      flatlevelgeneratorsettings.updateLayers();
      return flatlevelgeneratorsettings;
   }
}
