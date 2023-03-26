package com.ramaivan.mapas;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa2 extends Mapa {

	public Mapa2() {
		super.render = new OrthogonalTiledMapRenderer(new TmxMapLoader().load("mapas/mapa 2/mapa 2.tmx"));
	}

}
