package com.ramaivan.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ramaivan.utils.Utiles;

public class PantallaCarga extends Pantalla implements Screen {
	private Texture imagenCargaT;
	private Sprite imagenCargaS;
	private SpriteBatch sb;
	private OrthographicCamera camera;

	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	private int w;
	private int h;
	private BitmapFont font;
	private float acumTiempo = 0;

//	private void temporizador(MiJuego miJuego) {
//		acumTiempo += Gdx.graphics.getDeltaTime();
//		if(acumTiempo >= 5) {
//			miJuego.setScreen(new PantallaMenu());
//		}
//		
//	}

	public PantallaCarga(MiJuego miJuego) {
		super(miJuego);
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width - 10 / 2, 300);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		System.out.println("PantallaCargaShow");
		parameter.size = 80;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fuentes/gothicSchool.ttf"));
//		font = new BitmapFont(Gdx.files.internal("fuentes/gothicSchool.ttf"));
		font = generator.generateFont(parameter);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w / 2, h / 2);
		imagenCargaT = new Texture(Gdx.files.internal("imagenes/cockstudios.png"));
		imagenCargaS = new Sprite(imagenCargaT);
		imagenCargaS.setCenterX(w / 2);
		imagenCargaS.setScale(0.45f, 0.45f);
		sb = new SpriteBatch();

	}

	@Override
	public void render(float delta) {
		System.out.println("PANTALLACARGARENDER");

		sb.begin();
		imagenCargaS.draw(sb);
		font.draw(sb, "Cock Studios", w / 2 - 200, h / 4);
		sb.end();
		acumTiempo += Gdx.graphics.getDeltaTime();

		if (acumTiempo >= 1) {
			Utiles.juego.setScreen(new PantallaMenu(Utiles.juego));
		}

	}

	@Override
	public void hide() {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("DISPOSE PANTALLA CARGA");
		

	}

	@Override
	public void dispose() {
		this.imagenCargaT.dispose();
		this.sb.dispose();
		this.dispose();
	}


}
