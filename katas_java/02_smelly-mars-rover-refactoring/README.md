Smelly Mars Rover code refactoring
=============================================

Smelly Mars Rover code to practice refactoring.

We'll use it to learn to recognize some code smells
and to fix them applying some useful refactorings.

To check your solution doesn't break the tests at any commit you can use this command executing it at the root of project

````
$ git rebase -i --exec "cd katas_java/02_smelly-mars-rover-refactoring && mvn test" <initial commit to check>
```