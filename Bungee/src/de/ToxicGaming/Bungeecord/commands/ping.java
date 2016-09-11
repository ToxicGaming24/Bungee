package de.ToxicGaming.Bungeecord.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ping extends Command {
	public ping() {
		super("ping");
	}
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = ( ProxiedPlayer ) sender;
		player.sendMessage("§2Dein Ping beträgt §6"+ player.getPing()+" §2ms!");
	}
}
