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
        FXPosition.to(element, getPositionX(element), getPositionY(element)).animate(500);
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
