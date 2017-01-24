package br.com.fabiano.flappynerd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.com.fabiano.flappynerd.FlappyNerdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie Bird";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new FlappyNerdGame(), config);
	}
}
