package it.subito.puzzle

import it.subito.utils.MapLoader
import org.junit.Test

class GameMapTest extends GroovyTestCase {

    @Test
    void testValidateWhenGameMapNotValid() {
        Map invalidMap = MapLoader.getJsonFromFile("./src/test/resources/invalidMap.json")
        String errorMsg = shouldFail {
            new GameMap(invalidMap)
        }
        String expectedErrorMsg = "GameMap::validate - No rooms found into the game map.. Expression: (gameMap.rooms.size() > 0)"
        assertEquals(expectedErrorMsg, errorMsg)
    }

    @Test(expected = Test.None.class)
    void testValidateWhenGameMapValid() {
        Map validMap = MapLoader.getJsonFromFile("./src/main/resources/example1.json")
        GameMap gameMap = new GameMap(validMap)
        assertFalse(gameMap.rooms.isEmpty())
    }

    @Test
    void testDfs() {
        GameMap gameMap = new GameMap("./src/test/resources/testMap.json")
        List<String> objToFind = new ArrayList<>()
        objToFind.add("Knife")
        List results = gameMap.dfs(1, objToFind)
        assertEquals(2, results.size())
        assertEquals(2, results[1].room.id)
        assertEquals("Dining Room", results[1].room.name)
        assertTrue(results[1].objectFound.contains("Knife"))
    }
}
