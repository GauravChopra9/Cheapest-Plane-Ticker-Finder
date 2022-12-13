default: run

run: FlightMapper.class
	java FlightMapper

FlightMapper.class: FlightMapper.java TicketLoader.class TicketBackend.class FlightTicketGraph.class TicketFrontend.class TicketDOT.gv
	javac FlightMapper.java

runTests: runBackendDeveloperTests runFrontendDeveloperTests runDataWranglerTests runAlgorithmEngineerTests



runDataWranglerTests: DataWranglerTests.class 
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

DataWranglerTests.class: TicketLoader.class
	javac -cp .:junit5.jar DataWranglerTests.java
TicketLoader.class: TicketDOT.gv Ticket.class 
	javac TicketLoader.java
TicketDOT.gv: CSVToDOTLoader.class
	java CSVToDOTLoader > TicketDOT.gv
Ticket.class:
	javac Ticket.java
CSVToDOTLoader.class: Consumer_Airfare_Report__Table_1_-_Top_1_000_Contiguous_State_City-Pair_Markets.csv
	javac CSVToDOTLoader.java



runBackendDeveloperTests: BackendDeveloperTest.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTest

BackendDeveloperTest.class: BackendDeveloperTest.java TicketBackend.class FlightTicketBD.class junit5.jar
	javac -cp .:junit5.jar BackendDeveloperTest.java

TicketBackend.class: TicketBackend.java
	javac TicketBackend.java

FlightTicketBD.class: FlightTicketBD.java
	javac FlightTicketBD.java



runFrontendDeveloperTests: FrontendDeveloperTest.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTest
FrontendDeveloperTest.class: FrontendDeveloperTest.java TicketFrontend.class TicketBackendFD.class TicketLoaderFD.class TextUITester.class FDTicket.class
	javac -cp .:junit5.jar FrontendDeveloperTest.java
TicketFrontend.class: TicketFrontend.java
	javac TicketFrontend.java
TicketBackendFD.class: TicketBackendFD.java FDTicket.class
	javac TicketBackendFD.java
TicketLoaderFD.class: TicketLoaderFD.java FDTicket.class
	javac TicketLoaderFD.java
FDTicket.class: FDTicket.java
	javac FDTicket.java
TextUITester.class: TextUITester.java
	javac TextUITester.java



runAlgorithmEngineerTests: FlightTicketGraph.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

FlightTicketGraph.class: 
	javac FlightTicketGraph.java

clean:
	rm *.class
