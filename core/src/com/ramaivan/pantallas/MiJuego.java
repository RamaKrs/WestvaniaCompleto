package com.ramaivan.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ramaivan.utils.Utiles;

public class MiJuego extends Game{
	private SpriteBatch sb;
	
	public PantallaMenu pantallaMenu;
	public PantallaCarga pantallaCarga;
	public PantallaJuego pantallaJuego;
	
	@Override
	public void create() {

		
		Utiles.juego = this;
		
//		pantallaMenu = new PantallaMenu(this);
		pantallaCarga = new PantallaCarga(this);
//		pantallaJuego = new PantallaJuego(this);
		
		this.setScreen(pantallaCarga);
	}
	
	
	
	@Override
	public void render() {
		super.render();
	}

}
