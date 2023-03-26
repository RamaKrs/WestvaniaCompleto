package com.ramaivan.pantallas;

import com.badlogic.gdx.Game;

public class Pantalla {
	protected Game game;
	
	public Pantalla(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
	
}
