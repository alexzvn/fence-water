package dev.alexzvn.fence;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class Roll {

    protected FileConfiguration config;

    protected String world;

    protected Map<String, Integer> blocks = new HashMap<String, Integer>();

    protected Integer total = 0;

    public Roll(FileConfiguration config, String name) {
        this.config = config;
        this.world = name;

        this.init();
    }

    protected void init() {
        Set<String> blocks = this.config.getConfigurationSection("worlds." + this.world).getKeys(false);

        this.blocks = sortByValue(mapBlocks(blocks));
    }

    protected HashMap<String, Integer> mapBlocks(Set<String> blockNames) {
        HashMap<String, Integer> blocks = new HashMap<>();

        for (String name : blockNames) {
            total += this.config.getInt("worlds." + this.world + "." + name);

            blocks.put(name, total);
        }

        return blocks;
    }

    public Material material() {
        int random = random();

        for (String material : blocks.keySet()) {
            if (blocks.get(material) >= random) {
                return Material.valueOf(material);
            }
        }

        return Material.valueOf(config.getString("fallback_block"));
    }

    protected Integer random() {
        return ThreadLocalRandom.current().nextInt(0, total + 1);
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }
}
