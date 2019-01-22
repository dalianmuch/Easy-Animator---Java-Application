	The reason why we do not create the test for the controller is that we pass all arguments in the command line to the controller and 
want it to parse the input given by the user. And according to the instucrion in the assignment, when the input arguments is invalid, we should show an error message and exit the whole program immediately rather than throwing exception. So in this case, the methods we know to test the class so far cannot handle this situation. We can see the wrong message poping up when we enter invalid arguemtns in the console but we cannot create the automated test on it.

	Compare to the last assignment, we add more classes in the model package, add three extra packages controller, util and view. 
EasyAnimator is the beginning of our program and has main() method in it.

	A general work flow in our program is the EasyAnimator receives the arguments from the command line given by user, then it creates a 
controller and gives those four arguments and the control of the whole program to the controller. The controller itself interprets the four arguments and creates the model and view. Gets all necessary information from model, passes them to the view and asks the view to output the final text format result.

	The jobs that each different part does show below:

	The job of nested class ModelBuilder in the model part: Gets all key operation parameters returned by the AnimationFileReader and 
builds a whole model which has all information needed for a completed animation. Adds the real time unit in each operation based on the speed passed in by the controller part.

	The job of Operation part: Does different operations itself represents on the passed in shape. 
	There are several subclasses implementing the Operation interface. Each subclass represents an individual operation like move, add 
shape, change color, etc. Each operation will do its own change on the given shape. This character will contribute a lot to the future dynamic dispatch used in the method outPut in the view part.

	The job of Shape part: Implements all different operations and outputs the final text format result. 
	There are several subclasses implementing the Shape interface. Each subclass represents a individual shape like rectangle and oval. 
AbstractShape implements the same operation which can be shared by all sub-shapes. Then in each sub-shape class, it will implement all its own individual operations like addShape, scaling, move, etc. This character will contribute a lot to the dynamic dispatch used in the method goOperate in the Operation part.

	The job of the model part: Stores all shapes appear in this animation with their corresponding names and stores all valid operations 
on a single shape. Returns the key parameters the view needs.
	It determines whether operations got from its static nested class ModelBuilder are valid and stores those valid operations. If some 
operations are invalid like non-consistent with the former existed operations, try to do some changes on a non-existed shape, then the model part will throw a exception and not store those operations. After finishing storing all shapes and their corresponding operations, the model can return a map<name, shape>, a map<name, list<operation>>, a list<operation> to the view.

	The job of the controller part: Interprets the arguments passed by the EasyAnimator and creates the corresponding model and view. 
Gets key information from model and asks view to output the final result based on those information.
	Tells the AnimationFileReader which animation file to read based on the name of animation file. Creates different types of view 
based on the type of view. Tells view where to output the final text format string based one the where-output-show-go. Finally, passes the speed of the whole animation to the nested class ModelBuilder based on the integer-ticks-per-second. During the parsing the arguments procedure, it will end the whole program and show error message if some invalid arguments or formats appear. After finishing the above procedure, the controller will create corresponding model and view, get the information the view need from the model and pass them into the view.

	The job of the view part: Outputs the final text format result.
	There are two different views implementing the overhead view interface. The subclass text view needs a list<operation> and a map<
name, shape> returned by the model part. The subclass svg view need one more thing, a map<name, list<operation>>. Then the two subviews will output the final text format result based on all parameters they have.


