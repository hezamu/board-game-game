# Board Game Game

> A weekend project to explore progressive web apps with Scala.js

## Gameplay

Recognize board games from their cover art!

You are presented a name of a board game and 4 graphical tiles with portions of cover art from various board games.
You need to choose which tile is from the game in question.

[Play the game!](https://board-game-game.firebaseapp.com/)

![Screenshot](https://github.com/hezamu/board-game-game/blob/master/public/pics/screenshot.png?raw=true | width=320)

Inspired by [Javascript Guessing Game](https://github.com/samiheikki/javascript-guessing-game) by Sami Suo-Heikki.

## Description

This is a weekend project to explore PWA techniques with Scala.js. Some features:
* Service workers enable playing the game offline. Note that only the cover images that have been cached by your browser are usable in offline mode.
* UI is built in a responsive manner to make the game playable with all device types.
* Polymer [`<iron-icon>`](https://www.webcomponents.org/element/PolymerElements/iron-icon) is used to display the offline indicator. The project includes a small wrapper to enable a typesafe way to use the web component in Scala code.
* The DOM and CSS are built dynamically at runtime using [ScalaTags](http://www.lihaoyi.com/scalatags/), which allows us to write HTML and CSS in a declarative and typesafe manner.

## Adding new board games

  1. Find a good image of the cover for the board game you want to add. The image size should be at least 500x500 pixels.
        2. (OPTIONAL) Add the image to `public/pics` folder. 
  3. Add a new object with the game name, image URL and dimensions to `public/data.json`.
  4. Create a pull request :)

## Build Setup

``` bash
# Start SBT
$ sbt

# Start a loop that builds the project whenever sources change 
sbt> ~fastOptJS
```

You also need to serve the project directory over HTTP to access it with your browser. Eg. with Python:
```
$ python -c "import BaseHTTPServer as bhs, SimpleHTTPServer as shs; bhs.HTTPServer((\"127.0.0.1\", 8888), shs.SimpleHTTPRequestHandler).serve_forever()"
```
After this the UI is accessible at [http://localhost:8888](http://localhost:8888)
