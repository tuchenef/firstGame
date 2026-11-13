package io.github.firstGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Drop extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;
    public static ShapeRenderer square;


    @Override
    public void create() {
        batch = new SpriteBatch();
        square = new ShapeRenderer();

        // Default font, definitely look to put another one
        font = new BitmapFont();
        viewport = new FitViewport(8 ,8);

        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        this.setScreen(new MainMenuScreen(this)); // look what it does
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
