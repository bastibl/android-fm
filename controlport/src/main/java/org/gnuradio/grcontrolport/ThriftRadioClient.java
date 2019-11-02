package org.gnuradio.grcontrolport;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.gnuradio.controlport.ControlPort;

/**
 * Created by trondeau on 7/22/15.
 */
public class ThriftRadioClient {
    private TSocket tsocket;
    private TBinaryProtocol tprotocol;
    private ControlPort.Client radio;

    public ThriftRadioClient(String host, Integer port) {
        try {
            tsocket = new TSocket(host, port);
            tprotocol = new TBinaryProtocol(tsocket);
            tsocket.open();

            radio = new ControlPort.Client(tprotocol);
        } catch (TTransportException e) {
            System.err.println("Could not connect to ControlPort endpoint at " + host + ":" + port + ".\n\n");
            System.exit(1);
        }
    }

    public void finalize() throws Throwable {
        try {
            radio.shutdown();
            tsocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    ControlPort.Client getRadio() {
        return radio;
    }
}
