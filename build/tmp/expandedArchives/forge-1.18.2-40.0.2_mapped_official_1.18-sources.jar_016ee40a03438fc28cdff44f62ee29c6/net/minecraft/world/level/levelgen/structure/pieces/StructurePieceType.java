package net.minecraft.world.level.levelgen.structure.pieces;

import java.util.Locale;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.levelgen.structure.BuriedTreasurePieces;
import net.minecraft.world.level.levelgen.structure.DesertPyramidPiece;
import net.minecraft.world.level.levelgen.structure.EndCityPieces;
import net.minecraft.world.level.levelgen.structure.IglooPieces;
import net.minecraft.world.level.levelgen.structure.JunglePyramidPiece;
import net.minecraft.world.level.levelgen.structure.MineShaftPieces;
import net.minecraft.world.level.levelgen.structure.NetherBridgePieces;
import net.minecraft.world.level.levelgen.structure.NetherFossilPieces;
import net.minecraft.world.level.levelgen.structure.OceanMonumentPieces;
import net.minecraft.world.level.levelgen.structure.OceanRuinPieces;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.RuinedPortalPiece;
import net.minecraft.world.level.levelgen.structure.ShipwreckPieces;
import net.minecraft.world.level.levelgen.structure.StrongholdPieces;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.SwamplandHutPiece;
import net.minecraft.world.level.levelgen.structure.WoodlandMansionPieces;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public interface StructurePieceType {
   StructurePieceType f_210121_ = m_210152_(MineShaftPieces.MineShaftCorridor::new, "MSCorridor");
   StructurePieceType f_210126_ = m_210152_(MineShaftPieces.MineShaftCrossing::new, "MSCrossing");
   StructurePieceType f_210127_ = m_210152_(MineShaftPieces.MineShaftRoom::new, "MSRoom");
   StructurePieceType f_210128_ = m_210152_(MineShaftPieces.MineShaftStairs::new, "MSStairs");
   StructurePieceType f_210129_ = m_210152_(NetherBridgePieces.BridgeCrossing::new, "NeBCr");
   StructurePieceType f_210130_ = m_210152_(NetherBridgePieces.BridgeEndFiller::new, "NeBEF");
   StructurePieceType f_210131_ = m_210152_(NetherBridgePieces.BridgeStraight::new, "NeBS");
   StructurePieceType f_210132_ = m_210152_(NetherBridgePieces.CastleCorridorStairsPiece::new, "NeCCS");
   StructurePieceType f_210133_ = m_210152_(NetherBridgePieces.CastleCorridorTBalconyPiece::new, "NeCTB");
   StructurePieceType f_210134_ = m_210152_(NetherBridgePieces.CastleEntrance::new, "NeCE");
   StructurePieceType f_210135_ = m_210152_(NetherBridgePieces.CastleSmallCorridorCrossingPiece::new, "NeSCSC");
   StructurePieceType f_210136_ = m_210152_(NetherBridgePieces.CastleSmallCorridorLeftTurnPiece::new, "NeSCLT");
   StructurePieceType f_210137_ = m_210152_(NetherBridgePieces.CastleSmallCorridorPiece::new, "NeSC");
   StructurePieceType f_210138_ = m_210152_(NetherBridgePieces.CastleSmallCorridorRightTurnPiece::new, "NeSCRT");
   StructurePieceType f_210139_ = m_210152_(NetherBridgePieces.CastleStalkRoom::new, "NeCSR");
   StructurePieceType f_210140_ = m_210152_(NetherBridgePieces.MonsterThrone::new, "NeMT");
   StructurePieceType f_210141_ = m_210152_(NetherBridgePieces.RoomCrossing::new, "NeRC");
   StructurePieceType f_210142_ = m_210152_(NetherBridgePieces.StairsRoom::new, "NeSR");
   StructurePieceType f_210143_ = m_210152_(NetherBridgePieces.StartPiece::new, "NeStart");
   StructurePieceType f_210144_ = m_210152_(StrongholdPieces.ChestCorridor::new, "SHCC");
   StructurePieceType f_210145_ = m_210152_(StrongholdPieces.FillerCorridor::new, "SHFC");
   StructurePieceType f_210146_ = m_210152_(StrongholdPieces.FiveCrossing::new, "SH5C");
   StructurePieceType f_210147_ = m_210152_(StrongholdPieces.LeftTurn::new, "SHLT");
   StructurePieceType f_210148_ = m_210152_(StrongholdPieces.Library::new, "SHLi");
   StructurePieceType f_210149_ = m_210152_(StrongholdPieces.PortalRoom::new, "SHPR");
   StructurePieceType f_210150_ = m_210152_(StrongholdPieces.PrisonHall::new, "SHPH");
   StructurePieceType f_210095_ = m_210152_(StrongholdPieces.RightTurn::new, "SHRT");
   StructurePieceType f_210096_ = m_210152_(StrongholdPieces.RoomCrossing::new, "SHRC");
   StructurePieceType f_210097_ = m_210152_(StrongholdPieces.StairsDown::new, "SHSD");
   StructurePieceType f_210098_ = m_210152_(StrongholdPieces.StartPiece::new, "SHStart");
   StructurePieceType f_210099_ = m_210152_(StrongholdPieces.Straight::new, "SHS");
   StructurePieceType f_210100_ = m_210152_(StrongholdPieces.StraightStairsDown::new, "SHSSD");
   StructurePieceType f_210101_ = m_210152_(JunglePyramidPiece::new, "TeJP");
   StructurePieceType f_210102_ = m_210155_(OceanRuinPieces.OceanRuinPiece::new, "ORP");
   StructurePieceType f_210103_ = m_210155_(IglooPieces.IglooPiece::new, "Iglu");
   StructurePieceType f_210104_ = m_210155_(RuinedPortalPiece::new, "RUPO");
   StructurePieceType f_210105_ = m_210152_(SwamplandHutPiece::new, "TeSH");
   StructurePieceType f_210106_ = m_210152_(DesertPyramidPiece::new, "TeDP");
   StructurePieceType f_210107_ = m_210152_(OceanMonumentPieces.MonumentBuilding::new, "OMB");
   StructurePieceType f_210108_ = m_210152_(OceanMonumentPieces.OceanMonumentCoreRoom::new, "OMCR");
   StructurePieceType f_210109_ = m_210152_(OceanMonumentPieces.OceanMonumentDoubleXRoom::new, "OMDXR");
   StructurePieceType f_210110_ = m_210152_(OceanMonumentPieces.OceanMonumentDoubleXYRoom::new, "OMDXYR");
   StructurePieceType f_210111_ = m_210152_(OceanMonumentPieces.OceanMonumentDoubleYRoom::new, "OMDYR");
   StructurePieceType f_210112_ = m_210152_(OceanMonumentPieces.OceanMonumentDoubleYZRoom::new, "OMDYZR");
   StructurePieceType f_210113_ = m_210152_(OceanMonumentPieces.OceanMonumentDoubleZRoom::new, "OMDZR");
   StructurePieceType f_210114_ = m_210152_(OceanMonumentPieces.OceanMonumentEntryRoom::new, "OMEntry");
   StructurePieceType f_210115_ = m_210152_(OceanMonumentPieces.OceanMonumentPenthouse::new, "OMPenthouse");
   StructurePieceType f_210116_ = m_210152_(OceanMonumentPieces.OceanMonumentSimpleRoom::new, "OMSimple");
   StructurePieceType f_210117_ = m_210152_(OceanMonumentPieces.OceanMonumentSimpleTopRoom::new, "OMSimpleT");
   StructurePieceType f_210118_ = m_210152_(OceanMonumentPieces.OceanMonumentWingRoom::new, "OMWR");
   StructurePieceType f_210119_ = m_210155_(EndCityPieces.EndCityPiece::new, "ECP");
   StructurePieceType f_210120_ = m_210155_(WoodlandMansionPieces.WoodlandMansionPiece::new, "WMP");
   StructurePieceType f_210122_ = m_210152_(BuriedTreasurePieces.BuriedTreasurePiece::new, "BTP");
   StructurePieceType f_210123_ = m_210155_(ShipwreckPieces.ShipwreckPiece::new, "Shipwreck");
   StructurePieceType f_210124_ = m_210155_(NetherFossilPieces.NetherFossilPiece::new, "NeFos");
   StructurePieceType f_210125_ = m_210158_(PoolElementStructurePiece::new, "jigsaw");

   StructurePiece m_207333_(StructurePieceSerializationContext p_210161_, CompoundTag p_210162_);

   private static StructurePieceType m_210158_(StructurePieceType p_210159_, String p_210160_) {
      return Registry.register(Registry.STRUCTURE_PIECE, p_210160_.toLowerCase(Locale.ROOT), p_210159_);
   }

   private static StructurePieceType m_210152_(StructurePieceType.ContextlessType p_210153_, String p_210154_) {
      return m_210158_(p_210153_, p_210154_);
   }

   private static StructurePieceType m_210155_(StructurePieceType.StructureTemplateType p_210156_, String p_210157_) {
      return m_210158_(p_210156_, p_210157_);
   }

   public interface ContextlessType extends StructurePieceType {
      StructurePiece m_210166_(CompoundTag p_210167_);

      default StructurePiece m_207333_(StructurePieceSerializationContext p_210164_, CompoundTag p_210165_) {
         return this.m_210166_(p_210165_);
      }
   }

   public interface StructureTemplateType extends StructurePieceType {
      StructurePiece m_210171_(StructureManager p_210172_, CompoundTag p_210173_);

      default StructurePiece m_207333_(StructurePieceSerializationContext p_210169_, CompoundTag p_210170_) {
         return this.m_210171_(p_210169_.structureManager(), p_210170_);
      }
   }
}