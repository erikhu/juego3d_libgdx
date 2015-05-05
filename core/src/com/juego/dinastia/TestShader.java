package com.juego.dinastia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TestShader implements Shader{

	ShaderProgram shader;
	Camera camera;
    RenderContext context;
	@Override
	public void dispose() {
		shader.dispose();	
	}

	@Override
	public void init() {
		String frg = Gdx.files.internal("sombras/test.fragment.glsl").readString();
		String vertex = Gdx.files.internal("sombras/test.vertex.glsl").readString();
		 shader = new ShaderProgram(vertex, frg);
	        if (!shader.isCompiled())
	            throw new GdxRuntimeException(shader.getLog());
		
	}

	@Override
	public int compareTo(Shader other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canRender(Renderable instance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void begin(Camera camera, RenderContext context) {
		 this.camera = camera;
	      this.context = context;
		shader.begin();
		shader.setUniformMatrix("u_projViewTrans", camera.combined);
		
		
	}

	@Override
	public void render(Renderable renderable) {
		shader.setUniformMatrix("u_worldTrans", renderable.worldTransform);
		 renderable.mesh.render(shader,
		            renderable.primitiveType,
		            renderable.meshPartOffset,
		            renderable.meshPartSize);
	}

	@Override
	public void end() {
		shader.end();
	}

}
