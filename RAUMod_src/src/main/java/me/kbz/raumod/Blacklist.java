package me.kbz.raumod;

import java.io.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class Blacklist
{
    public static HashMap<String, UUID> blackListedUsers;

    public static void loadBlist()
    {

        try
        {
            Blacklist.blackListedUsers.clear();

            BufferedReader br = new BufferedReader(new FileReader(Core.bListFile));
            String line = br.readLine();
            while (line != null)
            {

                String[] fileEntry = line.split(" = ");
                String username = fileEntry[0];
                Blacklist.blackListedUsers.put(username, MethodsHandler.getUUIDfromUsername(username));

            }

            br.close();

        }
        catch (Exception e)
        {

            e.printStackTrace();

        }
    }

    public static void saveBlist()
    {

        try
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(Core.bListFile));
            Map<String, UUID> sortedBlackList = Blacklist.blackListedUsers
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            for (Map.Entry<String, UUID> ent : sortedBlackList.entrySet())
            {

                bw.write(ent.getKey() + " = " + ent.getValue() + ", ");

            }

            bw.close();
        }

        catch (Exception e2)
        {

            e2.printStackTrace();

        }
    }

    public static HashMap<String, UUID> sortBlist(HashMap<String, UUID> hashmap)
    {

        hashmap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return hashmap;

    }
}
