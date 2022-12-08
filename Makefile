default: runTests

runTests: BackendDeveloperTest.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTest

BackendDeveloperTest.class: BackendDeveloperTest.java TicketBackend.class FlightTicketBD.class junit5.jar
	javac -cp .:junit5.jar BackendDeveloperTest.java

TicketBackend.class: TicketBackend.java ITicketBackend.class ITicket.class
	javac TicketBackend.java

ITicketBackend.class: ITicketBackend.java
	javac ITicketBackend.java

ITicket.class: ITicket.java
	javac ITicket.java

FlightTicketBD.class: FlightTicketBD.java FlightTicketADT.class
	javac FlightTicketBD.java

FlightTicketADT.class: FlightTicketADT.java
	javac FlightTicketADT.java

clean:
	rm *.class
