package it.subito.utils


static void printResults(List sequence) {
    if (sequence) {
        String whitespaces = "         "
        println String.format("%3s%24s%s%s", "ID", "Room", whitespaces, "Object collected")
        println "----------------------------------------------------------------"

        sequence.each {
            step ->
                def objectCollected
                if (step.objectFound) objectCollected = step.objectFound
                else objectCollected = "None"
                println String.format("%3d%24s%s%s", step.room.id, step.room.name, whitespaces, objectCollected)
        }
    } else {
        println "No solution found for the game, objects are missing!"
    }

}