package dev.inworldoreprocessing.customclasses;

import dev.inworldoreprocessing.component.ModDataComponent;
import dev.inworldoreprocessing.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;


public class Floatation {

    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            for (Entity entity : world.iterateEntities()) {
                if (entity instanceof ItemEntity itemEntity) {
                    processItem(world, itemEntity);
                }
            }
        });
    }

    private static void processItem(World world, ItemEntity itemEntity) {
        if (world.isClient()) return;

        ItemStack stack = itemEntity.getStack();

        // Only process target item
        if (!stack.isOf(ModItems.CRUDE_COPPER_ORE)) return;

        BlockState state = world.getBlockState(itemEntity.getBlockPos());

        // Not in bubble column → reset
        if (!state.isOf(Blocks.BUBBLE_COLUMN)) {
            stack.set(ModDataComponent.Floatation_Ticks, 0);
            return;
        }

        // In bubble column → increment
        int ticks = stack.getOrDefault(ModDataComponent.Floatation_Ticks, 0);
        ticks++;
        stack.set(ModDataComponent.Floatation_Ticks, ticks);

        if (!stack.contains(ModDataComponent.Floatation_Targer_Ticks)) {
            int target = 200 + world.random.nextInt(100); // 30–60 ticks
            stack.set(ModDataComponent.Floatation_Targer_Ticks, target);
        }

        // Finished processing
        int target = stack.get(ModDataComponent.Floatation_Targer_Ticks);
        int count = stack.getCount();
        if (ticks >= target) {
            itemEntity.setStack(new ItemStack(Items.GUNPOWDER, count));
        }


    }
}