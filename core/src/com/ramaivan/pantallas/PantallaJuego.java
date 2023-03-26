package com.ramaivan.pantallas;

import static com.ramaivan.utils.Utiles.PPM;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ramaivan.Managers.MyContactListener;
import com.ramaivan.game.HUD;
import com.ramaivan.personajes.Jugador;
import com.ramaivan.utils.Constants;
import com.ramaivan.utils.Utiles;

public class PantallaJuego extends Pantalla implements Screen {

	private OrthographicCamera camera;

	private Box2DDebugRenderer b2dr;
	private World world;
	private Body platform, wall;

	private SpriteBatch batch;

	private Jugador personaje, enemigo;
	private float acumDeltaTime = 0;

	private HUD hud;
	float w;
	float h;

	public PantallaJuego(MiJuego miJuego) {
		super(miJuego);
	}

	public void resize(int width, int height) {
		camera.setToOrtho(false, width / 2, height / 2);
	}

	@Override
	public void dispose() {
		b2dr.dispose();
		world.dispose();
		batch.dispose();

	}

	public void update(float deltaTime) {
		Utiles.globalWorld.step(1 / 60f, 6, 2);

		acumDeltaTime = acumDeltaTime + deltaTime;
		// inputUpdate(deltaTime);
		personaje.input();
		cameraUpdate(deltaTime);
		if (personaje.getArmaActual() != null) {
			personaje.getArmaActual().update(personaje.getpBody().getPosition().x,
					personaje.getpBody().getPosition().y);
		}
		for (int i = 0; i < personaje.getArmaActual().balasEnMapa.size; i++) {
//		aa	System.out.println(i);
//			System.out.println(personaje.getArmaActual().balasEnMapa.get(i).destruido);
			personaje.getArmaActual().balasEnMapa.get(i).updateBalas(deltaTime, personaje.getArmaActual().balasEnMapa,
					acumDeltaTime);
		}
//		batch.setProjectionMatrix(camera.combined);
//		batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.updateHud(personaje.getArmaActual().getBalas(), personaje.getArmaActual().getNombre(),
				personaje.getPoderActual().usosDisponibles(), personaje.getPoderActual().getNombre());
//		hud.updateViewport((int) w, (int) h);
	}

	private void cameraUpdate(float deltaTime) {
		Vector3 position = camera.position;

		position.x = w / 2;
		position.y = h / 2;

		camera.position.set(position);

		camera.update();

	}

	@Override
	public void show() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w / 2, h / 2);

		world = new World(new Vector2(0, -9.8f), false);
		Utiles.globalWorld = world;
		Utiles.globalWorld.setContactListener(new MyContactListener());
		b2dr = new Box2DDebugRenderer();

		platform = Utiles.createBox(0, -100, 400, 32, true, (short) Constants.BIT_WALL, (short) Constants.BIT_PLAYER,
				(short) 0);
		wall = Utiles.createBox(100, 0, 32, 400, true, (short) Constants.BIT_WALL,
				(short) (Constants.BIT_PLAYER | Constants.BIT_BALA), (short) 0);
		personaje = new Jugador(-8, 10);
		enemigo = new Jugador(8, 10);
		batch = new SpriteBatch();
		hud = new HUD(batch, (int) w, (int) h, camera);

	}

	@Override
	public void render(float delta) {
		update(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(0, 0, 0, 1);

		b2dr.render(Utiles.globalWorld, camera.combined.scl(PPM));

		batch.begin();

		personaje.renderAnimacionActual(batch);

		batch.end();
		hud.stage.draw();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
