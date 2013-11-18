Clever Challenge
================

To Run:
-------

java -jar filename.jar argument

example, to run `challenge1-1 509fbd7ec474fab64a8e9d53`:
* `java -jar challenge1_1.jar 509fbd7ec474fab64a8e9d53`


Bonus Challenge:
-----------------

*When you expand the scope of this problem beyond one-room schoolhouses, 
you get a lot more entities that can interact in different ways. In the real Clever data model, 
there are teachers, sections, students, and apps, but also schools, districts, district 
administrators, student emergency contacts, and app developers.*

*With each entity comes a set of rules about what other entities they can see, 
as well as what entities they have the power to grant other entities access to. 
As the number of entities grows, the relationships become increasingly complex to compute.*

*How well would your design scale to hundreds of thousands of students? Would you need to 
make any changes? Or perhaps take a new approach?*

* My design is meant for a small-scale situation. It works well because there are so few entities to loop through,
but with a larger scale, this would cause significant latency issues. The time cost of for loops is O(N), so if I
were to design a large-scale system, I would probably use hashmaps, which generally have a time cost of O(1). In this 
exercise, though, N is small enough that the cost of using a for loop is negligible.

*How easy would it be to add new kinds of entities? What about adding or changing rules?*

* Adding new entities is a simple matter of adding JSON files. 

	In order to allow communication with current entities,
though, we would need to modify the JSON files we already have to include more IDs. (i.e. an app would contain a developer ID, 
a student or teacher would contain a district ID, etc.) 

* Another option would to recreate the model to be Composite (http://www.oodesign.com/composite-pattern.html). This way, when 
adding a new level to the SIS hierarchy, only the "child" and "parent" nodes of that level need to be changed. All other
nodes can continue as if nothing has changed. A drawback to this implementation would be the need to iterate through all levels
from top to bottom in order to get a specific object (i.e. student ID) from a general one (i.e. school district). 

	See [this diagram](Hierarchy.pdf) for more details (created using UML notation: http://www.tutorialspoint.com/uml/uml_basic_notations.htm)
