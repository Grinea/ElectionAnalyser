README

RUNNING THE PROGRAM

To run the program use ./ElectionAnalyser

This will bring up the main menu and all functions are controlled by prompted terminal input from there.

The program will exit if it can not find all of the files listed under the csvs/ file section.

FILES
reports/ : File that contains printed reports

csvs/ : File containing the .csv files needed for the program
    - HouseCandidatesDownload-20499.csv : file containing candidate information (not including votes)
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-ACT.csv : csv file containing vote information by polling place in ACT
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-NSW.csv : csv file containing vote information by polling place in NSW 
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-NT.csv : csv file containing vote information by polling place in NT
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-QLD.csv : csv file containing vote information by polling place in QLD
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-SA.csv : csv file containing vote information by polling place in SA
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-TAS.csv : csv file containing vote information by polling place in TAS
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-VIC.csv : csv file containing vote information by polling place in VIC
    - HouseStateFirstPrefsByPollingPlaceDownload-20499-WA.csv : csv file containing vote information by polling place in WA

Candidate.java : Source code for the Candidate class that contains information related to each candidate
    no dependencies
Division.java : Source code for the Division class that contains information about which candidates are in a division and the total vote count in the division
    Candidate, DSALinkedList
DSAHashTable.java : Sort code for the hash table implementation used in the election analyser
    no dependencies
DSALinkedList.java : Very limited implementation of linked list used in the election analyser
    no dependencies
ElectionAnalyser.java : Source code for the main driver of the election analyser
    DSALinkedList, Candidate, Division, FileIO, DSAHashTable, Tools, Menus, Filtering, Sorts
FileIO.java : Source code for the class handling file input and output for the election analyser
    Candidate, DSALinkedList, DSAHashTable, InputFunctions
Filtering.java : Source code for some utility functions that gets/processes sorting choices from user and does set intersection calculations
    Tools, DSALinkedList, DSAHashTable, Menus
InputFunctions.java : Source code for getting user input in a controlled manner
    no dependencies
Menus.java : Source code for printing menus to the user for flow control
    InputFunctions
Sorts.java : Source code for merge sort. There is 4 copies of merge sort in here, that sort based on different class fields. Ugly but I didn't have time to learn how to dynamically change the sort field
    Candidate
Tools.java : Source code for tools to convert state to its alphabetical order and turn a 0-7 into a state name
    no dependencies
DSAGraph.java : source code for graphing function
    GraphList, DSAVertex, DSAEdge
GraphFileIO.java : source code for the fileio for the graph unit test
    DSAVertex, DSAEdge, GraphList
GraphList.java : source code for the extended linked list used in DSAGraph
    DSAVertex, DSAEdge, AddSorted
AddSorted.java : Additional methods for the extended linked list related to the graph classes
    DSAVertex, DSAEdge, GraphList
DSAVertex.java : source for for the vertex of the graphs
    DSAEdge, GraphList
DSAEdge.java : source code for the edge object of the graphs
    DSAVertex

Test harnesses

run with junit	
	UnitTestCandidate.java : Source code for Candidate test harness
	UnitTestDivision.java : Source code for Division test harness
	UnitTestDSAHashTable.java : Source code for DSAHashTabletest harness
	UnitTestDSALinkedList.java : Source code for DSALinkedList test harness
	UnitTestFileIO.java : Source code for FileIO test harness
	UnitTestFiltering.java : Source code for Filtering test harness
	UnitTestInputFunctions.java : Source code for InputFunctions test harness
	UnitTestMenus.java : Source code for Menus test harness
	UnitTestSorts.java : Source code for Sorts test harness
	UnitTestTools.java : Source code for Tools test harness

run with java
	UnitTestDSAGraph.java : Source code for DSAGraph test harness
		graphTest1.al : test data for UnitTestGraph
		graphTest2.al : test data for UnitTestGraph