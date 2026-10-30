package io.github.firstGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture character;
    Texture background;
    Music backgroundMusic;


    SpriteBatch spriteBatch;
    FitViewport viewport;

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

    }

    private void logic() {

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
        spriteBatch.draw(character,0, 0, 1,1); //le perso commence au coin à gauche et chaque case fait la taille du perso


        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
