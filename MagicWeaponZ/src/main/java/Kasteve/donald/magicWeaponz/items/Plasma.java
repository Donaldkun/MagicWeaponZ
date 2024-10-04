package Kasteve.donald.magicWeaponz.items;

import Kasteve.donald.magicWeaponz.tools.CustomHead;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Plasma implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {

        Random random = new Random();

        int randomNumber = random.nextInt(100);
        if (randomNumber == 19) {

            if (event.getEntityType() == EntityType.CREEPER) {

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
            if (Type.equals(EntityType.CREEPER)){
                LivingEntity npc = (LivingEntity) entity.getWorld().spawnEntity(target, EntityType.CREEPER);
                SetNPC(npc);
            }
        }
    }
    public  static  void SetNPC(LivingEntity npc){
        String texture ="e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFjNjI4YmQ3YTIzZWM2ZDdjZjg4NzkzYTdlNDFlMDY2MTk3MTU5OWNlZjViODNmZmE2NzhhNmY0NWQyMDhkMCJ9fX0=";

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "プラズマ");
        lore.add(ChatColor.WHITE + "個体、液体、気体、そして"+ChatColor.AQUA+"プラズマ");
        lore.add(ChatColor.WHITE + "あなたの腕は頑張れば黒曜石ぐらい壊せるから、");
        lore.add(ChatColor.WHITE + "持っても平気！兵器！");
        ItemStack customHead = CustomHead.getHead(texture, UUID.fromString("f23b62ed-2e54-4cbe-8f3f-02e045fa135e"),ChatColor.AQUA+"Plasma",lore);

        npc.getEquipment().setChestplate(customHead);
        npc.getEquipment().setChestplateDropChance(100);
        npc.setAI(true);
    }}