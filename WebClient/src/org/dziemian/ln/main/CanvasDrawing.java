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
import org.dziemian.ln.view.ElementView;
import org.dziemian.ln.view.ElementsToDraw;
import org.dziemian.ln.view.LnNodeView;
import org.dziemian.ln.view.PaymentChannelView;
import org.dziemian.ln.view.PositionAdapter;

import jsweet.dom.Event;
import jsweet.dom.HTMLCanvasElement;
import jsweet.dom.MouseEvent;
import jsweet.dom.WebSocket;

public class CanvasDrawing {
	
	ElementsToDraw elementsToDraw = new ElementsToDraw();
	PositionAdapter positionAdapter = new PositionAdapter();
	DrawCanvas drawCanvas;
	
	public static void main(String[] args) {
		window.onload = (e) -> {
			return new CanvasDrawing();
		};
	}

	public CanvasDrawing() {
		alert("V13");
		
		for(int i=0; i<20; i++) {
			LnNodeView nodeView = new LnNodeView(100+Math.random()*400, 100+Math.random()*400, new LnNode("Node Czarka"));
			elementsToDraw.add(nodeView);
		}
		
		int size = elementsToDraw.getSize();
		for(int i=0; i<40; i++) {
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
			onMouseUp(event);
			return null;
		}, true);
		
		
		
		onFrame();
	}
	
//	private void onSocketOpen() {
//		
//	}
	
	private void socketOpen() {
		console.info("Socket connectong... to remote ");
		WebSocket socket = new WebSocket("ws://192.168.1.3/helloworld-1.1-SNAPSHOT/websocketendpoint");
		
		socket.addEventListener(jsweet.util.StringTypes.open, event -> {
			console.info("Socket opened");
			socket.send("Dzien doberek");
			console.info("Socket sent \"dzien doberek\"");
			return null;
		});
		
		socket.addEventListener(jsweet.util.StringTypes.message, event -> {
			
			console.info("Message received: " + event.data);
			return null;
		});
		
//		socket.addEventListener(WebSocket., listener); .onopen.apply(onSocketOpen());
//		console.info("Socket sending message ");
//		socket.send("Wiadomosc");
//		console.info("Socket message sent");
//		socket.onmessage(function -> );
	}
	
	public void onMouseUp(MouseEvent event) {
		event.preventDefault();
		socketOpen();
		console.info("Looking for node. Coordinates are: " + event.layerX + " and " + event.layerY);
		boolean clicked = false;
		for(ElementView el: elementsToDraw.getList()) {
			if(el instanceof LnNodeView) {
				LnNodeView lnNode = (LnNodeView)el;
				lnNode.setSelected(false);
				if(     (lnNode.getX() > event.layerX - 10) &&
						(lnNode.getX() < event.layerX + 10) &&
						(lnNode.getY() > event.layerY - 10) &&
						(lnNode.getY() < event.layerY + 10) ) {
					console.info("Selecting node");
					lnNode.setSelected(true);
					clicked = true;
				}
			}
		}
		if(clicked == false) {
			LnNodeView nodeView = new LnNodeView(event.layerX, event.layerY, new LnNode("Node Czarka"));
			elementsToDraw.add(nodeView);
		}
//		if (this.area.finished) {
//			this.initGame();
//			this.startGame();
//		}
//		this.area.onInputDeviceUp(event, false);
	}
	
	private void animateFrame() {

		drawCanvas.getCtx().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
		elementsToDraw.draw(drawCanvas);

	}

	private void onFrame() {
		positionAdapter.adaptPositions(elementsToDraw);
		animateFrame();
		window.requestAnimationFrame((time) -> {
			this.onFrame();
		});
	}

}
