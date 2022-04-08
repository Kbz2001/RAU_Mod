package me.kbz.raumod;

import java.time.*;
import net.minecraftforge.client.event.*;
import java.util.*;
import java.time.chrono.*;
import java.util.Timer;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.util.*;

public class RauMessage
{
    private Timer msgTimer = new Timer();
    public static String username = "";
    public static String playerName = "";
    public static HashMap<String, LocalDateTime> usersOnCD = new HashMap<String, LocalDateTime>();

    @SubscribeEvent
    public void onPlayerJoinedMsg(ClientChatReceivedEvent e) throws InterruptedException
    {

        String message = e.message.getUnformattedText();

        if (message.endsWith("joined.") && Core.isActive)
        {

            String[] splitMsg = message.split(" ");
            RauMessage.username = splitMsg[0];

            if (!RauMessage.username.equals(Core.getCoreInstance().getMc().thePlayer.getName()) && !RauMessage.usersOnCD.containsKey(RauMessage.username) && !Blacklist.blackListedUsers.containsKey(RauMessage.username))
            {

                msgTimer.schedule(new MessageTimer(RauMessage.username), 2 * 1000);
                RauMessage.usersOnCD.put(RauMessage.username, LocalDateTime.now());
                RauMessage.playerName = Core.getCoreInstance().getMc().thePlayer.getName();

            }

            if (RauMessage.usersOnCD.containsKey(RauMessage.username))
            {

                if (RauMessage.usersOnCD.get(RauMessage.username).plusMinutes(60).isBefore(LocalDateTime.now()))
                {

                    RauMessage.usersOnCD.remove(RauMessage.username);

                }

                else
                {

                    RauMessage.usersOnCD.put(RauMessage.username, LocalDateTime.now());

                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent e)
    {

        if (Core.toggleMessage.isPressed())
        {

            if (Core.isActive)
            {

                Core.isActive = false;
                FMLClientHandler.instance().getClientPlayerEntity().addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "RAU is now off!"));

            }

            else if (!Core.isActive)
            {

                Core.isActive = true;
                FMLClientHandler.instance().getClientPlayerEntity().addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "RAU is now on!"));

            }
        }
    }
}
