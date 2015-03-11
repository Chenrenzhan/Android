LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := GrayProcess
LOCAL_SRC_FILES := GrayProcess.cpp

include $(BUILD_SHARED_LIBRARY)
include $(CLEAR_VARS)

# OpenCV
OPENCV_CAMERA_MODULES:=on
OPENCV_INSTALL_MODULES:=on
include F:\openCV\OpenCV-2.4.8-android-sdk\sdk\native\jni\Opencv.mk