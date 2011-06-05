package me.l1k3.app.ui.layout.demo.client;

import me.l1k3.core.client.queue.Queue;
import me.l1k3.fx.client.FXFade;
import me.l1k3.ui.layout.client.*;
import me.l1k3.ui.layout.client.Center.Horizontal;
import me.l1k3.ui.layout.client.Center.Vertical;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;

public class Demo implements EntryPoint {
    private Center center;
    private double anchorX = 0.5;
    private double anchorY = 0.5;
    private double mainAnchorX = 0;
    private double mainAnchorY = 0;
    private int counter = 0;
    
    @Override
    public void onModuleLoad() {
        
        int type = 3;
        
        switch(type) {
            case 1:
                center = new Center(DOM.getElementById("center"), Horizontal.CENTER, Vertical.MIDDLE);
            break;
            case 2:
                center = new CenterEx(DOM.getElementById("center"), Horizontal.CENTER, Vertical.MIDDLE);
            break;
            case 3:
                center = new CenterAnimation(DOM.getElementById("center"), DOM.getElementById("fox"), Horizontal.CENTER, Vertical.MIDDLE);
                DOM.getElementById("foxlabel").getStyle().setVisibility(Visibility.HIDDEN);
            break;
        }
        
        final ResizeCommand resizeCallback = new ResizeCommand();
        
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                resizeCallback.execute();
            }
        });
        
        resizeCallback.resize().callback(new Command() {
            @Override
            public void execute() {
                //DOM.getElementById("root").getStyle().setVisibility(Style.Visibility.VISIBLE);
                FXFade.range(DOM.getElementById("root"), 1, 0).hide().show(1000);
            }
        });
        
        Button button = new Button("Visit the neighbours");
        
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                move();
            }
        });
        
        RootPanel.get("toolbar").add(button);
        
        final ToggleButton toggle = new ToggleButton("Overflow");
        
        toggle.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                overflow(toggle.isDown());
            }
        });
        
        RootPanel.get("toolbar").add(toggle);
    }
    
    protected void resize() {
        if(center instanceof CenterAnimation) {
            ((CenterAnimation)center).layout();
        }
        else {
            center.layouts();
        }
        
        Center.layouts(DOM.getElementById("left"), Horizontal.LEFT, Vertical.MIDDLE, anchorX, anchorY);
        Center.layouts(DOM.getElementById("right"), Horizontal.RIGHT, Vertical.MIDDLE, anchorX, anchorY);
        Center.layouts(DOM.getElementById("top"), Horizontal.CENTER, Vertical.TOP, anchorX, anchorY);
        Center.layouts(DOM.getElementById("bottom"), Horizontal.CENTER, Vertical.BOTTOM, anchorX, anchorY);
        
        Center.layouts(DOM.getElementById("topleft"), Horizontal.LEFT, Vertical.TOP, anchorX, anchorY);
        Center.layouts(DOM.getElementById("topright"), Horizontal.RIGHT, Vertical.TOP, anchorX, anchorY);
        Center.layouts(DOM.getElementById("bottomright"), Horizontal.RIGHT, Vertical.BOTTOM, anchorX, anchorY);
        Center.layouts(DOM.getElementById("bottomleft"), Horizontal.LEFT, Vertical.BOTTOM, anchorX, anchorY);
    }
    
    protected void overflow(boolean overflow) {
        if(overflow) {
            DOM.getElementById("center").addClassName("overflow");
            
            DOM.getElementById("left").addClassName("overflow");
            DOM.getElementById("right").addClassName("overflow");
            DOM.getElementById("top").addClassName("overflow");
            DOM.getElementById("bottom").addClassName("overflow");
            
            DOM.getElementById("topleft").addClassName("overflow");
            DOM.getElementById("topright").addClassName("overflow");
            DOM.getElementById("bottomright").addClassName("overflow");
            DOM.getElementById("bottomleft").addClassName("overflow");
        }
        else {
            DOM.getElementById("center").removeClassName("overflow");
            
            DOM.getElementById("left").removeClassName("overflow");
            DOM.getElementById("right").removeClassName("overflow");
            DOM.getElementById("top").removeClassName("overflow");
            DOM.getElementById("bottom").removeClassName("overflow");
            
            DOM.getElementById("topleft").removeClassName("overflow");
            DOM.getElementById("topright").removeClassName("overflow");
            DOM.getElementById("bottomright").removeClassName("overflow");
            DOM.getElementById("bottomleft").removeClassName("overflow");
        }
    }
    
    protected void move() {
        counter++;
        if (counter==9) { 
            counter = 0;
            
            mainAnchorX += 0.5;
            mainAnchorY += 0.5;
            
            if(mainAnchorX>1) {
                mainAnchorX = mainAnchorY = 0;
            }
        }
        
        if(center instanceof CenterAnimation) {
            CenterAnimation c =  (CenterAnimation)center;
            
            switch(counter) {
            case 0:
                c.setPosition(Horizontal.CENTER, Vertical.MIDDLE, mainAnchorX, mainAnchorY);
            break;
            case 1:
                c.setPosition(Horizontal.LEFT, Vertical.TOP, mainAnchorX, mainAnchorY);
            break;
            case 2:
                c.setPosition(Horizontal.CENTER, Vertical.TOP, mainAnchorX, mainAnchorY);
            break;
            case 3:
                c.setPosition(Horizontal.RIGHT, Vertical.TOP, mainAnchorX, mainAnchorY);
            break;
            case 4:
                c.setPosition(Horizontal.RIGHT, Vertical.MIDDLE, mainAnchorX, mainAnchorY);
            break;
            case 5:
                c.setPosition(Horizontal.RIGHT, Vertical.BOTTOM, mainAnchorX, mainAnchorY);
            break;
            case 6:
                c.setPosition(Horizontal.CENTER, Vertical.BOTTOM, mainAnchorX, mainAnchorY);
            break;
            case 7:
                c.setPosition(Horizontal.LEFT, Vertical.BOTTOM, mainAnchorX, mainAnchorY);
            break;
            case 8:
                c.setPosition(Horizontal.LEFT, Vertical.MIDDLE, mainAnchorX, mainAnchorY);
            break;
        }
        }
        else {
            switch(counter) {
                case 0:
                    center.setPositions(Horizontal.CENTER, Vertical.MIDDLE, mainAnchorX, mainAnchorY);
                break;
                case 1:
                    center.setPositions(Horizontal.LEFT, Vertical.TOP, mainAnchorX, mainAnchorY);
                break;
                case 2:
                    center.setPositions(Horizontal.CENTER, Vertical.TOP, mainAnchorX, mainAnchorY);
                break;
                case 3:
                    center.setPositions(Horizontal.RIGHT, Vertical.TOP, mainAnchorX, mainAnchorY);
                break;
                case 4:
                    center.setPositions(Horizontal.RIGHT, Vertical.MIDDLE, mainAnchorX, mainAnchorY);
                break;
                case 5:
                    center.setPositions(Horizontal.RIGHT, Vertical.BOTTOM, mainAnchorX, mainAnchorY);
                break;
                case 6:
                    center.setPositions(Horizontal.CENTER, Vertical.BOTTOM, mainAnchorX, mainAnchorY);
                break;
                case 7:
                    center.setPositions(Horizontal.LEFT, Vertical.BOTTOM, mainAnchorX, mainAnchorY);
                break;
                case 8:
                    center.setPositions(Horizontal.LEFT, Vertical.MIDDLE, mainAnchorX, mainAnchorY);
                break;
            }
        }
    }
    
    public class ResizeCommand implements Queue, Command {
        private Command callback;
        
        public Queue resize() {
            Scheduler.get().scheduleDeferred(this);
            return this;
        }
        
        @Override
        public void execute() {
            Demo.this.resize();
            
            if(callback!=null) {
                Scheduler.get().scheduleDeferred(callback);
                callback = null;
            }
        }
        
        @Override
        public void callback(Command callback) {
            this.callback = callback;
        }
    }
}
