/*
 * Copyright 2013 Benedikt Vogler.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * * Neither the name of Bombing Games nor Benedikt Vogler nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.BombingGames.WurfelEngine.Core.BasicMainMenu;

import com.badlogic.gdx.Gdx;

/**
 * The controller of the main Menu manages the data.
 * @author Benedikt
 */
public class MenuController {
    private final BasicMenuItem[] menuItems;
    
    /**
     * Creates a new Controller
     * @param menuItems
     */
    public MenuController(BasicMenuItem[] menuItems) {
        this.menuItems = menuItems;
        BasicMenuItem.setSound(Gdx.audio.newSound(Gdx.files.internal("com/BombingGames/WurfelEngine/Core/BasicMainMenu/click2.wav")));
    }
    
    /**
     * updates screen logic
     * @param delta
     */
    public void update(int delta){
        for (BasicMenuItem basicMenuItem : menuItems) {
            if (basicMenuItem.isClicked()) basicMenuItem.action();
        }
    }

    /**
     *
     */
    public void show(){

    }

    /**
     *
     * @return
     */
    public BasicMenuItem[] getMenuItems() {
        return menuItems;
    }
    
    }