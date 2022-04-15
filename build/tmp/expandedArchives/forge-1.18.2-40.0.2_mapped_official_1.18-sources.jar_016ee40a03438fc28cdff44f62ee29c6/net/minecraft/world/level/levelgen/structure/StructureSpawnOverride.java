package net.minecraft.world.level.levelgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import javax.annotation.Nullable;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.biome.MobSpawnSettings;

public record StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType f_210043_, WeightedRandomList<MobSpawnSettings.SpawnerData> f_210044_) {
   public static final Codec<StructureSpawnOverride> f_210042_ = RecordCodecBuilder.create((p_210051_) -> {
      return p_210051_.group(StructureSpawnOverride.BoundingBoxType.f_210060_.fieldOf("bounding_box").forGetter(StructureSpawnOverride::f_210043_), WeightedRandomList.codec(MobSpawnSettings.SpawnerData.CODEC).fieldOf("spawns").forGetter(StructureSpawnOverride::f_210044_)).apply(p_210051_, StructureSpawnOverride::new);
   });

   public static enum BoundingBoxType implements StringRepresentable {
      PIECE("piece"),
      STRUCTURE("full");

      public static final StructureSpawnOverride.BoundingBoxType[] f_210059_ = values();
      public static final Codec<StructureSpawnOverride.BoundingBoxType> f_210060_ = StringRepresentable.fromEnum(() -> {
         return f_210059_;
      }, StructureSpawnOverride.BoundingBoxType::m_210069_);
      private final String f_210061_;

      private BoundingBoxType(String p_210067_) {
         this.f_210061_ = p_210067_;
      }

      public String getSerializedName() {
         return this.f_210061_;
      }

      @Nullable
      public static StructureSpawnOverride.BoundingBoxType m_210069_(@Nullable String p_210070_) {
         if (p_210070_ == null) {
            return null;
         } else {
            for(StructureSpawnOverride.BoundingBoxType structurespawnoverride$boundingboxtype : f_210059_) {
               if (structurespawnoverride$boundingboxtype.f_210061_.equals(p_210070_)) {
                  return structurespawnoverride$boundingboxtype;
               }
            }

            return null;
         }
      }
   }
}