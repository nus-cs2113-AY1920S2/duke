#Welcome to Shannon's Duke
**Is this your first time here?**

Shannon's Duke is Shannon's implementation of Duke and it is an online to-do-list tracker.

In your to-do-list, you can categorise your tasks into these 3 types of tasks into your list:
* Todo `[T]` : These are tasks that are to be done, no deadlines, just at your own pace and at your own comfort. No pressure, just do it!
* Deadline `[D]` : These are tasks that have a deadline and must be done before a certain deadline.
* Event `[E]` : These are events that you need to be at and not just tasks to achieve.

These tasks added into the list can also be marked as done when completed. They are all automatically unmarked when first added into the list
* Unmarked Todo Task - `[T][0] do laundry`
* Marked Event Task - `[E][1] attend seminar (at: school hall)`

Here are some instructions and introductions to familiarise yourself with using this program.

##Instructions
**NOTE:** All commands are case-sensitive, but task descriptions are not case-sensitive.
####Adding Tasks
#####Adding a Todo task: `todo`
Adds a Todo task into your list

**Format:** `todo <DESCRIPTION>`

*Examples:*
* `todo buy lunch`
* `todo finish homework`

#####Adding a Deadline Task: `deadline`
Adds a Deadline task into your list.

**Format:** `deadline <DESCRIPTION> /by <DEADLINE OF TASK>`

*Examples:*
* `deadline submit proposal /by Wednesday 3PM`
* `deadline make reservations at restaurant /by tonight 7PM`

#####Adding an Event Task: `event`
Adds an Event task into your list

**Format:** `event <DESCRIPTION> /at <VENUE/DATE/TIME OF TASK>`

*Examples:*
* `event Secondary School Reunion /at Concorde Hotel, 14th Mar, 7PM`
* `event Mom's Birthday Celebrations /at Home, 28th Sept, 6PM`

####Removing Tasks: `remove`
Removes task at the stated index

**Format:** `remove <INDEX>`

*Example:*
* `remove 2` - Removes Task at index 2 of the list

####Marking Tasks as Done: `done`
Marks task at the stated index as done

**Format:** `done <INDEX>`

*Example:*
* `done 6` - Marks task 6 in list as done.

Returns the task to be `[1]` rather than `[0]`

####Listing All Tasks: `list`
Shows all the tasks inside the list, indexed from the tasks that are added first to the the latest entry.

**Format:** `list`

####Finding Tasks: `find`
Finds the tasks with containing the keywords
The command is not case-sensitive.

**Format:** `find <KEYWORD>`

*Example:*
* `find work` - finds tasks containing the keyword 'work'

Returns `homework` and `Work from Home` (assuming these are tasks inside the list)

####Getting Commands for List: `help`
Returns a list of all the commands and a description of their usage

**Format:** `help
`
####Exiting the Program: `bye`
**Format:** `bye`

####Saving the Data
Task list data are saved in the hard disk automatically after the program is exited.

There is no need to save manually.



## Setting up

**Prerequisites**

* JDK 11
* Recommended: IntelliJ IDE
* Fork this repo to your GitHub account and clone the fork to your computer

**Importing the project into IntelliJ**

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
1. Set up the correct JDK version.
   * Click `Configure` > `Structure for new Projects` (in older versions of Intellij:`Configure` > `Project Defaults` > `Project Structure`).
   * If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11.
   * Click `OK`.
1. Click `Import Project`.
1. Locate the project directory and click `OK`.
1. Select `Create project from existing sources` and click `Next`.
1. Rename the project if you want. Click `Next`.
1. Ensure that your src folder is checked. Keep clicking `Next`.
1. Click `Finish`.

# Tutorials 

Duke Increment | Tutorial
---------------|---------------
`A-Gradle` | [Gradle Tutorial](tutorials/gradleTutorial.md)
`A-TextUiTesting` | [Text UI Testing Tutorial](tutorials/textUiTestingTutorial.md)
`Level-10` | JavaFX tutorials:<br>→ [Part 1: Introduction to JavaFX][fx1]<br>→ [Part 2: Creating a GUI for Duke][fx2]<br>→ [Part 3: Interacting with the user][fx3]<br>→ [Part 4: Introduction to FXML][fx4]

[fx1]: <tutorials/javaFxTutorialPart1.md>
[fx2]: <tutorials/javaFxTutorialPart2.md>
[fx3]: <tutorials/javaFxTutorialPart3.md>
[fx4]: <tutorials/javaFxTutorialPart4.md>

# Feedback, Bug Reports

* If you have feedback or bug reports, please post in [se-edu/duke issue tracker](https://github.com/se-edu/duke/issues).
* We welcome pull requests too.