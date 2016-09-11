package de.ToxicGaming.Bungeecord.listener;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {
	private List<Long> loginTimes = new CopyOnWriteArrayList<>();
	@EventHandler
	public void onLogin(LoginEvent event) {
//		List<Player> onlinePlayers = new ArrayList<Player>();
		String ip = event.getConnection().getAddress().getAddress().getHostAddress();
		String name = event.getConnection().getName().toLowerCase();
		UUID uniqueID = event.getConnection().getUniqueId();
		BungeeProxy.getInstance().getLogger().log(Level.INFO, "Player "+name+" connected with ip-address: "+ip+" and uniqueID: "+uniqueID+"!");
//		if(BungeProxy.getWartungMode() = true) {
//			event.setCancelled(true);
//			event.setCancelReason("§6Der Server befindet sich im Wartungsmodus!");
//			return;
//		}
	}
	@EventHandler
	public void onPreLogn (PreLoginEvent event) {
		event.registerIntent(BungeeProxy.getInstance());
		ProxyServer.getInstance().getScheduler().runAsync(BungeeProxy.getInstance(), () -> {
			int atSecond = 0;
			Iterator<Long> timeIterator = loginTimes.iterator();
			while(timeIterator.hasNext()) {
				long time = timeIterator.next();
				long diff = System.currentTimeMillis() - time;
				if(diff <= 1000) {
					atSecond++;
				}else {
					loginTimes.remove(time);
				}
			}
			if(atSecond >= 3) {
				System.out.println("Denied Login for "+event.getConnection().getName()+" ["+atSecond+"]");
				event.setCancelReason("§cEs melden sich momentan zu viele Spieler auf diesem Proxyserver an !\n"
						+ "§2Probiere es in ca. 3 Sekunden neu!");
				event.setCancelled(true);
				event.completeIntent(BungeeProxy.getInstance());
				return;
			}
		});
	}
}
