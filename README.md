## Project Introduction

**Project Name:**  Project Duke

**Chatbot Name:**  Rick

**Project Description:**

This chatbot is inspired by the legendary Duke chatbot, and is aptly named Rick to complement the Morty chatbot introduced in the first lecture of CS2113. This chatbot can be used to keep track of and manage important (and not so important) tasks such as upcoming events and deadlines.

## Installation and Usage

**Prerequisites**
* Java (preferably Java 11 and above)
* A working keyboard

**Initialising Rick**
1. Run the Command Prompt **as Administrator**.
1. Type in `java -jar duke.jar` and press enter. Depending on your computer settings, you might have to include your file path. (e.g. `java -jar C:\Users\User\Documents\CS2113\duke.jar`)
1. Alternatively, Windows users can type `java -jar duke.jar` in a notepad, and save it as a .bat file in the same folder as duke.jar, while Mac users can upgrade to a Windows computer. This allows you to open duke.jar just by clicking on the created Windows batch file.

**Usage**
1. Upon initialisation, Rick will prompt you about all tasks that are due today (based on your system time).
1. If this is your first time using this chatbot, please enter the `help` command to get a detailed list of valid commands and formats.
1. Enter your desired command and press enter. If your command is valid, Rick will carry out the operation and reply with a confirmation that the command has been executed. If your command is invalid, Rick will reply with a prompt explaining why the command was invalid.
1. Add and delete your tasks as required. For events and deadlines, a date has to be specified, while specifying a time is optional. (e.g. `event watch the next episode of Rick and Morty /at 2020-03-03 03:30`)
1. Please enter the `help` command for a full list of all valid commands and formats.
1. Upon termination of the chatbot, Rick will automatically save all tasks in a text file `duke.txt`, and will load these tasks when it is next initialised. Please **do not tamper with the location or contents** of `duke.txt` as this could result in the loss of your stored data.

## Feedback, Bug Reports

* If you have feedback or bug reports, please post at https://github.com/JeremiasLiew/duke/issues.
* We welcome pull requests too.