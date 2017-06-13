package org.dziemian.ln.view;

public class PositionAdapter {
	
	public void adaptPositions(ElementsToDraw elementsToDraw) {
		for(ElementView elMain: elementsToDraw.getList()) {
			if(elMain instanceof LnNodeView) {
				LnNodeView lnNodeMain = (LnNodeView)elMain;
				boolean adjusted = false;
				for(ElementView elCompare: elementsToDraw.getList()) {
					if(elMain != elCompare) {
						if(elCompare instanceof LnNodeView) {
							LnNodeView lnNodeCompare = (LnNodeView)elCompare;
							if(adjust(lnNodeMain, lnNodeCompare)) {
								adjusted = true;
							}
						}
					}
				}
				if(adjusted == false) {
					lnNodeMain.setDirX(0);
					lnNodeMain.setDirY(0);
				}
			}
		}
	}
	
	private boolean adjust(LnNodeView lnNodeMain, LnNodeView lnNodeCompare) {
		double diffX = lnNodeMain.getX() - lnNodeCompare.getX();
		double diffY = lnNodeMain.getY() - lnNodeCompare.getY();
		if((diffX < 30) && (diffX > -30) && (diffY < 30) && (diffY > -30)) {
			if(diffX <= 0) {
				lnNodeMain.setDirX(-0.5);
			}
			if(diffX > 0) {
				lnNodeMain.setDirX(0.5);
			}
			if(diffY <= 0) {
				lnNodeMain.setDirY(-0.5);
			}
			if(diffY > 0) {
				lnNodeMain.setDirY(0.5);
			}
			return true;
		}
		return false;
	}
	
}
