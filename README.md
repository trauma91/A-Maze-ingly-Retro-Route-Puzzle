# A-Maze-ingly Retro Route Puzzle

This program output a valid route one person can follow to collect all specified items within a map. 
The map is a json description of a set of rooms with allowed path and contained object. 
The user should provide a valid map file, a valid starting room and the list of objects to collect.

## Assumptions

The solution provided implies some assumptions:
- A path is valid if and only if it can be simulated by a human: no jumps among non-adiacent rooms are allowed.
- Objects contained by each room are unknown until the room is visited.

## Requirements

- **Docker** - the project will be built and executed within a docker container.

**No other dependencies required.**

## Run

The project can be built and run within a docker container.

As a first step, the docker image should be built.

_This command should be run from the repository root._
```
docker build -t test .
```

The project contains 3 scripts in order to build, execute unit tests and run the application.

#### Build
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt test ./scripts/build.sh
```

#### Test
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt test ./scripts/tests.sh
```

#### Run
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt test ./scripts/run.sh <map_to_play> <starting_room> <objects_to_collect>
```
**Example**
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt test ./scripts/run.sh example2 4 "Knife, Potted Plant, Pillow"
```
In case no parameters are provided to the run.sh script, a default test case is executed:
- Map: example1
- Starting room: 2
- Object to collect: "Knife, Potted Plant"

## New test cases
Sample maps are contained into the _./src/main/resources/_ folder.

If you want to test the application with new maps, please add them into the map folder and launch the run script specifying the new map file name (without extension) as first parameter.