package de.blu.itemstackbuilder;

import de.blu.itemstackbuilder.enchantment.GlowEnchantment;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemStackBuilderPlugin extends JavaPlugin {

  @Getter private static ItemStackBuilderPlugin instance;

  @Override
  public void onEnable() {
    instance = this;
    GlowEnchantment.register();
  }
}
