package net.minecraft.client.gui.screens.multiplayer;

import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class WarningScreen extends Screen {
   private final Component f_210911_;
   private final Component f_210912_;
   private final Component f_210913_;
   private final Component f_210914_;
   protected final Screen f_210909_;
   @Nullable
   protected Checkbox f_210910_;
   private MultiLineLabel f_210915_ = MultiLineLabel.EMPTY;

   protected WarningScreen(Component p_210917_, Component p_210918_, Component p_210919_, Component p_210920_, Screen p_210921_) {
      super(NarratorChatListener.NO_TITLE);
      this.f_210911_ = p_210917_;
      this.f_210912_ = p_210918_;
      this.f_210913_ = p_210919_;
      this.f_210914_ = p_210920_;
      this.f_210909_ = p_210921_;
   }

   protected abstract void m_207212_(int p_210922_);

   protected void init() {
      super.init();
      this.f_210915_ = MultiLineLabel.create(this.font, this.f_210912_, this.width - 50);
      int i = (this.f_210915_.getLineCount() + 1) * 9 * 2;
      this.f_210910_ = new Checkbox(this.width / 2 - 155 + 80, 76 + i, 150, 20, this.f_210913_, false);
      this.addRenderableWidget(this.f_210910_);
      this.m_207212_(i);
   }

   public Component getNarrationMessage() {
      return this.f_210914_;
   }

   public void render(PoseStack p_210924_, int p_210925_, int p_210926_, float p_210927_) {
      this.renderDirtBackground(0);
      drawString(p_210924_, this.font, this.f_210911_, 25, 30, 16777215);
      this.f_210915_.renderLeftAligned(p_210924_, 25, 70, 9 * 2, 16777215);
      super.render(p_210924_, p_210925_, p_210926_, p_210927_);
   }
}