package net.minecraft.nbt.visitors;

import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StreamTagVisitor;
import net.minecraft.nbt.TagType;

public class SkipFields extends CollectToTag {
   private final Deque<FieldTree> f_202547_ = new ArrayDeque<>();

   public SkipFields(FieldSelector... p_202549_) {
      FieldTree fieldtree = FieldTree.m_202532_();

      for(FieldSelector fieldselector : p_202549_) {
         fieldtree.m_202538_(fieldselector);
      }

      this.f_202547_.push(fieldtree);
   }

   public StreamTagVisitor.EntryResult visitEntry(TagType<?> p_202551_, String p_202552_) {
      FieldTree fieldtree = this.f_202547_.element();
      if (fieldtree.m_202535_(p_202551_, p_202552_)) {
         return StreamTagVisitor.EntryResult.SKIP;
      } else {
         if (p_202551_ == CompoundTag.TYPE) {
            FieldTree fieldtree1 = fieldtree.f_202525_().get(p_202552_);
            if (fieldtree1 != null) {
               this.f_202547_.push(fieldtree1);
            }
         }

         return super.visitEntry(p_202551_, p_202552_);
      }
   }

   public StreamTagVisitor.ValueResult visitContainerEnd() {
      if (this.depth() == this.f_202547_.element().f_202523_()) {
         this.f_202547_.pop();
      }

      return super.visitContainerEnd();
   }
}