package com.juego.dinastia.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.juego.dinastia.Dinastia;
import com.juego.dinastia.Ejercicio1;
import com.juego.dinastia.Ejercicio2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Ejercicio1(), config);
	}
}
