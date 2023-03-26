package com.ramaivan.enums.poderes;

import java.lang.reflect.InvocationTargetException;

import com.ramaivan.poderes.Poder;

public enum Poderes {
	DobleSalto("DobleSalto", "Doble Salto"), Teleport("Teleport", "Teleport");
	private String nombreClase;
	private String nombrePoder;
	private Poderes(String nombreClase, String nombrePoder) {
		this.nombreClase = nombreClase;
		this.nombrePoder = nombrePoder;
	}
	
	
	public static Poder crearPoder(int opc) {
		Poder poder = null;
		Class clase;
		try {
			clase = Class.forName("com.ramaivan.poderes" + values()[opc].getNombreClase());
			poder = (Poder) clase.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return poder;
	}
	
	public String getNombreClase() {	
		return nombreClase;
	}
	
	public String getNombrePoder() {
		return nombrePoder;
	}
}
