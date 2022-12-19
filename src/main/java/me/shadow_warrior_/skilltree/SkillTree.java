package me.shadow_warrior_.skilltree;


import me.shadow_warrior_.skilltree.commands.Gui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class SkillTree extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getCommand("ulepszenia").setExecutor(new Gui(this));

        getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig(); // <-- create config.yml

        if (this.getConfig().contains("data")) {
            restoreTest();
            this.getConfig().set("data", null);
            this.saveConfig();
        }

        if (this.getConfig().contains("data1")) {
            restorePoints();
            this.getConfig().set("data1", null);
            this.saveConfig();
        }
    }

    @Override
    public void onDisable() {
        if (!test.isEmpty()) saveTest();
        if (!points.isEmpty()) savePoints();
    }

    // Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user "+p.getPlayer().getUniqueId()+" permission set test.test");

    public static Map<UUID, Integer> test = new HashMap<>();


    public static Map<UUID, Integer> points = new HashMap<>();

    public void addScore(Player player, int amount) {
        test.putIfAbsent(player.getUniqueId(), 1);
        points.putIfAbsent(player.getUniqueId(), 1);
        int currentAmount = test.get(player.getUniqueId());
        int newAmount = currentAmount + amount;
        int currentAmount2 = points.get(player.getUniqueId());
        int newAmount2 = currentAmount2 + amount;
        test.put(player.getUniqueId(), newAmount);
        points.put(player.getUniqueId(), newAmount2);
    }

    public void removeScore(Player player, int amount) {
        test.putIfAbsent(player.getUniqueId(), 1);
        points.putIfAbsent(player.getUniqueId(), 1);
        int currentAmount = test.get(player.getUniqueId());
        int newAmount = currentAmount - amount;
        int currentAmount2 = points.get(player.getUniqueId());
        int newAmount2 = currentAmount2 - amount;
        test.put(player.getUniqueId(), newAmount);
        points.put(player.getUniqueId(), newAmount2);
    }

    public void setScore(Player player, int amount) {
        test.put(player.getUniqueId(), amount);
    }

    public int getScore(Player player) {
        test.putIfAbsent(player.getUniqueId(), 1);
        return test.get(player.getUniqueId());
    }

    public int getPoints(Player player) {
        points.putIfAbsent(player.getUniqueId(), 1);
        return points.get(player.getUniqueId());
    }

    public void saveTest() {
        for (Map.Entry<UUID, Integer> entry : test.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void savePoints() {
        for (Map.Entry<UUID, Integer> entry : points.entrySet()) {
            this.getConfig().set("data1." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void restoreTest() {
        Objects.requireNonNull(this.getConfig().getConfigurationSection("data")).getKeys(false).forEach(key -> {
            Integer content = (Integer) this.getConfig().get("data." + key);
            test.put(UUID.fromString(key), content);
        });
    }

    public void restorePoints() {
        Objects.requireNonNull(this.getConfig().getConfigurationSection("data1")).getKeys(false).forEach(key -> {
            Integer content = (Integer) this.getConfig().get("data1." + key);
            points.put(UUID.fromString(key), content);
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        switch (e.getBlock().getType()) {
            case EMERALD_ORE:
                addScore(p, 4);
            case DIAMOND_ORE:
                addScore(p, 3);
            case GOLD_ORE:
                addScore(p, 2);
            default:
                addScore(p, 1);
        }
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent e) {


            Player p = e.getEntity().getKiller();
            if(p!=null) {
                switch (e.getEntity().getType()) {
                    case ENDER_DRAGON:
                        addScore(p, 100);
                    case WITHER:
                        addScore(p, 80);
                    case GHAST:
                        addScore(p, 20);
                    case WITCH:
                        addScore(p, 5);
                    case WITHER_SKELETON:
                        addScore(p, 4);
                    case ZOMBIE:
                        addScore(p, 2);
                    case SKELETON:
                        addScore(p, 2);
                    case VEX:
                        addScore(p, 2);
                    case HUSK:
                        addScore(p, 2);
                    case VINDICATOR:
                        addScore(p, 3);
                    case ENDERMAN:
                        addScore(p, 3);
                    case CREEPER:
                        addScore(p,2);
                    default:
                        addScore(p, 1);
                }
            }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        assert p != null;
        removeScore(p, 5);
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e) {
        int lvl = (getScore(e.getPlayer()) / 200);
        int num = (getScore(e.getPlayer()) % 200);
        e.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "[Lvl " + lvl + "] " + ChatColor.RESET + e.getPlayer().getDisplayName() + ": " + e.getMessage());
        if (num == 0) {
            Bukkit.broadcastMessage("§a" + e.getPlayer().getDisplayName() + " has reached level §7" + lvl + "§a!");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setscore")) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("Wpisz nazwe gracza: /psm <player>");
            } else {
                String playerName = args[0];
                int num = Integer.parseInt(args[1]);
                Player target = Bukkit.getServer().getPlayerExact(playerName);
                if (target == null) {
                    p.sendMessage("Ten gracz nie jest online");
                } else setScore(target, num);
            }

        }
        if (label.equalsIgnoreCase("score")) {
            Player p = (Player) sender;
            int num = getScore(p);
            p.sendMessage(String.valueOf(num));

        } else if (label.equalsIgnoreCase("punkty")) {
            Player p = (Player) sender;
            int num = getPoints(p);
            p.sendMessage(String.valueOf(num));
        }
        return true;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Ulepszenia Postaci")){

            if (e.getCurrentItem() == null){
                return;
            }

            e.setCancelled(true); //player cannot move items
        }


    }
    */
            public void addTreasure(Material material, Player player){
        ItemStack  item  = new ItemStack(material);
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN  +  "Znalazles:  " +  item);
        player.playSound(player.getLocation(), Sound.ANVIL_USE,1,1);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p  =  e.getPlayer();
        if(!p.hasPermission("poszukiwacz.1")) return;

        int random = (int) (Math.random()*99);

        if(random==1) addTreasure(Material.DIAMOND,p);

        else if(random==0) addTreasure(Material.EMERALD,p);

        else if(random<=6 && random>1) addTreasure(Material.IRON_INGOT,p);

        else if(random<=11 && random>6) addTreasure(Material.GOLD_INGOT,p);

    }
        */
}
