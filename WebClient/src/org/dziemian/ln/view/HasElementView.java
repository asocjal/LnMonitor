package org.dziemian.ln.view;

public interface HasElementView<T extends ElementView> {
	public void setView(final T elementView);
	public T getView();
}
