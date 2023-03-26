package com.ramaivan.mapas;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa1 extends Mapa {

	public Mapa1() {
		super.render = new OrthogonalTiledMapRenderer(new TmxMapLoader().load("mapas/mapa 1/mapa 1.tmx"));
	}

}
