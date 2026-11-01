package io.github.firstGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    final Drop game;

    Texture character;
    Texture background;
    Music backgroundMusic;


    SpriteBatch spriteBatch;
    FitViewport viewport;

    // Sprite variable, basically the thing to do movement
    Sprite player;


    public GameScreen(final Drop game) {
        this.game = game;

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
        player.setSize(.5f, .5f);

        // background music
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(.25f);
        backgroundMusic.play();
    }

    @Override
    public void show() {
        backgroundMusic.play();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        // render gets organized into three methods
        input();
        logic();
        draw();
    }

    private void input() {
        float speed = 10f;
        float delta = Gdx.graphics.getDeltaTime();



        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            player.translateX(speed * delta);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            player.translateX(-speed * delta);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.translateY(speed * delta);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            player.translateY(-speed * delta);
        }
    }

    private void logic() {
        // Store the worldWidth and worldHeight as local variables for brevity
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        // Clamp x to values between 0 and worldWidth (so the player can't go off the screen)
        player.setX(MathUtils.clamp(player.getX(), 0, worldWidth));
        player.setY(MathUtils.clamp(player.getY(), 0, worldHeight));

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        // where to draw the canvas for the game
        spriteBatch.begin();

        // storing the worldwidth and worldheight to use them as the background width and height (to cover all surface)
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        game.batch.draw(background, 0,0, worldWidth, worldHeight);
        player.draw(game.batch);


        game.batch.end();
    }

        @Override
        public void dispose() {
            backgroundMusic.dispose();
            background.dispose();
            character.dispose();
        }
    }


