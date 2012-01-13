package org.iplantc.core.uicommons.client.images;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Provides access to bundled image resources.
 */
public interface Icons extends ClientBundle {
    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("book_add.png")
    ImageResource category();

    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("book_open.png")
    ImageResource category_open();

    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("book.png")
    ImageResource subCategory();

}
