package mx.itesm.naughty.Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import mx.itesm.naughty.MainGame;

public class LoadingScreen extends MainScreen{
    private MainGame game;
    private float tiempo;
    private Texture texturaReloj;
    public LoadingScreen(MainGame game){
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        tiempo = 0;
        texturaReloj = new Texture("loadingTxt.png");
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(MainScreen.gameCam.combined);
        game.batch.begin();
        game.batch.draw(texturaReloj, MainGame.ANCHO_PANTALLA / 2 - texturaReloj.getWidth() / 2,
                MainGame.ALTO_PANTALLA / 2 - texturaReloj.getHeight() / 2);
        game.batch.end();
        // Actualiza
        tiempo += Gdx.graphics.getDeltaTime();  // Acumula tiempo
        if (game.getManager().update()) {
            if (tiempo >= 1f) {
                game.finishLoading();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

