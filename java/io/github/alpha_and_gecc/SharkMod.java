package io.github.alpha_and_gecc;
import io.github.alpha_and_gecc.core.init.SharkModBlocks;
import io.github.alpha_and_gecc.core.init.SharkmodItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(SharkMod.MODID)
public class SharkMod {
	public static final String MODID = "sharkmod";
	
	public SharkMod() {
		var bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		SharkModBlocks.BLOCKS.register(bus);
		SharkmodItems.ITEMS.register(bus);
	}
}
