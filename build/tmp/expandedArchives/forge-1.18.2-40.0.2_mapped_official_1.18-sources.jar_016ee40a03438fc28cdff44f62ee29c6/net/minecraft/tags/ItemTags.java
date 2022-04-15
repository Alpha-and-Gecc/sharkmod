package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class ItemTags {
   public static final TagKey<Item> WOOL = m_203854_("wool");
   public static final TagKey<Item> PLANKS = m_203854_("planks");
   public static final TagKey<Item> STONE_BRICKS = m_203854_("stone_bricks");
   public static final TagKey<Item> WOODEN_BUTTONS = m_203854_("wooden_buttons");
   public static final TagKey<Item> BUTTONS = m_203854_("buttons");
   public static final TagKey<Item> CARPETS = m_203854_("carpets");
   public static final TagKey<Item> WOODEN_DOORS = m_203854_("wooden_doors");
   public static final TagKey<Item> WOODEN_STAIRS = m_203854_("wooden_stairs");
   public static final TagKey<Item> WOODEN_SLABS = m_203854_("wooden_slabs");
   public static final TagKey<Item> WOODEN_FENCES = m_203854_("wooden_fences");
   public static final TagKey<Item> WOODEN_PRESSURE_PLATES = m_203854_("wooden_pressure_plates");
   public static final TagKey<Item> WOODEN_TRAPDOORS = m_203854_("wooden_trapdoors");
   public static final TagKey<Item> DOORS = m_203854_("doors");
   public static final TagKey<Item> SAPLINGS = m_203854_("saplings");
   public static final TagKey<Item> LOGS_THAT_BURN = m_203854_("logs_that_burn");
   public static final TagKey<Item> LOGS = m_203854_("logs");
   public static final TagKey<Item> DARK_OAK_LOGS = m_203854_("dark_oak_logs");
   public static final TagKey<Item> OAK_LOGS = m_203854_("oak_logs");
   public static final TagKey<Item> BIRCH_LOGS = m_203854_("birch_logs");
   public static final TagKey<Item> ACACIA_LOGS = m_203854_("acacia_logs");
   public static final TagKey<Item> JUNGLE_LOGS = m_203854_("jungle_logs");
   public static final TagKey<Item> SPRUCE_LOGS = m_203854_("spruce_logs");
   public static final TagKey<Item> CRIMSON_STEMS = m_203854_("crimson_stems");
   public static final TagKey<Item> WARPED_STEMS = m_203854_("warped_stems");
   public static final TagKey<Item> BANNERS = m_203854_("banners");
   public static final TagKey<Item> SAND = m_203854_("sand");
   public static final TagKey<Item> STAIRS = m_203854_("stairs");
   public static final TagKey<Item> SLABS = m_203854_("slabs");
   public static final TagKey<Item> WALLS = m_203854_("walls");
   public static final TagKey<Item> ANVIL = m_203854_("anvil");
   public static final TagKey<Item> RAILS = m_203854_("rails");
   public static final TagKey<Item> LEAVES = m_203854_("leaves");
   public static final TagKey<Item> TRAPDOORS = m_203854_("trapdoors");
   public static final TagKey<Item> SMALL_FLOWERS = m_203854_("small_flowers");
   public static final TagKey<Item> BEDS = m_203854_("beds");
   public static final TagKey<Item> FENCES = m_203854_("fences");
   public static final TagKey<Item> TALL_FLOWERS = m_203854_("tall_flowers");
   public static final TagKey<Item> FLOWERS = m_203854_("flowers");
   public static final TagKey<Item> PIGLIN_REPELLENTS = m_203854_("piglin_repellents");
   public static final TagKey<Item> PIGLIN_LOVED = m_203854_("piglin_loved");
   public static final TagKey<Item> IGNORED_BY_PIGLIN_BABIES = m_203854_("ignored_by_piglin_babies");
   public static final TagKey<Item> PIGLIN_FOOD = m_203854_("piglin_food");
   public static final TagKey<Item> FOX_FOOD = m_203854_("fox_food");
   public static final TagKey<Item> GOLD_ORES = m_203854_("gold_ores");
   public static final TagKey<Item> IRON_ORES = m_203854_("iron_ores");
   public static final TagKey<Item> DIAMOND_ORES = m_203854_("diamond_ores");
   public static final TagKey<Item> REDSTONE_ORES = m_203854_("redstone_ores");
   public static final TagKey<Item> LAPIS_ORES = m_203854_("lapis_ores");
   public static final TagKey<Item> COAL_ORES = m_203854_("coal_ores");
   public static final TagKey<Item> EMERALD_ORES = m_203854_("emerald_ores");
   public static final TagKey<Item> COPPER_ORES = m_203854_("copper_ores");
   public static final TagKey<Item> NON_FLAMMABLE_WOOD = m_203854_("non_flammable_wood");
   public static final TagKey<Item> SOUL_FIRE_BASE_BLOCKS = m_203854_("soul_fire_base_blocks");
   public static final TagKey<Item> CANDLES = m_203854_("candles");
   public static final TagKey<Item> DIRT = m_203854_("dirt");
   public static final TagKey<Item> TERRACOTTA = m_203854_("terracotta");
   public static final TagKey<Item> BOATS = m_203854_("boats");
   public static final TagKey<Item> FISHES = m_203854_("fishes");
   public static final TagKey<Item> SIGNS = m_203854_("signs");
   public static final TagKey<Item> MUSIC_DISCS = m_203854_("music_discs");
   public static final TagKey<Item> CREEPER_DROP_MUSIC_DISCS = m_203854_("creeper_drop_music_discs");
   public static final TagKey<Item> COALS = m_203854_("coals");
   public static final TagKey<Item> ARROWS = m_203854_("arrows");
   public static final TagKey<Item> LECTERN_BOOKS = m_203854_("lectern_books");
   public static final TagKey<Item> BEACON_PAYMENT_ITEMS = m_203854_("beacon_payment_items");
   public static final TagKey<Item> STONE_TOOL_MATERIALS = m_203854_("stone_tool_materials");
   public static final TagKey<Item> STONE_CRAFTING_MATERIALS = m_203854_("stone_crafting_materials");
   public static final TagKey<Item> FREEZE_IMMUNE_WEARABLES = m_203854_("freeze_immune_wearables");
   public static final TagKey<Item> AXOLOTL_TEMPT_ITEMS = m_203854_("axolotl_tempt_items");
   public static final TagKey<Item> OCCLUDES_VIBRATION_SIGNALS = m_203854_("occludes_vibration_signals");
   public static final TagKey<Item> CLUSTER_MAX_HARVESTABLES = m_203854_("cluster_max_harvestables");

   private ItemTags() {
   }

   private static TagKey<Item> m_203854_(String p_203855_) {
      return TagKey.m_203882_(Registry.ITEM_REGISTRY, new ResourceLocation(p_203855_));
   }

   public static TagKey<Item> create(final ResourceLocation name) {
      return TagKey.m_203882_(Registry.ITEM_REGISTRY, name);
   }
}
