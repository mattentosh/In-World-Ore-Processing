package dev.inworldoreprocessing.block;

import com.mojang.serialization.MapCodec;
import dev.inworldoreprocessing.InWorldOreProcessing;
import dev.inworldoreprocessing.block.custom.MillstoneBlock;
import dev.inworldoreprocessing.fluid.ModFluids;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block MILLSTONE = registerBlock("millstone",
            new MillstoneBlock(AbstractBlock.Settings.create().strength(1f)
                    .requiresTool().sounds(BlockSoundGroup.POLISHED_TUFF)) {});

    public static final Block SULFURICLEACHING_BLOCK = registerBlock("sulfuricleaching_block",
            new FluidBlock(ModFluids.SULFURICLEACHING_STILL,AbstractBlock.Settings.copy(Blocks.WATER).noCollision().strength(100.0F).dropsNothing().nonOpaque()) {
            });




    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(InWorldOreProcessing.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(InWorldOreProcessing.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        InWorldOreProcessing.LOGGER.info("Registering Mod Blocks for " + InWorldOreProcessing.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.MILLSTONE);

        });
    }
}