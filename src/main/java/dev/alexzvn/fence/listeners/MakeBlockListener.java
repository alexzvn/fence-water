package dev.alexzvn.fence.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import dev.alexzvn.fence.Roll;

public class MakeBlockListener implements Listener {

    protected FileConfiguration config;

    protected Map<String, Roll> worldRoll = new HashMap<String, Roll>();

    public MakeBlockListener(FileConfiguration config) {
        this.config = config;

        Set<String> worlds = config.getConfigurationSection("worlds").getKeys(false);

        for (String world : worlds) {
            worldRoll.put(world, new Roll(config, world));
        }
    }

    @EventHandler
    public void generateStoneLiquid(BlockFromToEvent event) {
        Block flowBlock = event.getToBlock();

        if (
            ! isAllowed(flowBlock.getWorld()) ||
            ! isWater(event.getBlock())
        ) return;

        if (this.shoudTranformLiquidToBlock(flowBlock)) {
            event.setCancelled(true);
            this.replaceBlock(flowBlock);
        }
    }

    protected boolean shoudTranformLiquidToBlock(Block block) {
        String[] faces = {"DOWN", "EAST", "WEST", "SOUTH", "NORTH"};

        for (String face : faces) {
            Block faced = block.getRelative(BlockFace.valueOf(face));

            if (this.isFence(faced)) {
                return true;
            }
        }

        return false;
    }

    protected void replaceBlock(Block block) {
        block.getWorld().getBlockAt(
            block.getX(),
            block.getY(),
            block.getZ()
        ).setType(
            worldRoll.get(block.getWorld().getName()).material()
        );
    }

    protected boolean isAllowed(World world) {
        return worldRoll.containsKey(world.getName());
    }

    protected boolean isFence(Block block) {
        return block.getType().name().endsWith("FENCE");
    }

    protected boolean isWater(Block block) {
        return block.getType().name().endsWith("WATER");
    }
}
