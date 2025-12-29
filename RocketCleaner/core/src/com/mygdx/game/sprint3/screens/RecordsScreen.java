package com.mygdx.game.sprint3.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.sprint3.GameResources;
import com.mygdx.game.sprint3.MyGdxGame;
import com.mygdx.game.sprint3.components.ButtonView;
import com.mygdx.game.sprint3.components.ImageView;
import com.mygdx.game.sprint3.components.MovingBackgroundView;
import com.mygdx.game.sprint3.components.RecordsListView;
import com.mygdx.game.sprint3.components.TextView;
import com.mygdx.game.sprint3.managers.MemoryManager;

public class RecordsScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;
    MovingBackgroundView backgroundView;
    TextView titleView;

    ButtonView exitButtonView;
    // ENDED state UI
    TextView recordsTextView;
    RecordsListView recordsListView;
    ButtonView homeButton2;
    ButtonView returnButton;

    ImageView fullBlackoutView;

    public RecordsScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        titleView = new TextView(myGdxGame.largeWhiteFont, 180, 960, "Field Cleaner");
        exitButtonView = new ButtonView(140, 456, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "exit");
        recordsListView = new RecordsListView(myGdxGame.commonWhiteFont, 690);
        fullBlackoutView = new ImageView(0, 0, GameResources.BLACKOUT_FULL_IMG_PATH);

        recordsTextView = new TextView(myGdxGame.largeWhiteFont, 206, 842, "Last records");


        returnButton = new ButtonView(
                280, 447,
                160, 70,
                myGdxGame.commonBlackFont,
                GameResources.BUTTON_SHORT_BG_IMG_PATH,
                "return"
        );
    }

    @Override
    public void render(float delta) {
        recordsListView.setRecords(MemoryManager.loadRecordsTable());

        handleInput();


        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        fullBlackoutView.draw(myGdxGame.batch);
        recordsTextView.draw(myGdxGame.batch);
        recordsListView.draw(myGdxGame.batch);
        returnButton.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    void handleInput() {

        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        }
    }

}
