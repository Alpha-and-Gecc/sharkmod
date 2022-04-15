package io.github.alpha_and_gecc.core.init; 

import io.github.alpha_and_gecc.SharkMod;
import io.github.alpha_and_gecc.common.entity.Spurdog;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class SharkModEntities {

	private SharkModEntities() {
	}

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			SharkMod.MODID);

	public static final RegistryObject<EntityType<Spurdog>> SPURDOG = ENTITIES.register("Spurdog",
			() -> EntityType.Builder.of(Spurdog::new, MobCategory.WATER_CREATURE).sized(1f,1f)
					.build(new ResourceLocation(SharkMod.MODID, "Spurdog").toString()));
}
