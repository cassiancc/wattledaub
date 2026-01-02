package evannakita.wattledaub;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

import static evannakita.wattledaub.WattleAndDaub.MOD_ID;

@Mod(MOD_ID)
public class WattleAndDaub {
	public static final String MOD_ID = "wattledaub";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public WattleAndDaub() {
		var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Forge world!");

		ModBlocks.BLOCKS.register(eventBus);
		ModItems.ITEMS.register(eventBus);
		eventBus.addListener(WattleAndDaub::buildCreativeModeTabs);
	}

	public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(ItemGroups.BUILDING_BLOCKS)) {
			addAfter(event, Items.WARPED_BUTTON,
					ModBlocks.WATTLE.asItem(),
					ModBlocks.SCATTERED_CLAY_DAUB.asItem(),
					ModBlocks.COVERED_CLAY_DAUB.asItem(),
					ModBlocks.CLAY_DAUB.asItem(),
					ModBlocks.SCATTERED_COARSE_CLAY_DAUB.asItem(),
					ModBlocks.COVERED_COARSE_CLAY_DAUB.asItem(),
					ModBlocks.COARSE_CLAY_DAUB.asItem(),
					ModBlocks.SCATTERED_SAND_DAUB.asItem(),
					ModBlocks.COVERED_SAND_DAUB.asItem(),
					ModBlocks.SAND_DAUB.asItem(),
					ModBlocks.SCATTERED_MUD_DAUB.asItem(),
					ModBlocks.COVERED_MUD_DAUB.asItem(),
					ModBlocks.MUD_DAUB.asItem(),
					ModBlocks.SCATTERED_PACKED_MUD_DAUB.asItem(),
					ModBlocks.COVERED_PACKED_MUD_DAUB.asItem(),
					ModBlocks.PACKED_MUD_DAUB.asItem()
			);
		}

		if (event.getTabKey().equals(ItemGroups.INGREDIENTS)) {
			addAfter(event, Items.CLAY_BALL,
					ModItems.CLAY_DAUB_BALL.get(),
					ModItems.COARSE_CLAY_DAUB_BALL.get(),
					ModItems.SAND_DAUB_BALL.get(),
					ModItems.MUD_DAUB_BALL.get(),
					ModItems.PACKED_MUD_DAUB_BALL.get()
			);
		}
	}

	private static void addAfter(BuildCreativeModeTabContentsEvent event, Item anchor, Item... items) {
		for (Item item : items) {
			event.add(item);
		}
	}
}