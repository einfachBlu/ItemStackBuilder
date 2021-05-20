package de.blu.itemstackbuilder.builder;

import de.blu.itemstackbuilder.ItemStackBuilderPlugin;
import de.blu.itemstackbuilder.enchantment.GlowEnchantment;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ItemStackBuilder {

  private ItemStack itemStack;
  private ItemMeta itemMeta;

  public ItemStackBuilder() {
    this.itemStack = new ItemStack(Material.AIR);
    this.itemMeta = this.getItemStack().getItemMeta();
  }

  public static ItemStackBuilder air() {
    return new ItemStackBuilder().type(Material.AIR);
  }

  public static BookItemStackBuilder book() {
    return new BookItemStackBuilder();
  }

  public static BookItemStackBuilder book(boolean writeable) {
    return new BookItemStackBuilder().writeable(writeable);
  }

  public static PotionItemStackBuilder potion() {
    return new PotionItemStackBuilder();
  }

  public static DefaultsItemStackBuilder defaults() {
    return new DefaultsItemStackBuilder();
  }

  public static LeatherArmorItemStackBuilder armor() {
    return new LeatherArmorItemStackBuilder();
  }

  public static LeatherArmorItemStackBuilder armor(
      LeatherArmorItemStackBuilder.ArmorElement armorElement) {
    return (LeatherArmorItemStackBuilder)
        new LeatherArmorItemStackBuilder().type(armorElement.getMaterial());
  }

  public static SkullItemStackBuilder skull() {
    return new SkullItemStackBuilder();
  }

  public static SkullItemStackBuilder skull(Material material) {
    return (SkullItemStackBuilder) new SkullItemStackBuilder().type(material);
  }

  public static ItemStackBuilder normal(Material material) {
    return new ItemStackBuilder().type(material);
  }

  public static ItemStackBuilder normal(Material material, int amount) {
    return new ItemStackBuilder().type(material).amount(amount);
  }

  public static ItemStackBuilder normal(Material material, int amount, short durability) {
    return new ItemStackBuilder().type(material).amount(amount).durability(durability);
  }

  public static ItemStackBuilder wrap(ItemStack itemStack) {
    if (itemStack.getType().equals(Material.SKELETON_SKULL)
        || itemStack.getType().equals(Material.WITHER_SKELETON_SKULL)
        || itemStack.getType().equals(Material.PLAYER_HEAD)
        || itemStack.getType().equals(Material.CREEPER_HEAD)
        || itemStack.getType().equals(Material.DRAGON_HEAD)
        || itemStack.getType().equals(Material.ZOMBIE_HEAD)) {
      SkullItemStackBuilder skullItemStackBuilder = new SkullItemStackBuilder();
      skullItemStackBuilder.setItemStack(itemStack);
      skullItemStackBuilder.setItemMeta(itemStack.getItemMeta());
      return skullItemStackBuilder;
    }

    ItemStackBuilder builder = new ItemStackBuilder();
    builder.setItemStack(itemStack);
    builder.setItemMeta(itemStack.getItemMeta());
    return builder;
  }

  public static ItemStackBuilder copy(ItemStack itemStack) {
    return ItemStackBuilder.wrap(itemStack.clone());
  }

  public ItemStack build() {
    this.getItemStack().setItemMeta(this.getItemMeta());
    return this.getItemStack();
  }

  public Material type() {
    return this.getItemStack().getType();
  }

  public ItemStackBuilder type(Material material) {
    this.getItemStack().setType(material);
    this.setItemMeta(this.getItemStack().getItemMeta());
    return this;
  }

  public int amount() {
    return this.getItemStack().getAmount();
  }

  public ItemStackBuilder amount(int amount) {
    this.getItemStack().setAmount(amount);
    return this;
  }

  public short durability() {
    return this.getItemStack().getDurability();
  }

  public ItemStackBuilder durability(short durability) {
    this.getItemStack().setDurability(durability);
    return this;
  }

  public MaterialData data() {
    return this.getItemStack().getData();
  }

  public ItemStackBuilder data(MaterialData data) {
    this.getItemStack().setData(data);
    return this;
  }

  public ItemStackBuilder displayName(String displayName) {
    if (this.getItemMeta() == null) {
      return this;
    }

    this.getItemMeta().setDisplayName(displayName);
    this.build();
    return this;
  }

  public String displayName() {
    return this.getItemMeta().getDisplayName();
  }

  public Component loreEntry(int index) {
    if (this.getItemMeta() == null) {
      return Component.empty();
    }

    List<Component> lore = this.getItemMeta().lore();
    if (lore == null) {
      return Component.empty();
    }

    if (lore.size() <= index) {
      return Component.empty();
    }

    return lore.get(index);
  }

  public ItemStackBuilder loreEntry(int index, Component line) {
    if (this.getItemMeta() == null) {
      return this;
    }

    if (index < 0) {
      return this;
    }

    List<Component> lore = this.getItemMeta().lore();
    if (lore == null) {
      lore = new ArrayList<>();
    }

    if (lore.size() <= index) {
      for (int i = 0; i < index + 1; i++) {
        if (lore.size() <= i) {
          lore.add(Component.empty());
        }
      }
    }

    lore.set(index, line);
    this.getItemMeta().lore(lore);
    this.build();

    return this;
  }

  public ItemStackBuilder clearLore() {
    if (this.getItemMeta() == null) {
      return this;
    }

    this.getItemMeta().lore(null);
    return this;
  }

  public List<Component> lore() {
    if (this.getItemMeta() == null) {
      return new ArrayList<>();
    }

    return this.getItemMeta().lore();
  }

  public ItemStackBuilder lore(Component... lines) {
    this.lore(Arrays.asList(lines));
    return this;
  }

  public ItemStackBuilder lore(String... lines) {
    this.lore(Arrays.stream(lines).map(Component::text).collect(Collectors.toList()));
    return this;
  }

  public ItemStackBuilder lore(List<Component> lines) {
    if (this.getItemMeta() == null) {
      return this;
    }

    this.getItemMeta().lore(lines);
    return this;
  }

  public ItemStackBuilder addItemFlags(ItemFlag... itemFlags) {
    this.getItemMeta().addItemFlags(itemFlags);
    this.build();
    return this;
  }

  public ItemStackBuilder removeItemFlags(ItemFlag... itemFlags) {
    this.getItemMeta().removeItemFlags(itemFlags);
    this.build();
    return this;
  }

  public ItemStackBuilder addEnchantment(Enchantment enchantment, int level) {
    this.getItemMeta().addEnchant(enchantment, level, true);
    this.build();
    return this;
  }

  public ItemStackBuilder removeEnchantment(Enchantment enchantment) {
    this.getItemMeta().removeEnchant(enchantment);
    this.build();
    return this;
  }

  public boolean isGlow() {
    NamespacedKey key = new NamespacedKey(ItemStackBuilderPlugin.getInstance(), "ItemStackBuilder");
    GlowEnchantment glowEnchantment = new GlowEnchantment(key);

    return this.getItemMeta().hasEnchant(glowEnchantment);
  }

  public ItemStackBuilder glow() {
    NamespacedKey key = new NamespacedKey(ItemStackBuilderPlugin.getInstance(), "ItemStackBuilder");
    GlowEnchantment glowEnchantment = new GlowEnchantment(key);
    this.getItemMeta().addEnchant(glowEnchantment, 1, true);
    this.build();
    return this;
  }

  public ItemStackBuilder unglow() {
    NamespacedKey key = new NamespacedKey(ItemStackBuilderPlugin.getInstance(), "ItemStackBuilder");
    GlowEnchantment glowEnchantment = new GlowEnchantment(key);
    this.getItemMeta().removeEnchant(glowEnchantment);
    this.build();
    return this;
  }

  public boolean unbreakable() {
    return this.getItemMeta().isUnbreakable();
  }

  public ItemStackBuilder unbreakable(boolean value) {
    this.getItemMeta().setUnbreakable(value);
    this.build();
    return this;
  }

  public ItemStackBuilder storeNBTBoolean(String key, boolean value) {
    NBTTagCompound tag = this.getOrCreateTag();
    tag.setBoolean(key, value);
    this.updateTag(tag);

    return this;
  }

  public boolean getNBTBoolean(String key) {
    NBTTagCompound tag = this.getOrCreateTag();

    if (!this.hasNBT(key)) {
      return false;
    }

    return tag.getBoolean(key);
  }

  public ItemStackBuilder storeNBTInt(String key, int value) {
    NBTTagCompound tag = this.getOrCreateTag();
    tag.setInt(key, value);
    this.updateTag(tag);

    return this;
  }

  public int getNBTInt(String key) {
    NBTTagCompound tag = this.getOrCreateTag();

    if (!this.hasNBT(key)) {
      return -1;
    }

    return tag.getInt(key);
  }

  public ItemStackBuilder storeNBTDouble(String key, double value) {
    NBTTagCompound tag = this.getOrCreateTag();
    tag.setDouble(key, value);
    this.updateTag(tag);

    return this;
  }

  public double getNBTDouble(String key) {
    NBTTagCompound tag = this.getOrCreateTag();

    if (!this.hasNBT(key)) {
      return -1;
    }

    return tag.getDouble(key);
  }

  public ItemStackBuilder storeNBTString(String key, String value) {
    NBTTagCompound tag = this.getOrCreateTag();
    tag.setString(key, value);
    this.updateTag(tag);

    return this;
  }

  public String getNBTString(String key) {
    NBTTagCompound tag = this.getOrCreateTag();

    if (!this.hasNBT(key)) {
      return "";
    }

    return tag.getString(key);
  }

  public ItemStackBuilder storeNBTList(String key, List<String> value) {
    NBTTagCompound tag = this.getOrCreateTag();

    // Size
    tag.setInt(key, value.size());

    // Set entries
    for (int i = 0; i < value.size(); i++) {
      tag.setString(key + "." + i, value.get(i));
    }

    this.updateTag(tag);
    return this;
  }

  public List<String> getNBTList(String key) {
    NBTTagCompound tag = this.getOrCreateTag();

    if (!this.hasNBT(key)) {
      return Collections.emptyList();
    }

    int size = tag.getInt(key);
    List<String> list = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      list.add(tag.getString(key + "." + i));
    }

    return list;
  }

  public boolean hasNBT(String key) {
    NBTTagCompound tag = this.getOrCreateTag();

    return tag.hasKey(key);
  }

  private NBTTagCompound getOrCreateTag() {
    this.build();

    net.minecraft.server.v1_16_R3.ItemStack stack = CraftItemStack.asNMSCopy(this.getItemStack());
    if (stack.getTag() != null) {
      return stack.getTag();
    }

    NBTTagCompound tag = new NBTTagCompound();
    stack.setTag(tag);
    this.itemStack = CraftItemStack.asCraftMirror(stack);
    this.itemMeta = this.getItemStack().getItemMeta();

    return tag;
  }

  private void updateTag(NBTTagCompound tag) {
    this.build();

    net.minecraft.server.v1_16_R3.ItemStack stack = CraftItemStack.asNMSCopy(this.getItemStack());
    stack.setTag(tag);
    this.itemStack = CraftItemStack.asCraftMirror(stack);
    this.itemMeta = this.getItemStack().getItemMeta();
  }
}
