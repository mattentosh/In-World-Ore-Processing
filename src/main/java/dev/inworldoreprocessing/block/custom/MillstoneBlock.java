package dev.inworldoreprocessing.block.custom;

import com.mojang.serialization.MapCodec;
import dev.inworldoreprocessing.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class MillstoneBlock extends FallingBlock {

    public MillstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingState, BlockState hitState, FallingBlockEntity entity) {
        super.onLanding(world, pos, fallingState, hitState, entity);
        crushItems(world, pos);
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity entity) {
        super.onDestroyedOnLanding(world, pos, entity);
        crushItems(world, pos);
    }

    private void crushItems(World world, BlockPos pos) {
        if (world.isClient) return;

        Box area = new Box(
                pos.getX() - 0, pos.getY() - 0.4, pos.getZ() - 0,
                pos.getX() + 1, pos.getY() + 0.4, pos.getZ() + 1
        );

        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, area, e -> true);

        System.out.println("Millstone: Found " + items.size() + " item(s)");
        for (ItemEntity item : items) {
            ItemStack stack = item.getStack();
            System.out.println("Millstone: Item detected = " + item.getStack().getItem());
            // Raw copper → crude copper ore
            if (stack.isOf(Items.RAW_COPPER)) {
                item.setStack(new ItemStack(ModItems.CRUDE_COPPER_ORE, stack.getCount()));
                System.out.println("Millstone: RAW_COPPER detected — crushing!");
            }
        }
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return createCodec(MillstoneBlock::new);
    }
}