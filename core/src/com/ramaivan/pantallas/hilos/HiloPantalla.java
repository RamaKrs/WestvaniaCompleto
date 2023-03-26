package com.ramaivan.pantallas.hilos;

import com.badlogic.gdx.Gdx;
import com.ramaivan.pantallas.MiJuego;

public class HiloPantalla extends Thread {
	
	public static boolean cambio = false;
	
	
	public HiloPantalla(MiJuego miJuego) {
		
	}
	
	@Override
	public void run() {
		float acumTiempo = 0;
		acumTiempo += Gdx.graphics.getDeltaTime();
		if(acumTiempo >= 5) {
			cambio = true;
		}
	}

}
