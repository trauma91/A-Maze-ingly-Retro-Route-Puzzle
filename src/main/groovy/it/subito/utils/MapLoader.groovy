package it.subito.utils

import groovy.json.JsonSlurper

static Map getJsonFromFile(String path) {
    return new JsonSlurper().parse(new File(path))
}