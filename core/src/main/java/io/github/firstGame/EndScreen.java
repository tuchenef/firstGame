package io.github.firstGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {

    final Drop game;
    Texture endScreen;
    Music outroSong;

    public EndScreen(Drop game) {
        this.game = game;
        endScreen = new Texture("endScreen.jpg");

        outroSong = Gdx.audio.newMusic(Gdx.files.internal("outroSong.mp3"));
        outroSong.setVolume(0.25f);
    }

    @Override
    public void show() {
        outroSong.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(endScreen, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.batch.end();

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
        outroSong.stop();
    }

    @Override
    public void dispose() {
        endScreen.dispose();
        outroSong.dispose();
    }
}
