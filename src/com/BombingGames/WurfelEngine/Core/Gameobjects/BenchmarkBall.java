/*
 * If this software is used for a game the official „Wurfel Engine“ logo or its name must be visible in an intro screen or main menu.
 *
 * Copyright 2014 Benedikt Vogler.
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
 * * Neither the name of Benedikt Vogler nor the names of its contributors 
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

package com.BombingGames.WurfelEngine.Core.Gameobjects;

import com.BombingGames.WurfelEngine.Core.Map.Map;
import com.BombingGames.WurfelEngine.Core.Map.Point;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Benedikt Vogler
 */
public class BenchmarkBall extends AbstractEntity {
    private Vector3 movement;
    private final EntityShadow shadow;
    private static float timer=0;
    private static float timeTillBall=1000;

    /**
     *Creates a Benchmark ball.
     * @param point
     */
    public BenchmarkBall(Point point) {
        super(21, point);
        movement = new Vector3((float) Math.random()-0.5f, (float) Math.random()-0.5f, -1);
        movement.nor();
        shadow = (EntityShadow) new EntityShadow(point.cpy()).exist();
    }
       
    
    @Override
    public void update(float delta) {
        timer+=delta;
        if (timer >timeTillBall){
            if (Gdx.graphics.getDeltaTime()<0.013f)//over 60 FPS
                new BenchmarkBall(Map.getCenter(Map.getGameHeight()-2*GAME_EDGELENGTH
                )).exist();
            timer=0;
        }
        timeTillBall-=delta/5000000f;
     
        
        getPos().addVector(movement.cpy().scl(delta/4f));
        movement.z -= WE.getCurrentConfig().getGravity()*delta/1000f;
        
        if (onGround()){ //hit floor
            movement = new Vector3((float) Math.random()-0.5f, (float) Math.random()-0.5f, -movement.z);
        }
        
        shadow.update(delta, this);
    }

 
}
