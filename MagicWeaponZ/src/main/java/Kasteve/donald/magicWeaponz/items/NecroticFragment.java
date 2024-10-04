package Kasteve.donald.magicWeaponz.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NecroticFragment implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {

        Random random = new Random();

        int randomNumber = random.nextInt(49);
        if (randomNumber == 36) {

            if (event.getEntityType() == EntityType.SKELETON||event.getEntityType()==EntityType.WITHER_SKELETON) {
                String name = event.getEntity().getCustomName();

                EntityType Type=event.getEntityType();

                    LivingEntity entity = event.getEntity();

                    String name2 = event.getEntity().getCustomName();
                    entity.remove();
                    spawnEnemyNPC(entity, name2 ,event,Type);

            }
        }
    }


    public static void spawnEnemyNPC(LivingEntity entity, String name2 ,CreatureSpawnEvent event,EntityType Type) {

        Location target = entity.getLocation();
        if (name2 == null) {
            if (Type.equals(EntityType.SKELETON)){
                LivingEntity npc = (LivingEntity) entity.getWorld().spawnEntity(target, EntityType.SKELETON);
                SetNPC(npc);
            }else{
                LivingEntity npc = (LivingEntity) entity.getWorld().spawnEntity(target, EntityType.WITHER_SKELETON);
                SetNPC(npc);
            }

        }
    }
    public  static  void SetNPC(LivingEntity npc){
    ItemStack sword = new ItemStack(Material.DRIED_KELP);
    ItemMeta meta = sword.getItemMeta();
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "ネクロティックフラグメント");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.WHITE + "呪われた何かの欠片");
            lore.add(ChatColor.DARK_PURPLE+"ネクロティックハンドル"+ChatColor.WHITE+"を作成できる。");
            lore.add(ChatColor.DARK_PURPLE+"");
            lore.add(ChatColor.DARK_PURPLE+"❂NECROTIC❂");
            meta.addEnchant(Enchantment.BINDING_CURSE, 4 ,true);
            meta.setLore(lore);
            sword.setItemMeta(meta);

            npc.getEquipment().setChestplate(sword);
            npc.getEquipment().setChestplateDropChance(100);
            npc.setAI(true);
}}