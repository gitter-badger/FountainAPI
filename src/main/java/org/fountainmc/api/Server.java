package org.fountainmc.api;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;

import org.fountainmc.api.command.CommandManager;
import org.fountainmc.api.enchantments.EnchantmentType;
import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.entity.EntityType;
import org.fountainmc.api.entity.Player;
import org.fountainmc.api.entity.data.EntityDataFactory;
import org.fountainmc.api.event.EventManager;
import org.fountainmc.api.inventory.item.ItemFactory;
import org.fountainmc.api.plugin.PluginManager;
import org.fountainmc.api.scheduler.Scheduler;

import static com.google.common.base.Preconditions.checkArgument;

@ParametersAreNonnullByDefault
public interface Server extends ServerInfo {

    PluginManager getPluginManager();

    CommandManager getCommandManager();

    EventManager getEventManager();

    ImmutableList<String> getLaunchArguments();

    @Nonnull
    Material getMaterial(String name);

    @Nonnull
    Material getMaterial(int id);

    @Nonnull
    default BlockType getBlockType(String name) {
        Material material = getMaterial(name);
        checkArgument(material instanceof BlockType, "%s is not a block!", name);
        return (BlockType) material;
    }

    @Nonnull
    default BlockType getBlockType(int id) {
        Material material = getMaterial(id);
        checkArgument(material instanceof BlockType, "%s is not a block!", id);
        return (BlockType) material;
    }

    @Nonnull
    EntityType<?> getEntityType(String name);

    @Nonnull
    @SuppressWarnings("unchecked")
    default <T extends Entity> EntityType<T> getEntityType(String name, Class<T> entityType) {
        EntityType<?> type = getEntityType(name);
        if (entityType.isAssignableFrom(type.getEntityClass())) {
            return (EntityType<T>) type;
        } else {
            throw new IllegalArgumentException("Entity type " + type + " is not a " + entityType.getTypeName());
        }
    }

    /**
     * Get the total number of experience points at the given level.
     *
     * @param level the exp level
     * @return if the exp
     * @throws IllegalArgumentException if the level is negative
     */
    long getExpAtLevel(int level);

    EnchantmentType getEnchantmentTypeByName(String name);

    ItemFactory getItemFactory();

    int getOnlinePlayerCount();

    List<Player> getOnlinePlayers();

    Scheduler getBlockingScheduler();

    Scheduler getAsynchronousScheduler();

    EntityDataFactory getEntityDataFactory();
}
