package com.juego.dinastia;


import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Dinastia extends ApplicationAdapter {
	
	CameraInputController controlador;
	PerspectiveCamera camara;
	ModelBatch batch ;
	ModelInstance carro;
	ModelInstance plano;
	Environment ambiente;
	AssetManager recursos;
	Array<ModelInstance> instancias;
	boolean cargado;
	
	@Override
	public void create () {
		
		batch = new ModelBatch();
		
		ambiente = new Environment();
		ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f), new ColorAttribute(ColorAttribute.Ambient,.3f,.1f,.5f,1f));
		ambiente.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -.8f, -.7f));
		
		camara = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camara.position.set(0f, 7f, 10f);
		camara.lookAt(0, 0, 0);
		camara.near = 1;
		camara.far = 300;
		camara.update();
		
		recursos = new AssetManager();
		recursos.load("objeto/sskl.g3db",Model.class);
		
		instancias = new Array<ModelInstance>();
		controlador = new CameraInputController(camara);
		Gdx.input.setInputProcessor(controlador);
		cargado = true;
		
		
		
	}
	
	public void cargarRecursos(){
		
		Model modelo  = recursos.get("objeto/sskl.g3db",Model.class);
		
		
		
		for(int i = 0 ; i< modelo.nodes.size ; i++){
		
			String id = modelo.nodes.get(i).id;
			 ModelInstance instancia = new ModelInstance(modelo,id);
			 instancias.add(instancia);
			 
			 if(id.equals("SsklBody")){
				 carro = instancia; 
					carro.transform.setToRotation(Vector3.Y, 90);
					
			 }else if(id.equals("Plane")){
				 plano = instancia;
				 Material material = instancia.materials.first();
				 material.set(ColorAttribute.createDiffuse(Color.GRAY));
			 }
		
		}
		
		
		
		
		cargado = false;
	}

	@Override
	public void render () {
	
		if(cargado && recursos.update()){
			cargarRecursos();
		}
		
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin(camara);
			batch.render(instancias,ambiente);
		batch.end();
	}
}
