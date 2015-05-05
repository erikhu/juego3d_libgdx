package com.juego.dinastia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;

public class Ejercicio2 extends Game{

	CameraInputController control;
	
	PerspectiveCamera camara;
	
	Environment ambiente;
	
	Array<ModelInstance> instancias;
	
	ModelBuilder constructor;
	
	Shader shader;
	
	ModelBatch batch;
	
	Model modelo;
	
	@Override
	public void create() {
	
		batch = new ModelBatch();
		
		instancias = new Array<ModelInstance>();
		
		camara = new PerspectiveCamera(67 , Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		camara.position.set(0,8,18);
		camara.lookAt(0, 0, 0);
		camara.near = 1;
		camara.far = 300;
		camara.update();
		
		control = new CameraInputController(camara);
		Gdx.input.setInputProcessor(control);
		
		ambiente = new Environment();
		ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight ,  0.4f, 0.4f, 0.4f, 1f));
		ambiente.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -.8f, -.7f));
		
		constructor = new ModelBuilder();
		
		modelo = constructor.createSphere(2f, 2f, 2f, 20, 20,
		         new Material(),
		         Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		
		for(int x = -5 ; x <= 5 ; x+=2){
			for(int z = -5 ; z<=5 ; z+=2){
				instancias.add(new ModelInstance(modelo , x , 0 , z));
			}
		}
		
		shader = new TestShader();
		shader.init();
	}
	
	@Override
	public void render(){
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		control.update();
		batch.begin(camara);
		for(ModelInstance instancia: instancias)
		batch.render(instancia, shader);
	
		batch.end();
	}
	
	@Override
	public void dispose(){
		shader.dispose();
	       modelo.dispose();
	       batch.dispose();
	}

}
