# Duke User Guide

## Content
1. Introduction
2. Quick Start
3. Usage
<br>3.1  Viewing help: `help`
<br>3.2  Listing all tasks: `list`
<br>3.3. Adding a todo task: `todo`
<br>3.4. Adding a deadline task: `deadline`
<br>3.5. Adding an event task: `event`
<br>3.6. Marking a task as completed: `done`
<br>3.7. Deleting a task: `delete`
<br>3.8. Finding a keyword: `find`
<br>3.9. Exiting the program: `bye`
4. Command Summary
 
## 1. Introduction
Duke is a Command Line Interface (CLI) bot that helps you to store and manage your tasks with ease. Using simple keyboard commands, you can list all tasks, add and delete tasks, mark tasks as completed and find tasks with keywords.

## 2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar jar file.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open the command prompt on your Computer. Navigate to your Duke home folder.
5. Enter "java -jar Duke.jar" to run the jar file.
6. Type the command in the command box and press Enter to execute it.
7. Here are some example commands you can try:
<br>`list` : lists all tasks
<br>`todo shopping` : adds a todo task named shopping to the task list.
<br>`delete 3` : deletes the 3rd task shown in the current list
<br>`exit` : exits the app. 
8. Refer to Section 3, “Usage” for details of each command.

## 3. Usage
Command Format:  
Items in square brackets are the parameters to be supplied by the user. For example, in `todo [task name]`,  `[task name]` can be replaced with "exercise" in `todo exercise`.
### 3.1. Viewing help: `help`
Format:
<pre>
help
</pre>
Expected outcome:
```
____________________________________________________________
 You can try the following commands:
     list
     todo <task name>
     deadline <task name> /by <date>
     event <task name> /at <location>
     done <task number>
     find <word>
     delete <task number>
     bye
____________________________________________________________
```
### 3.2. Listing all tasks: `list`
Format:
<pre>
list
</pre>
Expected outcome (example):
```
____________________________________________________________
 Here are the tasks in your list:
 1. [T][✘] task1
 2. [T][✓] task2
 3. [T][✘] task3
____________________________________________________________
```
### 3.3. Adding a todo task: `todo`

Format:
<pre>
todo [task name]
</pre>

Explanation: 

`[task name]` is the name of the todo task.

Example of usage: 
<pre>
todo buy groceries
</pre>

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [T][✘] buy groceries
 Now you have 1 tasks in the list.
____________________________________________________________
```
### 3.4. Adding a deadline task: `deadline`

Format:
<pre>
deadline [task name] /by [date]
</pre>

Explanation: 

`[task name]` is the name of the deadline task. `[date]` is the due date of the deadline task.


Example of usage: 
<pre>
deadline pay bills /by 2/4/2020
</pre>

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [D][✘] pay bills (by: 2/4/2020)
 Now you have 4 tasks in the list.
____________________________________________________________
```
### 3.5. Adding an event task: `event`

Format:
<pre>
event [task name]​ /at [location]
</pre>

Explanation: 

`[task name]` is the name of the event task. `[location]` is the location of the event task.

Example of usage: 
<pre>
event party /at Ben’s house
</pre>

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [E][✘] party (at: Ben’s house)
 Now you have 5 tasks in the list.
____________________________________________________________
```
### 3.6. Marking a task as completed: `done`

Format:
<pre>
done [task number]
</pre>

Explanation: 

`[task number]` is the nth position in the task list of the task to be marked as completed.
Example of usage: 

To mark the 2nd task in the task list as completed
<pre>
done 2
</pre>

Expected outcome:


```
list
____________________________________________________________
 Here are the tasks in your list:
 1. [T][✘] task1
 2. [T][✘] task2
 3. [T][✘] task3
____________________________________________________________
```


```
done 2
____________________________________________________________
 Nice! I've marked this task as done:
    [T][✓] task2
____________________________________________________________
```
### 3.7. Deleting a task: `delete`

Format:
<pre>
delete [task number]
</pre>

Explanation: 

`[task number]` is the nth position in the task list of the task to be deleted.

Example of usage: 

To delete the 3rd task in the task list
<pre>
delete 3
</pre>

Expected outcome:


```
list
____________________________________________________________
 Here are the tasks in your list:
 1. [T][✘] task1
 2. [T][✘] task2
 3. [T][✘] task3
____________________________________________________________
```

```
delete 3
____________________________________________________________
 Noted. I've removed this task:
    [T][✘] task3
 Now you have 2 tasks in the list.
____________________________________________________________
```
### 3.8. Finding a keyword: `find`

Format:
<pre>
find [keyword]
</pre>

Explanation: 

`find [keyword]` lists all tasks that contain the word or phrase `[keyword]`.

Example of usage: 

To list tasks with the word “meet”
<pre>
find meet
</pre>

Expected outcome:

```
list
____________________________________________________________
 Here are the tasks in your list:
 1. [E][✘] board meeting (at: office)
 2. [T][✘] homework
 3. [T][✘] meet Amy for lunch
____________________________________________________________
```

```
find meet
____________________________________________________________
 Here are the matching tasks in your list:
 1. [E][✘] board meeting (at: office)
 2. [T][✘] meet Amy for lunch
____________________________________________________________
```
### 3.9. Exiting the program: `bye`
Format:
<pre>
bye
</pre>
Expected outcome:
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## 4. Command Summary
* Viewing help: `help`

* Listing all tasks: `list`

* Adding a todo task: `todo <task name>`
<br>Eg. `todo exercise`

* Adding a deadline task: `deadline <task name> /by <date>`
<br>Eg. `deadline pay bills /by 2/4/2020`

* Adding an event task: `event <task name> /at <location>`
<br>Eg. `event party /at Ben’s house`

* Marking a task as completed: `done <task number>`
<br>Eg. `done 2`

* Deleting a task : `delete <task number>`
<br>Eg. `delete 2`

* Finding a keyword: `find <keyword>​`
<br>Eg. `find meet`

* Exiting the program : `bye`
