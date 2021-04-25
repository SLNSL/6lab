package wrappers;

import messenger.Messenger;

public interface Packet {

    public String getCommand();

    public Object[] getArguments();

    boolean hasError();

    String getError();

}
