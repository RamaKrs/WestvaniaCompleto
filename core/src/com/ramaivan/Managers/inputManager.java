package com.ramaivan.Managers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class inputManager extends InputAdapter{
	
	OrthographicCamera camera;
	static Vector3 temp = new Vector3();
	
	public inputManager(OrthographicCamera camera) {
		this.camera = camera;
		
	}
	
	
	
}
