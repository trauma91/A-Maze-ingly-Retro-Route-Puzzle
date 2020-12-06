#!/bin/sh
# Build the jar before running the application
./scripts/build.sh

# Input checks
# No input provided -> Example1 test case is executed
if [ $# -eq 0 ]
then
  echo "No params provided. Test case will be executed..."
  map="example1"
  starting_room=2
  object_to_collect="Knife, Potted Plant"
  echo "Map: $map"
  echo "Starting room: $starting_room"
  echo "Object to collect: $object_to_collect"
elif [ $# -ne 3 ] #Exactly 3 inputs params expected [map name - initial room - comma separated list of objects]
then
  echo "Following inputs are required: [map name - initial room - comma separated list of objects]"
  echo "Aborted"
  exit
else
  map=$1
  starting_room=$2
  object_to_collect=$3
fi

java -jar ./build/libs/subito-assignment-gradle-1.0-SNAPSHOT.jar $map $starting_room "$object_to_collect"