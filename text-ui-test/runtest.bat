@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\data\Duke.java ..\src\main\java\data\Deadline.java ..\src\main\java\data\Event.java ..\src\main\java\data\Todo.java ..\src\main\java\data\Task.java ..\src\main\java\data\IllegalKeywordException.java ..\src\main\java\data\NoDescriptionException.java ..\src\main\java\data\NoRemarkException.java ..\src\main\java\data\NumberFieldException.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF8 -classpath ..\bin data.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

pause