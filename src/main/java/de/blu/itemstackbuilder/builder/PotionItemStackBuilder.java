package de.blu.itemstackbuilder.builder;

import org.bukkit.Material;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public final class PotionItemStackBuilder extends ItemStackBuilder {

  public PotionItemStackBuilder() {
    super();
    this.type(Material.POTION);
  }

  public PotionItemStackBuilder effect(PotionType potionType, int level, boolean splash) {
    Potion potion = new Potion(potionType, level);
    potion.setSplash(splash);
    this.setItemStack(potion.toItemStack(this.getItemStack().getAmount()));
    return this;
  }
}
