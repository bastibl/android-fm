# GNU Radio Android FM Receiver

This is a simple demo application for GNU Radio on Android, implementing an FM receiver that works with the RTL-SDR and the HackRF. 

## Installation

Building the app requires the [GNU Radio Android toolchain](https://github.com/bastibl/gnuradio-android/). Please see this repository for further instructions on how to build the toolchain.

## Running the App

The USB vender/product IDs and the device type (HackRF or RTL-SDR) are currently hardcoded. Please adapt `native_lib.cpp` and `MainActivity.kt` accordingly.

## YouTube Video

[![FM Receiver](https://img.youtube.com/vi/8ReyVzUyppA/0.jpg)](https://www.youtube.com/watch?v=8ReyVzUyppA)
