package kr.kro.jhseo1107.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import kr.kro.jhseo1107.Main;

public class Atur implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        if(args[0].equals("info"))
        {
            sender.sendMessage(ChatColor.YELLOW + "AtUR v1.0.0");
            sender.sendMessage(ChatColor.WHITE+"made by jhseo1107");
            return true;
        }
        if(args[0].equals("commandtest"))
        {
            String commandtext = Main.mccommand;
            commandtext = commandtext.replace("NICKNAME",args[1]);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandtext);
            return true;
        }
        return false;
    }
}