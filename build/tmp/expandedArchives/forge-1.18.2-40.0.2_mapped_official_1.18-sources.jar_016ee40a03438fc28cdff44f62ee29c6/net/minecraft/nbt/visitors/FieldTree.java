package net.minecraft.nbt.visitors;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.TagType;

public record FieldTree(int f_202523_, Map<String, TagType<?>> f_202524_, Map<String, FieldTree> f_202525_) {
   private FieldTree(int p_202527_) {
      this(p_202527_, new HashMap<>(), new HashMap<>());
   }

   public static FieldTree m_202532_() {
      return new FieldTree(1);
   }

   public void m_202538_(FieldSelector p_202539_) {
      if (this.f_202523_ <= p_202539_.f_202497_().size()) {
         this.f_202525_.computeIfAbsent(p_202539_.f_202497_().get(this.f_202523_ - 1), (p_202534_) -> {
            return new FieldTree(this.f_202523_ + 1);
         }).m_202538_(p_202539_);
      } else {
         this.f_202524_.put(p_202539_.f_202499_(), p_202539_.f_202498_());
      }

   }

   public boolean m_202535_(TagType<?> p_202536_, String p_202537_) {
      return p_202536_.equals(this.f_202524_().get(p_202537_));
   }
}