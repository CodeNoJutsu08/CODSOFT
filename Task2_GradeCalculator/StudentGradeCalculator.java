package practice.com;

import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Enter Total Number of Subjects: ");
    	int nSubjects = scanner.nextInt();
    	
    	int[]marks = new int[nSubjects];
    	int totalMarks = 0;
    	
    	//Enter marks with Validation
    	for(int i = 0; i<nSubjects; i++) {
    		while(true) {
    			System.out.println("Enter Makrs for Subject " + (i+1) + "(out of 100): ");
    			int input = scanner.nextInt();
    			
    			if(input>=0&& input <= 100) {
    				marks[i] = input;
    				totalMarks +=input;
    				break;
    			}
    			else {
    				System.out.println("Invalid Input! Enter Marks between 0 and 100. ");
    			}
    		}
    	}
    	
    	//Calculate percentage
    	double percentage =(double) totalMarks/ nSubjects;
    	
    	String grade;
    	if(percentage>=90) {
    		grade ="A+";
    	}
    	else if(percentage >=80) {
    		grade = "A";
    	}
    	else if(percentage >=70) {
    		grade = "B";
    	}
    	else if(percentage >=60) {
    		grade = "C";
    	}
    	else if(percentage >=50) {
    		grade = "D";
    	}
    	else {
    		grade = "F";
    	}
        
    	System.out.println("\n---- Result -----");
    	System.out.println("Total Marks: " + totalMarks + "/" + (nSubjects*100));
    	System.out.printf("Average Percentage: %.2f%%\n", percentage);
    	System.out.println("Grade: " + grade);
    }
}
