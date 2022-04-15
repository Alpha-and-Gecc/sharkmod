package net.minecraft.client.tutorial;

import net.minecraft.client.gui.components.toasts.TutorialToast;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CraftPlanksTutorialStep implements TutorialStepInstance {
   private static final int HINT_DELAY = 1200;
   private static final Component CRAFT_TITLE = new TranslatableComponent("tutorial.craft_planks.title");
   private static final Component CRAFT_DESCRIPTION = new TranslatableComponent("tutorial.craft_planks.description");
   private final Tutorial tutorial;
   private TutorialToast toast;
   private int timeWaiting;

   public CraftPlanksTutorialStep(Tutorial p_120467_) {
      this.tutorial = p_120467_;
   }

   public void tick() {
      ++this.timeWaiting;
      if (!this.tutorial.isSurvival()) {
         this.tutorial.setStep(TutorialSteps.NONE);
      } else {
         if (this.timeWaiting == 1) {
            LocalPlayer localplayer = this.tutorial.getMinecraft().player;
            if (localplayer != null) {
               if (localplayer.getInventory().m_204075_(ItemTags.PLANKS)) {
                  this.tutorial.setStep(TutorialSteps.NONE);
                  return;
               }

               if (m_205662_(localplayer, ItemTags.PLANKS)) {
                  this.tutorial.setStep(TutorialSteps.NONE);
                  return;
               }
            }
         }

         if (this.timeWaiting >= 1200 && this.toast == null) {
            this.toast = new TutorialToast(TutorialToast.Icons.WOODEN_PLANKS, CRAFT_TITLE, CRAFT_DESCRIPTION, false);
            this.tutorial.getMinecraft().getToasts().addToast(this.toast);
         }

      }
   }

   public void clear() {
      if (this.toast != null) {
         this.toast.hide();
         this.toast = null;
      }

   }

   public void onGetItem(ItemStack p_120470_) {
      if (p_120470_.m_204117_(ItemTags.PLANKS)) {
         this.tutorial.setStep(TutorialSteps.NONE);
      }

   }

   public static boolean m_205662_(LocalPlayer p_205663_, TagKey<Item> p_205664_) {
      for(Holder<Item> holder : Registry.ITEM.m_206058_(p_205664_)) {
         if (p_205663_.getStats().getValue(Stats.ITEM_CRAFTED.get(holder.m_203334_())) > 0) {
            return true;
         }
      }

      return false;
   }
}