# rock-paper-scissors Rest API

Spring Boot Application that provides an HTTP Rest API to play Rock,Paper,Scissor against the Computer. 

Add the Header : Content-Type = application/json

Application runs in port 8080 by default.

The file resources/ROCK-PAPER-SCISSOR.postman_collection.json has basic examples that you can import in POSTMAN to run.

# Create a Game

POST http://localhost:8080/api/game

Request Body Example
```json
{
	  "playerOneName" : "Juan" 
}
```

Response Example

```json
{
    "id": 1,
    "scorePlayerOne": 0,
    "scorePlayerTwo": 0,
    "playerOneName": "Juan",
    "playerTwoName": "Computer",
    "gameStatus": "CREATED",
    "moves": []
}
```

# Get a Game
GET http://localhost:8080/api/game/{gameId}

Request Example: http://localhost:8080/api/game/1


Response Example
```json
{
    "id": 1,
    "scorePlayerOne": 2,
    "scorePlayerTwo": 3,
    "playerOneName": "Juan",
    "playerTwoName": "Computer",
    "gameStatus": "FINISHED",
    "moves": [
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "ROCK",
            "moveResult": "DRAW"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "PAPER",
            "moveResult": "LOOSE"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "SCISSOR",
            "moveResult": "WIN"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "ROCK",
            "moveResult": "DRAW"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "SCISSOR",
            "moveResult": "WIN"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "PAPER",
            "moveResult": "LOOSE"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "ROCK",
            "moveResult": "DRAW"
        },
        {
            "playerOneMove": "ROCK",
            "playerTwoMove": "PAPER",
            "moveResult": "LOOSE"
        }
    ]
}
```

# Play a Game
PUT http://localhost:8080/api/game/{gameId}/play
Example : http://localhost:8080/api/game/1/play


Request Body Example
```json
"ROCK"
```

Response Example
```json
{
    "playerOneMove": "ROCK",
    "playerTwoMove": "PAPER",
    "moveResult": "LOOSE"
}
```
