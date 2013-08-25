package de.zimmerpforte.drop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Drop implements ApplicationListener {
		Texture dropImage;
	   Texture bucketImage;
	   Sound dropSound;
	   Music rainMusic;
	   
	   OrthographicCamera camera;
	   SpriteBatch batch;
	   
	   Rectangle bucket;
	   
	   @Override
	   public void create() {
	      // load the images for the droplet and the bucket, 64x64 pixels each
	      dropImage = new Texture(Gdx.files.internal("droplet.png"));
	      bucketImage = new Texture(Gdx.files.internal("bucket.png"));
	      
	      // load the drop sound effect and the rain background "music"
	      dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	      rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	      
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();

	      //... more to come ...
	      //define camera movement
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      
	      //define batch
	      batch = new SpriteBatch();
	      
	      //define bucket
	      bucket = new Rectangle();
	      bucket.x = 800 / 2 - 64 / 2;
	      bucket.y = 20;
	      bucket.width = 64;
	      bucket.height = 64;
	   }


	@Override
	public void dispose() {
		/*batch.dispose();
		texture.dispose();*/
	}

	@Override
	   public void render() {
	      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	      //... more to come here ...
	      //update camera
	      camera.update();
	      //render bucket
	      batch.setProjectionMatrix(camera.combined);
	      batch.begin();
	      batch.draw(bucketImage, bucket.x, bucket.y);
	      batch.end();
	      
	      //button pressed
	      if(Gdx.input.isTouched()) {
	          Vector3 touchPos = new Vector3();
	          touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	          camera.unproject(touchPos);
	          bucket.x = touchPos.x - 64 / 2;
	       }
	   }
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
