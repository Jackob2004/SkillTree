package me.shadow_warrior_.skilltree.commands;

import me.shadow_warrior_.skilltree.SkillTree;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Gui  implements CommandExecutor {

    private SkillTree plugin;

    public Gui(SkillTree plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Inventory inventory = Bukkit.createInventory(p,54, ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Ulepszenia Postaci");

        ItemStack pkt = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta pktmeta = pkt.getItemMeta();
        pktmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ilosc punktow: " + plugin.getPoints(p) );
        pkt.setItemMeta(pktmeta);


        ItemStack item = new ItemStack(Material.APPLE, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zwiekszone zycie I");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Efekt: Twoja liczba serc zwiekszy sie do 12");
        lore.add(ChatColor.GOLD + "Koszt: 1000pkt");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        ItemStack item2 = new ItemStack(Material.APPLE, 1);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zwiekszone zycie II");

        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.GREEN +  "Efekt: Twoja liczba serc zwiekszy sie do 14");
        lore2.add(ChatColor.GOLD + "Koszt: 4000pkt");
        itemMeta2.setLore(lore2);
        item2.setItemMeta(itemMeta2);

        ItemStack item3 = new ItemStack(Material.APPLE, 1);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zwiekszone zycie III");

        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.GREEN + "Efekt: Twoja liczba serc zwiekszy sie do 16");
        lore3.add(ChatColor.GOLD + "Koszt: 8000pkt");
        itemMeta3.setLore(lore3);
        item3.setItemMeta(itemMeta3);

        ItemStack item4 = new ItemStack(Material.APPLE, 1);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zwiekszone zycie IV");

        ArrayList<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.GREEN +  "Efekt: Twoja liczba serc zwiekszy sie do 20");
        lore4.add(ChatColor.GOLD + "Koszt: 20000pkt");
        itemMeta4.setLore(lore4);
        item4.setItemMeta(itemMeta4);

        //miecz

        ItemStack item5 = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta itemMeta5 = item5.getItemMeta();
        itemMeta5.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Zwiekszone obrazenia I");

        ArrayList<String> lore5 = new ArrayList<>();
        lore5.add(ChatColor.GREEN + "Efekt: Twoj atak zwiekszy sie o 130%");
        lore5.add(ChatColor.GREEN + "Szansa na zwiekszenie ataku 20%");
        lore5.add(ChatColor.GOLD + "Koszt: 1000pkt");
        itemMeta5.setLore(lore5);
        item5.setItemMeta(itemMeta5);

        ItemStack item6 = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta itemMeta6 = item6.getItemMeta();
        itemMeta6.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Zwiekszone obrazenia II");

        ArrayList<String> lore6 = new ArrayList<>();
        lore6.add(ChatColor.GREEN +  "Efekt: Twoj atak zwiekszy sie o 260%");
        lore6.add(ChatColor.GREEN + "Szansa na zwiekszenie ataku 40%");
        lore6.add(ChatColor.GOLD + "Koszt: 4000pkt");
        itemMeta6.setLore(lore6);
        item6.setItemMeta(itemMeta6);

        ItemStack item7 = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta itemMeta7 = item7.getItemMeta();
        itemMeta7.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Zwiekszone obrazenia III");

        ArrayList<String> lore7 = new ArrayList<>();
        lore7.add(ChatColor.GREEN + "Efekt: Twoj atak zwiekszy sie o 390%");
        lore7.add(ChatColor.GREEN + "Szansa na zwiekszenie ataku 50%");
        lore7.add(ChatColor.GOLD + "Koszt: 8000pkt");
        itemMeta7.setLore(lore7);
        item7.setItemMeta(itemMeta7);

        ItemStack item8 = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta itemMeta8 = item8.getItemMeta();
        itemMeta8.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Zwiekszone obrazenia IV");

        ArrayList<String> lore8 = new ArrayList<>();
        lore8.add(ChatColor.GREEN +  "Efekt: Twoj atak zwiekszy sie o 480%");
        lore8.add(ChatColor.GREEN + "Szansa na zwiekszenie ataku 60%");
        lore8.add(ChatColor.GOLD + "Koszt: 20000pkt");
        itemMeta8.setLore(lore8);
        item8.setItemMeta(itemMeta8);



        /*
        if(p.hasPermission("test.perm")) {
            axeMeta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
        }*/


        inventory.setItem(4, pkt);
        inventory.setItem(10,item4);
        inventory.setItem(19,item3);
        inventory.setItem(28,item2);
        inventory.setItem(37,item);

        inventory.setItem(12,item8);
        inventory.setItem(21,item7);
        inventory.setItem(30,item6);
        inventory.setItem(39,item5);

        p.openInventory(inventory);


        return true;
    }
}
