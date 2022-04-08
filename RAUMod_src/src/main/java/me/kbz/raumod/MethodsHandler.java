package me.kbz.raumod;

import java.io.*;
import java.util.*;
import net.minecraft.server.*;
import com.mojang.authlib.*;
import net.minecraft.entity.*;

public class MethodsHandler
{
    public static String Read(File f)
    {

        String content = null;

        try
        {

            content = new Scanner(f).useDelimiter("\\Z").next();

        }

        catch (FileNotFoundException ex)
        {

            ex.printStackTrace();

        }

        return content;

    }

    public static void Write(File f, String content)
    {

        try
        {

            FileWriter fileWriter = new FileWriter(f);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
            {

                bufferedWriter.write(content);

            }
        }

        catch (IOException ex)
        {

            ex.printStackTrace();

        }
    }

    public static boolean isNumeric(String str)
    {

        try
        {

            Double.parseDouble(str);

        }

        catch (NumberFormatException nfe)
        {

            return false;

        }

        return true;

    }

    public static UUID getUUIDfromUsername(String username)
    {

        GameProfile profile = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(username);
        UUID uuid = profile.getId();
        return uuid;

    }

    public static String getUsernamefromUUID(UUID uuid)
    {

        Entity player = MinecraftServer.getServer().getEntityFromUuid(uuid);
        return player.getName();

    }
}
