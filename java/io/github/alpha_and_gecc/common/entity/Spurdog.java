package io.github.alpha_and_gecc.common.entity;

import net.minecraft.world.entity.animal.Animal;
import io.github.alpha_and_gecc.core.init.SharkModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class Spurdog extends Animal{

	public Spurdog(EntityType<? extends Animal> entityType, Level level) {
		super(entityType, level);
	}
	
	

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		// TODO Auto-generated method stub
		return SharkModEntities.SPURDOG.get().create(level);
	}


}
