package dev.inworldoreprocessing.item;

import dev.inworldoreprocessing.InWorldOreProcessing;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CRUDE_COPPER_ORE = registerItem("crude_copper_ore",new Item(new Item.Settings()));




    private static Item registerItem(String name, Item item){
return Registry.register(Registries.ITEM, Identifier.of(InWorldOreProcessing.MOD_ID, name), item);

    }

    public static void registerModItems(){
        InWorldOreProcessing.LOGGER.info("Registering Mod Items For " + InWorldOreProcessing.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CRUDE_COPPER_ORE);

        });

    }
}
