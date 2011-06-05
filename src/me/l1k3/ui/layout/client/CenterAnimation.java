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
        effect.to(getPositionX(element), getPositionY(element));
        effect.animate(500);
    }
    
    public void setPosition(Horizontal positionX, Vertical positionY, double anchorX, double anchorY) {    
        set(positionX, positionY, anchorX, anchorY);
        effect.cancel();
        effect.to(getPositionX(element), getPositionY(element));
        effect.animate(500);
    }
}
