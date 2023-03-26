package com.ramaivan.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ramaivan.enums.Direcciones;
import com.ramaivan.utils.Utiles;

public class Animacion {
	private Animation<TextureRegion> animacion;
	private TextureRegion[] regionesAnimaciones;
	private Texture imagenBase;
	private float tiempoTransc = 0, anchoImagenRegion = 0, alturaImagenRegion = 0;
	private float posXInicial = 0, posYinicial = 0;

	public Animacion(String ruta, int columnasImagen, int filasImagen, float duracionFrame, int ancho, int alto) {
		imagenBase = new Texture(ruta);

		TextureRegion[][] tmp = TextureRegion.split(imagenBase, ancho + 2, alto + 2);

		regionesAnimaciones = new TextureRegion[filasImagen * columnasImagen];
		System.out.println();
		int index = 0;
		for (int i = 0; i < filasImagen; i++) {
			for (int j = 0; j < columnasImagen; j++) {
				regionesAnimaciones[index++] = tmp[i][j];
			}
		}

		anchoImagenRegion = regionesAnimaciones[0].getRegionWidth();

		alturaImagenRegion = regionesAnimaciones[0].getRegionHeight();

		animacion = new Animation<TextureRegion>(duracionFrame, regionesAnimaciones);

	}

	public void render(Batch batch, float delta, float x, float y, float ancho, float alto,
			Direcciones direccionApuntada) {
		tiempoTransc += delta;
		TextureRegion frameActual = animacion.getKeyFrame(tiempoTransc, true);
		if (direccionApuntada == Direcciones.IZQUIERDA) {
			x += ancho;
			ancho *= -1;
		}
		
		batch.draw(frameActual, x, y, ancho, alto);
	}

	public Animation<TextureRegion> getAnimacion() {
		return animacion;
	}

	public TextureRegion[] getRegionesAnimaciones() {
		return regionesAnimaciones;
	}

	public Texture getImagenBase() {
		return imagenBase;
	}

	public float getTiempoTransc() {
		return tiempoTransc;
	}

	public float getAnchoImagenRegion() {
		return anchoImagenRegion;
	}

	public float getAlturaImagenRegion() {
		return alturaImagenRegion;
	}

	public float getPosXInicial() {
		return posXInicial;
	}

	public float getPosYinicial() {
		return posYinicial;
	}

}
