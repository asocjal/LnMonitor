package org.dziemian.ln.view;

import static jsweet.util.Globals.union;

import org.dziemian.ln.data.LnNode;

import jsweet.dom.CanvasRenderingContext2D;

public class LnNodeView implements ElementView {
	
	private final int radius = 5;
	private double dirx;
	private double diry;
	private double x;
	private double y;
	private boolean selected = false;
	
	private final LnNode node;
	
	public LnNodeView(double x, double y, LnNode node) {
		dirx = (Math.random()-0.5);///4;
		diry = (Math.random()-0.5);///4;
		this.x = x;
		this.y = y;
		this.node = node;
		this.node.setView(this);
	}
	
	public LnNode getLnNode() {
		return node;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void draw(DrawCanvas drawCanvas) {
		x += dirx;
		y += diry;
		if((x < radius+1) || (x > drawCanvas.getWidth()-radius-1)) {
			dirx = -dirx;
		}
		if((y < radius+1) || (y > drawCanvas.getHeight()-radius-1)) {
			diry = -diry;
		}
		
		CanvasRenderingContext2D ctx = drawCanvas.getCtx();

		ctx.beginPath();
		
		if(selected == true) {
			ctx.fillStyle = union("rgb(50,50,250)");
			ctx.arc(x, y, radius*2, 0,2*Math.PI);
		} else {
			ctx.fillStyle = union("rgb(100,100,200)");
			ctx.arc(x, y, radius, 0,2*Math.PI);
		}
		
//		ctx.stroke();
		ctx.fill();
		ctx.closePath();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
