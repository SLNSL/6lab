package wrappers;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;

public interface Treat {
    Answer treat(DatagramPacket packet);
}
