package com.ramaivan.mapas;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa3 extends Mapa {

	public Mapa3() {
		super.render = new OrthogonalTiledMapRenderer(new TmxMapLoader().load("mapas/mapa 3/mapa 3.tmx"));
	}

}
