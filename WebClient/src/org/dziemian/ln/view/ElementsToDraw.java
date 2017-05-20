package org.dziemian.ln.view;

import java.util.ArrayList;
import java.util.List;

public class ElementsToDraw {
	
	private final List<ElementView> elements = new ArrayList<ElementView>();
	
	public void add(final ElementView elementView) {
		elements.add(elementView);
	}
	
	public void draw(final DrawCanvas drawCanvas) {
		for(ElementView el : elements) {
			el.draw(drawCanvas);
		}
	}
	
	public int getSize() {
		return elements.size();
	}
	
	public ElementView get(int index) {
		return elements.get(index);
	}

}
