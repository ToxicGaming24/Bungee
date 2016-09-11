package de.ToxicGaming.Bungeecord.lobby;

import lombok.Getter;

public class Lobby {
	@Getter
	private String name;
	public Lobby(String name) {
		this.name = name;
	}
}
