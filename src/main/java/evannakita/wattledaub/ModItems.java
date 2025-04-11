package evannakita.wattledaub;

import evannakita.wattledaub.item.DaubItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static evannakita.wattledaub.WattleAndDaub.MOD_ID;

public class ModItems  {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final Supplier<Item> CLAY_DAUB_BALL = ModItems.register("clay_daub_ball",  ()-> new DaubItem(new Item.Settings().group(ItemGroup.MATERIALS)));
    public static final Supplier<Item> COARSE_CLAY_DAUB_BALL = ModItems.register("coarse_clay_daub_ball", ()-> new DaubItem(new Item.Settings().group(ItemGroup.MATERIALS)));
    public static final Supplier<Item> MUD_DAUB_BALL = ModItems.register("mud_daub_ball", ()-> new DaubItem(new Item.Settings().group(ItemGroup.MATERIALS)));
    public static final Supplier<Item> PACKED_MUD_DAUB_BALL = ModItems.register("packed_mud_daub_ball", ()-> new DaubItem(new Item.Settings().group(ItemGroup.MATERIALS)));
    public static final Supplier<Item> SAND_DAUB_BALL = ModItems.register("sand_daub_ball", ()-> new DaubItem(new Item.Settings().group(ItemGroup.MATERIALS)));

    public static Supplier<Item> register(String id, Supplier<Item> item) {
        return ITEMS.register(id, item);
    }

}
