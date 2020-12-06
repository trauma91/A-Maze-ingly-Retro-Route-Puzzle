package it.subito.puzzle

import it.subito.utils.MapLoader
import it.subito.utils.Printer

class GameMap {
    Map<Integer, Room> rooms = new HashMap<>()
    List<Map> solution = new ArrayList<>()

    /**
     * Instantiate a gameMap from Map
     * @param map
     */
    GameMap(Map map) {
        validate(map)
        map.rooms.each {
            room ->
                Room roomObj = new Room(room)
                this.rooms.put(room.id, roomObj)
        }
    }

    /**
     * Instantiate a game map loading json data from file
     * @param jsonPath: path/to/jsonFile representing the map
     */
    GameMap(String jsonPath) {
        Map map = MapLoader.getJsonFromFile(jsonPath)
        validate(map)
        map.rooms.each {
            room ->
                Room roomObj = new Room(room)
                this.rooms.put(room.id, roomObj)
        }
    }

    /**
     * Basic validation: checks that a map contains at least a room.
     * @param gameMap
     */
    void validate(Map gameMap) {
        assert (gameMap.rooms.size() > 0) : "GameMap::validate - No rooms found into the game map."
    }

    /**
     * dfs recursive implementation for graph.
     * It terminates when all objects are found and/or all rooms are visited
     * @param root: starting room (starting graph node)
     * @param objToSearch: list of object to be searched
     * @return list of rooms visited with object found each
     */
    List dfs(Integer root, List objToSearch) {
        if (!objToSearch) return
        Room room = rooms.get(root)
        room.visited = true
        List<String> objFound = room.objects.intersect(objToSearch)
        if (objFound) {
            objToSearch.removeAll(objFound)
        }
        solution.add(["room": room, objectFound : objFound])
        room.neighbors.each {
            neighbor ->
                if(!rooms.get(neighbor).visited && objToSearch) {
                    dfs(neighbor, objToSearch)
                    if (!objToSearch) return
                    solution.add(["room": room, "objectFound" : objFound])
                }
        }
        if (objToSearch) return null
        solution
    }

    static void main(String[] args) {
        //Checks inputs: 3 needs to be provided
        assert (args.size() == 3) : "Following inputs are required: [map name - initial room - comma separated list of objects]"
        String mapDir = "./src/main/resources/"
        //Load input map from file
        String mapPath = "${mapDir}${args[0]}.json"
        GameMap gameMap = new GameMap(mapPath)
        List<String> objToFind = args[2].split(',')
        List solution = gameMap.dfs(args[1].toInteger(), objToFind*.trim())
        //Print solution
        Printer.printResults(solution)
    }
}