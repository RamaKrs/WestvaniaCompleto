package com.ramaivan.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class ColisionesTiled {

	public void parseObjetosMapa(World mundo, MapObjects objetos) {
		for(MapObject objeto : objetos) {
			Shape forma;
			if(objeto instanceof PolygonMapObject) {
				forma = crearPoligonos((PolygonMapObject) objeto);
			} else {
				continue;
			}
			Body cuerpo;
			BodyDef defCuerpo = new BodyDef();
			defCuerpo.type = BodyDef.BodyType.StaticBody;
			cuerpo = mundo.createBody(defCuerpo);
			cuerpo.createFixture(forma, 1.0f);
			forma.dispose();
		}
	}

	private ChainShape crearPoligonos(PolygonMapObject poligono) {
		float[] vertices = poligono.getPolygon().getTransformedVertices();
		Vector2[] verticesMundo = new Vector2[vertices.length / 2];
		
		for (int i = 0; i < verticesMundo.length; i++) {
			verticesMundo[i] = new Vector2(vertices[i * 2] / Utiles.PPM, vertices[i * 2 + 1] / Utiles.PPM);			
		}
		
		ChainShape cs = new ChainShape();
		cs.createChain(verticesMundo);
		
		return cs;
	}
	
}
