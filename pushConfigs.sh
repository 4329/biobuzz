#!/bin/bash

adb connect 192.168.43.1:5555


adb push --sync ./config /storage/self/primary/FIRST
