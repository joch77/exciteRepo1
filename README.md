exciteRepo1
===========

Repo for Excite Holidays ATM Project based on the Backend Developer Test – ATM Simulation Requirement Specification document.
This repository contains the eclipse project exciteATMP. 

About the Project:
The project is implemeted and tested using JRE 1.6.
It is purely a command line interface.
The project is tested in a Mac OS 10.9 environment.
jUnit test cases are within the exciteATMP/test folder.

The ATM Project although currently configured to use 50,20 bills can be configured
to use other currecny denominations. This can be done by modifying the config.properties
file under exciteATMP/src before building.


Building the Project:

An ant build can be found under exciteATMP folder. This will create a runnable jar.

Running the Project:
Execute a java -jar exciteATM.jar to run the project. 
Prior any transaction can be made in the ATM, the amount of bills should be initialized.
Once that is done, withdraw and inquiry can be done.
