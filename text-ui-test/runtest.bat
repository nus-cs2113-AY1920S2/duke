@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\Duke.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\commands\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\constants\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\exceptions\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\parser\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\storage\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\taskList\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\taskManager\*.java
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\ui\*.java



IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM java -classpath ..\bin Duke < input.txt > ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input.txt > ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input.txt > EXPECTED.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT