#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_cambricon_productdisplay_activity_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_cambricon_productdisplay_caffenative_CaffeDetection_getBitmap(JNIEnv *env,
                                                                       jobject instance) {

    // TODO

}