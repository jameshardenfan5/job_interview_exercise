/*
	edit input on line 67
	compile with javac Main.java
	run with java Main
*/

import java.util.List; // to use Lists
import java.util.Map; // to use Maps
// you may import more classes
import java.util.Arrays; // to initialize our input
import java.util.Collections; // to sort and reverse
import java.util.HashMap; // to define our Map
import java.util.ArrayList;

class Main{

	public static List<Map<Integer,String>> atheniumGrading(List<Integer> scores){ // public means it's accessible outside. static means it doesn't change
        // your code here
        
        	List<Map<Integer,String>> scoreswithGrades = new ArrayList<>(); // define list of maps
        	int maxStudentsPerGrade = (int)Math.ceil((double)scores.size()/5);  // initiailizes size of each bucket of a specific grade
        	List<String> grades = Arrays.asList("A","B","C","D","F"); // list of grades
        	int level; // index for list of grades
        	int count; // counter for maxStudentsPerGrade
        	int i; // index for scores
        	int nStudents; //  score.size
        	int cutOff; // border score to deal with duplicates
		int remainder = scores.size()%5; // to deal with maxStudentsPerGrade when total is not divisible by 5.

        	Collections.sort(scores); // merge sort list of scores
        	Collections.reverse(scores); // descending order list of scores
        	level = 0; // 0 corresponds to 'A'
        	count=0; // counter starts at 0
        	nStudents = scores.size(); // nStudents is score.size
        	cutOff = scores.get(maxStudentsPerGrade-1); // first border score to deal with duplicates
        	for(i=0;i<scores.size();i++){ // the only loop in this function to make scoreswithGrades
        		if (scores.get(i) >= cutOff || count<maxStudentsPerGrade){ // the == conditional is for duplicates.
                		Map<Integer,String> scorewithGrade = new HashMap<Integer,String>();// without this line the map gets bigger and bigger
                		scorewithGrade.put(scores.get(i), grades.get(level)); // this is the map.
                		scoreswithGrades.add(i, scorewithGrade); // we put the map to a List.
                		count++; //count gets closer to maxSTudentsPerGrade
             		}
             		else{ // the idea here is to decrease the grade; A to B, B to C, et cetera.
                		count=0; // count initializes
                        	level++; // index for grade increases.
                    		if(level >=5) // level should never increase above 4 anyway.
                        		level=4; // however, in case it does we set it equal to 4
				if(level == remainder && maxStudentsPerGrade!=1) // the max students per grade is higher for the first couple grades. grade inflation
					maxStudentsPerGrade--; // lower amounts of lower grades.
                    		if(maxStudentsPerGrade+i >= nStudents) // when i is large
                        		cutOff = scores.get(nStudents-1); // this is the lowest cutoff grade.
                    		else{ // when we are not identifying the lowest cutoff grade then cutoff is equal to the following
                                	cutOff = scores.get(maxStudentsPerGrade+i-1); // we subtract one from the index.
                    		}
                    		i--; // we decrement i to enter the if conditional
                    		//}
            		}
            	//scoreswithGrades.add(scorewithGrade);
        	}
       		//scoreswithGrades.add(scorewithGrade);
        	return scoreswithGrades; // we return the list
	}
    	public static void main(String[] args){// standard Java main

        	List<Integer> scores_main;//define scores_main

        	scores_main = Arrays.asList(99, 92, 91, 91, 89, 85, 83, 82, 80, 79, 78, 78, 77, 76, 75, 74, 62, 55, 43, 20);//scores_main example

		List<Map<Integer,String>> scoreswithGrades_main = new ArrayList<>();//define list of maps.

        	scoreswithGrades_main = atheniumGrading(scores_main);//call the above function.

        	for(Map<Integer,String> map : scoreswithGrades_main){// printing to access each map of list.
            		for(Map.Entry<Integer,String> entry : map.entrySet()){//accessing each key of map.
                		System.out.printf("[%d, %s] ",entry.getKey(),entry.getValue());//printing format.
           		 }		
       		}
    	}
}


/**
Athenium Coding Exercise
-------------------------
A teacher gives a class of students an exam.  She decides to grade the exam 
using the following method:
* A score in the top 20% of all scores is an A.
* A score in the next 20% of scores is a B.
* A score in the next 20% of scores is a C.
* A score in the next 20% of scores is a D.
* A score in the bottom 20% of scores is an F.
For example, if a class of 20 students has the following scores:
99, 92, 91, 91, 89, 85, 83, 82, 80, 79, 78, 78, 77, 76, 75, 74, 62, 55, 43, 20
As there are 20 grades in this example, each 20% grade bucket will contain 4 
scores. The first four scores (99, 92, 91, 91) would be an A, scores 5 through 8
(89,85, 83, 82) would be a B, scores 9 through 12 (80, 79, 78, 78) would be a 
C,scores 13 through 16 would be a D (77, 76, 75, 74) and scores 17 through 20 
(62, 55, 43, 20) would be an F.
Write a function that takes an arbitrary (possibly unsorted) score list of any 
length (not necessarily the list used as an example above) as a parameter, and 
returns a grade for each score.
ADDITIONAL REQUIREMENT: If there are two (or more) scores that are identical, 
then those identical numerical scores must always receive the same grade, even 
if that causes the grade distribution to be uneven.
We will use the following criteria to evaluate your solution:
1) Does the function return a data structure that contains the same number of 
scores as the input along with a corresponding letter grade of A, B, C, D, F. 
Example output for input [99,82,80,75,60]: [[99, A], [82, B], [80, C], [75, D], 
[60, F]]
You are not required to use this exact output format, as long as it's clear what
grade goes with each score in the input.
2) Do identical scores receive the same letter grade (to accomplish this the 
number of scores receiving each grade may be uneven, which is fine)
3) Does it gracefully and consistently handle lists that are not divisible by 5
4) Does it handle lists that contain fewer than 5 scores.
5) Is your code syntactically valid for the language you chose (we will be 
executing your code)
6) Is your code well commented
7) Is your code well structured and extensible
*/
