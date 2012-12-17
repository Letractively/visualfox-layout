/*
 * Copyright 2011 Philippe Blanc.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package me.l1k3.ui.layout.client;

import me.l1k3.fx.client.FXPosition;

import com.google.gwt.dom.client.Element;

public class CenterAnimation extends Center {
    private Element element;
    private FXPosition effect;
    
    public CenterAnimation(Element panel, Element element, Horizontal positionX, Vertical positionY) {
        this(panel, element, positionX, positionY, 0, 0);
    }
    
    public CenterAnimation(Element panel, Element element, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        super(panel, positionX, positionY, anchorX, anchorY);
        this.element = element;
        effect = new FXPosition();
        effect.init(element);
    }
    
    //- - -
    
    public void layout() {
        layout(element);
    }
    
    public void setPosition(Horizontal positionX, Vertical positionY) {    
        set(positionX, positionY);
        effect.cancel();
        effect.to((int)getPositionX(element), (int)getPositionY(element));
        effect.animate(500);
    }
    
    public void setPosition(Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {    
        set(positionX, positionY, anchorX, anchorY);
        effect.cancel();
        effect.to((int)getPositionX(element), (int)getPositionY(element));
        effect.animate(500);
    }
}
