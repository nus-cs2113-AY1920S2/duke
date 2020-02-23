@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\misc\Messages.java
..\src\main\java\command\*.java ..\src\main\java\duke\Duke.java
..\src\main\java\duke\task\*.java ..\src\main\java\parser\Parser.java
..\src\main\java\storage\*.java ..\src\main\java\ui\Ui.java
..\src\main\java\Main.java ..\storage.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Main < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM delete storage.txt file after checking for differences. This is to prevent side effect of duke reading from an existing storage file which affects the expected output (based on no storage file existing at first).
del storage.txt