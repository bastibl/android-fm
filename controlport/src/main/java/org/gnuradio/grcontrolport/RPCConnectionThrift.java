package org.gnuradio.grcontrolport;

import android.util.Log;

import org.gnuradio.controlport.BaseTypes;
import org.gnuradio.controlport.Knob;
import org.gnuradio.controlport.KnobBase;
import org.gnuradio.controlport.KnobProp;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by trondeau on 7/22/15.
 */
public class RPCConnectionThrift extends RPCConnection {

    private ThriftRadioClient client;

    public RPCConnectionThrift(String host, Integer port)
    {
        super("thrift", host, port);
        // Get port from prefs

        newConnection(host, port);
    }

    protected void newConnection(String host, Integer port) {
        client = new ThriftRadioClient(host, port);
    }

    protected KnobInfo unpackKnob(String key, Knob knob) {
        KnobInfo x;

        if(knob.getType() == BaseTypes.BOOL) {
            x = new KnobInfo(key, knob.getValue().a_bool, BaseTypes.BOOL);
        }
        else if(knob.getType() == BaseTypes.BYTE) {
            x = new KnobInfo(key, knob.getValue().a_byte, BaseTypes.BYTE);
        }
        else if(knob.getType() == BaseTypes.SHORT) {
            x = new KnobInfo(key, knob.getValue().a_short, BaseTypes.SHORT);
        }
        else if(knob.getType() == BaseTypes.INT) {
            x = new KnobInfo(key, knob.getValue().a_int, BaseTypes.INT);
        }
        else if(knob.getType() == BaseTypes.LONG) {
            x = new KnobInfo(key, knob.getValue().a_long, BaseTypes.LONG);
        }
        else if(knob.getType() == BaseTypes.DOUBLE) {
            x = new KnobInfo(key, knob.getValue().a_double, BaseTypes.DOUBLE);
        }
        else if(knob.getType() == BaseTypes.STRING) {
            x = new KnobInfo(key, knob.getValue().a_string, BaseTypes.STRING);
        }
        else if(knob.getType() == BaseTypes.COMPLEX) {
            x = new KnobInfo(key, knob.getValue().a_complex, BaseTypes.COMPLEX);
        }
        else if(knob.getType() == BaseTypes.F32VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_f32vector, BaseTypes.F32VECTOR);
        }
        else if(knob.getType() == BaseTypes.F64VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_f64vector, BaseTypes.F64VECTOR);
        }
        else if(knob.getType() == BaseTypes.S64VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_s64vector, BaseTypes.S64VECTOR);
        }
        else if(knob.getType() == BaseTypes.S32VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_s32vector, BaseTypes.S32VECTOR);
        }
        else if(knob.getType() == BaseTypes.S16VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_s16vector, BaseTypes.S16VECTOR);
        }
        else if(knob.getType() == BaseTypes.S8VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_s8vector, BaseTypes.S8VECTOR);
        }
        else if(knob.getType() == BaseTypes.C32VECTOR) {
            x = new KnobInfo(key, knob.getValue().a_c32vector, BaseTypes.C32VECTOR);
        }
        else {
            throw new RuntimeException("Unknown Knob Base Type.");
        }
        return x;
    }

    protected Knob packKnob(KnobInfo knob) {
        Knob x = new Knob();
        KnobBase k = new KnobBase();

        if (knob.ktype == BaseTypes.BOOL) {
            k.a_bool = Boolean.parseBoolean(knob.value.toString());
        }
        else if (knob.ktype == BaseTypes.BYTE) {
            k.a_byte = Byte.parseByte(knob.value.toString());
        }
        else if (knob.ktype == BaseTypes.SHORT) {
            k.a_short = Short.parseShort(knob.value.toString());
        }
        else if (knob.ktype == BaseTypes.INT) {
            k.a_int = Integer.parseInt(knob.value.toString());
        }
        else if (knob.ktype == BaseTypes.LONG) {
            k.a_long = Long.parseLong(knob.value.toString());
        }
        else if (knob.ktype == BaseTypes.DOUBLE) {
            k.a_double = Double.parseDouble(knob.value.toString());
        }
        else if (knob.ktype == BaseTypes.STRING) {
            k.a_string = knob.value.toString();
        }
        //else if (knob.ktype == BaseTypes.COMPLEX) {
        //    k.a_complex = (knob.value.toString());
        //}
        //else if (knob.ktype == BaseTypes.F32VECTOR) {
        //    k.a_f32vector = Short.parseShort(knob.value.toString());
        //}
        else {
            throw new RuntimeException("Unknown Knob Base Type.");
        }

        x.setType(knob.ktype);
        x.setValue(k);
        return x;
    }


    public Map<String, KnobProp> properties(List<String> args) {
        Map<String, KnobProp> knobprops = new HashMap<String, KnobProp>();
        try {
            knobprops = client.getRadio().properties(args);
            for (Map.Entry<String, KnobProp> entry : knobprops.entrySet()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return knobprops;
    }

    public Map<String, KnobInfo> getKnobs(List<String> args)
    {
        Map<String, KnobInfo> results = new HashMap<String, KnobInfo>();
        try {
            for (Map.Entry<String, Knob> entry : client.getRadio().getKnobs(args).entrySet()) {
                results.put(entry.getKey(), unpackKnob(entry.getKey(), entry.getValue()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public Map<String, KnobInfo>  getRe(List<String> args)
    {
        Map<String, KnobInfo> results = new HashMap<String, KnobInfo>();
        try {
            for (Map.Entry<String, Knob> entry : client.getRadio().getRe(args).entrySet()) {
                results.put(entry.getKey(), this.unpackKnob(entry.getKey(), entry.getValue()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public void setKnobs(Map<String, KnobInfo> args) {
        Map<String, Knob> results = new HashMap<String, Knob>();
        for (Map.Entry<String, KnobInfo> entry : args.entrySet()) {
            results.put(entry.getKey(), this.packKnob(entry.getValue()));
        }

        try {
            client.getRadio().setKnobs(results);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setKnobs(List<KnobInfo> args) {
        Map<String, Knob> results = new HashMap<String, Knob>();

        for (KnobInfo entry : args) {
            System.out.println(entry.key);
            System.out.println(entry);
            System.out.println(this.packKnob(entry));
            results.put(entry.key, this.packKnob(entry));
        }

        try {
            client.getRadio().setKnobs(results);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //String to hex
    public static String toHex(String c) throws UnsupportedEncodingException {
        //Change encoding according to your need
        return String.format("%04x", new BigInteger(1, c.getBytes("UTF8")));
    }

    public void postMessage(String blk_alias, String blk_port, String cmd_name, Double cmd_val) {
        try {
            byte [] x = SerializeMsgDouble(cmd_name, cmd_val);

            String s_alias = new String(SerializePMTString(blk_alias));
            String s_port = new String(SerializePMTString(blk_port));

            //Log.d("RPCConnectionThrift", "Serialized PMT Alias:   " + s_alias.substring(3));
            //Log.d("RPCConnectionThrift", "Serialized PMT Port:    " + s_port.substring(3));
            //Log.d("RPCConnectionThrift", "Serialized PMT Cmd: " + cmd_name);
            //Log.d("RPCConnectionThrift", "Serialized PMT Val: " + cmd_val);

            client.getRadio().postMessage(s_alias, s_port, ByteBuffer.wrap(x));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public native byte[] SerializePMTString(String data);
    public native byte[] SerializeMsgDouble(String port, double val);

    static {
        System.loadLibrary("pmtjni");
    }

}
