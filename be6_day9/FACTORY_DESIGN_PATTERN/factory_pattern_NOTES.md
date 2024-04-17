# POJO (Plain Old Java Objects) VS SERVICE CLASSES

 **POJO classes**: These are classes which contain information only. It is the resemblance of a database. These classes do not have any functions/methods inside it. When we design a program, we only think about classes and what kind of information it contains but not yet about what functions it has.

**Service Classes**: Behaviors/methods/functions of a class are defined later in service classes. 

**The rationale** behind this is that behaviours/functions of a class can be changed  or updated frequently. However we do not want such changes affect our database's stability. We would like to keep our database in secured place with limited access. Hence the POJO classes should be kept as they are from their creations to their terminations with minimum alternations. 

# IMPLEMENTATION OF FACTORY PATTERN IN PROGRAM DESIGN

**CONTEXT**
Imagine we are building a banking application. Users of this applicaition are classified according to their rank : gold, silver or bronze. Users of different ranks will differ in results of functions welcomeMessage() and showBenefits(). 
Our POJO class is **User**. This class's attributes include: name, age, rank,etc.
Our Service classes are **UserService**- the parent class. Under this parent, we have three children classes **GoldUserService**, **SilverUserService** and **BronzeUserService**

Base on attribute "rank" of Users class, we need to initiate the correct associate class. 

**OPTION 1**: write a function in main program to initiate the associate child class based on "rank"

This type of programing is just a temporary solution since the function is writen in the main file, it is not re-usable anywhere else but the program where it was written. Imagine if we have multiple main program and we need to initiate methods of UserServices, we will need to re-write the function again! Duplications should be avoided in programming. 

**OPTION 2**: UserService is added as an attribute of class User. After each object's construction, we call the related child class. 

This is also a temporary solution because we need to manually initiate the child class. What if we have multiple Services other than UserServices classified basing on attributes other than "rank"?

**OPTION 3**: initiate an UserService object. Write a function inside UserService class to return a new UserService type of object ( can be  **GoldUserService**,or **SilverUserService** or **BronzeUserService** ) basing on "rank"
Write in UserService class file --> can be re-user for multiple main programs 
                                --> no change is made to User class file (POJO)
