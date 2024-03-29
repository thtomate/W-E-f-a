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
package com.BombingGames.WurfelEngine.Core.LightEngine;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author Benedikt Vogler
 */
public class GlobalLightSource {   
    /**
     *The higher the value the faster/shorter is the day.
     */
    private float power;
    private Color tone; //the color of the light
    private float height;
    private float azimuth;
    private final int amplitude; //the max possible angle (from horizon) the sun can has

    /**
     * A GlobalLightSource can be the moon, the sun or even something new.
     * @param azimuth The starting position.
     * @param height The starting position.
     * @param color the starting color of the light. With this parameter you controll its brightness.
     * @param amplitudeHeight the maximum degree during a day the LightSource rises
     */
    public GlobalLightSource(float azimuth, float height, Color color, int amplitudeHeight) {
        this.azimuth = azimuth;
        this.height = height;
        this.tone = color;
        this.amplitude = amplitudeHeight;
    }

    /**
     * A light source shines can shine brighter and darker. This amplitude is called power. What it real emits says the brightness.
     * @return a value between 0 and 1
     */
    public float getPower() {
        return power;
    }
    
    /**
     *
     * @return
     */
    public Color getTone() {
        return tone;
    }

    /**
     *
     * @return
     */
    public float getHeight() {
        return height;
    }

    /**
     *
     * @return in degrees 0-360°
     */
    public float getAzimuth() {
        return azimuth;
    }

    /**
     *
     * @return
     */
    public float getAzimuthSpeed() {
        return WE.getCurrentConfig().getLEAzimutSpeed();
    }

    /**
     *
     * @return
     */
    public int getMaxAngle() {
        return amplitude;
    }

    /**
     *The Latitude posiiton. 
     * @param height in degrees 0-360°
     */
    public void setHeight(final float height) {
        this.height = height % 360;
        if (this.height < 0)
            this.height += 360;
    }

    /**
     *The longitudinal position
     * @param azimuth in degrees 0-360°
     */
    public void setAzimuth(final float azimuth) {
        this.azimuth = azimuth % 360;
        if (this.azimuth < 0)
            this.azimuth += 360;
    }

    /**
     *
     * @param tone
     */
    public void setTone(final Color tone) {
        this.tone = tone;
    }

    /**
     *
     * @param delta
     */
    public void update(float delta) {    
        //automove
        if (getAzimuthSpeed() != 0) {
            azimuth += getAzimuthSpeed() * delta;
            height = (float) (amplitude * Math.sin((azimuth + Controller.getMap().getWorldSpinDirection()) * Math.PI / 180));
        }
            
        //brightness calculation
        if (height > amplitude/2 && height < 180)
                power = 1;//day
        else if (height < -amplitude/2)
                 power = 0;//night
            else if (height < amplitude/2) 
                    power = (float) (0.5f + 0.5f*Math.sin(height * Math.PI/amplitude)); //morning   & evening
        

        if (power > 1) power=1;
        if (power < 0) power=0;    
        
        //if (azimuth>180+IGLPrototype.TWISTDIRECTION)
        //color = new Color(127 + (int) (power * 128), 255, 255);
        //else color = new Color(1f,1f,1f);
        //I_a = (int) ((90-height)*0.1f);
    }

    /**
     * Returns the light the GLS emits.
     * @return 
     */
    public Color getLight() {
        return getTone().cpy().mul(power);
    }
}
