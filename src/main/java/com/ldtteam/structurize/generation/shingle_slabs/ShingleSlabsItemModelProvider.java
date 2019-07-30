package com.ldtteam.structurize.generation.shingle_slabs;

import com.google.gson.JsonObject;
import com.ldtteam.structurize.blocks.ModBlocks;
import com.ldtteam.structurize.blocks.decorative.BlockShingleSlab;
import com.ldtteam.structurize.generation.AbstractItemModelProvider;
import com.ldtteam.structurize.generation.DataGeneratorConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ShingleSlabsItemModelProvider extends AbstractItemModelProvider
{
    private final DataGenerator generator;

    public ShingleSlabsItemModelProvider(final DataGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void act(@NotNull DirectoryCache cache) throws IOException
    {
        final JsonObject modelJson = new JsonObject();

        setModelDisplay(modelJson);

        for (BlockShingleSlab shingleSlab : ModBlocks.getShingleSlabs())
        {
            if (shingleSlab.getRegistryName() == null)
                continue;

            setModelParent(modelJson, new ResourceLocation("structurize:block/shingle_slab/" + shingleSlab.getRegistryName().getPath() + "_top"));

            final String name = shingleSlab.getRegistryName().getPath();
            IDataProvider.save(DataGeneratorConstants.GSON, cache, modelJson, generator.getOutputFolder().resolve(DataGeneratorConstants.ITEM_MODEL_DIR).resolve(name + ".json"));
        }
    }

    private void setModelDisplay(final JsonObject modelJson)
    {
        final JsonObject guiState = new JsonObject();

        setRotationForState(guiState, 30, 215, 0);
        setTranslationForState(guiState, 0, 0, 0);
        setScaleForState(guiState, 0.5, 0.5, 0.5);

        addState(modelJson, guiState, "gui");

        final JsonObject thirdPerson = new JsonObject();

        setRotationForState(thirdPerson, 0, 180, 0);
        setTranslationForState(thirdPerson, 0, 0, 0);
        setScaleForState(thirdPerson, 0.5, 0.5, 0.5);

        addState(modelJson, thirdPerson, "thirdperson_lefthand");
        addState(modelJson, thirdPerson, "thirdperson_righthand");

        final JsonObject firstPerson = new JsonObject();

        setRotationForState(firstPerson, 0, 180, 0);
        setTranslationForState(firstPerson, 0, 0, 0);
        setScaleForState(firstPerson, 0.3, 0.3, 0.3);

        addState(modelJson, firstPerson, "firstperson_lefthand");
        addState(modelJson, firstPerson, "firstperson_righthand");
        addState(modelJson, firstPerson, "ground");

        final JsonObject fixed = new JsonObject();

        setRotationForState(fixed, 0, 0, 0);
        setTranslationForState(fixed, 0, 2, 0);
        setScaleForState(fixed, 1, 1, 1);

        addState(modelJson, fixed, "fixed");

    }

    @Override
    @NotNull
    public String getName()
    {
        return "Shingle Slabs Item Model Provider";
    }
}