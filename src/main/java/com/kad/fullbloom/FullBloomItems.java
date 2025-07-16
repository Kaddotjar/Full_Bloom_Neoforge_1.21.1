package com.kad.fullbloom;

import com.kad.fullbloom.custom.Incomplete_Flosculus_Item;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FullBloomItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FullBloom.MODID);

    public static final DeferredItem<Item> FLOSCULUS = ITEMS.register("flosculus", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_FLOSCULUS = ITEMS.register("incomplete_flosculus", () -> new Incomplete_Flosculus_Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
