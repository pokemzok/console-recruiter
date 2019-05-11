package pokemzok.consolerecruiter.domain

import com.fasterxml.jackson.databind.ObjectMapper

class JsonObject {

    private final static ObjectMapper objectMapper = new ObjectMapper()
    private final Object jsonObject

    JsonObject(Object jsonObject) {
        this.jsonObject = jsonObject
    }

    String toString() {
        return objectMapper.writeValueAsString(jsonObject)
    }
}
