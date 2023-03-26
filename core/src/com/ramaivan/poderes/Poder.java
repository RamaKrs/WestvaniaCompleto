package com.ramaivan.poderes;

public class Poder {
	
	private int cooldown;
	public int usosDisponibles;
	private int usosTotales;
	private String nombre;
	public Poder(int cooldown, int usosDisponibles, int usosTotales, String nombre) {
		this.cooldown = cooldown;
		this.usosDisponibles = usosDisponibles;
		this.usosTotales = usosTotales;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int usosDisponibles() {
		return usosDisponibles;
	}
	public int getCooldown() {
		return cooldown;
	}

	
}
