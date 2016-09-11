package de.ToxicGaming.Bungeecord.lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.ToxicGaming.Bungeecord.util.LobbyDataPacket;
import lombok.Getter;

public class LobbyManager {
	@Getter
	private static List<Lobby> servers = new CopyOnWriteArrayList<>();
	public static int nextLobby = 0;
	public static String getLobby() {
		if(servers.size() == 0 ) return null;
		if(nextLobby > servers.size()-1) {
			nextLobby = 0;
		}
		Lobby returnLobby = servers.get(nextLobby);
		nextLobby++;
		return returnLobby.getName();
	}
	public static void receiveData(LobbyDataPacket lobbyDataPacket) {
        List<String> receivedLobbies = new ArrayList<>( );
        for( String[] data : lobbyDataPacket.getLobbyDatas()) {
            String name = data[0];
            receivedLobbies.add(name);
            Lobby getLobby = getLobbyByName(name);
            if(getLobby == null) {
                servers.add( new Lobby(name));
            }
            receivedLobbies.add(name.toLowerCase());
        }
        for(Lobby lobby : servers) {
            if(!receivedLobbies.contains(lobby.getName().toLowerCase())) {
                servers.remove(lobby);
            }
        }
    }
    public static Lobby getLobbyByName(String name) {
        for(Lobby lobby : servers) {
            if(lobby.getName().equalsIgnoreCase(name)) {
                return lobby;
            }
        }
        return null;
    }
}
