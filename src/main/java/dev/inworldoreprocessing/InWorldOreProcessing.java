package dev.inworldoreprocessing;

import dev.inworldoreprocessing.block.ModBlocks;
import dev.inworldoreprocessing.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InWorldOreProcessing implements ModInitializer {
	public static final String MOD_ID = "inworldoreprocessing";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		LOGGER.info("Hello Fabric world!");
	}
}