#!/bin/bash

cd app-service/
gradle wrapper
gradle clean build

cd ../song-service
gradle wrapper
gradle clean build

cd ../playlist-service
gradle wrapper
gradle clean build

cd ../app-service/ && gradle run >app.out &
cd ../song-service/ && gradle run >song.out &
cd ../playlist-service/ && gradle run >playlist.out &
