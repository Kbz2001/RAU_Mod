package me.kbz.raumod;

import net.minecraftforge.common.config.*;
import net.minecraft.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;
import net.minecraft.command.*;

public class RauCommands extends CommandBase
{
    public static Property setMsg;

    public String getCommandName()
    {

        String cmdName = "rau";

        return cmdName;
    }

    public List<String> getCommandAliases()
    {

        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("respectallusers");
        return aliases;

    }

    public String getCommandUsage(ICommandSender sender)
    {

        String cmdUsage = "/rau";
        return cmdUsage;

    }

    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {

        return true;

    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {

        if (args.length == 0)
        {

            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "This Mod was created by Kbz with help from P0ke!" +
                    "\n" + EnumChatFormatting.DARK_GREEN + "Command List:" +
                    "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau info - Displays Info about the Mod." +
                    "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau motto - Displays the RAU motto message." +
                    "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau togglemsg - Turns the automated RAU on or off." +
                    "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau setmsg <message> - Sets the automated message content." +
                    "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau setmsgdefault - Sets the automated message to the default " + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "message." +
                    "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau blacklisthelp - Displays the blacklist commands."));

        }

        else
         {

            String Cmd = args[0];
            switch (Cmd)
            {

                case "info":
                {

                    if (args.length != 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!" +
                                "\n /rau <info>"));
                        return;

                    }

                    if (Core.isActive)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Authors: " + EnumChatFormatting.BLUE + "Kbz" +
                                "\n" + EnumChatFormatting.AQUA + "Mod Name: " + EnumChatFormatting.LIGHT_PURPLE + "RAU Mod" +
                                "\n" + EnumChatFormatting.AQUA + "Version: " + EnumChatFormatting.GOLD + "1.7.2" +
                                "\n" + EnumChatFormatting.AQUA + "Automated Message: " + EnumChatFormatting.GREEN + "On" +
                                "\n" + EnumChatFormatting.AQUA + "Automated Message Content: " + EnumChatFormatting.WHITE + RauCommands.setMsg.getString()));
                    }
                    if (!Core.isActive)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Authors: " + EnumChatFormatting.BLUE + "Kbz" +
                                "\n" + EnumChatFormatting.AQUA + "Mod Name: " + EnumChatFormatting.LIGHT_PURPLE + "RAU Mod" +
                                "\n" + EnumChatFormatting.AQUA + "Version: " + EnumChatFormatting.GOLD + "1.7.2" +
                                "\n" + EnumChatFormatting.AQUA + "Automated Message: " + EnumChatFormatting.DARK_GRAY + "Off" +
                                "\n" + EnumChatFormatting.AQUA + "Automated Message Content: " + EnumChatFormatting.WHITE + RauCommands.setMsg.getString()));
                        break;

                    }

                    break;
                }

                case "motto":
                {
                    if (args.length != 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!" +
                                "\n /rau <motto>"));
                        return;

                    }

                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "RAU - Respect All Users, " + EnumChatFormatting.WHITE + "we are all human and we all like the Hypixel Network." + EnumChatFormatting.WHITE +
                            "\n" + "Please be " + EnumChatFormatting.GOLD + "respectful " + EnumChatFormatting.WHITE + "and " + EnumChatFormatting.GOLD + "kind " + EnumChatFormatting.WHITE + "to others while playing games, or even lounging in lobbies." + EnumChatFormatting.WHITE +
                            "\n" + "Forum " + EnumChatFormatting.GOLD + "respect " + EnumChatFormatting.WHITE + "will be enforced, do not write something that will be considered rude, it does hurt someone's feelings in the end. " + EnumChatFormatting.WHITE + EnumChatFormatting.RED +
                            "\n" + "Discrimination " + EnumChatFormatting.WHITE + "of any kind is not tolerated and will result in a " + EnumChatFormatting.RED + "ban."));
                    break;

                }

                case "togglemsg":
                {

                    if (args.length != 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!" +
                                "\n /rau <togglemsg>"));
                        return;

                    }

                    if (Core.isActive)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "RAU is now off!"));
                        Core.isActive = false;
                        break;

                    }

                    if (!Core.isActive)
                    {

                        Core.isActive = true;
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "RAU is now on!"));
                        break;

                    }

                    break;

                }

                case "setmsg":
                {

                    if (args.length <= 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!" +
                                "\n /rau <setmsg> <message>"));
                        return;

                    }

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++)
                    {

                        builder.append(' ').append(args[i]);

                    }

                    if (builder.toString().toLowerCase().contains("enjoy your stay on the network"))
                    {

                        sender.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Please choose a different message, that one is in use."));
                        break;

                    }

                    RauCommands.setMsg.set(builder.toString().replaceFirst(" ", ""));
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Message has been set!"));
                    Core.config.save();

                    break;
                }

                case "setmsgdefault":
                {

                    if (args.length != 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!\n /rau <setmsgdefault>"));
                        return;

                    }

                    RauCommands.setMsg.set("Friendly reminder to Respect All Users on the Hypixel Network! :)!");
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Message has been reset to default!"));
                    Core.config.save();

                    break;
                }

                case "blacklisthelp":
                case "blisthelp":
                {

                    if (args.length != 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!\n /rau blacklisthelp"));
                        break;

                    }

                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Blacklist Commands:" +
                            "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau blacklisthelp - The list of commands for the blacklist." +
                            "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau blacklistadd <username> - Adds a user to the blacklist. " +
                            "\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "!All users added do not recieve the automated message! " +
                            "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau blacklistremove <username> - Removes a user from the blacklist. " +
                            "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau blacklistremoveall - Removes all users from the blacklist. " +
                            "\n" + EnumChatFormatting.BLUE + EnumChatFormatting.BOLD + "/rau blacklist <page #> - View all blacklisted players. " +
                            "\n" + EnumChatFormatting.DARK_AQUA + EnumChatFormatting.BOLD + "!blist can be used as an aliase instead of blacklist!" +
                            "\n" + EnumChatFormatting.DARK_AQUA + EnumChatFormatting.BOLD + "!clear can be used as an aliase instead of removeall!"));
                    break;

                }

                case "blacklistadd":
                case "blistadd":
                {

                    if (args.length != 2)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!\n /rau <blacklistadd> <username>"));
                        return;

                    }

                    String username = args[1];
                    UUID uuid = MethodsHandler.getUUIDfromUsername(username);
                    if (!Blacklist.blackListedUsers.containsValue(uuid))
                    {

                        Blacklist.blackListedUsers.put(username, uuid);
                        Blacklist.saveBlist();
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + username + " has been added to the blacklist!"));
                        break;

                    }

                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + username + " is already on the blacklist!"));
                    break;

                }

                case "blacklistremove":
                case "blistremove":
                {
                    if (args.length != 2)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!\n /rau <blacklistremove> <username>"));
                        return;

                    }

                    String username = args[1];
                    UUID uuid = MethodsHandler.getUUIDfromUsername(username);
                    if (Blacklist.blackListedUsers.containsValue(uuid))
                    {

                        Blacklist.blackListedUsers.remove(username);
                        Blacklist.saveBlist();
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + username + " has been removed from the blacklist!"));
                        break;

                    }

                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + username + " is not on the blacklist!"));
                    break;
                }

                case "blacklistremoveall":
                case "blacklistclear":
                case "blistremoveall":
                case "blistclear":
                {

                    if (args.length != 1)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!\n /rau blacklistremoveall"));
                        break;

                    }

                    Blacklist.blackListedUsers.clear();
                    Blacklist.saveBlist();
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "All users have been removed from the blacklist!"));
                    break;
                }

                case "blacklist":
                case "blist":
                {

                    int pageNumber = 0;
                    if (args.length >= 3)
                    {

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please use the correct command format!" +
                                "\n" + "/rau <blacklist> <page #>"));
                        return;

                    }

                    if (args.length == 1)
                    {

                        pageNumber = 1;
                        final ArrayList<String> blackListMessage = new ArrayList<String>();
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------" +
                                "\n" + EnumChatFormatting.GOLD + "Blacklist (Page " + pageNumber + " of " + (int)Math.ceil(Blacklist.blackListedUsers.size() / 8.0) + ")"));

                        for (int j = (pageNumber - 1) * 8; j < pageNumber * 8; j++)
                        {

                            if (Blacklist.blackListedUsers.size() == 0)
                            {

                                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No one added to the blacklist!\n" + EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------"));
                                return;

                            }

                            if (pageNumber > (int)Math.ceil(Blacklist.blackListedUsers.size() / 8.0))
                            {

                                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "This page does not exist!\n" + EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------"));
                                return;

                            }

                            Map<String, UUID> sortedBlackList = Blacklist.blackListedUsers.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

                            for (String key : sortedBlackList.keySet())
                            {

                                blackListMessage.add(key);

                            }

                            if (j < Blacklist.blackListedUsers.size())
                            {

                                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + blackListMessage.get(j)));

                            }
                        }

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------"));
                        break;

                    }

                    if (MethodsHandler.isNumeric(args[1]) && args.length == 2)
                    {

                        pageNumber = parseInt(args[1]);
                        ArrayList<String> blackListMessage = new ArrayList<String>();
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------\n" + EnumChatFormatting.GOLD + "Blacklist (Page " + pageNumber + " of " + (int)Math.ceil(Blacklist.blackListedUsers.size() / 8.0) + ")"));
                        for (int j = (pageNumber - 1) * 8; j < pageNumber * 8; j++)
                        {

                            if (Blacklist.blackListedUsers.size() == 0)
                            {

                                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No one added to the blacklist!\n" + EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------"));
                                return;

                            }

                            if (pageNumber > (int)Math.ceil(Blacklist.blackListedUsers.size() / 8.0)) {
                                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "This page does not exist!\n" + EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------"));
                                return;
                            }

                            Map<String, UUID> sortedBlackList = Blacklist.blackListedUsers.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

                            for (final String key : sortedBlackList.keySet())
                            {

                                blackListMessage.add(key);

                            }

                            if (j < Blacklist.blackListedUsers.size())
                            {

                                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + blackListMessage.get(j)));

                            }
                        }

                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "--------------------------------------------------------------------------------"));
                        break;

                    }

                    break;

                }
            }
        }
    }
}
