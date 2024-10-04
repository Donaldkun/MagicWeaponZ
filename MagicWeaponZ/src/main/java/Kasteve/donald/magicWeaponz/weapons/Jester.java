package Kasteve.donald.magicWeaponz.weapons;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import Kasteve.donald.magicWeaponz.tools.CustomHead;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Jester implements Listener {

        public static void registerRecipes(){
            String base64="e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjEyYjUwMDRjNmVlMWFlZjgzMzQwNmY1MTkyY2UwNzY3MDMzYTc3MTRjMzY1NzhkMmM2MzhhNDZmZmNiOTE3MSJ9fX0=";
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.RED + ChatColor.ITALIC + "Donald The Jester");
            lore.add(ChatColor.WHITE + "ダメージを受けられない。");
            lore.add(ChatColor.WHITE + "絶対に死ねない呪いの仮面");
            lore.add(ChatColor.GREEN + "❃CYBORG❃");
            lore.add(ChatColor.RED + "✢管理者用アイテム✢");

            ItemStack customHead = CustomHead.getHead(base64, UUID.fromString("45451919-abcd-1515-aaaa-114514df11d5"),ChatColor.RESET+"Donald The "+ChatColor.RED+"JESTER",lore);
            //metas
            ItemMeta meta = customHead.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION,4,true);
            meta.addEnchant(Enchantment.THORNS,5,true);
            meta.addEnchant(Enchantment.BINDING_CURSE,4, true);
            meta.addEnchant(Enchantment.SHARPNESS,5, true);
            customHead.setItemMeta(meta);

            NamespacedKey key = new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Jester_Donald");
            ShapedRecipe recipe = new ShapedRecipe(key, customHead);
            recipe.shape("BBB", "BBB", "BBB");
            recipe.setIngredient('B', Material.BARRIER);

            Bukkit.addRecipe(recipe);
        }

        @EventHandler
        public void NightVision(EntityDamageEvent event){
            if (event.getEntityType().equals(EntityType.PLAYER)) {
                Player player = (Player) event.getEntity();
                ItemStack newHelmet = player.getInventory().getHelmet();
                if (newHelmet.getType() == Material.PLAYER_HEAD && newHelmet.getItemMeta().getLore().contains(ChatColor.WHITE + "絶対に死ねない呪いの仮面")) {
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT,  0.5f, 1);
                }else {
                    ItemStack Handitem = player.getInventory().getItemInMainHand();

                    //OverClock Sword
                    if (Handitem.getType() == Material.IRON_SWORD  && newHelmet.getItemMeta().getLore().contains(ChatColor.WHITE + "自分の"+ChatColor.RED+"♥最大体力"+ChatColor.WHITE+"の6倍以上のダメージを無効化する。")) {


                     if (player.getMaxHealth()*5 <= event.getDamage()) {
                         event.setCancelled(true);
                         player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 10, 170));
                         player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT,  0.5f, 1);

                         String input = Handitem.getLore().get(5);
                         String[] parts = input.split(":");
                         String numberString = parts[1].trim();
                         int number = Integer.parseInt(numberString);
                         if (number !=1){

                         int number2=number-1;

                         ItemStack Sword = new ItemStack(Material.IRON_SWORD);
                         ItemMeta SwordMeta = Sword.getItemMeta();
                         SwordMeta.setDisplayName(ChatColor.BLUE+"Over-clock Sword");
                         List<String> lore = new ArrayList<>();
                         lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.GREEN + ChatColor.ITALIC + "OC-Mode"+ChatColor.YELLOW+"手に持っている間に発動。");
                         lore.add(ChatColor.WHITE + "チャージをひとつ消費して発動する。");
                         lore.add(ChatColor.WHITE + "自分の"+ChatColor.RED+"♥最大体力"+ChatColor.WHITE+"の6倍以上のダメージを無効化する。");
                         lore.add(ChatColor.WHITE + "ただし、1秒だけ動けなくなる。");
                         lore.add(ChatColor.WHITE + "チャージ : "+number2);
                         lore.add(ChatColor.WHITE + "サバイバルでこれ作れたらすごすぎる");
                         lore.add(ChatColor.GRAY + "☣Mechanic☣");

                         SwordMeta.setLore(lore);
                         SwordMeta.addEnchant(Enchantment.SHARPNESS,25,true);
                         SwordMeta.setUnbreakable(true);
                         Sword.setItemMeta(SwordMeta);

                         player.getInventory().setItem(player.getInventory().first(Handitem), Sword);
                        }else {
                             player.sendMessage(ChatColor.RED+""+ChatColor.STRIKETHROUGH+"OH NO!!! "+ChatColor.RESET+ ChatColor.RED +"あなたのOver-clock Swordが劣化でこわれました！");
                         }
                     }
                    }
                }
            }
        }
    }
