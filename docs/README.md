# User Guide
## About Duke



## Quick Guide


## Features 

###1. Creating new `Todo`:
Adds a new Todo to the TaskList 

Format:

> todo [Description]

Example of usage:

    todo buy lunch
    
Expected outcome:

    1. [T][N] buy lunch
    
###2. Creating new `Deadline`:
Adds a new deadline to the TaskList.

Format:

>deadline [Description] \by [endDate]

Example of usage:

    deadline complete CS1010 Assignment \by 10-03-2022
    
Expected outcome:

    2. [D][N] complete CS1010 Assignment ( by: 10-03-2022 ) 

###3. Creating new `Event`: 
Adds a new event to the TaskList.

Format:

>event [Description] \at [Date]

Example of usage:

    event attend cousin wedding \at 01-04-2022

Expected outcome:

    3. [E][N] attend cousin wedding  ( at: 01-04-2022 ) 
        
###4. `List` Command:
List out all the existing tasks in the TaskList

Format:

>list

Example of usage (if there are existing tasks):

    list

Expected outcome (if there are existing tasks):

    1. [T][N] buy lunch
    2. [D][N] complete CS1010 Assignment ( by: 10-03-2022 ) 
    3. [E][N] attend cousin wedding  ( at: 01-04-2022 ) 
    Now you have 3 tasks in the list.
    
Example of usage (if there are no existing tasks):

    list

Expected outcome (if there are no existing tasks):

    Empty!

###5. `Mark` existing tasks:
Format:

>list

####If there are existing tasks

Example of usage:

    mark 1

Expected outcome:

    You have marked -- [T][Y] buy lunch

####If there are no existing tasks
Example of usage:

    mark 1

Expected outcome:

    Out of Bound!

###?. `Exit` the program:

Example of usage:

    bye

Expected outcome:

    Bye. Hope to see you again soon!

###6. :

###7. :




## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
