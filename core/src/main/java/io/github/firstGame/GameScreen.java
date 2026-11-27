package io.github.firstGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import javax.swing.*;

public class GameScreen implements Screen {
    final Drop game;

    ShapeRenderer shapeRenderer = new ShapeRenderer();   // the squares on the background

    Texture character;
    Texture background;
    Texture wall;
    Music backgroundMusic;
    Music footsteps;
    Texture door;


    SpriteBatch spriteBatch;
    FitViewport viewport;

    // Sprite variable, basically the thing to do movement
    Sprite player;


    public GameScreen(final Drop game) {
        this.game = game;

        // Texture: character and background
        character = new Texture("perso1.png");
        background = new Texture("background.jpg");
        wall = new Texture("wall.jpg");
        door = new Texture("porte.png");

        // Sound effects
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        footsteps = Gdx.audio.newMusic(Gdx.files.internal("footsteps.mp3"));
        // implementing rendering
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,8);

        // implementing sprite
        player = new Sprite(character); // Basically associating the sprite to the character
        player.setSize(1f, 1f);

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
        backgroundMusic.stop();
    }

    @Override
    public void render(float delta) {
        // render gets organized into three methods
        input();
        logic();
        draw();

    }

    private void input() {
        float step = 1f;

        // Définir l'origine de rotation au centre du sprite
        player.setOrigin(player.getWidth() / 2f, player.getHeight() / 2f);

        if (player.getX() == 7 && player.getY() == 0) {
            game.setScreen(new SecondLevel(game));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if ((player.getX() == 1 && (player.getY() == 0 || player.getY() == 1)) ||
                (player.getX() == 3 && (player.getY() == 2 || player.getY() == 3)) ||
                (player.getX() == 4 && (player.getY() == 4 || player.getY() == 5)) ||
                (player.getX() == 5 && (player.getY() == 0 || player.getY() == 1 || player.getY() == 2 || player.getY() == 3)) ||
                (player.getX() == 6 && (player.getY() == 6 || player.getY() == 7)) ||
                (player.getX() == 2 && (player.getY() == 6 || player.getY() == 7))) {
                        player.setRotation(90f);
                    } else {
                        player.translateX(step);
                        player.setRotation(90f);
                    }
//            footsteps.setLooping(false);
//            footsteps.setVolume(1f);
//            footsteps.play();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if ((player.getX() == 2 && player.getY() == 4) ||
                (player.getX() == 3 && (player.getY() == 0 || player.getY() == 1)) ||
                (player.getX() == 5 && (player.getY() == 2 || player.getY() == 3)) ||
                (player.getX() == 7 && (player.getY() == 0 || player.getY() == 1 || player.getY() == 2 || player.getY() == 3)) ||
                (player.getX() == 6 && (player.getY() == 4 || player.getY() == 5)) ||
                (player.getX() == 4 && (player.getY() == 6 || player.getY() == 7))) {
                        player.setRotation(270f);
            } else {
                player.translateX(-step);
                player.setRotation(270f);
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (((player.getX() == 0 || player.getX() == 1) && player.getY() == 3) ||
                (player.getX() == 4 && player.getY() == 1) ||
                (player.getX() == 5 && player.getY() == 3) ||
                (player.getX() == 7 && player.getY() == 5) ||
                (player.getX() == 3 && player.getY() == 5)) {
                        player.setRotation(180f);
            } else {
                player.translateY(step);
                player.setRotation(180f);
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (((player.getX() == 0 || player.getX() == 1) && player.getY() == 5) ||
                (player.getX() == 2 && player.getY() == 2) ||
                (player.getX() == 4 && player.getY() == 4) ||
                (player.getX() == 5 && player.getY() == 6) ||
                (player.getX() == 6 && player.getY() == 4)) {
                        player.setRotation(0f);
            } else {
                player.translateY(-step);
                player.setRotation(0f);

            }
        }
    }

    private void logic() {
        // Store the worldWidth and worldHeight as local variables for brevity
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // Clamp x to values between 0 and worldWidth (so the player can't go off the screen)
        player.setX(MathUtils.clamp(player.getX(), 0, worldWidth - player.getWidth()));
        player.setY(MathUtils.clamp(player.getY(), 0, worldHeight - player.getWidth()));
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // Dessin du background
        game.batch.begin();
        game.batch.draw(background, 0,0, worldWidth, worldHeight);
        game.batch.end();

        // Dessiner la grille
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);

        float tileSize = 1f;

        for (float x = 0; x <= worldWidth; x += tileSize) {
            shapeRenderer.line(x, 0, x, worldHeight);
        }
        for (float y = 0; y <= worldHeight; y += tileSize) {
            shapeRenderer.line(0, y, worldWidth, y);
        }

        shapeRenderer.end();

        // dessin des murs
        game.batch.begin();
        game.batch.draw(wall, 2, 0, 1f, 2f);
        game.batch.draw(wall, 0, 4, 2f, 1f);
        game.batch.draw(wall, 4, 2, 1f, 2f);
        game.batch.draw(wall, 6, 0, 1f, 4f);
        game.batch.draw(wall, 7, 6, 1f, 2f);
        game.batch.draw(wall, 3, 6, 1f, 2f);
        game.batch.draw(wall, 5, 4, 1f, 2f);
        game.batch.end();

        // dessin du personnage
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        // dessin de la porte
        game.batch.begin();
        game.batch.draw(door, 7, 0, 1f, 1f);
        game.batch.end();
    }

        @Override
        public void dispose() {
            backgroundMusic.dispose();
            background.dispose();
            character.dispose();
            spriteBatch.dispose();
            shapeRenderer.dispose();
            wall.dispose();
            door.dispose();
        }
    }


