package mx.itexm.naughty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.security.AlgorithmParameterGenerator;

class PantallaAcerca extends Pantalla {
    private final PantallaInicio pantallaInicio;
    private Stage escenaAcerca;
    private Texture textFondo;
    private Texto info;

    public PantallaAcerca(PantallaInicio pantallaInicio) {
        this.pantallaInicio=pantallaInicio;
    }
    private void crearEscena(){
        escenaAcerca=new Stage(vista);
        TextureRegionDrawable trdRegresar_up=new TextureRegionDrawable(new TextureRegion(new Texture("Botones/regresar.png")));
        TextureRegionDrawable trdRegresar_down=new TextureRegionDrawable(new TextureRegion(new Texture("Botones/regresar_s.png")));
        ImageButton btnReg=new ImageButton(trdRegresar_up,trdRegresar_down);
        btnReg.setPosition(50,50);
        btnReg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));
            }
        });
        escenaAcerca.addActor(btnReg);

    }
    @Override
    public void show() {
        crearEscena();
        textFondo=new Texture("acerca_fondo.jpg");
        info=new Texto();
        Gdx.input.setInputProcessor(escenaAcerca);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(textFondo,0,0);
        info.mostrarMensaje(batch, "--CREADO POR--"+"\n"+"Eric Gomez (ISC)"+"\n"+"Erick Bautista (ISC)"+"\n"+"Jessica I. Alvarez (LAD)"+"\n"+"Sebastian Gomez (LAD)" ,ANCHO*0.7f, ALTO*0.90f);
        batch.end();
        escenaAcerca.draw();
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
