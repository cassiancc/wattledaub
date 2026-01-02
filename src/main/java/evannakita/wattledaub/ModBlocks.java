package evannakita.wattledaub;

import evannakita.wattledaub.block.DaubBlock;
import evannakita.wattledaub.block.WattleBlock;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static evannakita.wattledaub.WattleAndDaub.MOD_ID;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final BlockSupplier SCATTERED_CLAY_DAUB = ModBlocks.register("scattered_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier COVERED_CLAY_DAUB = ModBlocks.register("covered_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier CLAY_DAUB = ModBlocks.register("clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier SCATTERED_COARSE_CLAY_DAUB = ModBlocks.register("scattered_coarse_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier COVERED_COARSE_CLAY_DAUB = ModBlocks.register("covered_coarse_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier COARSE_CLAY_DAUB = ModBlocks.register("coarse_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier SCATTERED_MUD_DAUB = ModBlocks.register("scattered_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier COVERED_MUD_DAUB = ModBlocks.register("covered_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier MUD_DAUB = ModBlocks.register("mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier SCATTERED_PACKED_MUD_DAUB = ModBlocks.register("scattered_packed_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.PACKED_MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier COVERED_PACKED_MUD_DAUB = ModBlocks.register("covered_packed_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.PACKED_MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier PACKED_MUD_DAUB = ModBlocks.register("packed_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.PACKED_MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier SCATTERED_SAND_DAUB = ModBlocks.register("scattered_sand_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.SAND)
            .mapColor(MapColor.PALE_YELLOW)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier COVERED_SAND_DAUB = ModBlocks.register("covered_sand_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.SAND)
            .mapColor(MapColor.PALE_YELLOW)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier SAND_DAUB = ModBlocks.register("sand_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.SAND)
            .mapColor(MapColor.PALE_YELLOW)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final BlockSupplier WATTLE = ModBlocks.register("wattle", ()-> new WattleBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.OAK_TAN)
            .breakInstantly()
            .sounds(BlockSoundGroup.BAMBOO)
            .strength(1.0F)
    ));

    public static BlockSupplier register(String id, Supplier<Block> block) {
		RegistryObject<Block> supplier = BLOCKS.register(id, block);
        var item = ModItems.ITEMS.register(id, ()-> new BlockItem(supplier.get(), new Item.Settings()));
        return new BlockSupplier(id, supplier, item);
    }
}