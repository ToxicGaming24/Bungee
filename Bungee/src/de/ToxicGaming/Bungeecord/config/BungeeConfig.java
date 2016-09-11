package de.ToxicGaming.Bungeecord.config;

import java.io.File;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import lombok.Getter;
import net.cubespace.Yamler.Config.Config;

public class BungeeConfig extends Config{
	@Getter
	private String name = "proxyname";
	@Getter
	private String host = "proxyip";
	@Getter
	private int port = 0;
	public BungeeConfig() {
		CONFIG_FILE = new File(BungeeProxy.getInstance().getDataFolder().getAbsolutePath(),"config.yml");
	}
}
