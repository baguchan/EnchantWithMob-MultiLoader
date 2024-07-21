package com.infamousmisadventures.infamousexample.registry;

import com.google.common.collect.ImmutableList;
import com.infamousmisadventures.infamousexample.platform.Services;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

import java.util.function.Supplier;

import static com.infamousmisadventures.infamousexample.ExampleConstants.MOD_ID;
import static com.infamousmisadventures.infamousexample.util.ResourceLocationHelper.modLoc;

public class ExampleAttributes {
    private static final ObjectArrayList<Supplier<Attribute>> ATTRIBUTES = new ObjectArrayList<>();

    public static void register() {
    }

    public static ImmutableList<Supplier<Attribute>> getAttributes() {
        return ImmutableList.copyOf(ATTRIBUTES);
    }

    private static Supplier<Attribute> registerAttribute(String attributeName, double defaultValue, double minValue, double maxValue) {
        Supplier<Attribute> attribSupToRegister = registerAttribute(attributeName,
                () -> new RangedAttribute("attribute.name.generic."+ MOD_ID + "." + attributeName, defaultValue, minValue, maxValue)
                        .setSyncable(true));

        ATTRIBUTES.add(attribSupToRegister);
        return attribSupToRegister;
    }

    private static Supplier<Attribute> registerAttribute(String id, Supplier<Attribute> attribSup) {
        return Services.REGISTRAR.registerObject(modLoc(id), attribSup, BuiltInRegistries.ATTRIBUTE);
    }
}
