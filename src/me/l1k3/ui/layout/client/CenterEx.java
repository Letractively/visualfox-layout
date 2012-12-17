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
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;

public class CenterEx extends Center {

    public CenterEx(Element panel, Horizontal positionX, Vertical positionY) {
        this(panel, positionX, positionY, 0, 0);
    }
    
    public CenterEx(Element panel, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        super(panel, positionX, positionY, anchorX, anchorY);
    }
    
    //- - -
    
    public void layoutAnimated() {
        NodeList<Node> list = getPanel().getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                layoutAnimated(Element.as(node));
            }
        }
    }
    
    public void layoutAnimated(Element element) {
        FXPosition.to(element, (int)getPositionX(element), (int)getPositionY(element)).animate(500);
    }
    
    //- - -
    
    @Override
    public void setPosition(Element element, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {    
        set(positionX, positionY, anchorX, anchorY);
        layoutAnimated(element);
    }
    
    @Override
    public void setPositions(Horizontal positionX, Vertical positionY) {
        set(positionX, positionY);
        layoutAnimated();
    }
    
    @Override
    public void setPositions(Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        set(positionX, positionY, anchorX, anchorY);
        layoutAnimated();
    }
}
