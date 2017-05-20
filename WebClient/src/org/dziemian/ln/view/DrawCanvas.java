package org.dziemian.ln.view;

import jsweet.dom.CanvasRenderingContext2D;
import jsweet.dom.HTMLCanvasElement;
import jsweet.util.StringTypes;

public class DrawCanvas {

	private final HTMLCanvasElement canvas;
	private final CanvasRenderingContext2D ctx;
	
//	public DrawCanvas(CanvasRenderingContext2D ctx) {
//		this.ctx = ctx;
//	}
	
	public DrawCanvas(final HTMLCanvasElement canvas) {
		this.canvas = canvas;
		this.ctx = canvas.getContext(StringTypes._2d);
	}
	
	public double getWidth() {
		return canvas.width;
	}
	
	public double getHeight() {
		return canvas.height;
	}
	
	public CanvasRenderingContext2D getCtx() {
		return ctx;
	}
}
