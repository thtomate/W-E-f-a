package com.BombingGames.WurfelEngine;

import android.os.Bundle;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.Core.View;
import com.BombingGames.WurfelEngine.Core.BasicMainMenu.BasicMainMenu;
import com.BombingGames.WurfelEngine.Core.BasicMainMenu.BasicMenuItem;
import com.BombingGames.WurfelEngine.Core.BasicMainMenu.GameViewWithCamera;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidActivity extends AndroidApplication {
	AndroidApplicationConfiguration cfg;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        cfg = new AndroidApplicationConfiguration();
        
        cfg.useGL20 = false;
      
        WE.construct();

        initialize(WE.getInstance(), cfg);
    }
    protected void onResume () {//  Reoping the engine causes renderer to fail on sprites #3 https://github.com/thtomate/Wurfel-Engine-for-android/issues/3
    	super.onResume();
    	//initialize(WE.getInstance(), cfg);
    	
    }
}