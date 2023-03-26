package com.ramaivan.mapas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa {

	public OrthogonalTiledMapRenderer render;
	
	public void dibujar(OrthographicCamera cam) {
		render.setView(cam);
        render.render();
	}
	
	
	

}
