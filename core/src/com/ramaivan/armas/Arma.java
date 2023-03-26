package com.ramaivan.armas;

import java.sql.Time;
import java.time.Instant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.ramaivan.enums.Direcciones;
import com.ramaivan.utils.Constants;
import com.ramaivan.utils.Utiles;

public abstract class Arma {
	private String nombre;
	protected int balas;
	private Vector2 posicion;
	protected float lastAttack = 0;
	private float coolDownTime;
	float time = 0;
	private Sound sonidoDisparo;
	public Array<Bala> balasEnMapa = new Array<>();

	public Arma(String nombre, int balas, float x, float y, float f) {
		sonidoDisparo = Gdx.audio.newSound(Gdx.files.internal("sonidos/disparo.mp3"));
		this.nombre = nombre;
		this.balas = balas;
		posicion = new Vector2();
		this.posicion.x = x;
		this.posicion.y = y;
		this.coolDownTime = f;
	}

	public String getNombre() {
		return nombre;
	}

	public int getBalas() {
		return balas;
	}

	public Vector2 getPosicion() {
		return posicion;
	}
	public Sound getSonidoDisparo() {
		return sonidoDisparo;
	}

	public void update(float x, float y) {
		this.posicion.x = x;
		this.posicion.y = y;
		time += Gdx.graphics.getDeltaTime();

	}

	public void disparar(float x, float y, Direcciones direccionApuntada, float acumDeltaTime) {

		if (time > getLastAttack() + getCoolDownTime()) {
//			System.out.println("dispara");
			// sale una caja con una velocidad x desde la mitad del sprite del pj;
			if (direccionApuntada == Direcciones.DERECHA) {
				Bala bala = new Bala(posicion.x + 20 / Utiles.PPM, posicion.y + y / 4, 4, 4, false,
						(short) Constants.BIT_BALA, (short) ((Constants.BIT_WALL) | (Constants.BIT_PLAYER)), (short) 0,
						balasEnMapa.size, acumDeltaTime);
				bala.getCuerpoBala().applyLinearImpulse(new Vector2(15 / Utiles.PPM, 0), posicion, false);
				bala.getCuerpoBala().setGravityScale(0);
				balas--;
				balasEnMapa.add(bala);
			} else {
				Bala bala = new Bala(posicion.x - (20 / Utiles.PPM), posicion.y + y / 4, 4, 4, false,
						(short) Constants.BIT_BALA, (short) ((Constants.BIT_WALL) | (Constants.BIT_PLAYER)), (short) 0,
						balasEnMapa.size, acumDeltaTime);
				bala.getCuerpoBala().applyLinearImpulse(new Vector2(-15 / Utiles.PPM, 0), posicion, false);
				bala.getCuerpoBala().setGravityScale(0);
				balas--;
				balasEnMapa.add(bala);
			}

			lastAttack = time;
		}
	}

	public float getLastAttack() {
		return lastAttack;
	}

	public float getCoolDownTime() {
		return coolDownTime;
	}
}
