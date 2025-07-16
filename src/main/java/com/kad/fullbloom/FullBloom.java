package com.kad.fullbloom;

import net.minecraft.world.item.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FullBloom.MODID)
public class FullBloom
{

    public static final String MODID = "full_bloom";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);



    public static final Supplier<CreativeModeTab> FULL_BLOOM_TAB = CREATIVE_MODE_TAB.register("full_bloom_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(FullBloomItems.FLOSCULUS.get()))
                    .title(Component.translatable("creativetab.fullbloom"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(FullBloomItems.FLOSCULUS);
                        output.accept(FullBloomItems.INCOMPLETE_FLOSCULUS);


                    }).build());

    public FullBloom(IEventBus modEventBus, ModContainer modContainer)
    {



        modEventBus.addListener(this::commonSetup);


        NeoForge.EVENT_BUS.register(this);

        FullBloomItems.register(modEventBus);



        CREATIVE_MODE_TAB.register(modEventBus);


        modEventBus.addListener(this::addCreative);


        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(FullBloomItems.FLOSCULUS);
        }
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }


    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
