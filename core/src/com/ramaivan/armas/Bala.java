package com.ramaivan.armas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.ramaivan.utils.Utiles;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Bala implements BalasUpdater {
	public Body cuerpoBala;
	public int id;
	public boolean destruido;
	private float tiempoBala;

	public Bala(float x, float y, int w, int h, boolean isStatic, short categoriaBits, short mascaraBits, short gIndice,
			int idBala, float acumDeltaTime) {

		this.id = idBala;
		createBala(x, y, w, h, isStatic, categoriaBits, mascaraBits, gIndice);

		tiempoBala = acumDeltaTime;
	}

	

	public void createBala(float x, float y, int w, int h, boolean isStatic, short categoriaBits, short mascaraBits,
			short gIndice) {

		BodyDef def = new BodyDef();
		if (isStatic) {
			def.type = BodyType.StaticBody;
		} else {
			def.type = BodyType.DynamicBody;
		}

		def.position.set(x, y);
		def.fixedRotation = true;
		def.bullet = true;


		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w / 2 / Utiles.PPM, h / 2 / Utiles.PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = (short) categoriaBits;
		fdef.filter.maskBits = (short) mascaraBits;
		fdef.filter.groupIndex = gIndice;
		fdef.density = 1;
		fdef.isSensor = true;


		this.cuerpoBala = Utiles.globalWorld.createBody(def);

		this.cuerpoBala.createFixture(fdef).setUserData(this);
//		System.out.println(id + "    dwdwd3d3");


	}

	public Body getCuerpoBala() {
		return cuerpoBala;
	}

	public void eliminarBala() {

		Utiles.globalWorld.destroyBody(this.cuerpoBala);

	}

	@Override
	public void updateBalas(float deltaTime, Array<Bala> balasEnMapa, float acumDeltaTime) {
		// ahora tendría que borrar la bala despues de cierta distancia recorrida
//		System.out.println("update de la bala");
		if (destruido) {
//			System.out.println("id antes del remove index: " + this.id);
//			System.out.println("array size: " + balasEnMapa.size);
			balasEnMapa.removeIndex(this.id);
			for (int i = this.id; i < balasEnMapa.size; i++) {
				balasEnMapa.get(i).id -= 1;
			}
			// para cada bala con mayor id que este, restarle -1 a su id
//			System.out.println(this.id);
			eliminarBala();
//			System.out.println("eliminar bala");
		}
//		System.out.println(acumDeltaTime);
		if (tiempoBala + 0.3 < acumDeltaTime) {
			this.destruido = true;
		}

	}

}
