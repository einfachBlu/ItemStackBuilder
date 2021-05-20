package de.blu.itemstackbuilder.builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public final class DefaultsItemStackBuilder extends ItemStackBuilder {

  /**
   * get a placeholder-glass
   *
   * @return itembuilder
   */
  public DefaultsItemStackBuilder placeHolderGlass(Material glassMaterial) {
    this.type(glassMaterial);
    this.displayName(ChatColor.GRAY + " ");
    return this;
  }
}
