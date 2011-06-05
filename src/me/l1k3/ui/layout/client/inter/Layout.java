package me.l1k3.ui.layout.client.inter;

import com.google.gwt.dom.client.Element;

public interface Layout {
    public abstract void initElements();
    public abstract void initElement(Element element);
    public abstract void layouts();
    public abstract void layout(Element element);
}