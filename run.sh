#!/usr/bin/env bash

javac src/MainProc.java
java -cp src MainProc ./tweet_input/tweets.txt ./tweet_output/ft1.txt ./tweet_output/ft2.txt
