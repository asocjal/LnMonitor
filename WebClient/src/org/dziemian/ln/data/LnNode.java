package org.dziemian.ln.data;

import org.dziemian.ln.view.HasElementView;
import org.dziemian.ln.view.LnNodeView;

public class LnNode implements HasElementView<LnNodeView> {
	
	private String name;
	
	private LnNodeView lnNodeView;
	
	public LnNode(final String name) {
		this.name = name;
	}

	@Override
	public void setView(LnNodeView lnNodeView) {
		this.lnNodeView = lnNodeView;
		
	}

	@Override
	public LnNodeView getView() {
		return lnNodeView;
	}

}
