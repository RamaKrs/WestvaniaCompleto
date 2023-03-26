package com.ramaivan.armas;

import com.badlogic.gdx.utils.Array;

public interface BalasUpdater {
	public void updateBalas(float deltaTime, Array<Bala> balasEnMapa, float acumDeltaTime);
}
