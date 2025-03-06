# Sonic User Guide

![img.png](img.png)

Sonic is a task management Command Line Interface application that helps the user organize tasks, whether it's a to-do task, a deadline, or an event.

## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
  * [Adding a todo task](#adding-a-todo-task-todo)
  * [Adding a deadline](#adding-a-deadline-deadline)
  * [Adding an event](#adding-an-event-event)
  * [Listing all tasks](#listing-all-tasks-list)
  * [Marking tasks as done](#marking-tasks-as-done-mark)
  * [Unmarking tasks as not done](#unmarking-tasks-as-not-done-unmark)
  * [Deleting a task](#deleting-a-task-delete)
  * [Finding tasks using keyword](#finding-tasks-using-keyword-find)
  * [Exiting the application](#exiting-the-application-bye)
- [Command Summary](#command-summary)
- [Date and Time Formats](#date-and-time-formats)
- [File Storage](#file-storage)

---

## Getting Started

  1. Ensure you have Java 17 or above installed on your computer.
  2. Download the latest .jar from [here](https://github.com/theertha120/ip/releases).
  3. Copy the file to the folder you want to use as the home folder for your TaskList.
  4. Open a command terminal, cd into the folder you put the jar file in and run the following: <br>
`java -jar sonic.jar`

---
## Features

### Adding a todo task: `todo`

Adds a new todo task to the task list.

Format: `todo <task description>`

Example: 
```
todo finish assignment

Got it, I have added this task: 
  [T][ ] finish assignment
Now you have 1 tasks in the list.
```
---
### Adding a deadline: `deadline`

Adds a new deadline to the task list.

Format: `deadline <deadline description> /by <time>`

Example:
```
deadline read book /by 1930

Got it, I have added this task:
   [D][ ] read book (by: 7:30pm)
Now you have 2 tasks in the list.
```
---
### Adding an event: `event`

Adds a new event to the task list.

Format: `event <event description> /from <start> /to <end>`

Example:
```
event attend meeting /from 2025-09-03 1700 /to 1900 2025-09-03

Got it, I have added this task:
   [E][ ] attend meeting (from: Sep 03 2025 5pm to: 7pm Sep 03 2025)
Now you have 3 tasks in the list.
```
---
### Listing all tasks: `list`

Displays all the tasks in the task list.

Format: `list`

Example:
```
list

1. [T][ ] finish assignment
2. [D][X] read book (by: 7:30pm)
3. [E][ ] attend meeting (from: Sep 03 2025 5pm to: 7pm Sep 03 2025)
```
---
### Marking tasks as done: `mark`

Marks the tasks as completed.

Format: `mark <task number>`

Example:
```
mark 2

Nice! I've marked this task as done:
[D][X] read book (by: 7:30pm)
```
---
### Unmarking tasks as not done: `unmark`

Unmarks the task as not completed.

Format: `unmark <task number>`

Example:
```
unmark 3

Ok, I've marked this task as not done yet:
[E][ ] attend meeting (from: Sep 03 2025 5pm to: 7pm Sep 03 2025)
```
---
### Deleting a task: `delete`

Deletes the task from the task list.

Format: `delete <task number>`

Example:
```
delete 1

Okay! I've deleted this task:
[T][ ] finish assignment
Now you have 2 tasks in the list.
```
---
### Finding tasks using keyword: `find`

Finds all tasks containing the keyword entered.

Format: `find <keyword>`

Example:
```
find book

Here are the matching tasks in your list:
1. [D][X] read book (by: 7:30pm)
2. [T][ ] buy book
```
---
### Exiting the application: `bye`

Exits the application.

Format: `bye`

Example:
```
bye

Goodbye! Have a great day!
```
---
## Command Summary

| Command                        | Format                                              |
|--------------------------------|-----------------------------------------------------|
| **Add a todo task**            | `todo <task description>`                           |
| **Add a deadline**             | `deadline <deadline description> /by <time>`        |
| **Add an event**               | `event <event description> /from <start> /to <end>` |
| **List all tasks**             | `list`                                              |
| **Mark a task as done**        | `mark <task number>`                                |
| **Unmark a task**              | `unmark <task number>`                              |
| **Delete a task**              | `delete <task number>`                              |
| **Find tasks using a keyword** | `find <keyword>`                                    |
| **Exit the application**       | `bye`                                               |



## Date and Time Formats

The user can use these date and time formats for deadline and event features:

- `2019-03-22` is converted to `Mar 22 2019`
- `2200` is converted to `10pm`
- `2019-03-22 2200` is converted to `Mar 22 2019 10pm`
- `2200 2019-03-22` is converted to `10pm Mar 22 2019`

---
## File Storage

Sonic stores tasks in a file named sonic.txt at ip/data/sonic.txt.

The tasks are saved in the sonic.txt in the below format:

`D | 0 | submit assignment | Mar 22 2019`<br>
`T | 1 | Read textbook`<br>
`E | 0 | do the user guide | 6pm | not sure`


When the user launches Sonic, it automatically loads the tasks from sonic.txt into the task list. 
Any changes made to the task list are instantly saved, ensuring the tasks remain accessible even after restarting the application.
