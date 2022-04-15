package net.minecraft.client;

import com.google.common.collect.ImmutableMap;
import com.google.common.math.LongMath;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2BooleanFunction;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class PeriodicNotificationManager extends SimplePreparableReloadListener<Map<String, List<PeriodicNotificationManager.Notification>>> implements AutoCloseable {
   private static final Codec<Map<String, List<PeriodicNotificationManager.Notification>>> f_205285_ = Codec.unboundedMap(Codec.STRING, RecordCodecBuilder.<PeriodicNotificationManager.Notification>create((p_205303_) -> {
      return p_205303_.group(Codec.LONG.optionalFieldOf("delay", Long.valueOf(0L)).forGetter(PeriodicNotificationManager.Notification::f_205328_), Codec.LONG.fieldOf("period").forGetter(PeriodicNotificationManager.Notification::f_205329_), Codec.STRING.fieldOf("title").forGetter(PeriodicNotificationManager.Notification::f_205330_), Codec.STRING.fieldOf("message").forGetter(PeriodicNotificationManager.Notification::f_205331_)).apply(p_205303_, PeriodicNotificationManager.Notification::new);
   }).listOf());
   private static final Logger f_205286_ = LogUtils.getLogger();
   private final ResourceLocation f_205287_;
   private final Object2BooleanFunction<String> f_205288_;
   @Nullable
   private java.util.Timer f_205289_;
   @Nullable
   private PeriodicNotificationManager.NotificationTask f_205290_;

   public PeriodicNotificationManager(ResourceLocation p_205293_, Object2BooleanFunction<String> p_205294_) {
      this.f_205287_ = p_205293_;
      this.f_205288_ = p_205294_;
   }

   protected Map<String, List<PeriodicNotificationManager.Notification>> prepare(ResourceManager p_205300_, ProfilerFiller p_205301_) {
      try {
         Resource resource = p_205300_.getResource(this.f_205287_);

         Map map;
         try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            try {
               map = f_205285_.parse(JsonOps.INSTANCE, JsonParser.parseReader(bufferedreader)).result().orElseThrow();
            } catch (Throwable throwable2) {
               try {
                  bufferedreader.close();
               } catch (Throwable throwable1) {
                  throwable2.addSuppressed(throwable1);
               }

               throw throwable2;
            }

            bufferedreader.close();
         } catch (Throwable throwable3) {
            if (resource != null) {
               try {
                  resource.close();
               } catch (Throwable throwable) {
                  throwable3.addSuppressed(throwable);
               }
            }

            throw throwable3;
         }

         if (resource != null) {
            resource.close();
         }

         return map;
      } catch (Exception exception) {
         f_205286_.warn("Failed to load {}", this.f_205287_, exception);
         return ImmutableMap.of();
      }
   }

   protected void apply(Map<String, List<PeriodicNotificationManager.Notification>> p_205318_, ResourceManager p_205319_, ProfilerFiller p_205320_) {
      List<PeriodicNotificationManager.Notification> list = p_205318_.entrySet().stream().filter((p_205316_) -> {
         return this.f_205288_.apply(p_205316_.getKey());
      }).map(Entry::getValue).flatMap(Collection::stream).collect(Collectors.toList());
      if (list.isEmpty()) {
         this.m_205295_();
      } else if (list.stream().anyMatch((p_205326_) -> {
         return p_205326_.f_205329_ == 0L;
      })) {
         Util.logAndPauseIfInIde("A periodic notification in " + this.f_205287_ + " has a period of zero minutes");
         this.m_205295_();
      } else {
         long i = this.m_205310_(list);
         long j = this.m_205312_(list, i);
         if (this.f_205289_ == null) {
            this.f_205289_ = new java.util.Timer();
         }

         if (this.f_205290_ == null) {
            this.f_205290_ = new PeriodicNotificationManager.NotificationTask(list, i, j);
         } else {
            this.f_205290_ = this.f_205290_.m_205356_(list, j);
         }

         this.f_205289_.scheduleAtFixedRate(this.f_205290_, TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(j));
      }
   }

   public void close() {
      this.m_205295_();
   }

   private void m_205295_() {
      if (this.f_205289_ != null) {
         this.f_205289_.cancel();
      }

   }

   private long m_205312_(List<PeriodicNotificationManager.Notification> p_205313_, long p_205314_) {
      return p_205313_.stream().mapToLong((p_205298_) -> {
         long i = p_205298_.f_205328_ - p_205314_;
         return LongMath.gcd(i, p_205298_.f_205329_);
      }).reduce(LongMath::gcd).orElseThrow(() -> {
         return new IllegalStateException("Empty notifications from: " + this.f_205287_);
      });
   }

   private long m_205310_(List<PeriodicNotificationManager.Notification> p_205311_) {
      return p_205311_.stream().mapToLong((p_205305_) -> {
         return p_205305_.f_205328_;
      }).min().orElse(0L);
   }

   @OnlyIn(Dist.CLIENT)
   public static record Notification(long f_205328_, long f_205329_, String f_205330_, String f_205331_) {
      public Notification(long f_205328_, long f_205329_, String f_205330_, String f_205331_) {
         this.f_205328_ = f_205328_ != 0L ? f_205328_ : f_205329_;
         this.f_205329_ = f_205329_;
         this.f_205330_ = f_205330_;
         this.f_205331_ = f_205331_;
      }
   }

   @OnlyIn(Dist.CLIENT)
   static class NotificationTask extends TimerTask {
      private final Minecraft f_205345_ = Minecraft.getInstance();
      private final List<PeriodicNotificationManager.Notification> f_205346_;
      private final long f_205347_;
      private final AtomicLong f_205348_;

      public NotificationTask(List<PeriodicNotificationManager.Notification> p_205350_, long p_205351_, long p_205352_) {
         this.f_205346_ = p_205350_;
         this.f_205347_ = p_205352_;
         this.f_205348_ = new AtomicLong(p_205351_);
      }

      public PeriodicNotificationManager.NotificationTask m_205356_(List<PeriodicNotificationManager.Notification> p_205357_, long p_205358_) {
         this.cancel();
         return new PeriodicNotificationManager.NotificationTask(p_205357_, this.f_205348_.get(), p_205358_);
      }

      public void run() {
         long i = this.f_205348_.getAndAdd(this.f_205347_);
         long j = this.f_205348_.get();

         for(PeriodicNotificationManager.Notification periodicnotificationmanager$notification : this.f_205346_) {
            if (i >= periodicnotificationmanager$notification.f_205328_) {
               long k = i / periodicnotificationmanager$notification.f_205329_;
               long l = j / periodicnotificationmanager$notification.f_205329_;
               if (k != l) {
                  this.f_205345_.execute(() -> {
                     SystemToast.add(Minecraft.getInstance().getToasts(), SystemToast.SystemToastIds.PERIODIC_NOTIFICATION, new TranslatableComponent(periodicnotificationmanager$notification.f_205330_, k), new TranslatableComponent(periodicnotificationmanager$notification.f_205331_, k));
                  });
                  return;
               }
            }
         }

      }
   }
}