package me.kbz.raumod;

import net.minecraftforge.event.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.network.*;

public class PlayerLogin
{
    private int msgCounter;

    public PlayerLogin()
    {

        msgCounter = 0;

    }

    @SubscribeEvent
    public void onPlayerLoginMP(EntityJoinWorldEvent e)
    {

        if (e.entity instanceof EntityPlayer && !Core.getCoreInstance().getMc().isSingleplayer())
        {

            EntityPlayer player = (EntityPlayer)e.entity;

            if (player.getName().equals(Core.getCoreInstance().getMc().thePlayer.getName()) && msgCounter == 0)
            {

                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Disclaimer: I, Kbz, am not responsible if you are banned for using this mod.\n" + EnumChatFormatting.LIGHT_PURPLE + "Type ''/rau'' for help!"));
                Blacklist.loadBlist();
                msgCounter++;
            }
            if (msgCounter > 0)
            {

                return;

            }
        }
    }

    @SubscribeEvent
    public void onPlayerLogoutMP(FMLNetworkEvent.ClientDisconnectionFromServerEvent e)
    {

        Blacklist.saveBlist();
        msgCounter = 0;

    }
}
