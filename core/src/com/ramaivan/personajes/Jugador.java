package com.ramaivan.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.ramaivan.Managers.Animacion;
import com.ramaivan.armas.Arma;
import com.ramaivan.armas.SMG;
import com.ramaivan.enums.Direcciones;
import com.ramaivan.poderes.Poder;
import com.ramaivan.poderes.Teleport;
import com.ramaivan.utils.Constants;
import com.ramaivan.utils.Utiles;

public class Jugador extends InputAdapter {

	private Poder poderActual;
	private Arma armaActual;

	private Animacion animacionIdleWithoutGunDer;
	private Animacion animacionIdleWithGunDer;
	private Animacion animacionWalkWithoutGunDer;
	private Animacion animacionWalkWithGunDer;
	private Animacion animacionMuerte;
	// hacer animacino de muerte

	private Animacion animacionJumpWithoutGunDer;
	private Animacion animacionJumpWithGunDer;

	private Animacion animacionShootDer;

	private int saltosDisponibles = 1;

	private Body pBody;

	private float tamanioCuerpo;

	private float acumDeltaTime;

	private boolean saltando = false;
	private boolean disparando = false;
	private boolean enMov = false;

	private Direcciones direccionApuntada = Direcciones.DERECHA;

	public Jugador(int x, int y) {

		animacionIdleWithoutGunDer = new Animacion("personajesAssests/personajesAnimaciones/idleWithoutGunDer.png", 2,
				1, 100f, 27, 45);

		animacionIdleWithGunDer = new Animacion("personajesAssests/personajesAnimaciones/idleWithGunDer.png", 2, 1,
				100f, 29, 45);

		animacionWalkWithoutGunDer = new Animacion("personajesAssests/personajesAnimaciones/walkWithoutGunDer.png", 3,
				1, 15f, 26, 45);

		animacionWalkWithoutGunDer.getAnimacion().setPlayMode(PlayMode.LOOP_PINGPONG);

		animacionWalkWithGunDer = new Animacion("personajesAssests/personajesAnimaciones/walkWithGunDer.png", 3, 1, 15f,
				27, 45);

		animacionWalkWithGunDer.getAnimacion().setPlayMode(PlayMode.LOOP_PINGPONG);

		animacionJumpWithoutGunDer = new Animacion("personajesAssests/personajesAnimaciones/jumpWithoutGunDer.png", 1,
				1, 1f, 33, 38);

		animacionJumpWithGunDer = new Animacion("personajesAssests/personajesAnimaciones/jumpWithGunDer.png", 1, 1, 1f,
				29, 35);

		pBody = createJugadorBox(x, y, 20, 36, false, (short) Constants.BIT_PLAYER,
				(short) (Constants.BIT_WALL | Constants.BIT_BALA), (short) 0);

		armaActual = new SMG(pBody.getPosition().x, pBody.getPosition().y);
		poderActual = new Teleport();
		animacionShootDer = new Animacion("personajesAssests/personajesAnimaciones/shootDer.png", 2, 1, 15, 32, 45);
		animacionShootDer.getAnimacion().setPlayMode(PlayMode.LOOP_REVERSED);

		tamanioCuerpo = (animacionIdleWithGunDer.getAnchoImagenRegion() / 2) / Utiles.PPM;

	}

	public Animacion getAnimacionIdleWithGun() {
		return animacionIdleWithGunDer;
	}

	public float getAcumDeltaTime() {
		return acumDeltaTime;
	}

	public boolean isEnMov() {
		return enMov;
	}

	public void renderAnimacionActual(SpriteBatch batch) {

		if (disparando) {
			animacionShootDer.render(batch, 1,
					(pBody.getPosition().x * Utiles.PPM)
							- animacionShootDer.getRegionesAnimaciones()[0].getRegionWidth() / 2,
					pBody.getPosition().y * Utiles.PPM - 18, Utiles.MEDIDAS_PERSONAJE.x, Utiles.MEDIDAS_PERSONAJE.y,
					direccionApuntada);

		} else if (!isEnMov()) {
			if (armaActual != null) {

				animacionIdleWithGunDer.render(batch, 1,
						pBody.getPosition().x * Utiles.PPM
								- animacionIdleWithGunDer.getRegionesAnimaciones()[0].getRegionWidth() / 2,
						pBody.getPosition().y * Utiles.PPM - 18, Utiles.MEDIDAS_PERSONAJE.x, Utiles.MEDIDAS_PERSONAJE.y,
						direccionApuntada);
			}

			if (armaActual == null) {
				animacionIdleWithoutGunDer.render(batch, 1,
						pBody.getPosition().x * Utiles.PPM
								- animacionIdleWithoutGunDer.getRegionesAnimaciones()[0].getRegionWidth() / 2,
						pBody.getPosition().y * Utiles.PPM - 18, Utiles.MEDIDAS_PERSONAJE.x, Utiles.MEDIDAS_PERSONAJE.y,
						direccionApuntada);
			}

		} else if (isEnMov()) {
			if (armaActual != null) {

				animacionWalkWithGunDer.render(batch, 1,
						pBody.getPosition().x * Utiles.PPM
								- animacionWalkWithGunDer.getRegionesAnimaciones()[0].getRegionWidth() / 2,
						pBody.getPosition().y * Utiles.PPM - 18, Utiles.MEDIDAS_PERSONAJE.x, Utiles.MEDIDAS_PERSONAJE.y,
						direccionApuntada);
			}

			if (armaActual == null) {
				animacionWalkWithoutGunDer.render(batch, 1,
						pBody.getPosition().x * Utiles.PPM
								- animacionWalkWithoutGunDer.getRegionesAnimaciones()[0].getRegionWidth() / 2,
						pBody.getPosition().y * Utiles.PPM - 18, Utiles.MEDIDAS_PERSONAJE.x, Utiles.MEDIDAS_PERSONAJE.y,
						direccionApuntada);
			}
		}
	}

	public void input() {
		saltando = false;
		enMov = false;
		disparando = false;
		acumDeltaTime += Gdx.graphics.getDeltaTime();
		int horizontalForce = 0;
		int verticalForce = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {

			horizontalForce += 1;
			enMov = true;
			direccionApuntada = Direcciones.DERECHA;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {

			horizontalForce -= 1;
			enMov = true;
			direccionApuntada = Direcciones.IZQUIERDA;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			if (saltosDisponibles > 0) {
				pBody.applyForceToCenter(0, 250, false);
				saltosDisponibles -= 1;
			}

		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {

			verticalForce -= 1;
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && armaActual != null && armaActual.getBalas() > 0) {

			armaActual.disparar(tamanioCuerpo / 2, tamanioCuerpo / 2, direccionApuntada, acumDeltaTime);

			armaActual.getSonidoDisparo().play();
			disparando = true;
		}
		if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			tpCortaDistancia();
		}
		pBody.setLinearVelocity(horizontalForce * 2, pBody.getLinearVelocity().y + verticalForce);
	}

	private void tpCortaDistancia() {
		if (poderActual.getNombre().equals("Teleport") && poderActual.usosDisponibles > 0) {
			switch (direccionApuntada) {
			case DERECHA:
				System.out.println("derecha");
				pBody.setTransform(pBody.getPosition().x + 100 / Utiles.PPM, pBody.getPosition().y, 0);
				poderActual.usosDisponibles -= 1;
				break;

			default:
				System.out.println("derecha");
				pBody.setTransform(pBody.getPosition().x - 100 / Utiles.PPM, pBody.getPosition().y, 0);
				poderActual.usosDisponibles -= 1;
				break;
			}
		}

	}

	public void morir() {
		System.out.println("muere");
		Utiles.globalWorld.destroyBody(pBody);

	}

	public Body createJugadorBox(float x, float y, int w, int h, boolean isStatic, short categoriaBits,
			short mascaraBits, short gIndice) {
		Body body;
		BodyDef Bodydef = new BodyDef();
		Bodydef.fixedRotation = true;
		Bodydef.position.set(x / Utiles.PPM, y / Utiles.PPM);

		if (isStatic) {
			Bodydef.type = BodyType.StaticBody;
		} else {
			Bodydef.type = BodyType.DynamicBody;
		}

//		body = globalWorld.createBody(Bodydef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w / 2 / Utiles.PPM, h / 2 / Utiles.PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.density = 1.0f;
		fdef.filter.categoryBits = (short) categoriaBits;
		fdef.filter.maskBits = (short) mascaraBits;
		fdef.filter.groupIndex = gIndice;

//		body.createFixture(shape, 1);

//		shape.dispose();

		body = Utiles.globalWorld.createBody(Bodydef);
		body.createFixture(fdef).setUserData(this);
		return body;
	}

	public Animacion animacionWalkWithGunDer() {
		return animacionWalkWithGunDer;
	}

	public boolean isSaltando() {
		return saltando;
	}

	public Body getpBody() {
		return pBody;
	}

	public Arma getArmaActual() {
		return armaActual;
	}

	public Poder getPoderActual() {
		return poderActual;
	}

	public void updateSalto() {
		if (poderActual.getNombre().equals("Doble Salto")) {
			saltosDisponibles = 2;
			System.out.println("2");
		} else {
			saltosDisponibles = 200000;
			System.out.println("1");
		}
	}

}
