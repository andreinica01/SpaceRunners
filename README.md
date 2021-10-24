# SpaceRunners
Here it is a history update of the release versions:

  - 1.0: Game runnable from Eclipse only.
  
  - 1.1: Game runnable from jar and testing new ways to load resources.
  
  - 1.2: Game runnable from jar only with Eclipse terminal.
  
  
# How to generate .jar file:
From Eclipse terminal type:
  - gradlew shadowjar

This will create 2 jar files in build/libs/. Now in order to run the game from the jar file you need to type this in eclipse terminal.
  - java -jar build/libs/SpaceRunners-all.jar

You can move the file and then change the path as many times as you want, the most important thing to remember is that the file is only runnable from Eclipse terminal.

A game made in java made by 4 Unibo Students.
