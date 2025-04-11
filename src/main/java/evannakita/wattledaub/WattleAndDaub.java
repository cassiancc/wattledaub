package evannakita.wattledaub;

import net.minecraft.item.Item;
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


	}
}