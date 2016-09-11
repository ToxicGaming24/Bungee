package de.ToxicGaming.Bungeecord.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class alert extends Command{
	public alert() {
		super("alert");
	}
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer proxiedPlayer = ( ProxiedPlayer ) sender;
		ProxyServer.getInstance().broadcast("§2Rundruf: §6"+args);
	}
}
