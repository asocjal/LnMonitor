package org.dziemian.ln.view;

import static jsweet.util.Globals.union;

import org.dziemian.ln.data.PaymentChannel;

import jsweet.dom.CanvasRenderingContext2D;

public class PaymentChannelView implements ElementView {

	private final PaymentChannel paymentChannel;

	public PaymentChannelView(final PaymentChannel paymentChannel) {
		this.paymentChannel = paymentChannel;
	}
	
	@Override
	public void draw(final DrawCanvas drawCanvas) {
		CanvasRenderingContext2D ctx = drawCanvas.getCtx();
		
		LnNodeView node1View = paymentChannel.getNode1().getView();
		LnNodeView node2View = paymentChannel.getNode2().getView();
		
		ctx.beginPath();
		if(node1View.isSelected() || node2View.isSelected()) {
			ctx.strokeStyle = union("rgb(150,50,50)");
			ctx.lineWidth = 4;
		} else {
			ctx.strokeStyle = union("rgb(100,100,200)");
			ctx.lineWidth = 1;		
		}
		
		ctx.moveTo(node1View.getX(), node1View.getY());
		ctx.lineTo(node2View.getX(), node2View.getY());
		ctx.stroke();
		ctx.closePath();
		
	}
	
}
