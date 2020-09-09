#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ./src -Xlint:none -d ./bin ./src/main/java/chatty/*.java ./src/main/java/chatty/*/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Solution below adapted from: https://apple.stackexchange.com/questions/15318/using-terminal-to-copy-a-file-to-clipboard
cat ./data/chattychatbotstarttemplate.txt | pbcopy
pbpaste > ./data/chattychatbot.txt

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ./bin chatty.ChattyChatBot < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT

# compare the output to the expected output
diff text-ui-test/ACTUAL.TXT text-ui-test/EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
