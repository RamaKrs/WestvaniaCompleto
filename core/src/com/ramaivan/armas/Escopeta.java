package com.ramaivan.armas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ramaivan.enums.Direcciones;
import com.ramaivan.utils.Constants;
import com.ramaivan.utils.Utiles;

public class Escopeta extends Arma {

	public Escopeta(float x, float y) {
		super("Escopeta", 4, x, y, 400);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void disparar(float x, float y, Direcciones direccionApuntada, float acumDeltaTime) {

		long time = System.currentTimeMillis();
		if (time > getLastAttack() + getCoolDownTime() && getBalas() > 0) {
			// sale una caja con una velocidad x desde la mitad del sprite del pj;
			if (direccionApuntada == Direcciones.DERECHA) {
				for (int i = 0; i < 6; i++) {
					Bala bala = new Bala(getPosicion().x + x, getPosicion().y + y / 4, 4, 4, false,
							(short) Constants.BIT_BALA, (short) (Constants.BIT_WALL | Constants.BIT_PLAYER), (short) 0,
							super.balasEnMapa.size, acumDeltaTime);

					bala.getCuerpoBala().applyAngularImpulse((Utiles.r.nextInt(10) + 100) / Utiles.PPM, false);
					bala.getCuerpoBala().applyLinearImpulse(new Vector2((Utiles.r.nextInt(3) + 10) / Utiles.PPM, 0),
							getPosicion(), false);
					bala.getCuerpoBala().setGravityScale(0);

				}
				balas--;
			} else {
				for (int i = 0; i < 6; i++) {
					Bala bala = new Bala(getPosicion().x - (10 / Utiles.PPM), getPosicion().y + y / 4, 4, 4, false,
							(short) Constants.BIT_BALA, (short) (Constants.BIT_WALL | Constants.BIT_PLAYER), (short) 0,
							super.balasEnMapa.size, acumDeltaTime);
					bala.getCuerpoBala().applyAngularImpulse((Utiles.r.nextInt(10) + 100) / Utiles.PPM, false);
					bala.getCuerpoBala().applyLinearImpulse(new Vector2(-(Utiles.r.nextInt(3) + 10) / Utiles.PPM, 0),
							getPosicion(), false);
					bala.getCuerpoBala().setGravityScale(0);
				}
				balas--;
			}
			lastAttack = time;
		}

	}
}
