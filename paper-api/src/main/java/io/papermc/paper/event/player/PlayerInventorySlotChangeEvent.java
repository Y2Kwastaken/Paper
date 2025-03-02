package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

/**
 * Called when a slot contents change in a player's inventory.
 */
@NullMarked
public class PlayerInventorySlotChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int rawSlot;
    private final int slot;
    private final ItemStack oldItemStack;
    private final ItemStack newItemStack;
    private boolean triggerAdvancements = true;

    public PlayerInventorySlotChangeEvent(final Player player, final int rawSlot, final ItemStack oldItemStack, final ItemStack newItemStack) {
        super(player);
        this.rawSlot = rawSlot;
        this.slot = player.getOpenInventory().convertSlot(rawSlot);
        this.oldItemStack = oldItemStack;
        this.newItemStack = newItemStack;
    }

    /**
     * The raw slot number that was changed.
     *
     * @return The raw slot number.
     */
    public int getRawSlot() {
        return this.rawSlot;
    }

    /**
     * The slot number that was changed, ready for passing to
     * {@link Inventory#getItem(int)}. Note that there may be two slots with
     * the same slot number, since a view links two different inventories.
     * <p>
     * If no inventory is opened, internal crafting view is used for conversion.
     *
     * @return The slot number.
     */
    public int getSlot() {
        return this.slot;
    }

    /**
     * Clone of ItemStack that was in the slot before the change.
     *
     * @return The old ItemStack in the slot.
     */
    public ItemStack getOldItemStack() {
        return this.oldItemStack;
    }

    /**
     * Clone of ItemStack that is in the slot after the change.
     *
     * @return The new ItemStack in the slot.
     */
    public ItemStack getNewItemStack() {
        return this.newItemStack;
    }

    /**
     * Gets whether the slot change advancements will be triggered.
     *
     * @return Whether the slot change advancements will be triggered.
     */
    public boolean shouldTriggerAdvancements() {
        return this.triggerAdvancements;
    }

    /**
     * Sets whether the slot change advancements will be triggered.
     *
     * @param triggerAdvancements Whether the slot change advancements will be triggered.
     */
    public void setShouldTriggerAdvancements(final boolean triggerAdvancements) {
        this.triggerAdvancements = triggerAdvancements;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
