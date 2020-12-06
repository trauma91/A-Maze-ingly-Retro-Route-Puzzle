package it.subito.puzzle

class Room {
    int id
    String name
    List<Integer> neighbors = new ArrayList<Integer>()
    List<String> objects = new ArrayList<String>()
    boolean visited = false

    Room(Map room) {
        validate(room)
        id = room.id
        name = room.name
        neighbors = getNeighborsAsList(room)
        objects = getObjectsAsList(room.objects)
    }

    /**
     * Compute the adjacency list of neighbors
     * @param Map room
     * @return List of neigthbors
     */
    private List<Integer> getNeighborsAsList(Map room) {
        List<Integer> neighbors = new ArrayList<>()
        if (room.north) neighbors.add(room.north)
        if (room.south) neighbors.add(room.south)
        if (room.west) neighbors.add(room.west)
        if (room.east) neighbors.add(room.east)
        neighbors
    }

    /**
     * Transform object map into a list of objects as string
     * @param Map objects
     * @return List of objects
     */
    private List<String> getObjectsAsList(def objects) {
        List<String> objectList = new ArrayList<>()
        objects.each {
            object ->
                objectList.add(object.name)
        }
        objectList
    }

    /**
     * Basic validation: verify that room contains at least id and name
     * @param room
     */

    private void validate(Map room) {
        String errMsgPfx = "Room::validate - Mondatory field missing: "
        assert room.id : "${errMsgPfx}id"
        assert room.name : "${errMsgPfx}name"
    }
}