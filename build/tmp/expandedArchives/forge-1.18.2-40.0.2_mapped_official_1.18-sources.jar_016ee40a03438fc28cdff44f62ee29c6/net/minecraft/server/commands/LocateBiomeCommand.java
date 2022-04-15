package net.minecraft.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.datafixers.util.Pair;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceOrTagLocationArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.biome.Biome;

public class LocateBiomeCommand {
   private static final DynamicCommandExceptionType ERROR_BIOME_NOT_FOUND = new DynamicCommandExceptionType((p_137850_) -> {
      return new TranslatableComponent("commands.locatebiome.notFound", p_137850_);
   });
   private static final int MAX_SEARCH_RADIUS = 6400;
   private static final int SEARCH_STEP = 8;

   public static void register(CommandDispatcher<CommandSourceStack> p_137837_) {
      p_137837_.register(Commands.literal("locatebiome").requires((p_201816_) -> {
         return p_201816_.hasPermission(2);
      }).then(Commands.argument("biome", ResourceOrTagLocationArgument.m_210968_(Registry.BIOME_REGISTRY)).executes((p_201814_) -> {
         return m_207503_(p_201814_.getSource(), ResourceOrTagLocationArgument.m_210952_(p_201814_, "biome"));
      })));
   }

   private static int m_207503_(CommandSourceStack p_207504_, ResourceOrTagLocationArgument.Result<Biome> p_207505_) throws CommandSyntaxException {
      BlockPos blockpos = new BlockPos(p_207504_.getPosition());
      Pair<BlockPos, Holder<Biome>> pair = p_207504_.getLevel().m_207571_(p_207505_, blockpos, 6400, 8);
      if (pair == null) {
         throw ERROR_BIOME_NOT_FOUND.create(p_207505_.m_207276_());
      } else {
         return LocateCommand.m_207517_(p_207504_, p_207505_, blockpos, pair, "commands.locatebiome.success");
      }
   }
}