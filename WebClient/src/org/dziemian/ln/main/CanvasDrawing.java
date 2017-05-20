package org.dziemian.ln.main;

import static jsweet.dom.Globals.alert;
import static jsweet.dom.Globals.console;
import static jsweet.dom.Globals.document;
import static jsweet.dom.Globals.window;
import static jsweet.util.StringTypes.mousedown;
import static jsweet.util.StringTypes.mousemove;
import static jsweet.util.StringTypes.mouseup;

import org.dziemian.ln.data.LnNode;
import org.dziemian.ln.data.PaymentChannel;
import org.dziemian.ln.view.DrawCanvas;
import org.dziemian.ln.view.ElementsToDraw;
import org.dziemian.ln.view.LnNodeView;
import org.dziemian.ln.view.PaymentChannelView;

import jsweet.dom.HTMLCanvasElement;

public class CanvasDrawing {
	
	ElementsToDraw elementsToDraw = new ElementsToDraw();
	DrawCanvas drawCanvas;
	
	public static void main(String[] args) {
		window.onload = (e) -> {
			return new CanvasDrawing();
		};
	}

	public CanvasDrawing() {
		alert("V12");
		
		for(int i=0; i<14; i++) {
			LnNodeView nodeView = new LnNodeView(100+Math.random()*400, 100+Math.random()*400, new LnNode("Node Czarka"));
			if(i == 5) {
				nodeView.setSelected(true);
			}
			elementsToDraw.add(nodeView);
		}
		
		int size = elementsToDraw.getSize();
		for(int i=0; i<12; i++) {
			LnNodeView nodeView1 = (LnNodeView)elementsToDraw.get((int)(Math.random()*size));
			LnNodeView nodeView2 = (LnNodeView)elementsToDraw.get((int)(Math.random()*size));
			if(nodeView1!= nodeView2) {
				LnNode node1 = nodeView1.getLnNode();
				LnNode node2 = nodeView2.getLnNode();
	//			elementsToDraw.add(new LnNodeView(100+Math.random()*400, 100+Math.random()*400, node1));
	//			elementsToDraw.add(new LnNodeView(100+Math.random()*400, 100+Math.random()*400, node2));
				
				PaymentChannel paymentChannel = new PaymentChannel(node1, node2);
				elementsToDraw.add(new PaymentChannelView(paymentChannel));
			}
		}
		
//		elementsToDraw.add(new LnNodeView(50, 60, new LnNode("Node Natalki")));
//		elementsToDraw.add(new LnNodeView(200, 40, new LnNode("Node Natalki")));
//		elementsToDraw.add(new LnNodeView(130, 20, new LnNode("Node Natalki")));
//		elementsToDraw.add(new LnNodeView(80, 120, new LnNode("Node Natalki")));
		
		console.info("creating canvas drawing example");
		HTMLCanvasElement canvasElement = (HTMLCanvasElement) document.getElementById("canvas");
		drawCanvas = new DrawCanvas(canvasElement);

		
		canvasElement.addEventListener(mousedown, event -> {
			console.info("Mouse down");
			return null;
		}, true);
		canvasElement.addEventListener(mousemove, event -> {
			console.info("Mouse move");
			return null;
		}, true);
		canvasElement.addEventListener(mouseup, event -> {
			console.info("Mouse up");
			return null;
		}, true);
		
		
		
		onFrame();
	}
	
	private void animateFrame() {

		drawCanvas.getCtx().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
		elementsToDraw.draw(drawCanvas);

	}

	private void onFrame() {
		animateFrame();
		window.requestAnimationFrame((time) -> {
			this.onFrame();
		});
	}

}
