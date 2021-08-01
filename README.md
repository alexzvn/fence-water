# fence-water
Ore generator common use for skyblock spigot/paper

## Install
 - Download plugin at [release page](https://github.com/alexzvn/fence-water/releases)
 - Drop .jar file to `plugins` folder.

## Config
```yml
# config.yml

# If block not found then COBBLESTONE will generated
fallback_block: COBBLESTONE

# List of worlds enable
worlds:

  # Enable at world `world`
  world:
    
    # Percent equals sum of all value, automate calculate percent of spawn block
    COBBLESTONE: 30
    IRON_ORE: 20
    GOLD_ORE: 20
    COAL_ORE: 20
    DIAMOND_ORE: 10
```
