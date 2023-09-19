package com.zurvival.fmgr35.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zurvival.fmgr35.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	    config.width = screenDimension.width;
	    config.height = screenDimension.height;
	    config.fullscreen = true;
	    new LwjglApplication(new Juego(), config);

	}
}
