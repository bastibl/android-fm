# GNU Radio Android FM Receiver

This is a simple demo application for GNU Radio on Android, implementing an FM receiver that works with the RTL-SDR and the HackRF. 

## Installation

Building the app requires the [GNU Radio Android toolchain](https://github.com/bastibl/gnuradio-android/). Please see this repository for further instructions on how to build the toolchain.

## Running the App

The USB vender/product IDs and the device type (HackRF or RTL-SDR) are currently hardcoded. Please adapt `native_lib.cpp` and `MainActivity.kt` accordingly.

## YouTube Video

[![FM Receiver](https://img.youtube.com/vi/8ReyVzUyppA/0.jpg)](https://www.youtube.com/watch?v=8ReyVzUyppA)

## Publication

If you use this project, we would appreciate a reference to:

<ul>
<li>
<a href="http://dx.doi.org/10.1145/3411276.3412184"><img src="https://www.bastibl.net/bib/icons/ACM-logo.gif" title="ACM" alt=""></a> <a class="bibauthorlink" href="https://www.bastibl.net/">Bastian Bloessl</a>, Lars Baumgärtner and Matthias Hollick, “<strong>Hardware-Accelerated Real-Time Stream Data Processing on Android with GNU Radio</strong>,” Proceedings of 14th International Workshop on Wireless Network Testbeds, Experimental evaluation &amp; Characterization (WiNTECH’20), London, UK, September 2020.
 <small>[<a href="http://dx.doi.org/10.1145/3411276.3412184">DOI</a>, <a href="https://www.bastibl.net/bib/bloessl2020hardware/bloessl2020hardware.bib">BibTeX</a>, <a href="https://www.bastibl.net/bib/bloessl2020hardware/">PDF and Details…</a>]</small></p>
</li>
</ul>
