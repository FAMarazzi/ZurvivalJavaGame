package com.zurvival.fmgr35;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Juego extends ApplicationAdapter 
{
	private int ancho = 0, alto = 0;
	
	SpriteBatch batch;
	Texture fondo, personaje, bala;
	BitmapFont fontA, fontB;
	Sprite miPersonaje, miBala;
	private enum gameState{MENU, PLAY, PUNTAJES};
	private gameState estado = gameState.MENU;
	private float x=0, y=0, balaX, balaY;
	private float r, rBala;
	private boolean disparado=false;
	private ArrayList<Bala> balas;
	private float dx, dy;
	
	@Override
	public void dispose()
	{
		super.dispose();
		fondo.dispose();
	}
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		fondo= new Texture("FONDO ZURVIVAL.png");
		fontA = new BitmapFont();
		fontB= new BitmapFont();
		fontA.setColor(Color.BLACK);
		ancho = Gdx.graphics.getWidth();
		alto = Gdx.graphics.getHeight(); 
		personaje=new Texture("Personaje1.png");
		bala=new Texture("bala.png");
		miPersonaje= new Sprite(personaje, 116, 8, 145, 111);
		miPersonaje.setPosition(x = ancho/2 - 100, y = alto/2 - 50);
		miPersonaje.setOrigin(102, 55);
		
	}

	@Override
	public void render () 
	{
		if (Gdx.input.getX() == 0 && Gdx.input.getY() == 0 && Gdx.input.isTouched())
			System.exit(0);
		
		batch.begin();
		
		switch(estado)
		{
		case MENU:
				cargarMenu();
			break;
		case PLAY:
				cargarJuego();
			break;
		case PUNTAJES:
			break;
		default:
			break;
		}
		
		batch.end();
	}

	private void cargarJuego() 
	{
		try
		{
			boolean click = Gdx.input.justTouched();
			batch.draw(fondo, 0, 0, ancho, alto);
			miPersonaje.draw(batch);
			
			calcularMovimientos();
			
			if (click)
				disparar();
			
			
			if (disparado)
			{
			miBala.setPosition(balaX, balaY);
			miBala.setRotation(rBala);
//			balaX=(float) Math.sqrt((Math.pow(balaX, 2))*(Math.pow(balaY, 2)))*450;
			dx=MathUtils.cos(rBala)*450;
			dy=MathUtils.sin(rBala)*450;
			balaX+=dx*Gdx.graphics.getDeltaTime();
			balaY+=dy*Gdx.graphics.getDeltaTime();
			miBala.draw(batch);
			}
		}
		catch(Exception e)
		{
			System.out.println(e + " cargarJuego()");
		}
	}

	private void disparar() 
	{
		miBala= new Sprite(bala, 7, 3, 58, 25);
		balaX=x-50;
		balaY=y+45;	
		miBala.setOrigin(miPersonaje.getOriginX()+50, miPersonaje.getOriginY()-45);
		rBala=r;
		disparado=true;
	}


	private void calcularMovimientos() 
	{
		r= (float) ((Math.atan2 (Gdx.input.getX() - (miPersonaje.getX()+102), -((alto - Gdx.input.getY()) - (miPersonaje.getY()+55)))*180.0d/Math.PI)+90.0f);
		miPersonaje.setRotation(r);
		boolean arriba = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
		boolean abajo = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
		boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.LEFT)|| Gdx.input.isKeyPressed(Input.Keys.A);
		boolean derecha = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
		if(arriba && izquierda)
		{
			y+=3;
			x-=3;
		}
		else if(arriba && derecha)
		{
			y+=3;
			x+=3;
		}
		else if(abajo && derecha)
		{
			y-=3;
			x+=3;
		}
		else if(abajo && izquierda)
		{
			y-=3;
			x-=3;
		}
		else if(arriba)
		{
			y+=3;
		}
		else if(abajo)
		{
			y-=3;
		}
		else if(izquierda)
		{
			x-=3;
		}
		else if (derecha)
		{
			x+=3;
		}
		miPersonaje.setPosition(x, y);
	}


	private void cargarMenu() 
	{
		try
		{
			boolean arriba = Gdx.input.isKeyJustPressed(Input.Keys.UP);
			boolean abajo = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
			boolean enter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
			
			batch.draw(fondo, 0, 0, ancho, alto);
			fontA.draw(batch, "JUGAR", ancho/2, alto/3);
			fontB.draw(batch, "SALIR", ancho/2, alto/3 - 20);
			
			if (arriba)
			{
				fontA.setColor(Color.BLACK);
				fontB.setColor(Color.WHITE);
			}else if (abajo)
			{
				fontB.setColor(Color.BLACK);
				fontA.setColor(Color.WHITE);
			}
	
			if (enter)
			{
				if (fontA.getColor().equals(Color.BLACK))
				{	
					estado = gameState.PLAY;
					fondo = new Texture("MAPA.png");
				}
				else
					System.exit(0);
			}
		}
		catch(Exception e)
		{
			System.out.println(e + " cargarMenu()");
		}
	}
}















