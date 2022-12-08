runTests: FlightTicketGraph.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

FlightTicketGraph.class: 
	javac FlightTicketGraph.java

clean:
	rm *.class
