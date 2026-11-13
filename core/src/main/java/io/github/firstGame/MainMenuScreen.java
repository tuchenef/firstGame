package io.github.firstGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {

    final Drop game;

    Texture mainScreen;
    Music introSong;

    public MainMenuScreen(Drop game) {
        mainScreen = new Texture("mainScreen.jpg");
        this.game = game;

        introSong = Gdx.audio.newMusic(Gdx.files.internal("tiki-tiki-phonk.mp3"));
        introSong.setLooping(true);
        introSong.setVolume(0.5f);
    }

    @Override
    public void show() {
        introSong.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);


        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(mainScreen, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        introSong.stop();
    }

    @Override
    public void dispose() {
        mainScreen.dispose();
        introSong.dispose();
    }
}
