# sijingpeng-projects

## Web Project: Apartment Resident Chatroom
##### Web Link: https://hod-final-project.web.app/
![web](/readme_images/1.png)
![web](/readme_images/2.png)
![web](/readme_images/3.png)



## Python Project: Command line Todo Application

## Design pattern:

- Builder -> optional attribute
	- We used builder pattern in the Todo class, AddNewTodo and DisplayTodos class to prevent having too many optional parameters in a single class constructor. Given all Todo attributes except for the Todo text are optional, using a builder method reduced the need for multiple overloading constructors, and keep the code conciseice and extendable.

- Factory -> return ICommand
	- We used the abstract factory pattern to create appropriate command instances (add todo, mark complete, display sorted and/or filtered todos) by calling the appropriate generate command method in the CommandFactory class. Each of the 3 commands overrides the single execute(DataProcessor dataProcessor) method in the ICommand interface. 

## MVC Architecture:

- Controller:
	- SystemMain
	- CommandLineParser
	- CommandExecutor
	- CommandFactory
	- Constants
	- ICommand
	- AddNewToDo
	- CompleteTodo
	- DisplayTodos
	- ErrorLogger

- Model
	- DataBaseProcessor
	- Todo

- View
	- Viewer


## Basic logic :


- User -> input String[] args 
- CommandLineParser + CommandFactory => List<ICommand> commands </br>
- CommandLineParser -> Path -> DataBaseProcessor </br>
- CommandLineExecutor + DataBaseProcessor -> exectue commands ,process database</br>
-> View : By viewer

## Exception in ErrorLog
- CommandException : Any wrong input will cause this exception. They will be Logged into the CommandLineParser's errorlog.
- IllegalExecuteException : When execute the commands, if there is anyting wrong to pause the program, throw the exception. They will be Logged into CommandLineExecutor's errorlog.
