package commands;

import wrappers.Packet;

public interface ClientCommands {
    Packet make(String message);
}
