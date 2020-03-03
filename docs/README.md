# Introduction

Duke is a command-line interface task manager. You can use Duke to manage a list of to-do items, upcoming deadlines, and upcoming events. The list is saved upon exit and will be loaded upon the next session of Duke.

# Features

This section describes the features that Duke supports.

<b>NOTE:</b>
* Commands have to be the first word of the user input and in lower case
* Words in ```UPPER_CASE``` are the parameters to be supplied by the user 


## 1. Adding New Tasks

New tasks can be added by typing the command relevant to the type of task that is to be added (i.e. ```todo```,``` event``` or ```deadline```).

#### 1.1 Adding New To-Dos: ```todo```
- Adds a new to-do item to the task list.
- Format: ```todo NAME_OF_TASK```
- Examples: 
   - ```todo Math Online Quiz```
   - ```todo CS2113 Homework```
- Expected Output:
```
todo CS2113 Homework
____________________________________________________________
Got it. I've added this task:
[T][✘] CS2113 Homework
Now you have 2 tasks in the list.
____________________________________________________________
```

#### 1.2 Adding New Events: ```event```
- Adds a new event to the task list.
- Format: ```event NAME_OF_EVENT /at DATE_OF_EVENT```
- Examples: 
   - ```event Career Fair /at 26-02-2020```
   - ```event Family Gathering /at 26 Feb```
   - ```event Computing Convention /at Next Month```
- Expected Output:
```
event Career Fair /at 26-02-2020
____________________________________________________________
Got it. I've added this task:
[E][✘] Career Fair (at: 26-02-2020)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### 1.3 Adding New Deadlines: ```deadline```
- Adds a new deadline to the task list.
- Format: ```deadline NAME_OF_DEADLINE /by DATE_OF_DEADLINE```
- Examples: 
   - ```deadline Math Online Quiz /by 26-02-2020```
   - ```deadline English Essay /by 26 Feb```
   - ```deadline CS2113 Homework /by Tomorrow```
- Expected Output:
```
deadline English Essay /by 26 Feb
____________________________________________________________
Got it. I've added this task:
[D][✘] English Essay (by: 26 Feb)
Now you have 4 tasks in the list.
____________________________________________________________
```

## 2. Listing All Tasks: ```list```
- Lists all task in the list.
- Format: ```list```
- Expected Output:
```
list
1.[T][✓] CS2113 Homework
2.[E][✘] Career Fair (at: 26-02-2020)
3.[D][✘] English Essay (by: 26 Feb)
```

## 3. Finding Tasks by Keyword: ```find```
- Lists all task in the list that contains the specified keyword.
- Format: ```find KEYWORD```
- Examples:
   - ```find Math```
   - ```find CS2113```
   - ```find CS```

- Expected Output:
```
find CS
____________________________________________________________
Here are the matching tasks in your list:
1.[D][✘] CS2105 Assignment (by: next week)
2.[T][✘] CS2113 Assignment
____________________________________________________________
```

## 4.	Marking Tasks as Done: ```done```
- Marks specified tasks as done. 
- Specified index of the task will be its index of the entire task list by default, unless the ```list``` or ```find``` command has been called. In which case, it will be the index of the item as shown in the last shown list.
- Format: ```done INDEX```
- Examples:
   - ```done 1```
   - ```done 2```
- Expected Output:
```
done 1
Nice! I've marked this task as done: 
[✓] CS2113 Homework
```

## 5.	Deleting Tasks: ```delete```
- Delete specified tasks from the list. 
- Specified index of the task will be its index of the entire task list by default, unless the ```list``` or ```find``` command has been called. In which case, it will be the index of the item as shown in the last shown list.
- Format: ```done INDEX```
- Examples:
   - ```delete 1```
   - ```delete 2```
- Expected Output:

```
delete 1
____________________________________________________________
Noted. I've removed this task:
[T][✓] CS2113 Homework
Now you have 3 tasks in the list.
____________________________________________________________
```

## 6. Loading Tasks from File
- Loading of tasks will be done automatically upon starting the application when there is a saved file present in the same directory of the Duke application. The file has the name ```data.txt```.

## 7. Saving Tasks: ```bye```
- Saving the state of the task list is done automatically when the program is exited using the ```bye``` command.
- Format: ```bye```
- Expected Output:

```
bye
Bye. Hope to see you again soon!
```
