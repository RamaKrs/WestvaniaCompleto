package com.ramaivan.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaMenu extends Pantalla implements Screen {
	private Texture imagenMenuT;
	private Sprite imagenMenuS;
	private SpriteBatch sb;
	private OrthographicCamera camera;
	private int w;
	private int h;

	public PantallaMenu(MiJuego miJuego) {
		super(miJuego);
		System.out.println("PantallaMenuConstructor");
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
	public void dispose() {

	}

	@Override
	public void show() {

		imagenMenuT = new Texture(Gdx.files.internal("imagenes/PantallaMenu.jpg"));
		imagenMenuS = new Sprite(imagenMenuT);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		imagenMenuS.setSize(w, h);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w / 2, h / 2);

		sb = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		imagenMenuS.draw(sb);
		sb.end();
		System.out.println("PENEP ENEPENEPEENE");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
