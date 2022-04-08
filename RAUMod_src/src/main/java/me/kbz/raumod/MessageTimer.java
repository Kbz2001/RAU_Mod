package me.kbz.raumod;

import java.util.*;

public class MessageTimer extends TimerTask
{
    public static boolean isRepeat;

    public MessageTimer(String username)
    {

        username = RauMessage.username;

    }

    @Override
    public void run()
    {

        if (MessageTimer.isRepeat)
        {

            Core.getCoreInstance().getMc().thePlayer.sendChatMessage("/msg " + RauMessage.username + " " + RauMessage.username + ", " + RauCommands.setMsg.getString());
            MessageTimer.isRepeat = !MessageTimer.isRepeat;

        }

        else if (!MessageTimer.isRepeat)
        {

            Core.getCoreInstance().getMc().thePlayer.sendChatMessage("/msg " + RauMessage.username + " " + RauMessage.username + ", Enjoy your stay on the network!");
            MessageTimer.isRepeat = !MessageTimer.isRepeat;

        }
    }
}

