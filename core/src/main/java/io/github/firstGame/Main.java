package io.github.firstGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture character;
    Texture background;
    Music backgroundMusic;


    SpriteBatch spriteBatch;
    FitViewport viewport;

    // Sprite variable, basically the thing to do movement
    Sprite player;

    @Override
    public void create() {

        // Texture: character and background
        character = new Texture("perso1.png");
        background = new Texture("background.jpg");

        // Sound effects
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));

        // implementing rendering
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,5);

        // implementing sprite
        player = new Sprite(character); // Basically associating the sprite to the character
        player.setSize(1, 1);

        // background music
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(.25f);
        backgroundMusic.play();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

        // render gets organized into three methods
        input();
        logic();
        draw();
    }

    private void input() {
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.flip(true, false);
            player.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setRotation(270f);
            player.translateX(-speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setRotation(180f);
            player.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setRotation(0f);
            player.translateY(-speed * delta);
        }
    }

    private void logic() {
        // Store the worldWidth and worldHeight as local variables for brevity
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // Clamp x to values between 0 and worldWidth (so the player can't go off the screen)
        player.setX(MathUtils.clamp(player.getX(), 0, worldWidth));
        player.setY(MathUtils.clamp(player.getY(), 0, worldHeight));

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        // where to draw the canvas for the game
        spriteBatch.begin();

        // storing the worldwith and worldheight to use them as the background width and height (to cover all surface)
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        spriteBatch.draw(background, 0,0, worldWidth, worldHeight);
        player.draw(spriteBatch);


        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
