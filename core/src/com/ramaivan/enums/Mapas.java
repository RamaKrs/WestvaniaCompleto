package com.ramaivan.enums;

import java.lang.reflect.InvocationTargetException;

import com.ramaivan.mapas.Mapa;
import com.ramaivan.utils.Utiles;

public enum Mapas {

	MAPA1("Mapa1"), MAPA2("Mapa2"), MAPA3("Mapa3");
	
	private String nombreClase;

	private Mapas(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	
	public static Mapa generarMapa() {
			
			Class clase;
			Mapa mapa = null;
			
			try {
				clase = Class.forName("com.ramaivan.mapas." + values()[Utiles.r.nextInt(values().length)].nombreClase);
				mapa = (Mapa) clase.getDeclaredConstructor().newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return mapa;		
		}
	
}
