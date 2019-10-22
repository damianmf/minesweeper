# minesweeper

### Repo

[Minesweeper-deviget-challeng](https://github.com/damianmf/minesweeper)

### Tech stack

Backend
  * Java
  * Spring boot
  * Spring Data
  * Mysql
  
Frontend
  * Javascript
  * Angular 7
  * Angular-cli
  
This techs were selected due to my experience and try to resolve as much as possible of the challenge.
Probable with more time i would have do it with node, and a no sql db.

### Features

* Design and implement a documented RESTful API for the game (think of a mobile app for your API)
Implemented on java, documented with javadoc and swagger, the api development was evolving when more features were involved.

Started creating a basic game with two http operations, create board (POST) and reveal a cell (PATCH). With this operations
and a client to interact with the api a first version could be delivered.
Api becames bigger and fetch games was implemented in order to list plays and load a not finished game.
Config initial properties for the board  

* Implement an API client library for the API designed above. Ideally, in a different language, of your preference, to the one used for the API
Done on Angular 7, due to fast set up of a project and previous experience. (I would not use it if i had more time to develop it)

* When a cell with no adjacent mines is revealed, all adjacent squares will be revealed (and repeat)
Resolve on backend to avoid many api interactions between the front and back, taking into account, the less api calls the better for a mobile interaction
Should check dtos in order to provide just the need information to reduce network transfer

* Ability to 'flag' a cell with a question mark or red flag [PENDING]
* Detect when game is over

If a mine is found, the game mentions you lose, it is not consistence with the game status and the possibility of keep playing after find a mine, also counts how many cell you need to reveal to win the game and it tells you when this happend 

*Persistence

Game model is persisted in a mysql (i would probably choose another db in another context)

*Time tracking [PENDING]
* Ability to start a new game and preserve/resume the old ones

Client shows started game, it allow to pause and resume. It does not keep status and validation of buttons 

*Ability to select the game parameters: number of rows, columns, and mines

Done in an easy way with three different selects of rows, columns and mines.

*Ability to support multiple users/accounts [PENDING]

### Tests
Backend [PENDING]
Frontend [PENDING]

### Usage
Brief of how to start the project...

Please address any questions and comments to [dami.fontenla@gmail.com](dami.fontenla@gmail.com).
