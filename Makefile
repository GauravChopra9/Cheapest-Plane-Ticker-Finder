runTests: DataWranglerTests.class 
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
clean:
	rm *.class
