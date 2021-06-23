package de.blu.itemstackbuilder;

import de.blu.itemstackbuilder.builder.ItemStackBuilder;
import de.blu.itemstackbuilder.builder.LeatherArmorItemStackBuilder;
import de.blu.itemstackbuilder.enchantment.GlowEnchantment;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;

public final class ItemStackBuilderPlugin extends JavaPlugin {

  @Getter private static ItemStackBuilderPlugin instance;

  @Override
  public void onEnable() {
    instance = this;
    GlowEnchantment.register();

    Bukkit.getPluginManager()
        .registerEvents(
            new Listener() {
              @EventHandler
              public void onJoin(PlayerJoinEvent e) {
                Player player = e.getPlayer();
                player.sendMessage("Granted Items");

                CraftPlayer craftPlayer = (CraftPlayer) player;

                player.sendMessage("locale: " + craftPlayer.getLocale());
                player.sendMessage(ChatMessageType.ACTION_BAR);
              }
            },
            this);
  }
}
