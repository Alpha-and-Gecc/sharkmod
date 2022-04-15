package net.minecraft.data.tags;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ConfiguredStructureTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

public class ConfiguredStructureTagsProvider extends TagsProvider<ConfiguredStructureFeature<?, ?>> {
   public ConfiguredStructureTagsProvider(DataGenerator p_211098_) {
      super(p_211098_, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE);
   }

   protected void addTags() {
      this.m_206424_(ConfiguredStructureTags.f_207637_).m_211101_(BuiltinStructures.f_209864_).m_211101_(BuiltinStructures.f_209865_).m_211101_(BuiltinStructures.f_209866_).m_211101_(BuiltinStructures.f_209867_).m_211101_(BuiltinStructures.f_209868_);
      this.m_206424_(ConfiguredStructureTags.f_207638_).m_211101_(BuiltinStructures.f_209846_).m_211101_(BuiltinStructures.f_209847_);
      this.m_206424_(ConfiguredStructureTags.f_207641_).m_211101_(BuiltinStructures.f_209857_).m_211101_(BuiltinStructures.f_209858_);
      this.m_206424_(ConfiguredStructureTags.f_207639_).m_211101_(BuiltinStructures.f_209852_).m_211101_(BuiltinStructures.f_209853_);
      this.m_206424_(ConfiguredStructureTags.f_207640_).m_211101_(BuiltinStructures.f_209870_).m_211101_(BuiltinStructures.f_209840_).m_211101_(BuiltinStructures.f_209842_).m_211101_(BuiltinStructures.f_209844_).m_211101_(BuiltinStructures.f_209843_).m_211101_(BuiltinStructures.f_209869_).m_211101_(BuiltinStructures.f_209841_);
      this.m_206424_(ConfiguredStructureTags.f_207632_).m_211101_(BuiltinStructures.f_209855_);
      this.m_206424_(ConfiguredStructureTags.f_207633_).m_206428_(ConfiguredStructureTags.f_207641_).m_206428_(ConfiguredStructureTags.f_207639_);
      this.m_206424_(ConfiguredStructureTags.f_207634_).m_211101_(BuiltinStructures.f_209848_);
      this.m_206424_(ConfiguredStructureTags.f_207635_).m_211101_(BuiltinStructures.f_209856_);
      this.m_206424_(ConfiguredStructureTags.f_207636_).m_211101_(BuiltinStructures.f_209862_);
   }

   public String getName() {
      return "Configured Structure Feature Tags";
   }
}