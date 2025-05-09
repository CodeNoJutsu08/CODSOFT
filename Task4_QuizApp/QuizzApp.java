package practice.com;

import java.util.*;
//Class representing a quiz question
class Question {
    private String question;
    private String[] options;
    private char correctAnswer;

    public Question(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    
 // Display question and options
    public void displayQuestion() {
        System.out.println(question);
        char optionChar = 'A';
        for (String option : options) {
            System.out.println(optionChar + ". " + option);
            optionChar++;
        }
    }

    public boolean isCorrect(char answer) {
        return Character.toUpperCase(answer) == Character.toUpperCase(correctAnswer);
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}

public class QuizzApp {
    private static final int TIME_LIMIT_SECONDS = 10;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        loadQuestions();
        System.out.println(" Welcome to the Quiz!");
        System.out.println(" You have " + TIME_LIMIT_SECONDS + " seconds per question.");
        System.out.println("---------------------------------\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ":");
            q.displayQuestion();

            char userAnswer = getAnswerWithTimer();
            if (userAnswer == 'X') {
                System.out.println(" Time's up! Moving to next question.\n");
                continue;
            }

            if (q.isCorrect(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println(" Wrong. Correct answer: " + q.getCorrectAnswer() + "\n");
            }
        }

        // Result screen
        System.out.println(" Quiz Completed!");
        System.out.println("Your Score: " + score + " / " + questions.size());
    }

    private static volatile boolean answered = false;

    private static char getAnswerWithTimer() {
        answered = false;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (!answered) {
                    System.out.println("\n Time's up!");
                    // do not exit — just simulate no answer
                    answered = true;
                }
            }
        }, TIME_LIMIT_SECONDS * 1000);

        String input = "";
        long start = System.currentTimeMillis();
        while (!answered && System.currentTimeMillis() - start < TIME_LIMIT_SECONDS * 1000) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
                answered = true;
                break;
            }
        }
        timer.cancel();

        if (!answered || input.isEmpty()) return 'X';
        return input.toUpperCase().charAt(0);
    }

    private static void loadQuestions() {
        questions.add(new Question(
                "Who invented Java Programming?",
                new String[]{"James Gosling", "Guido van Rossum", "Dennis Ritchie", "Bjarne Stroustrup"},
                'A'
        ));

        questions.add(new Question(
                "Which component is used to compile, debug and execute the java programs?",
                new String[]{"JRE", "JIT", "JVM", "JDK"},
                'D'
        ));

        questions.add(new Question(
                "What is the extension of java code files?",
                new String[]{".js", ".java", ".class", ".txt"},
                'B'
        ));

        questions.add(new Question(
                "Which of the following is a superclass of every class in Java?",
                new String[]{"Abstract class", "Object class", "ArrayList", "String"},
                'B'
        ));
    }
}
