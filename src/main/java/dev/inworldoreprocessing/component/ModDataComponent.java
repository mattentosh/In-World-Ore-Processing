package dev.inworldoreprocessing.component;

import com.mojang.serialization.Codec;
import dev.inworldoreprocessing.InWorldOreProcessing;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponent {



        public static final ComponentType<Integer> Floatation_Ticks =
                register("floatation_ticks", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> Floatation_Targer_Ticks =
            register("floatation_target_ticks", builder -> builder.codec(Codec.INT));


        private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
            return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(InWorldOreProcessing.MOD_ID, name),
                    builderOperator.apply(ComponentType.builder()).build());
        }


    public static void registerDataComponentTypes() {
        InWorldOreProcessing.LOGGER.info("Registering DataComponentTypes for " + InWorldOreProcessing.MOD_ID);


    }

}
