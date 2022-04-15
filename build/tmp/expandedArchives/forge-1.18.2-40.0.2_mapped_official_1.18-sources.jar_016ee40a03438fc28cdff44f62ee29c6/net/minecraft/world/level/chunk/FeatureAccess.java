package net.minecraft.world.level.chunk;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;

public interface FeatureAccess {
   @Nullable
   StructureStart m_207072_(ConfiguredStructureFeature<?, ?> p_208099_);

   void m_207296_(ConfiguredStructureFeature<?, ?> p_208102_, StructureStart p_208103_);

   LongSet m_207420_(ConfiguredStructureFeature<?, ?> p_208104_);

   void m_207350_(ConfiguredStructureFeature<?, ?> p_208100_, long p_208101_);

   Map<ConfiguredStructureFeature<?, ?>, LongSet> getAllReferences();

   void setAllReferences(Map<ConfiguredStructureFeature<?, ?>, LongSet> p_62638_);
}