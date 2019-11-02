# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

cmake_minimum_required(VERSION 3.4.1)

add_library(native-lib
            SHARED
            native-lib.cpp )

find_library(log-lib log)

set(TOOLCHAIN $ENV{HOME}/src/gnuradio-android/toolchain)

target_include_directories(native-lib PUBLIC
        ${TOOLCHAIN}/${ANDROID_ABI}/include
        ${TOOLCHAIN}/${ANDROID_ABI}/include/boost-1_69)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries(native-lib
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-runtime.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-pmt.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-blocks.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libhackrf.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libusb1.0.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-osmosdr.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-grand.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-analog.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-filter.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-fft.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-uhd.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libuhd.so
        ${log-lib} )