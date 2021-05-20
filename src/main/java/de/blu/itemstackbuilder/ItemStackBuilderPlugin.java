package de.blu.itemstackbuilder;

import de.blu.itemstackbuilder.builder.ItemStackBuilder;
import de.blu.itemstackbuilder.builder.LeatherArmorItemStackBuilder;
import de.blu.itemstackbuilder.enchantment.GlowEnchantment;
import lombok.Getter;
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
                player.sendMessage("clientBrandName: " + craftPlayer.getClientBrandName());

                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.skull()
                            .profile(craftPlayer.getProfile())
                            .displayName("§c§lDas ist ein TestName")
                            .lore("§7Line1", "§7Line2", "§7Line3", "", "§aLine5", "")
                            .glow()
                            .build());

                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.air()
                            .type(Material.DIRT)
                            .displayName("§c§lDas ist ein glow Item")
                            .clearLore()
                            .storeNBTInt("id", 5)
                            .storeNBTInt("id2", 10)
                            .storeNBTBoolean("hasGlow", true)
                            .glow()
                            .build());

                player
                    .getInventory()
                    .addItem(ItemStackBuilder.book(true)
                            .title("§aTitel 1")
                            .author("§ceinfachBlu")
                            .addPage("Test")
                            .addPage("Test2")
                            .addPage("Test3")
                            .addPage("§aTe§cst§b4")
                            .displayName("§c§lBeschreibbar")
                            .build());

                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.book(false)
                                .title("§aTitel 1")
                                .author("§ceinfachBlu")
                                .addPage("Test")
                                .addPage("Test2")
                                .addPage("Test3")
                                .addPage("§aTe§cst§b4")
                                .displayName("§c§lnicht Beschreibbar")
                                .build());
                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.potion()
                            .effect(PotionType.SPEED, 2, true)
                            .displayName("§c§lPotion 1")
                            .build());
                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.potion()
                            .effect(PotionType.SPEED, 1, false)
                            .displayName("§c§lPotion 2")
                            .build());
                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.potion()
                            .effect(PotionType.SPEED, 2, true)
                            .displayName("§c§lPotion 3")
                            .build());
                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.potion()
                            .effect(PotionType.SPEED, 1, false)
                            .displayName("§c§lPotion 4")
                            .build());
                player
                    .getInventory()
                    .addItem(
                        ItemStackBuilder.armor(LeatherArmorItemStackBuilder.ArmorElement.CHESTPLATE)
                            .dye(Color.ORANGE)
                            .displayName("§c§lOrange Jacke")
                            .glow()
                            .build());
              }
            },
            this);
  }
}
