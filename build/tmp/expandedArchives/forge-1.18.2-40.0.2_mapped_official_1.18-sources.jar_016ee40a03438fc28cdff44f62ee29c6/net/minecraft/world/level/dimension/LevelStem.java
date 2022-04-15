package net.minecraft.world.level.dimension;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public final class LevelStem {
   public static final Codec<LevelStem> CODEC = RecordCodecBuilder.create((p_63986_) -> {
      return net.minecraftforge.common.ForgeHooks.expandLevelStemCodec(p_63986_, () -> { // forge: reroute the codec builder here to add extra json fields
      return p_63986_.group(DimensionType.CODEC.fieldOf("type").forGetter(LevelStem::m_204521_), ChunkGenerator.CODEC.fieldOf("generator").forGetter(LevelStem::generator)).apply(p_63986_, p_63986_.stable(LevelStem::new));
      }); // forge: we're wrapping the original codec builder in our expansion up there
   });
   public static final ResourceKey<LevelStem> OVERWORLD = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, new ResourceLocation("overworld"));
   public static final ResourceKey<LevelStem> NETHER = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, new ResourceLocation("the_nether"));
   public static final ResourceKey<LevelStem> END = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, new ResourceLocation("the_end"));
   private static final Set<ResourceKey<LevelStem>> BUILTIN_ORDER = ImmutableSet.of(OVERWORLD, NETHER, END);
   private final Holder<DimensionType> type;
   private final ChunkGenerator generator;

   public LevelStem(Holder<DimensionType> p_204519_, ChunkGenerator p_204520_) {
      this(p_204519_, p_204520_, false); // forge: by default, dimension jsons use the fixed seed required by the vanilla format
   } // forge: reroute the vanilla constructor to our expanded constructor with default args
   private final boolean useServerSeed; // forge: whether the dimension parser should use the server seed instead of the fixed seed
   public boolean useServerSeed() { return this.useServerSeed; }
   public LevelStem(Holder<DimensionType> p_204519_, ChunkGenerator p_204520_, boolean useServerSeed) { // forge: allow dimension jsons to specify that they should use the server seed instead of the fixed seed field
      this.useServerSeed = useServerSeed; // forge
      this.type = p_204519_;
      this.generator = p_204520_;
   }

   public Holder<DimensionType> m_204521_() {
      return this.type;
   }

   public ChunkGenerator generator() {
      return this.generator;
   }

   public static Registry<LevelStem> m_204525_(Registry<LevelStem> p_204526_) {
      WritableRegistry<LevelStem> writableregistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental(), (Function<LevelStem, Holder.Reference<LevelStem>>)null);

      for(ResourceKey<LevelStem> resourcekey : BUILTIN_ORDER) {
         LevelStem levelstem = p_204526_.get(resourcekey);
         if (levelstem != null) {
            writableregistry.m_203505_(resourcekey, levelstem, p_204526_.lifecycle(levelstem));
         }
      }

      for(Entry<ResourceKey<LevelStem>, LevelStem> entry : p_204526_.entrySet()) {
         ResourceKey<LevelStem> resourcekey1 = entry.getKey();
         if (!BUILTIN_ORDER.contains(resourcekey1)) {
            writableregistry.m_203505_(resourcekey1, entry.getValue(), p_204526_.lifecycle(entry.getValue()));
         }
      }

      return writableregistry;
   }

   public static boolean m_204522_(long p_204523_, Registry<LevelStem> p_204524_) {
      if (p_204524_.size() != BUILTIN_ORDER.size()) {
         return false;
      } else {
         Optional<LevelStem> optional = p_204524_.getOptional(OVERWORLD);
         Optional<LevelStem> optional1 = p_204524_.getOptional(NETHER);
         Optional<LevelStem> optional2 = p_204524_.getOptional(END);
         if (!optional.isEmpty() && !optional1.isEmpty() && !optional2.isEmpty()) {
            if (!optional.get().m_204521_().m_203565_(DimensionType.OVERWORLD_LOCATION) && !optional.get().m_204521_().m_203565_(DimensionType.OVERWORLD_CAVES_LOCATION)) {
               return false;
            } else if (!optional1.get().m_204521_().m_203565_(DimensionType.NETHER_LOCATION)) {
               return false;
            } else if (!optional2.get().m_204521_().m_203565_(DimensionType.END_LOCATION)) {
               return false;
            } else if (optional1.get().generator() instanceof NoiseBasedChunkGenerator && optional2.get().generator() instanceof NoiseBasedChunkGenerator) {
               NoiseBasedChunkGenerator noisebasedchunkgenerator = (NoiseBasedChunkGenerator)optional1.get().generator();
               NoiseBasedChunkGenerator noisebasedchunkgenerator1 = (NoiseBasedChunkGenerator)optional2.get().generator();
               if (!noisebasedchunkgenerator.stable(p_204523_, NoiseGeneratorSettings.NETHER)) {
                  return false;
               } else if (!noisebasedchunkgenerator1.stable(p_204523_, NoiseGeneratorSettings.END)) {
                  return false;
               } else if (!(noisebasedchunkgenerator.getBiomeSource() instanceof MultiNoiseBiomeSource)) {
                  return false;
               } else {
                  MultiNoiseBiomeSource multinoisebiomesource = (MultiNoiseBiomeSource)noisebasedchunkgenerator.getBiomeSource();
                  if (!multinoisebiomesource.stable(MultiNoiseBiomeSource.Preset.NETHER)) {
                     return false;
                  } else {
                     BiomeSource biomesource = optional.get().generator().getBiomeSource();
                     if (biomesource instanceof MultiNoiseBiomeSource && !((MultiNoiseBiomeSource)biomesource).stable(MultiNoiseBiomeSource.Preset.OVERWORLD)) {
                        return false;
                     } else if (!(noisebasedchunkgenerator1.getBiomeSource() instanceof TheEndBiomeSource)) {
                        return false;
                     } else {
                        TheEndBiomeSource theendbiomesource = (TheEndBiomeSource)noisebasedchunkgenerator1.getBiomeSource();
                        return theendbiomesource.stable(p_204523_);
                     }
                  }
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }
}
