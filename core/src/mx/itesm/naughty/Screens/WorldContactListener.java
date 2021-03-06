package mx.itesm.naughty.Screens;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import mx.itesm.naughty.MainGame;
import mx.itesm.naughty.Sprites.Enemies.Enemy;
import mx.itesm.naughty.Sprites.TileObjects.InteractiveTileObject;
import mx.itesm.naughty.Sprites.Items.Item;
import mx.itesm.naughty.Sprites.Player;

class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cDef){
            case MainGame.PLAYER_BIT | MainGame.COFRE_BIT:
                if(fixA.getFilterData().categoryBits == MainGame.PLAYER_BIT){
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit();
                } else {
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit();
                }
                break;

            case MainGame.ENEMY_BIT | MainGame.BALA_BIT:
            case MainGame.ENEMY_BIT | MainGame.PLAYER_HEAD_BIT:
                if(fixA.getFilterData().categoryBits == MainGame.ENEMY_BIT){
                    ((Enemy)fixA.getUserData()).hitOnHead();
                }
                else {
                    ((Enemy)fixB.getUserData()).hitOnHead();
                }
                break;
            case MainGame.PLAYER_BIT | MainGame.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == MainGame.PLAYER_BIT){
                    ((Player)fixA.getUserData()).hit();
                }
                else {
                    ((Player)fixB.getUserData()).hit();
                }
                break;
            case MainGame.PLAYER_BIT | MainGame.DOOR_BIT:
                if(fixB.getFilterData().categoryBits == MainGame.PLAYER_BIT){
                    ((Player)fixB.getUserData()).win();
                }
                else {
                    ((Player)fixA.getUserData()).win();
                }
                break;
            case MainGame.ENEMY_BIT | MainGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == MainGame.ENEMY_BIT){
                    ((Enemy)fixA.getUserData()).revereVelocity(true, false);
                }
                else {
                    ((Enemy)fixB.getUserData()).revereVelocity(true, false);
                }
                break;
            case MainGame.ENEMY_BIT | MainGame.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).revereVelocity(true, false);
                ((Enemy)fixB.getUserData()).revereVelocity(true, false);
                break;

            case MainGame.ITEM_BIT | MainGame.PLAYER_BIT:
                if(fixB.getFilterData().categoryBits == MainGame.ITEM_BIT){
                    ((Item)fixB.getUserData()).use((Player) fixA.getUserData());
                }
                else {
                    ((Item)fixA.getUserData()).use((Player) fixB.getUserData());
                }
                break;

        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
