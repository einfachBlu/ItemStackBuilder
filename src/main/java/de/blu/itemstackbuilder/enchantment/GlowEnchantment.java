package de.blu.itemstackbuilder.enchantment;

import de.blu.itemstackbuilder.ItemStackBuilderPlugin;
import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Set;

public final class GlowEnchantment extends Enchantment {

  public GlowEnchantment(NamespacedKey i) {
    super(i);
    // TODO Auto-generated constructor stub
  }

  @Override
  public boolean canEnchantItem(ItemStack arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public @NotNull Component displayName(int level) {
    return null;
  }

  @Override
  public boolean isTradeable() {
    return false;
  }

  @Override
  public boolean isDiscoverable() {
    return false;
  }

  @Override
  public @NotNull EnchantmentRarity getRarity() {
    return null;
  }

  @Override
  public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
    return 0;
  }

  @Override
  public @NotNull Set<EquipmentSlot> getActiveSlots() {
    return null;
  }

  @Override
  public boolean conflictsWith(Enchantment arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public EnchantmentTarget getItemTarget() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getMaxLevel() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getStartLevel() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isCursed() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTreasure() {
    // TODO Auto-generated method stub
    return false;
  }

  public static void register() {
    try {
      Field f = Enchantment.class.getDeclaredField("acceptingNew");
      f.setAccessible(true);
      f.set(null, true);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      NamespacedKey key =
          new NamespacedKey(ItemStackBuilderPlugin.getInstance(), "ItemStackBuilder");

      GlowEnchantment glowEnchantment = new GlowEnchantment(key);
      Enchantment.registerEnchantment(glowEnchantment);
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
