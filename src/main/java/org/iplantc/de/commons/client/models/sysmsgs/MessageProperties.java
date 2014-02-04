package org.iplantc.de.commons.client.models.sysmsgs;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MessageProperties extends PropertyAccess<Message> {

    ModelKeyProvider<Message> id();

    ValueProvider<Message, Date> activationTime();

    ValueProvider<Message, Date> deactivationTime();

    ValueProvider<Message, String> body();

    ValueProvider<Message, String> type();

    ValueProvider<Message, Boolean> dismissible();

    @Path("type")
    LabelProvider<Message> typeLabel();
}
