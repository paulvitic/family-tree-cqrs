CQRS Example 
============

## How to build

You need Java 8 and Gradle 2.x

Execute gradle build command:

```
> gradle clean build
```

Built package **familytree.jar** will be available at *[source dirctory]/build/libs*


## How to run

Run project by

```
> java -jar familytree.jar
```

### Sample dialog


```
Welcome to family tree. Current tree can be viewed at ./view/tree.html
Enter input file location (default ./input/input.txt): 
Minerva Dominique 
Exit (y/n): n
Enter input file location (default ./input/input.txt): ../../src/test/resources/input/input1.txt
Molly Lucy 
Ronald Charlie Bill 
Exit (y/n): y
Final tree can be viewed at ./view/tree.html. Bye!

```

### Input file

An empty default input file will be generated at *[binary directory]/input/input.txt*. 
You can choose to edit this file for input or provide another input file at command prompt.

### Database

The is stateful. It uses a H2 file database under *[binary directory]/db/* directory.

### View

The projects generates an html view file that uses a [D3.js based tree view library](https://github.com/ErikGartner/dTree). 
The file can be found at *[binary directory]/view/tree.html*

### Logs

Logs can be found at *[binary directory]/log/log.txt*
