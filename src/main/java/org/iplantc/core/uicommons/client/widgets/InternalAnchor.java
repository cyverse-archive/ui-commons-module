package org.iplantc.core.uicommons.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasOpenHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.RootPanel;

import com.sencha.gxt.core.client.dom.XElement;

/**
 * This is an class defines a widget that acts like an HTML <a/>, but instead of opening an URL, it
 * opens an internal window inside the application.
 *
 * @param <T> the type the anchor opens
 */
public final class InternalAnchor<T> extends InlineHTML implements HasOpenHandlers<T> {

	/**
	 * Creates an InternalAnchor that wraps an existing <div/> or <span/> element. The element must
	 * already be attached to the document.
	 * 
	 * @param target the type the anchor will open
	 * @param element the DOM element to wrap
	 *
	 * @return the InternalAnchor that wraps the DOM element
	 */
	public static <T> InternalAnchor<T> wrap(final T target, final XElement element) {
		final InternalAnchor<T> anchor = new InternalAnchor<T>(target, element);
		RootPanel.detachOnWindowClose(anchor);
		return anchor;
	}
	
	private final T target;

	/**
	 * Creates an Internal Anchor for a given target that displays the given text. It uses the 
	 * default appearance.
	 * 
	 * @param target The object the anchor will open
	 * @param text the text to display in the anchor
	 */
	public InternalAnchor(final T target, final String text) {
		this(target, text, new InternalAnchorDefaultAppearance());
	}

	/**
	 * Creates an Internal Anchor for a given target that displays the given text. It uses the 
	 * default appearance. This constructor accepts a handler that will be called when the anchor
	 * is clicked.
	 * 
	 * @param target The object the anchor will open
	 * @param text the text to display in the anchor
	 * @param handler the open handler 
	 */
	public InternalAnchor(final T target, final String text, final OpenHandler<T> handler) {
		this(target, text, new InternalAnchorDefaultAppearance(), handler);
	}
	
	/**
	 * Creates an Internal Anchor for a given target that displays the given text.
	 * 
	 * @param target The object the anchor will open
	 * @param text the text to display in the anchor
	 * @param appearance the appearance of the anchor
	 */
	public InternalAnchor(final T target, final String text, 
			final InternalAnchorAppearance appearance) {
		super(appearance.render(text));
		this.target = target;
		initClickHandler();
	}

	/**
	 * Creates an Internal Anchor for a given target that displays the given text. This constructor 
	 * accepts a handler that will be called when the anchor is clicked.
	 * 
	 * @param target The object the anchor will open
	 * @param text the text to display in the anchor
	 * @param appearance the appearance of the anchor
	 * @param handler the open handler 
	 */
	public InternalAnchor(final T target, final String text, 
			final InternalAnchorAppearance appearance, final OpenHandler<T> handler) {
		this(target, text, appearance);
		addOpenHandler(handler);
	}
	
	private InternalAnchor(final T target, final XElement element) {
		super(element);
		this.target = target;
		initClickHandler();
		onAttach();
	}
	
 	private final void initClickHandler() {
 		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				OpenEvent.fire(InternalAnchor.this, target);
			}}); 		
 	}
 	
	/**
	 * @see HasOpenHandlers#addOpenHandler(OpenHandler)
	 */
	@Override
	public HandlerRegistration addOpenHandler(final OpenHandler<T> handler) {
		return addHandler(handler, OpenEvent.getType());
	}
}
