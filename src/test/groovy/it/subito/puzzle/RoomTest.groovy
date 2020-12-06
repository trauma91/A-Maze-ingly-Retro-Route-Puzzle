package it.subito.puzzle
import org.junit.Test

class RoomTest extends GroovyTestCase {

    @Test
    void testValidateWhenRoomNotValid() {
        Map roomAsMap = [id: 1, objects: [[name : "knife"]]]

        String errorMsg = shouldFail {
            new Room(roomAsMap)
        }
        String expectedErrorMsg = "Room::validate - Mondatory field missing: name. Expression: room.name"
        assertEquals(expectedErrorMsg, errorMsg)
    }

    @Test
    void testValidateWhenRoomValid() {
        Map roomAsMap = [id: 1, name:"name", objects: [[name : "knife"]]]
        Room room = new Room(roomAsMap)
        assertEquals(1, room.id)
        assertEquals("name", room.name)
        assertEquals("knife", room.objects.get(0))
    }

    @Test
    void testGetNeighborsAsListWhenNoNeighbors() {
        Map roomAsMap = [id: 1, name:"name", objects: [[name : "knife"]]]
        Room room = new Room(roomAsMap)

        List neighbors = room.getNeighborsAsList(roomAsMap)
        assertTrue(neighbors.isEmpty())
    }

    @Test
    void testGetNeighborsAsListWhenNeighbors() {
        Map roomAsMap = [id: 1, name:"name", north: 2, south: 3, objects: [[name : "knife"]]]
        Room room = new Room(roomAsMap)

        List neighbors = room.getNeighborsAsList(roomAsMap)
        assertTrue(neighbors.contains(2))
        assertTrue(neighbors.contains(3))
        assertEquals(2, neighbors.size())
    }

    @Test
    void testGetObjectsAsListWhenNoObjects() {
        Map roomAsMap = [id: 1, name:"name", north: 2, south: 3, objects: []]
        Room room = new Room(roomAsMap)

        List objectsAsList = room.getObjectsAsList(roomAsMap.objects)
        assertTrue(objectsAsList instanceof List)
        assertTrue(objectsAsList.isEmpty())
    }

    @Test
    void testGetObjectsAsListWhenObjects() {
        Map roomAsMap = [id: 1, name:"name", north: 2, south: 3, objects: [[name: "Knife" ]]]
        Room room = new Room(roomAsMap)

        List objectsAsList = room.getObjectsAsList(roomAsMap.objects)
        assertTrue(objectsAsList instanceof List)
        assertEquals(1, objectsAsList.size())
        assertEquals("Knife", objectsAsList.get(0))

    }

}
