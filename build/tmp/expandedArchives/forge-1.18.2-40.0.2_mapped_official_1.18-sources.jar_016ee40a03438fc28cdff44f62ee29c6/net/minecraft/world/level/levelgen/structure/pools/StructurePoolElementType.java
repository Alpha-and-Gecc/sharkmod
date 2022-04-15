package net.minecraft.world.level.levelgen.structure.pools;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;

public interface StructurePoolElementType<P extends StructurePoolElement> {
   StructurePoolElementType<SinglePoolElement> f_210542_ = m_210550_("single_pool_element", SinglePoolElement.f_210410_);
   StructurePoolElementType<ListPoolElement> f_210543_ = m_210550_("list_pool_element", ListPoolElement.f_210359_);
   StructurePoolElementType<FeaturePoolElement> f_210544_ = m_210550_("feature_pool_element", FeaturePoolElement.f_210204_);
   StructurePoolElementType<EmptyPoolElement> f_210545_ = m_210550_("empty_pool_element", EmptyPoolElement.f_210174_);
   StructurePoolElementType<LegacySinglePoolElement> f_210546_ = m_210550_("legacy_single_pool_element", LegacySinglePoolElement.f_210345_);

   Codec<P> m_210553_();

   static <P extends StructurePoolElement> StructurePoolElementType<P> m_210550_(String p_210551_, Codec<P> p_210552_) {
      return Registry.register(Registry.STRUCTURE_POOL_ELEMENT, p_210551_, () -> {
         return p_210552_;
      });
   }
}