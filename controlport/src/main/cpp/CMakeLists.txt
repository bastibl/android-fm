# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

cmake_minimum_required(VERSION 3.4.1)

add_library(pmtjni
            SHARED
            pmtjni.cpp )

find_library(log-lib
              log )

set(TOOLCHAIN $ENV{HOME}/src/gnuradio-android/toolchain)

target_include_directories(pmtjni PUBLIC
        ${TOOLCHAIN}/${ANDROID_ABI}/include
        ${TOOLCHAIN}/${ANDROID_ABI}/include/boost-1_69)

target_link_libraries(pmtjni
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-runtime.so
        ${TOOLCHAIN}/${ANDROID_ABI}/lib/libgnuradio-pmt.so
        ${log-lib})