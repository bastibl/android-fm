package org.gnuradio.grcontrolport;

import org.gnuradio.controlport.BaseTypes;
import org.gnuradio.controlport.Knob;
import org.gnuradio.controlport.KnobProp;

import java.util.List;
import java.util.Map;

/**
 */
public abstract class RPCConnection {

    public static class KnobInfo {
        final public String key;
        final public Object value;
        final public BaseTypes ktype;

        public KnobInfo(String _key, Object _value, BaseTypes _ktype) {
            key = _key;
            value = _value;
            ktype = _ktype;
        }

        public KnobInfo(String _key) {
            key = _key;
            value = true;
            ktype = BaseTypes.BOOL;
        }
    }

    private String method;
    private String host;
    private Integer port;

//    Map<String, String> RPCMethods = {
//            'thrift': 'Apache Thrift',
//            #'ice': 'Zeroc ICE'
//   };

    RPCConnection(String method, String hostName, Integer portNum) {
        System.out.println("Host: " + hostName + " : " + portNum);
        this.method = method;
        this.port = portNum;
        if (hostName.length() == 0) {
            this.host = "127.0.0.1";
        } else {
            this.host = host;
        }
    }

    public String getName() {
        //return RPCMethods[self.method];
        return this.method;
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    abstract protected void newConnection(String host, Integer port);
    abstract protected KnobInfo unpackKnob(String key, Knob knob);
    abstract protected Knob packKnob(KnobInfo knob);
    abstract public Map<String, KnobProp> properties(List<String> args);
    abstract public Map<String, KnobInfo> getKnobs(List<String> args);
    abstract public Map<String, KnobInfo>  getRe(List<String> args);
    abstract public void setKnobs(Map<String, KnobInfo> args);
    abstract public void setKnobs(List<KnobInfo> args);
    abstract public void postMessage(String blk_alias, String blk_port,
                                     String cmd_name, Double cmd_val);
    //abstract void shutdown();
    //abstract void printProperties(props);
}
