package Kasteve.donald.magicWeaponz.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CancelPlace implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "漆黒にそまった奈落の欠片");
        lore.add(ChatColor.WHITE+"虚空の心を作成するために使用される");

        if (item != null && item.getType() == Material.POLISHED_BLACKSTONE_BUTTON && item.getItemMeta().getLore().equals(lore)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);


            }
        }
    }
}
