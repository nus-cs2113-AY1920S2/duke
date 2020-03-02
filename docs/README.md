# User Guide
## About Duke
Duke is a personal assistant chat bot with a **command line interface** to manage all your upcoming tasks


## Quick Guide
1. Ensure you have `Java 11` or above installed in your computer.
1. Download the latest `Duke.jar` [here](https://github.com/benchan911/duke/releases).
1. Store `Duke.jar` at the folder you intend to use as your home folder.
1. Navigate to the folder in your Command Prompt (Windows) or Terminal (MacOS & Linux).
1. Type `java -jar Duke.jar` to start the app.

Refer to the next section [Features](#features) for details of each command.


## Features 
- [Create a todo](#feature-1-create-new-todo-todo)
- [Create a deadline](#feature-2-create-new-deadline-deadline)
- [Create an event](#feature-3-create-new-event-event)
- [List all tasks](#feature-4-list-all-tasks-list)
- [Mark task](#feature-5-mark-existing-tasks-mark)
- [Delete task](#feature-6-delete-existing-tasks-delete)
- [Find task](#feature-7-find-existing-tasks-find)
- [Exit Duke](#feature-8-exit-the-program-exit)
- [Saving](#feature-9-saving-information)

### Feature 1. Create new Todo: `Todo`
Adds a new Todo to the TaskList

#### Syntax: todo DESCRIPTION

#### Example of usage:

    todo buy lunch
    
#### Expected outcome:

        1. [T][N] buy lunch
    
### Feature 2. Create new Deadline: `Deadline`
Adds a new deadline to the TaskList

#### Syntax: deadline DESCRIPTION /by DATE

#### Example of usage:

    deadline complete CS1010 Assignment \by 10-03-2022
    
#### Expected outcome:

        2. [D][N] complete CS1010 Assignment ( by: 10-03-2022 ) 

### Feature 3. Create new Event: `Event` 
Adds a new event to the TaskList.
                               
#### Syntax: event DESCRIPTION /at DATE                        
                               
#### Example of usage:

    event attend cousin wedding \at 01-04-2022

#### Expected outcome:

        3. [E][N] attend cousin wedding  ( at: 01-04-2022 ) 
        
### Feature 4. List all tasks: `List`
List out all the existing tasks in the TaskList

#### Syntax: List

#### If there are existing tasks

#### Example of usage:

    list

#### Expected outcome:

        1. [T][N] buy lunch
        2. [D][N] complete CS1010 Assignment ( by: 10-03-2022 ) 
        3. [E][N] attend cousin wedding  ( at: 01-04-2022 ) 
        Now you have 3 tasks in the list.
    
    
#### If there are no existing tasks

##### Example of usage:

    list

##### Expected outcome:

        Empty!

### Feature 5. Mark existing tasks: `Mark`
Marks an existing task in the TaskList

#### Syntax: mark INDEX

#### If there are existing tasks

##### Example of usage:

    mark 1

##### Expected outcome:

        You have marked -- [T][Y] buy lunch

#### If there are no existing tasks

##### Example of usage:

    mark 1

##### Expected outcome:

        Out of Bound!
    
### Feature 6. Delete existing tasks: `Delete`
Deletes an existing task in the TaskList

#### Syntax: delete INDEX

#### If there are existing tasks

##### Example of usage:

    delete 1

##### Expected outcome:

        You have deleted -- [T][Y] buy lunch

#### If there are no existing tasks

##### Example of usage:

    delete 1

##### Expected outcome:

        Out of Bound!
    
### Feature 7. Find existing tasks: `Find`
Find an item using a keyword in the TaskList

#### Syntax: find KEYWORD

#### If there are existing tasks

##### Example of usage:

    find lunch

##### Expected outcome:

        1. [T][N]buy lunch
        There are 1 items found.

#### If there are no existing tasks

##### Example of usage:

        find boy
        
##### Expected outcome:

        Item not found!!!


### Feature 8. Exit the program: `Exit`
Exits the program

#### Syntax: bye

#### Example of usage:

    bye

#### Expected outcome:

        Bye. Hope to see you again soon!
        
### Feature 9. Saving information
* Duke automatically saves the task list into your OS home directory under <code>/Duke.txt</code> <br>
* Duke automatically loads the task list from previous session if the file <code>/Duke.txt</code> exist in your home directory.
* If the file <code>/Duke.txt</code> does not exist in your home directory, Duke automatically creates a new <code>/Duke.txt</code> in your home directory.