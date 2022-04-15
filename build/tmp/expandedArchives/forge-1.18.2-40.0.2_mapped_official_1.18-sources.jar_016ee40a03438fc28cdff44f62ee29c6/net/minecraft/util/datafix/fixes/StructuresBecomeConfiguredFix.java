package net.minecraft.util.datafix.fixes;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;

public class StructuresBecomeConfiguredFix extends DataFix {
   private static final Map<String, StructuresBecomeConfiguredFix.Conversion> f_207676_ = ImmutableMap.<String, StructuresBecomeConfiguredFix.Conversion>builder().put("mineshaft", StructuresBecomeConfiguredFix.Conversion.m_207750_(Map.of(List.of("minecraft:badlands", "minecraft:eroded_badlands", "minecraft:wooded_badlands"), "minecraft:mineshaft_mesa"), "minecraft:mineshaft")).put("shipwreck", StructuresBecomeConfiguredFix.Conversion.m_207750_(Map.of(List.of("minecraft:beach", "minecraft:snowy_beach"), "minecraft:shipwreck_beached"), "minecraft:shipwreck")).put("ocean_ruin", StructuresBecomeConfiguredFix.Conversion.m_207750_(Map.of(List.of("minecraft:warm_ocean", "minecraft:lukewarm_ocean", "minecraft:deep_lukewarm_ocean"), "minecraft:ocean_ruin_warm"), "minecraft:ocean_ruin_cold")).put("village", StructuresBecomeConfiguredFix.Conversion.m_207750_(Map.of(List.of("minecraft:desert"), "minecraft:village_desert", List.of("minecraft:savanna"), "minecraft:village_savanna", List.of("minecraft:snowy_plains"), "minecraft:village_snowy", List.of("minecraft:taiga"), "minecraft:village_taiga"), "minecraft:village_plains")).put("ruined_portal", StructuresBecomeConfiguredFix.Conversion.m_207750_(Map.of(List.of("minecraft:desert"), "minecraft:ruined_portal_desert", List.of("minecraft:badlands", "minecraft:eroded_badlands", "minecraft:wooded_badlands", "minecraft:windswept_hills", "minecraft:windswept_forest", "minecraft:windswept_gravelly_hills", "minecraft:savanna_plateau", "minecraft:windswept_savanna", "minecraft:stony_shore", "minecraft:meadow", "minecraft:frozen_peaks", "minecraft:jagged_peaks", "minecraft:stony_peaks", "minecraft:snowy_slopes"), "minecraft:ruined_portal_mountain", List.of("minecraft:bamboo_jungle", "minecraft:jungle", "minecraft:sparse_jungle"), "minecraft:ruined_portal_jungle", List.of("minecraft:deep_frozen_ocean", "minecraft:deep_cold_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean", "minecraft:frozen_ocean", "minecraft:ocean", "minecraft:cold_ocean", "minecraft:lukewarm_ocean", "minecraft:warm_ocean"), "minecraft:ruined_portal_ocean"), "minecraft:ruined_portal")).put("pillager_outpost", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:pillager_outpost")).put("mansion", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:mansion")).put("jungle_pyramid", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:jungle_pyramid")).put("desert_pyramid", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:desert_pyramid")).put("igloo", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:igloo")).put("swamp_hut", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:swamp_hut")).put("stronghold", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:stronghold")).put("monument", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:monument")).put("fortress", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:fortress")).put("endcity", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:end_city")).put("buried_treasure", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:buried_treasure")).put("nether_fossil", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:nether_fossil")).put("bastion_remnant", StructuresBecomeConfiguredFix.Conversion.m_207746_("minecraft:bastion_remnant")).build();

   public StructuresBecomeConfiguredFix(Schema p_207679_) {
      super(p_207679_, false);
   }

   protected TypeRewriteRule makeRule() {
      Type<?> type = this.getInputSchema().getType(References.CHUNK);
      Type<?> type1 = this.getInputSchema().getType(References.CHUNK);
      return this.writeFixAndRead("StucturesToConfiguredStructures", type, type1, this::m_207691_);
   }

   private Dynamic<?> m_207691_(Dynamic<?> p_207692_) {
      return p_207692_.update("structures", (p_207728_) -> {
         return p_207728_.update("starts", (p_207734_) -> {
            return this.m_207699_(p_207734_, p_207692_);
         }).update("References", (p_207731_) -> {
            return this.m_207716_(p_207731_, p_207692_);
         });
      });
   }

   private Dynamic<?> m_207699_(Dynamic<?> p_207700_, Dynamic<?> p_207701_) {
      Map<? extends Dynamic<?>, ? extends Dynamic<?>> map = p_207700_.getMapValues().result().get();
      List<Dynamic<?>> list = new ArrayList<>();
      map.forEach((p_207721_, p_207722_) -> {
         if (p_207722_.get("id").asString("INVALID").equals("INVALID")) {
            list.add(p_207721_);
         }

      });

      for(Dynamic<?> dynamic : list) {
         p_207700_ = p_207700_.remove(dynamic.asString(""));
      }

      return p_207700_.updateMapValues((p_207715_) -> {
         return this.m_207684_(p_207715_, p_207701_);
      });
   }

   private Pair<Dynamic<?>, Dynamic<?>> m_207684_(Pair<Dynamic<?>, Dynamic<?>> p_207685_, Dynamic<?> p_207686_) {
      Dynamic<?> dynamic = this.m_207723_(p_207685_, p_207686_);
      return new Pair<>(dynamic, p_207685_.getSecond().set("id", dynamic));
   }

   private Dynamic<?> m_207716_(Dynamic<?> p_207717_, Dynamic<?> p_207718_) {
      Map<? extends Dynamic<?>, ? extends Dynamic<?>> map = p_207717_.getMapValues().result().get();
      List<Dynamic<?>> list = new ArrayList<>();
      map.forEach((p_207704_, p_207705_) -> {
         if (p_207705_.asLongStream().count() == 0L) {
            list.add(p_207704_);
         }

      });

      for(Dynamic<?> dynamic : list) {
         p_207717_ = p_207717_.remove(dynamic.asString(""));
      }

      return p_207717_.updateMapValues((p_207698_) -> {
         return this.m_207710_(p_207698_, p_207718_);
      });
   }

   private Pair<Dynamic<?>, Dynamic<?>> m_207710_(Pair<Dynamic<?>, Dynamic<?>> p_207711_, Dynamic<?> p_207712_) {
      return p_207711_.mapFirst((p_207690_) -> {
         return this.m_207723_(p_207711_, p_207712_);
      });
   }

   private Dynamic<?> m_207723_(Pair<Dynamic<?>, Dynamic<?>> p_207724_, Dynamic<?> p_207725_) {
      String s = p_207724_.getFirst().asString("UNKNOWN").toLowerCase(Locale.ROOT);
      StructuresBecomeConfiguredFix.Conversion structuresbecomeconfiguredfix$conversion = f_207676_.get(s);
      if (structuresbecomeconfiguredfix$conversion == null) {
         throw new IllegalStateException("Found unknown structure: " + s);
      } else {
         Dynamic<?> dynamic = p_207724_.getSecond();
         String s1 = structuresbecomeconfiguredfix$conversion.f_207737_;
         if (!structuresbecomeconfiguredfix$conversion.f_207736_().isEmpty()) {
            Optional<String> optional = this.m_207693_(p_207725_, structuresbecomeconfiguredfix$conversion);
            if (optional.isPresent()) {
               s1 = optional.get();
            }
         }

         Dynamic<?> dynamic1 = dynamic.createString(s1);
         return dynamic1;
      }
   }

   private Optional<String> m_207693_(Dynamic<?> p_207694_, StructuresBecomeConfiguredFix.Conversion p_207695_) {
      Object2IntArrayMap<String> object2intarraymap = new Object2IntArrayMap<>();
      p_207694_.get("sections").asList(Function.identity()).forEach((p_207683_) -> {
         p_207683_.get("biomes").get("palette").asList(Function.identity()).forEach((p_207709_) -> {
            String s = p_207695_.f_207736_().get(p_207709_.asString(""));
            if (s != null) {
               object2intarraymap.mergeInt(s, 1, Integer::sum);
            }

         });
      });
      return object2intarraymap.object2IntEntrySet().stream().max(Comparator.comparingInt(it.unimi.dsi.fastutil.objects.Object2IntMap.Entry::getIntValue)).map(Entry::getKey);
   }

   static record Conversion(Map<String, String> f_207736_, String f_207737_) {
      public static StructuresBecomeConfiguredFix.Conversion m_207746_(String p_207747_) {
         return new StructuresBecomeConfiguredFix.Conversion(Map.of(), p_207747_);
      }

      public static StructuresBecomeConfiguredFix.Conversion m_207750_(Map<List<String>, String> p_207751_, String p_207752_) {
         return new StructuresBecomeConfiguredFix.Conversion(m_207748_(p_207751_), p_207752_);
      }

      private static Map<String, String> m_207748_(Map<List<String>, String> p_207749_) {
         Builder<String, String> builder = ImmutableMap.builder();

         for(Entry<List<String>, String> entry : p_207749_.entrySet()) {
            entry.getKey().forEach((p_207745_) -> {
               builder.put(p_207745_, entry.getValue());
            });
         }

         return builder.build();
      }
   }
}