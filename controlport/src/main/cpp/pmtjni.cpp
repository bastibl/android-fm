//
// Created by trondeau on 11/25/15.
//

#include <jni.h>
#include <string>
#include <android/log.h>

#include "pmtjni.h"
#include <pmt/pmt.h>

extern "C" {

JNIEXPORT jbyteArray JNICALL
Java_org_gnuradio_grcontrolport_RPCConnectionThrift_SerializePMTString(JNIEnv* env, jobject thiz, jstring data)
{
    const char *c_data = env->GetStringUTFChars(data, NULL);
    pmt::pmt_t pmt_data = pmt::intern(c_data);
    std::string pmt_str = pmt::serialize_str(pmt_data);
    std::string printable = pmt_str.substr(3);

    //__android_log_print(ANDROID_LOG_DEBUG, "PMT Input String", c_data);
    //__android_log_print(ANDROID_LOG_DEBUG, "PMT JNI", pmt::write_string(pmt_data).c_str());
    //__android_log_print(ANDROID_LOG_DEBUG, "PMT JNI", "Serialized length = %d --> %s", pmt_str.size(), printable.c_str());

    jbyteArray ret = env->NewByteArray(pmt_str.size());
    env->SetByteArrayRegion(ret, 0, pmt_str.size(), (const jbyte*)(pmt_str.c_str()));
    return ret;
}

JNIEXPORT jbyteArray JNICALL
Java_org_gnuradio_grcontrolport_RPCConnectionThrift_SerializeMsgDouble(JNIEnv* env, jobject thiz,
                                                                       jstring cmd_name, double val)
{
    const char *c_cmd_name = env->GetStringUTFChars(cmd_name, NULL);

    //__android_log_print(ANDROID_LOG_DEBUG, "MsgDouble", "Name:  %s", c_cmd_name);
    //__android_log_print(ANDROID_LOG_DEBUG, "MsgDouble", "Value: %f", val);

    pmt::pmt_t pmt_port = pmt::intern(c_cmd_name);
    pmt::pmt_t pmt_val  = pmt::from_double(val);
    pmt::pmt_t pmt_pair = pmt::cons(pmt_port, pmt_val);
    std::string pmt_str = pmt::serialize_str(pmt_pair);

    std::string s_pmt_port = pmt::write_string(pmt_port);
    std::string s_pmt_val  = pmt::write_string(pmt_val);
    std::string s_pmt_pair = pmt::write_string(pmt_pair);
    //__android_log_print(ANDROID_LOG_DEBUG, "MsgDouble", "PMT Name:  %s", s_pmt_port.c_str());
    //__android_log_print(ANDROID_LOG_DEBUG, "MsgDouble", "PMT Value: %s", s_pmt_val.c_str());
    //__android_log_print(ANDROID_LOG_DEBUG, "MsgDouble", "PMT Pair:  %s", s_pmt_pair.c_str());

    jbyteArray ret = env->NewByteArray(pmt_str.size());
    env->SetByteArrayRegion(ret, 0, pmt_str.size(), (const jbyte*)(pmt_str.c_str()));

    return ret;
}

}
