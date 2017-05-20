package org.dziemian.ln.data;

public class PaymentChannel {
	
	private final LnNode node1;
	private final LnNode node2;

	public PaymentChannel(final LnNode node1, final LnNode node2) {
		this.node1 = node1;
		this.node2 = node2;
	}

	public LnNode getNode1() {
		return node1;
	}

	public LnNode getNode2() {
		return node2;
	}
}
