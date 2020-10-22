package dev.alexzvn.fence.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class MakeBlockListener implements Listener {

    @EventHandler
    public void generateStoneLiquid(BlockFromToEvent event) {
        Block flowBlock = event.getToBlock();

        Bukkit.broadcastMessage(flowBlock.getType().toString());

        if (this.shoudTranformLiquidToBlock(flowBlock)) {
            event.setCancelled(true);
            this.replaceBlock(flowBlock);
        }
    }

    protected boolean shoudTranformLiquidToBlock(Block block) {

        if (
            ! this.isSkyworld(block.getWorld()) ||
            ! block.getType().equals(Material.WATER)
        ) return false;

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
        ).setType(Material.COBBLESTONE);
    }

    protected boolean isSkyworld(World world) {
        return world.getName().startsWith("bskyblock_world");
    }

    protected boolean isFence(Block block) {
        return block.getType().name().endsWith("FENCE");
    }
}
