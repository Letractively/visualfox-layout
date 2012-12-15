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

import me.l1k3.core.client.Core;
import me.l1k3.ui.layout.client.inter.Layout;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;

public class Center implements Layout {
    public static enum Horizontal {
        LEFT, CENTER, RIGHT
    };
    
    public static enum Vertical {
        TOP, MIDDLE, BOTTOM
    };
    
    private Element panel;
    private Horizontal positionX;
    private Vertical positionY;
    private double anchorX;
    private double anchorY;
    
    public Center(Element panel, Horizontal positionX, Vertical positionY) {
        this(panel, positionX, positionY, 0, 0);
    }
    
    public Center(Element panel, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        this.panel = panel;
        this.positionX = positionX;
        this.positionY = positionY;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }
    
    //- - -
    
    @Override
    public final void initElements() {
        initElements(panel);
    }
    
    protected final void initElements(Element parent) {
        NodeList<Node> list = parent.getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                initElement(Element.as(node));
            }
        }
    }
    
    @Override
    public final void initElement(Element element) {
        Style style = element.getStyle();
        
        //style.setProperty("float", "left");
        //style.setProperty("display", "block");
        //style.setProperty("position", "absolute");
        
        style.setProperty("display", "inline-block");
        style.setProperty("verticalAlign", "top");
    }
    
    @Override
    public void layouts() {
        NodeList<Node> list = panel.getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                layout(Element.as(node));
            }
        }
    }
    
    @Override
    public void layout(Element element) {
        element.getStyle().setProperty("left", getPositionX(element)+"px");
        element.getStyle().setProperty("top", getPositionY(element)+"px");
    }
    
    public void layout(Element element, int width, int height) {
        element.getStyle().setProperty("left", getPositionX(width)+"px");
        element.getStyle().setProperty("top", getPositionY(height)+"px");
    }
    
    public void setPosition(Element element, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {    
        set(positionX, positionY, anchorX, anchorY);
        layout(element);
    }
    
    public void setPositions(Horizontal positionX, Vertical positionY) {
        set(positionX, positionY);
        layouts();
    }
    
    public void setPositions(Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        set(positionX, positionY, anchorX, anchorY);
        layouts();
    }
    
    //- - -
    
    protected final Element getPanel() {
        return panel;
    }
    
    protected final int getPositionX(int width) {
        return getPositionX(panel, width, positionX, anchorX);
    }
    
    protected final int getPositionX(Element element) {
        return getPositionX(panel, element, positionX, anchorX);
    }
    
    protected final int getPositionY(int height) {
        return getPositionY(panel, height, positionY, anchorY);
    }
    
    protected final int getPositionY(Element element) {
        return getPositionY(panel, element, positionY, anchorY);
    }
    
    protected final void set(Horizontal positionX, Vertical positionY) {
        if(this.positionX==positionX && this.positionY==positionY) {
            return;
        }
        
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    protected final void set(Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        if(this.positionX==positionX && this.positionY==positionY && this.anchorX==anchorX && anchorY==anchorY) {
            return;
        }
        
        this.positionX = positionX;
        this.positionY = positionY;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }
    
    //- - -
    
    public final static void layouts(Element panel, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        NodeList<Node> list = panel.getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                layout(panel, Element.as(node), positionX, positionY, anchorX, anchorY);
            }
        }
    }
    
    public final static void layout(Element panel, Element element, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        layout(panel, element.getStyle(), (int)Core.getOuterWidth(element), (int)Core.getOuterHeight(element), positionX, positionY, anchorX, anchorY);
    }
    
    public final static void layout(Element panel, Style style, int width, int height, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        style.setProperty("left", getPositionX(panel, width, positionX, anchorX)+"px");
        style.setProperty("top", getPositionY(panel, height, positionY, anchorY)+"px");
    }
    
    public final static int getPositionX(Element panel, Element element, Horizontal position, double anchor) {
        return getPositionX(panel, (int)Core.getOuterWidth(element), position, anchor);
    }
    
    public final static int getPositionX(Element panel, int width, Horizontal position, double anchor) {
        switch (position)
        {
            case LEFT:
                if(anchor>0)
                return -(int)(width*anchor);
            break;
            case CENTER:
                return (int)((panel.getClientWidth()-width)/2.0);
            case RIGHT:
                if(anchor>0)
                return (int)(panel.getClientWidth()-(width*(1.0-anchor)));
                
                return panel.getClientWidth()-width;
        }

        return 0;
    }
    
    public final static int getPositionY(Element panel, Element element, Vertical position, double anchor) {
        return getPositionY(panel, (int)Core.getOuterHeight(element), position, anchor);
    }
    
    public final static int getPositionY(Element panel, int height, Vertical position, double anchor) {
        switch (position) {
            case TOP:
                if(anchor>0)
                return -(int)(height*anchor);
            break;
            case MIDDLE:
                return (int)((panel.getClientHeight()-height)/2.0);
            case BOTTOM:
                if(anchor>0)
                return (int)(panel.getClientHeight()-(height*(1.0-anchor)));

                return panel.getClientHeight()-height;
        }
        
        return 0;
    }
}
