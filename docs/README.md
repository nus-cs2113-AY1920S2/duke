# Using duke task manager  
  
Duke BAPE is a task manager built specially for you to store your daily task. With export function built-in, you will be able to transfer all your task easily between machines.  
    
**Starting Up**
* Install `Java 11 or higher` in your computer
* Download the most updated `duke.jar` from release page. Current version : `Duke v1.5`
* Copy the jar file to any folder that you wish to run from.
* Launch terminal on your OS / CMD for window users --- `Win Key + R and type cmd, hit enter`
* Navigate to the folder where duke.jar is in. -- `cd filepath`
* Type the command `java -jar duke.jar` to launch the program.
  
**Functions available**  
**=========================================================**
  
    
|Functions             | Example                                        |  
|----------------------|------------------------------------------------|  
|`todo [task]`         | store a new todo task                          |       
|`deadline [task]`     | store a new task with deadline                 |  
|`event [task]`        | store a new event task                         |  
|`list`                | list all task stored                           |  
|`done [tasknumber]`   | mark a task as completed                       |  
|`delete [tasknumber]` | delete task                                    |  
|`find [keyword]`      | find task with the keyword in the descriptions |  
|`bye`                 | exit program                                   |  
|`reset`               | reset and clear data directory and Tasklist.txt|  
|`help`                | call up the functions table (TBC)              |  
  
**=========================================================**
  
1. Todo : Creating and storing a todo task.  
Eg: `todo borrow book` will create a new task with description `borrow book` and store in the Array of task.  
Please adhere to the spacing after todo, otherwise it will result in `invalid function`.

2. deadline : Creating and storing a task with a deadline in it.  
Eg: `deadline return book /by 1200` will create a new deadline task with description `return book` and `/by` will be the escaping keyword.  
Anything after `\by` will be part of the timing stored into the task.  

2. event : Creating and storing a task with a deadline in it.  
Eg: `event career fair /at library` will create a new event task with description `career fair` and `/at` will be the escaping keyword.  
Anything after `\at` will be part of the location stored into the task.  

3. list : Will list out all task stored in the array list.  

4. reset : This function will clear and reset the Tasklist.txt file in the /data dir. After completion, the program will exit.  
Launch the program again to start storing new task.  
Use this function when there are unknown errors to the Tasklist.txt file when using program.

5. help : Type `help` to access the help table again and follow the example format given for input.

6. (IMPT) Do not use more than one escaping datetime or location keyword in a task.  
Eg : `deadline Complete CS2113 /by 2359 /by Friday`  
Eg: `event CFG /at /at NUS Utown`  
Doing so will result in data lost. (Fixing in progress)  

# Feedback, Bug Reports

* If you have feedback or bug reports, PLEASE IGNORE IT because it will be minor :)
* We welcome pull requests too.