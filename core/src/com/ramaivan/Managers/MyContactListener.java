package com.ramaivan.Managers;

import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ramaivan.armas.Bala;
import com.ramaivan.personajes.Jugador;

public class MyContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA == null || fixtureB == null)
			return;
		if (fixtureA.getUserData() == null || fixtureB.getUserData() == null)
			return;
		isColisionBala(fixtureA, fixtureB);

		isColisionJugadorConPared(fixtureA, fixtureB);
//		((Bala) fixtureB.getUserData()).destruido = true;

//		System.out.println("colision");
	}

	public void isColisionBala(Fixture a, Fixture b) {
		System.out.println("a: " + a.getUserData().getClass().getName());
		System.out.println("b: " + b.getUserData().getClass().getName());
		if (a.getUserData() instanceof Bala || b.getUserData() instanceof Bala) {
			
			if (a.getUserData() instanceof Body || b.getUserData() instanceof Body || b.getUserData() instanceof Jugador || a.getUserData() instanceof Jugador ) {
//				System.out.println("entro al if if2");
				
				try {
					((Bala) b.getUserData()).destruido = true;
					
				} catch (Exception e) {
					((Bala) a.getUserData()).destruido = true;
				}
			}
		}

	}

	public void isColisionJugadorConPared(Fixture a, Fixture b) {

		if (a.getUserData() instanceof Jugador || b.getUserData() instanceof Jugador) {
		
			try {
				((Jugador) a.getUserData()).updateSalto();
			} catch(Exception e) {
				((Jugador) b.getUserData()).updateSalto();
			}

		}
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
