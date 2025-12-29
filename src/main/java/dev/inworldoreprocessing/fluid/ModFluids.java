package dev.inworldoreprocessing.fluid;

import dev.inworldoreprocessing.InWorldOreProcessing;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid SULFURICLEACHING_STILL = register("sulfuricleaching_still", new SulfuricLeachingFluid.Still() {
    });
    public static final FlowableFluid SULFURICLEACHING_FLOWING = register("sulfuricleaching_flowing", new SulfuricLeachingFluid.Flowing() {
    });




    private static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, Identifier.of(InWorldOreProcessing.MOD_ID, name), flowableFluid);
    }
}