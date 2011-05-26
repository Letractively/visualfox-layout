package me.l1k3.ui.layout.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;

public class Center {
    public static enum Horizontal {
        LEFT, CENTER, RIGHT
    };
    
    public static enum Vertical {
        TOP, MIDDLE, BOTTOM
    };
    
    private Element screen;
    private Horizontal positionX;
    private Vertical positionY;
    private double anchorX;
    private double anchorY;
    
    public Center(Element screen, Horizontal positionX, Vertical positionY) {
        this(screen, positionX, positionY, 0, 0);
    }
    
    public Center(Element screen, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        this.screen = screen;
        this.positionX = positionX;
        this.positionY = positionY;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }
    
    //- - -
    
    public void initElements() {
        Center.initElements(screen);
    }
    
    public void layout() {
        NodeList<Node> list = screen.getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                layout(Element.as(node));
            }
        }
    }
    
    public void layout(Element element) {
        element.getStyle().setProperty("left", getPositionX(screen, element, positionX, anchorX)+"px");
        element.getStyle().setProperty("top", getPositionY(screen, element, positionY, anchorY)+"px");
    }
    
    //- - -
    
    public final static void initElements(Element screen) {
        NodeList<Node> list = screen.getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                initElement(Element.as(node));
            }
        }
    }
    
    public final static void initElement(Element element) {
        Style style = element.getStyle();
        
        style.setProperty("float", "left");
        style.setProperty("display", "block");
        style.setProperty("position", "absolute");
    }
    
    public final static void layout(Element screen, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        NodeList<Node> list = screen.getChildNodes();
        
        for(int i=0;i<list.getLength();i++) {
            Node node = list.getItem(i);

            if(Element.is(node)) {
                layout(screen, Element.as(node), positionX, positionY, anchorX, anchorY);
            }
        }
    }
    
    public final static void layout(Element screen, Element element, Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {
        element.getStyle().setProperty("left", getPositionX(screen, element, positionX, anchorX)+"px");
        element.getStyle().setProperty("top", getPositionY(screen, element, positionY, anchorY)+"px");
    }
    
    public final static int getPositionY(Element screen, Element element, Vertical position, double anchor) {
        switch (position) {
            case TOP:
                if(anchor>0)
                return -(int)(element.getOffsetHeight()*anchor);
            break;
            case MIDDLE:
                return (int)((screen.getClientHeight()-element.getOffsetHeight())/2.0);
            case BOTTOM:
                if(anchor>0)
                return (int)(screen.getClientHeight()-(element.getOffsetHeight()*(1.0-anchor)));

                return screen.getClientHeight()-element.getOffsetHeight();
        }
        
        return 0;
    }

    public final static int getPositionX(Element screen, Element element, Horizontal position, double anchor) {
        switch (position)
        {
            case LEFT:
                if(anchor>0)
                return -(int)(element.getOffsetWidth()*anchor);
            break;
            case CENTER:
                return (int)((screen.getClientWidth()-element.getOffsetWidth())/2.0);
            case RIGHT:
                if(anchor>0)
                return (int)(screen.getClientWidth()-(element.getOffsetWidth()*(1.0-anchor)));
                
                return screen.getClientWidth()-element.getOffsetWidth();
        }

        return 0;
    }
}
