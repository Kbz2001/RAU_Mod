package me.kbz.raumod;

import net.minecraftforge.fml.common.*;
import net.minecraft.client.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.common.config.*;
import java.io.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.*;
import net.minecraft.command.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.MOD_NAME, acceptedMinecraftVersions = Reference.acceptedMinecraftVersions)
public class Core
{
    private final Minecraft mc;
    private static Core coreInstance;
    public static KeyBinding toggleMessage = new KeyBinding("Toggle Automated Message", 43, "RAU Mod");
    public static boolean isActive = true;
    public static Configuration config;
    public static File bListFile;

    //Make sure to change version # in build gradle & in Reference.

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {

        this.initConfiguration(e);
        ClientRegistry.registerKeyBinding(Core.toggleMessage);

    }

    public void initConfiguration(FMLPreInitializationEvent e)
    {

        (Core.config = new Configuration(e.getSuggestedConfigurationFile())).load();
        RauCommands.setMsg = Core.config.get("Rau Mod", "Automated Message", "Friendly reminder to Respect All Users on the Hypixel Network! :)!", "This is the message that is displayed in chat.");
        Core.config.save();

        try
        {

            (Core.bListFile = new File(e.getModConfigurationDirectory() + "/RAUBlacklist.txt")).createNewFile();

        }

        catch (Exception ex)
        {

            ex.printStackTrace();

        }

        Blacklist.loadBlist();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {

        MinecraftForge.EVENT_BUS.register(new RauMessage());
        MinecraftForge.EVENT_BUS.register(new PlayerLogin());

        ClientCommandHandler.instance.registerCommand(new RauCommands());

        Core.coreInstance = this;

    }

    @Mod.EventHandler
    public void postInt(FMLPostInitializationEvent e)
    {



    }

    public Core()
    {

        this.mc = Minecraft.getMinecraft();

    }

    public Minecraft getMc()
    {

        return this.mc;

    }

    public static Core getCoreInstance()
    {

        return Core.coreInstance;

    }
}

