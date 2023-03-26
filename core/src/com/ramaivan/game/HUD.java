package com.ramaivan.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD  {
	public Stage stage;
	private Viewport viewport;
	
	private Integer balas, usosPoder;
	private String armaActual, poderActual;

	
	Label contadorBalasLabel;
	Label armaActualLabel;
	Label poderActualLabel;
	Label usosPoderLabel;
	
	public HUD(SpriteBatch sb, int w, int h, OrthographicCamera camera){
		
		balas = 0;
		
		armaActual = "N/A";
		viewport = new FitViewport(w, h, camera);
		stage = new Stage(viewport, sb);
		
		
		
		Table table = new Table();
		table.debugAll();
		table.top();	
		table.setFillParent(true);
		
		contadorBalasLabel = new Label(String.format("%02d", balas), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		armaActualLabel = new Label(armaActual, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		usosPoderLabel = new Label(String.format("%02d", usosPoder), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		poderActualLabel = new Label(poderActual, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(contadorBalasLabel).expandX().padTop(10);
		table.add(armaActualLabel).expandX().padTop(10);
		table.add(usosPoderLabel).expandX().padTop(10);
		table.add(poderActualLabel).expandX().padTop(10);
		stage.addActor(table);
	}
	
	public void updateHud(int balas, String armaActual, int usosPoder, String poderActual) {
		contadorBalasLabel.setText(balas);
		armaActualLabel.setText(armaActual);
		usosPoderLabel.setText(usosPoder);
		poderActualLabel.setText(poderActual);
	}

	
}
