package de.ToxicGaming.Bungeecord;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import de.ToxicGaming.Bungeecord.commands.alert;
import de.ToxicGaming.Bungeecord.commands.ping;
import de.ToxicGaming.Bungeecord.config.BungeeConfig;
import de.ToxicGaming.Bungeecord.listener.LoginListener;
import lombok.Getter;
import lombok.Setter;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeProxy extends Plugin{
	@Getter
    private static BungeeProxy instance;
    @Getter
    private BungeeConfig bungeeConfig;
    @Getter
    private boolean running = true;
    @Getter
    @Setter
    private int onlinePlayers = 0;
    @Getter
    @Setter
    private List<String> chatBlocked = new CopyOnWriteArrayList<>();
    @Getter
    private boolean connected = false;
    @Getter
    @Setter
    private String motd;
    @Getter
    private String bungeeName;
    @Setter
    private static long lastPacketReceive;
	@Getter
	private Logger logger = Logger.getLogger("Bungee");
	@Override
	public void onEnable() {
		bungeeConfig = new BungeeConfig();
		try{
			bungeeConfig.init();
		}catch(InvalidConfigurationException e) {
			e.printStackTrace();
		}
		instance = this;
		logger.setParent(this.getLogger());
		PluginManager pm = ProxyServer.getInstance().getPluginManager();
		pm.registerListener(getInstance(), new LoginListener());
		pm.registerCommand(getInstance(), new ping());
		pm.registerCommand(getInstance(), new alert());
	}
	@Override
	public void onDisable() {
	}
}
