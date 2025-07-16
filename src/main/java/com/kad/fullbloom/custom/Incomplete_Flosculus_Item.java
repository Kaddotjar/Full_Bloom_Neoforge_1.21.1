package com.kad.fullbloom.custom;

import com.kad.fullbloom.Config;
import com.kad.fullbloom.FullBloom;
import com.kad.fullbloom.FullBloomItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Incomplete_Flosculus_Item extends Item {

    public Incomplete_Flosculus_Item(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(!level.isClientSide()) {
            if (player.experienceLevel >= Config.experienceCost) {
                player.giveExperienceLevels(-Config.experienceCost);


                if (!player.getAbilities().instabuild) {
                    stack.shrink(1); // Remove one instance of the original item
                }




                player.addItem(new ItemStack(FullBloomItems.FLOSCULUS.get()));



                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.EXPERIENCE_ORB_PICKUP , SoundSource.PLAYERS, 0.8F, 1.0F);

                return InteractionResultHolder.success(stack);
            } else {
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.REDSTONE_TORCH_BURNOUT , SoundSource.PLAYERS, 0.5F, 1.0F);
                return InteractionResultHolder.fail(stack);
            }
        }

        return InteractionResultHolder.pass(stack);
    }
}
