package evannakita.wattledaub;

import evannakita.wattledaub.block.DaubBlock;
import evannakita.wattledaub.block.WattleBlock;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static evannakita.wattledaub.WattleAndDaub.MOD_ID;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final Supplier<Block> SCATTERED_CLAY_DAUB = ModBlocks.register("scattered_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> COVERED_CLAY_DAUB = ModBlocks.register("covered_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> CLAY_DAUB = ModBlocks.register("clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> SCATTERED_COARSE_CLAY_DAUB = ModBlocks.register("scattered_coarse_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> COVERED_COARSE_CLAY_DAUB = ModBlocks.register("covered_coarse_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> COARSE_CLAY_DAUB = ModBlocks.register("coarse_clay_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> SCATTERED_MUD_DAUB = ModBlocks.register("scattered_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> COVERED_MUD_DAUB = ModBlocks.register("covered_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> MUD_DAUB = ModBlocks.register("mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> SCATTERED_PACKED_MUD_DAUB = ModBlocks.register("scattered_packed_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.PACKED_MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> COVERED_PACKED_MUD_DAUB = ModBlocks.register("covered_packed_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.PACKED_MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> PACKED_MUD_DAUB = ModBlocks.register("packed_mud_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.PACKED_MUD)
            .mapColor(MapColor.DIRT_BROWN)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> SCATTERED_SAND_DAUB = ModBlocks.register("scattered_sand_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.SAND)
            .mapColor(MapColor.PALE_YELLOW)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> COVERED_SAND_DAUB = ModBlocks.register("covered_sand_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.SAND)
            .mapColor(MapColor.PALE_YELLOW)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> SAND_DAUB = ModBlocks.register("sand_daub", ()-> new DaubBlock(
        Settings.copy(Blocks.SAND)
            .mapColor(MapColor.PALE_YELLOW)
            .sounds(BlockSoundGroup.MUD)
            .strength(1.0F, 3.0F)
    ));

    public static final Supplier<Block> WATTLE = ModBlocks.register("wattle", ()-> new WattleBlock(
        Settings.copy(Blocks.CLAY)
            .mapColor(MapColor.OAK_TAN)
            .breakInstantly()
            .sounds(BlockSoundGroup.BAMBOO)
            .strength(1.0F)
    ));

    public static RegistryObject<Block> register(String id, Supplier<Block> block) {
        var supplier = BLOCKS.register(id, block);
        ModItems.ITEMS.register(id, ()-> new BlockItem(supplier.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        return supplier;
    }
}