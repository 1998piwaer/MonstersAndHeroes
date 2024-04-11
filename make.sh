#!/bin/bash

# Compile all .java files
javac *.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    # Run the Main class
    java Main
    # Delete all .class files
    rm *.class
else
    echo "Compilation failed."
fi