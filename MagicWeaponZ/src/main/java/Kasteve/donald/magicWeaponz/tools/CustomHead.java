package Kasteve.donald.magicWeaponz.tools;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class CustomHead {
    public static ItemStack getHead(String base64Texture, UUID uuid, String Display, List<String> lore) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();

        GameProfile profile = new GameProfile(uuid, "donald_kun");
        profile.getProperties().put("textures", new Property("textures", base64Texture));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        skullMeta.setDisplayName(Display);
        skullMeta.setLore(lore);
        head.setItemMeta(skullMeta);
        return head;
    }
}
