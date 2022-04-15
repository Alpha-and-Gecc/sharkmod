package io.github.alpha_and_gecc.core.init;

import io.github.alpha_and_gecc.SharkMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class SharkModBlocks {

	private SharkModBlocks() {
	}

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			SharkMod.MODID);

	public static final RegistryObject<Block> VOID_BOUNCER = BLOCKS.register("void_bouncer",
			() -> new SlimeBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.COLOR_MAGENTA).strength(0.2f, 20f)
					.lightLevel(GlowLichenBlock.emission(7)).friction(0.5f).sound(SoundType.SLIME_BLOCK)
					.jumpFactor(1.2f)));
	
	public static final RegistryObject<Block> SANDBARTEETH_BLOCK = BLOCKS.register("sandbarteeth_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).strength(1.2f, 25f)
					.sound(SoundType.CHAIN)));
}
