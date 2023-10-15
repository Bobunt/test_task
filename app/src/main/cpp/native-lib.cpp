#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_1task_fragments_MainViewModel_stringFromJNI(
        JNIEnv* env,
        jobject /* this */, jstring newString, jstring text) {

    jclass cls_StringBuilder = env->FindClass("java/lang/StringBuilder");
    jmethodID ctr_StringBuilder = env->GetMethodID(cls_StringBuilder, "<init>", "(I)V");
    jobject stringBuilder = env->NewObject(cls_StringBuilder, ctr_StringBuilder, 100);

    jmethodID mid_StringBuilder_append = env->GetMethodID(cls_StringBuilder, "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
    for (auto str: {  text, newString }) {
        env->CallObjectMethod(stringBuilder, mid_StringBuilder_append, str);
    }
    jmethodID mid_StringBuilder_toString = env->GetMethodID(cls_StringBuilder, "toString", "()Ljava/lang/String;");
    jstring final = (jstring) env->CallObjectMethod(stringBuilder, mid_StringBuilder_toString);
    return final;
}