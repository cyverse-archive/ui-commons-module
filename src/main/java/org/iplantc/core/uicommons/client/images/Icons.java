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

    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("magnifier.png")
    ImageResource search();

    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("new.gif")
    ImageResource add();

    /**
     * Image resource
     * 
     * @return image.
     */
    @Source("delete.gif")
    ImageResource delete();

    /**
     * Image resource for window title-bars and the taskbar.
     * 
     * @return image.
     */
    @Source("whitelogo_sm.png")
    ImageResource whitelogoSmall();

    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("group_key.png")
    ImageResource share();

    /**
     * Image resource.
     * 
     * @return image.
     */
    @Source("close.png")
    ImageResource close();

}
