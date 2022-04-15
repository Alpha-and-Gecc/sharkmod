package net.minecraft.client.gui.screens.multiplayer;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Realms32bitWarningScreen extends WarningScreen {
   private static final Component f_210892_ = (new TranslatableComponent("title.32bit.deprecation.realms.header")).withStyle(ChatFormatting.BOLD);
   private static final Component f_210893_ = new TranslatableComponent("title.32bit.deprecation.realms");
   private static final Component f_210894_ = new TranslatableComponent("title.32bit.deprecation.realms.check");
   private static final Component f_210895_ = f_210892_.copy().append("\n").append(f_210893_);

   public Realms32bitWarningScreen(Screen p_210898_) {
      super(f_210892_, f_210893_, f_210894_, f_210895_, p_210898_);
   }

   protected void m_207212_(int p_210900_) {
      this.addRenderableWidget(new Button(this.width / 2 - 75, 100 + p_210900_, 150, 20, CommonComponents.GUI_DONE, (p_210902_) -> {
         if (this.f_210910_.selected()) {
            this.minecraft.options.f_210816_ = true;
            this.minecraft.options.save();
         }

         this.minecraft.setScreen(this.f_210909_);
      }));
   }
}