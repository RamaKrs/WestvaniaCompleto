package com.ramaivan.utils;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ramaivan.pantallas.MiJuego;

public abstract class Utiles {
	
	public static MiJuego juego;
	public final static float PPM = 32;
	public static World globalWorld;
	public static Random r = new Random();
	public final static Vector2 MEDIDAS_PERSONAJE = new Vector2(25, 40);
	
	public static Body createBox(float x, float y, int w, int h, boolean isStatic, short categoriaBits,
			short mascaraBits, short gIndice) {
		Body body;
		BodyDef Bodydef = new BodyDef();
		Bodydef.fixedRotation = true;
		Bodydef.position.set(x / PPM, y / PPM);

		if (isStatic) {
			Bodydef.type = BodyType.StaticBody;
		} else {
			Bodydef.type = BodyType.DynamicBody;
		}

//		body = globalWorld.createBody(Bodydef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w / 2 / PPM, h / 2 / PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.density = 1.0f;
		fdef.filter.categoryBits = (short) categoriaBits;
		fdef.filter.maskBits = (short) mascaraBits;
		fdef.filter.groupIndex = gIndice;

//		body.createFixture(shape, 1);

//		shape.dispose();

		body = Utiles.globalWorld.createBody(Bodydef);
		body.createFixture(fdef).setUserData(body);
		return body;
	}
	
	
	
//
//	public static Body createBala(float x, float y, int w, int h, boolean isStatic, short categoriaBits,
//			short mascaraBits, short gIndice) {
////		Body pBody;
//		BodyDef def = new BodyDef();
//		if (isStatic) {
//			def.type = BodyType.StaticBody;
//		} else {
//			def.type = BodyType.DynamicBody;
//		}
//
//		def.position.set(x, y);
//		def.fixedRotation = true;
////		pBody = globalWorld.createBody(def);
//
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(w / 2 / PPM, h / 2 / PPM);
//
//		FixtureDef fdef = new FixtureDef();
//		fdef.shape = shape;
//		fdef.density = 1.0f;
//		fdef.filter.categoryBits = (short) categoriaBits;
//		fdef.filter.maskBits = (short) mascaraBits;
//		fdef.filter.groupIndex = gIndice;
//
////		pBody.createFixture(shape, 1);
////		shape.dispose();
//		
//		return globalWorld.createBody(def).createFixture(fdef).getBody();
//	}

}
