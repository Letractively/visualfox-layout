package me.l1k3.app.ui.layout.demo.client;

import me.l1k3.core.client.queue.Callback;
import me.l1k3.core.client.queue.Queue;
import me.l1k3.ui.layout.client.Center;
import me.l1k3.ui.layout.client.Center.Horizontal;
import me.l1k3.ui.layout.client.Center.Vertical;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

public class Demo implements EntryPoint {

    private double anchorX = 0.5;
    private double anchorY = 0.5;
    
    @Override
    public void onModuleLoad() {
        final ResizeCommand resizeCallback = new ResizeCommand();
        
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                resizeCallback.execute();
            }
        });
        
        resizeCallback.resize().callback(new Callback() {
            @Override
            public void execute() {
                DOM.getElementById("root").getStyle().setVisibility(Style.Visibility.VISIBLE);
            }
        });
    }
    
    protected void resize() {        
        Center.layout(DOM.getElementById("center"), Horizontal.CENTER, Vertical.MIDDLE, anchorX, anchorY);
        
        Center.layout(DOM.getElementById("left"), Horizontal.LEFT, Vertical.MIDDLE, anchorX, anchorY);
        Center.layout(DOM.getElementById("right"), Horizontal.RIGHT, Vertical.MIDDLE, anchorX, anchorY);
        Center.layout(DOM.getElementById("top"), Horizontal.CENTER, Vertical.TOP, anchorX, anchorY);
        Center.layout(DOM.getElementById("bottom"), Horizontal.CENTER, Vertical.BOTTOM, anchorX, anchorY);
        
        Center.layout(DOM.getElementById("topleft"), Horizontal.LEFT, Vertical.TOP, anchorX, anchorY);
        Center.layout(DOM.getElementById("topright"), Horizontal.RIGHT, Vertical.TOP, anchorX, anchorY);
        Center.layout(DOM.getElementById("bottomright"), Horizontal.RIGHT, Vertical.BOTTOM, anchorX, anchorY);
        Center.layout(DOM.getElementById("bottomleft"), Horizontal.LEFT, Vertical.BOTTOM, anchorX, anchorY);
    }
    
    public class ResizeCommand implements Queue, Command {
        private Callback callback;
        
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
        public void callback(Callback callback) {
            this.callback = callback;
        }
    }
}
