kmax
====
Custom forwarding for Floodlight.

Last edited 15 May 2014.
This project was developed during 34359 Software Defined Networks course at Technical University of Denmark.
It was designed and prepared by Adrian Alan Pol (s121015@student.dtu.dk)

Settings
To adjust settings for the project edit dk/dtu/sdn/project/utils/Constants.java lines 6-9

theK is the number of alternative routes to be generated (if possible)
FlowPriority is the priority of the rules installled on switches
FlowActive is boolean property of switch entry determining if the rules will be active (mainly debugging purpose)
IP is the address of the controller
