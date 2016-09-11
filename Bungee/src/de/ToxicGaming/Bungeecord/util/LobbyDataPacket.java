package de.ToxicGaming.Bungeecord.util;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@SuppressWarnings("serial")
public class LobbyDataPacket implements Serializable {
    @Getter
    private List<String[]> lobbyDatas;
    public LobbyDataPacket( List<String[]> lobbyDatas ) {
        this.lobbyDatas = lobbyDatas;
    }
}
