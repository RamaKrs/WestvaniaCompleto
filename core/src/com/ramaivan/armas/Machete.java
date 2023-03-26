package com.ramaivan.armas;

import com.badlogic.gdx.physics.box2d.Body;
import com.ramaivan.utils.Utiles;

public class Machete extends Arma{

	public Machete(float x, float y) {
		super("Machete", 1, x, y, 200);
		
	}
	
	
	
	public void melee() {
		
	//	Body hitBoxAtaqueMelee = Utiles.createBox((getPosicion().x + 20) / Utiles.PPM, (getPosicion().y + 20) / Utiles.PPM, 40, 40, true);
		
	}
}
