package net.minecraft.data.tags;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class ItemTagsProvider extends TagsProvider<Item> {
   private final Function<TagKey<Block>, Tag.Builder> blockTags;

   @Deprecated
   public ItemTagsProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_) {
      super(p_126530_, Registry.ITEM);
      this.blockTags = p_126531_::m_206426_;
   }
   public ItemTagsProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, String modId, @javax.annotation.Nullable net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
      super(p_126530_, Registry.ITEM, modId, existingFileHelper);
      this.blockTags = p_126531_::m_206426_;
   }

   protected void addTags() {
      this.m_206421_(BlockTags.WOOL, ItemTags.WOOL);
      this.m_206421_(BlockTags.PLANKS, ItemTags.PLANKS);
      this.m_206421_(BlockTags.STONE_BRICKS, ItemTags.STONE_BRICKS);
      this.m_206421_(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
      this.m_206421_(BlockTags.BUTTONS, ItemTags.BUTTONS);
      this.m_206421_(BlockTags.CARPETS, ItemTags.CARPETS);
      this.m_206421_(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
      this.m_206421_(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
      this.m_206421_(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
      this.m_206421_(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
      this.m_206421_(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
      this.m_206421_(BlockTags.DOORS, ItemTags.DOORS);
      this.m_206421_(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
      this.m_206421_(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);
      this.m_206421_(BlockTags.DARK_OAK_LOGS, ItemTags.DARK_OAK_LOGS);
      this.m_206421_(BlockTags.BIRCH_LOGS, ItemTags.BIRCH_LOGS);
      this.m_206421_(BlockTags.ACACIA_LOGS, ItemTags.ACACIA_LOGS);
      this.m_206421_(BlockTags.SPRUCE_LOGS, ItemTags.SPRUCE_LOGS);
      this.m_206421_(BlockTags.JUNGLE_LOGS, ItemTags.JUNGLE_LOGS);
      this.m_206421_(BlockTags.CRIMSON_STEMS, ItemTags.CRIMSON_STEMS);
      this.m_206421_(BlockTags.WARPED_STEMS, ItemTags.WARPED_STEMS);
      this.m_206421_(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
      this.m_206421_(BlockTags.LOGS, ItemTags.LOGS);
      this.m_206421_(BlockTags.SAND, ItemTags.SAND);
      this.m_206421_(BlockTags.SLABS, ItemTags.SLABS);
      this.m_206421_(BlockTags.WALLS, ItemTags.WALLS);
      this.m_206421_(BlockTags.STAIRS, ItemTags.STAIRS);
      this.m_206421_(BlockTags.ANVIL, ItemTags.ANVIL);
      this.m_206421_(BlockTags.RAILS, ItemTags.RAILS);
      this.m_206421_(BlockTags.LEAVES, ItemTags.LEAVES);
      this.m_206421_(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
      this.m_206421_(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
      this.m_206421_(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
      this.m_206421_(BlockTags.BEDS, ItemTags.BEDS);
      this.m_206421_(BlockTags.FENCES, ItemTags.FENCES);
      this.m_206421_(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
      this.m_206421_(BlockTags.FLOWERS, ItemTags.FLOWERS);
      this.m_206421_(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
      this.m_206421_(BlockTags.CANDLES, ItemTags.CANDLES);
      this.m_206421_(BlockTags.OCCLUDES_VIBRATION_SIGNALS, ItemTags.OCCLUDES_VIBRATION_SIGNALS);
      this.m_206421_(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES);
      this.m_206421_(BlockTags.IRON_ORES, ItemTags.IRON_ORES);
      this.m_206421_(BlockTags.DIAMOND_ORES, ItemTags.DIAMOND_ORES);
      this.m_206421_(BlockTags.REDSTONE_ORES, ItemTags.REDSTONE_ORES);
      this.m_206421_(BlockTags.LAPIS_ORES, ItemTags.LAPIS_ORES);
      this.m_206421_(BlockTags.COAL_ORES, ItemTags.COAL_ORES);
      this.m_206421_(BlockTags.EMERALD_ORES, ItemTags.EMERALD_ORES);
      this.m_206421_(BlockTags.COPPER_ORES, ItemTags.COPPER_ORES);
      this.m_206421_(BlockTags.DIRT, ItemTags.DIRT);
      this.m_206421_(BlockTags.TERRACOTTA, ItemTags.TERRACOTTA);
      this.m_206424_(ItemTags.BANNERS).add(Items.WHITE_BANNER, Items.ORANGE_BANNER, Items.MAGENTA_BANNER, Items.LIGHT_BLUE_BANNER, Items.YELLOW_BANNER, Items.LIME_BANNER, Items.PINK_BANNER, Items.GRAY_BANNER, Items.LIGHT_GRAY_BANNER, Items.CYAN_BANNER, Items.PURPLE_BANNER, Items.BLUE_BANNER, Items.BROWN_BANNER, Items.GREEN_BANNER, Items.RED_BANNER, Items.BLACK_BANNER);
      this.m_206424_(ItemTags.BOATS).add(Items.OAK_BOAT, Items.SPRUCE_BOAT, Items.BIRCH_BOAT, Items.JUNGLE_BOAT, Items.ACACIA_BOAT, Items.DARK_OAK_BOAT);
      this.m_206424_(ItemTags.FISHES).add(Items.COD, Items.COOKED_COD, Items.SALMON, Items.COOKED_SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH);
      this.m_206421_(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
      this.m_206424_(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(Items.MUSIC_DISC_13, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD, Items.MUSIC_DISC_WARD, Items.MUSIC_DISC_11, Items.MUSIC_DISC_WAIT);
      this.m_206424_(ItemTags.MUSIC_DISCS).m_206428_(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(Items.MUSIC_DISC_PIGSTEP).add(Items.MUSIC_DISC_OTHERSIDE);
      this.m_206424_(ItemTags.COALS).add(Items.COAL, Items.CHARCOAL);
      this.m_206424_(ItemTags.ARROWS).add(Items.ARROW, Items.TIPPED_ARROW, Items.SPECTRAL_ARROW);
      this.m_206424_(ItemTags.LECTERN_BOOKS).add(Items.WRITTEN_BOOK, Items.WRITABLE_BOOK);
      this.m_206424_(ItemTags.BEACON_PAYMENT_ITEMS).add(Items.NETHERITE_INGOT, Items.EMERALD, Items.DIAMOND, Items.GOLD_INGOT, Items.IRON_INGOT);
      this.m_206424_(ItemTags.PIGLIN_REPELLENTS).add(Items.SOUL_TORCH).add(Items.SOUL_LANTERN).add(Items.SOUL_CAMPFIRE);
      this.m_206424_(ItemTags.PIGLIN_LOVED).m_206428_(ItemTags.GOLD_ORES).add(Items.GOLD_BLOCK, Items.GILDED_BLACKSTONE, Items.LIGHT_WEIGHTED_PRESSURE_PLATE, Items.GOLD_INGOT, Items.BELL, Items.CLOCK, Items.GOLDEN_CARROT, Items.GLISTERING_MELON_SLICE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR, Items.GOLDEN_SWORD, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.RAW_GOLD, Items.RAW_GOLD_BLOCK);
      this.m_206424_(ItemTags.IGNORED_BY_PIGLIN_BABIES).add(Items.LEATHER);
      this.m_206424_(ItemTags.PIGLIN_FOOD).add(Items.PORKCHOP, Items.COOKED_PORKCHOP);
      this.m_206424_(ItemTags.FOX_FOOD).add(Items.SWEET_BERRIES, Items.GLOW_BERRIES);
      this.m_206424_(ItemTags.NON_FLAMMABLE_WOOD).add(Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM, Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE, Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM, Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE, Items.CRIMSON_PLANKS, Items.WARPED_PLANKS, Items.CRIMSON_SLAB, Items.WARPED_SLAB, Items.CRIMSON_PRESSURE_PLATE, Items.WARPED_PRESSURE_PLATE, Items.CRIMSON_FENCE, Items.WARPED_FENCE, Items.CRIMSON_TRAPDOOR, Items.WARPED_TRAPDOOR, Items.CRIMSON_FENCE_GATE, Items.WARPED_FENCE_GATE, Items.CRIMSON_STAIRS, Items.WARPED_STAIRS, Items.CRIMSON_BUTTON, Items.WARPED_BUTTON, Items.CRIMSON_DOOR, Items.WARPED_DOOR, Items.CRIMSON_SIGN, Items.WARPED_SIGN);
      this.m_206424_(ItemTags.STONE_TOOL_MATERIALS).add(Items.COBBLESTONE, Items.BLACKSTONE, Items.COBBLED_DEEPSLATE);
      this.m_206424_(ItemTags.STONE_CRAFTING_MATERIALS).add(Items.COBBLESTONE, Items.BLACKSTONE, Items.COBBLED_DEEPSLATE);
      this.m_206424_(ItemTags.FREEZE_IMMUNE_WEARABLES).add(Items.LEATHER_BOOTS, Items.LEATHER_LEGGINGS, Items.LEATHER_CHESTPLATE, Items.LEATHER_HELMET, Items.LEATHER_HORSE_ARMOR);
      this.m_206424_(ItemTags.AXOLOTL_TEMPT_ITEMS).add(Items.TROPICAL_FISH_BUCKET);
      this.m_206424_(ItemTags.CLUSTER_MAX_HARVESTABLES).add(Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE, Items.IRON_PICKAXE, Items.NETHERITE_PICKAXE, Items.STONE_PICKAXE, Items.WOODEN_PICKAXE);
   }

   protected void m_206421_(TagKey<Block> p_206422_, TagKey<Item> p_206423_) {
      Tag.Builder tag$builder = this.m_206426_(p_206423_);
      Tag.Builder tag$builder1 = this.blockTags.apply(p_206422_);
      tag$builder1.getEntries().forEach(tag$builder::add);
   }

   public String getName() {
      return "Item Tags";
   }
}
