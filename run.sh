#!/bin/sh
chmod a+x run.sh
# next I'll make sure that all my programs (written in Java) have the proper permissions
cd src
chmod a+x WordCount.java
chmod a+x RunningMedian.java

# finally I'll execute my programs, with the input directory wc_input and output the files in the directory wc_output

javac WordCount.java
java WordCount
javac RunningMedian.java
java RunningMedian ./wc_input ./wc_output/med_result.txt

