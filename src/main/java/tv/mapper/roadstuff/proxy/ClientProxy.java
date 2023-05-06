package tv.mapper.roadstuff.proxy;


import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.roadstuff.util.ModColorHandler;
import tv.mapper.roadstuff.world.item.BrushItem;
import tv.mapper.roadstuff.world.item.RSItemRegistry;

public class ClientProxy implements IProxy
{
    @Override
    public void setup(FMLCommonSetupEvent event)
    {
        ModColorHandler.registerBlockColor();

        ItemProperties.register(RSItemRegistry.PAINT_BUCKET_ITEM.get(), new ResourceLocation("color"), (itemStack, world, entity, id) ->
        {
            if(itemStack.hasTag())
            {
                CompoundTag nbt = itemStack.getTag();
                if(nbt.getInt("paint") == 0)
                    return 0.0f;
                else if(nbt.getInt("color") == 0)
                    return 0.5f;
                else if(nbt.getInt("color") == 1)
                    return 1.0f;
            }
            return 0.0f;
        });

        for(RegistryObject<Item> item : RSItemRegistry.MOD_PAINTBRUSHES)
        {
            ItemProperties.register(item.get(), new ResourceLocation("color"), (itemStack, world, entity, id) ->
            {
                CompoundTag nbt = BrushItem.checkNBT(itemStack);
                if(nbt.getInt("paint") == 0)
                    return 0.0f;
                else if(nbt.getInt("color") == 0)
                    return 0.5f;
                else if(nbt.getInt("color") == 1)
                    return 1.0f;
                return 0.0f;
            });
        }
    }
}