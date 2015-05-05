package com.juego.dinastia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ejercicio1 extends Game implements InputProcessor {

	OrthographicCamera camara;
	SpriteBatch batch;
	Texture textura;
	TextureRegion[][] region ;
	Animation[] animacion;
	float x = 0, y = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camara = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camara.position.set(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0);
		
		animacion = new Animation[4];
		
		textura = new Texture(Gdx.files.internal("badlogic.jpg"));
		region = TextureRegion.split(new Texture(Gdx.files.internal("animacion.png")), 32, 48);
		//Abajo
		animacion[0] = new Animation(.125f, obtener(region,0));	
		//izq
		animacion[1] = new Animation(.125f,obtener(region,1));
		//derecha
		animacion[2]  = new Animation(.125f,obtener(region,2));
		//arriba
		animacion[3] = new Animation(.125f , obtener(region,3));
		
		Gdx.input.setInputProcessor(this);
	}
	
	public TextureRegion[] obtener(TextureRegion[][] imgs , int fila){
		TextureRegion[] para  = new TextureRegion[imgs[0].length];
		for(int i = 0 ; i < imgs[0].length ; i++){
			para[i] = imgs[fila][i];
		}
		
		return para;
	}
	
	float tiempo = 0 ;

	@Override
	public void render() {

		float delta = Gdx.graphics.getDeltaTime();
		System.out.println(delta);
		control();
		tiempo += delta;
		
		Gdx.gl20.glClearColor(1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camara.update();

		
		
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		
		
		batch.draw(imagen(tiempo,true), x, y);
		batch.end();

		
	}
	
	public TextureRegion imagen(float tiempo , boolean loop){
		if (ar) {
			return animacion[3].getKeyFrame(tiempo, loop);
		}
		if (ab) {
			return animacion[0].getKeyFrame(tiempo, loop);
		}
		if (de) {
			return animacion[2].getKeyFrame(tiempo, loop);
		}
		if (iz) {
			return animacion[1].getKeyFrame(tiempo, loop);
		}
		return animacion[0].getKeyFrame(0);
	}
	
	public void control(){
		if (ar) {
			y += 1;
		}
		if (ab) {
			y -= 1;
		}
		if (de) {
			x += 1;
		}
		if (iz) {
			x -= 1;
		}
	}

	boolean ar, ab, iz, de;

	@Override
	public boolean keyDown(int keycode) {

		if (Keys.UP == keycode) {
			ar = true;
		}
		if (Keys.DOWN == keycode) {
			ab = true;
		}
		if (Keys.RIGHT == keycode) {
			de = true;
		}
		if (Keys.LEFT == keycode) {
			iz = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		if (Keys.UP == keycode) {
			ar = false;
		}
		if (Keys.DOWN == keycode) {
			ab = false;
		}
		if (Keys.RIGHT == keycode) {
			de = false;
		}
		if (Keys.LEFT == keycode) {
			iz = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
