package io.github.alpha_and_gecc.core.init;

import io.github.alpha_and_gecc.SharkMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class SharkmodItems {

	private SharkmodItems() {
	}

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SharkMod.MODID);

	public static final RegistryObject<Item> raw_loli = ITEMS.register("raw_loli",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(1).saturationMod(0)
							.effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 1), 1).build())));
	// raw cephalurus cephalus(lollipop catshark)

	public static final RegistryObject<Item> raw_roughshark = ITEMS.register("raw_roughshark",
			() -> new Item(
					new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(1)
							.saturationMod(2).effect(() -> new MobEffectInstance(MobEffects.HARM, 1), 1).build())));
	// raw caribbean roughshark

	public static final RegistryObject<Item> raw_bramble = ITEMS.register("raw_bramble",
			() -> new Item(
					new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(3)
							.saturationMod(4).effect(() -> new MobEffectInstance(MobEffects.HARM, 2), 1).build())));
	// raw brambleshark

	public static final RegistryObject<Item> raw_angel = ITEMS.register("raw_angel",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod(2).build())));
	// raw angelshark

	public static final RegistryObject<Item> raw_spurdog = ITEMS.register("raw_spurdog",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(1).saturationMod(1)
							.effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 1), 1).build())));;
	// raw spiny dogfish

	public static final RegistryObject<Item> raw_swellie = ITEMS.register("raw_swellie",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod(2).build())));;
	// raw swellshark

	public static final RegistryObject<Item> raw_pseudocarcharias = ITEMS.register("raw_pseudocarcharias",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(3).saturationMod(2).build())));;
	// raw crocodile shark

	public static final RegistryObject<Item> raw_pseudotriakis = ITEMS.register("raw_pseudotriakis",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(4).saturationMod(2).build())));;
	// raw false catshark

	public static final RegistryObject<Item> raw_birdbeak = ITEMS.register("raw_birdbeak",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(1).saturationMod(1).build())));;
	// raw birdbeak dogfish

	public static final RegistryObject<Item> raw_cutter = ITEMS.register("raw_cutter",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod((float) 0.4).build())));;
	// raw cookiecutter

	public static final RegistryObject<Item> raw_sawshark = ITEMS.register("raw_sawshark",
			() -> new Item(
					new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(1)
							.saturationMod(3).effect(() -> new MobEffectInstance(MobEffects.HARM, 1), 1).build())));
	// raw sawshark

	public static final RegistryObject<Item> raw_eppie = ITEMS.register("raw_eppie",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod(2).build())));;
	// raw epaulette

	public static final RegistryObject<Item> raw_gulper = ITEMS.register("raw_gulper",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));;
	// raw gulper shark

	public static final RegistryObject<Item> raw_prionace = ITEMS.register("raw_prionace",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(1).saturationMod(2).build())));;
	// raw blue shark steak

	public static final RegistryObject<Item> raw_sandbar = ITEMS.register("raw_sandbar",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));;
	// raw sandbar shark fillet

	public static final RegistryObject<Item> cooked_angelshark = ITEMS.register("cooked_angelshark",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(6).saturationMod(6).build())));;
	// cooked angelshark

	public static final RegistryObject<Item> cooked_cutter = ITEMS.register("cooked_cutter",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(4).saturationMod(2).build())));;
	// cooked cookiecutter

	public static final RegistryObject<Item> cooked_birdbeak = ITEMS.register("cooked_birdbeak",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(4).saturationMod(4).build())));;
	// cooked birdbeak

	public static final RegistryObject<Item> cooked_bramble = ITEMS.register("cooked_bramble",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
					.food(new FoodProperties.Builder().nutrition(6).saturationMod(4).build())));;
	// cooked brambleshark

	public static final RegistryObject<BlockItem> void_bouncer_item = ITEMS.register("void_bouncer",
			() -> new BlockItem(SharkModBlocks.VOID_BOUNCER.get(),
					new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));;
	// BOUNCER BLOCK

	public static final RegistryObject<BlockItem> sandbarteeth_block_item = ITEMS.register("sandbarteeth_block",
			() -> new BlockItem(SharkModBlocks.SANDBARTEETH_BLOCK.get(),
					new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));;
	// BOUNCER BLOCK
}
