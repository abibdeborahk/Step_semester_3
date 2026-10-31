import java.util.*;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public abstract boolean evaluate(String answer);

    public String getQuestionText() {
        return questionText;
    }
}

// Multiple Choice Question
class MCQQuestion extends Question {

    public MCQQuestion(String questionText, String correctAnswer) {
        super(questionText, correctAnswer);
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

// True or False Question
class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText, correctAnswer);
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String examName;
    ArrayList<Question> questions = new ArrayList<>();

    public Examination(String examName) {
        this.examName = examName;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}

class Attempt {
    Student student;
    Examination exam;

    HashMap<Integer, String> answers = new HashMap<>();

    boolean submitted = false;

    public Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    public void answerQuestion(int questionNumber, String answer) {

        if (submitted) {
            System.out.println("Cannot change answers after submission.");
            return;
        }

        if (questionNumber < 1 ||
                questionNumber > exam.getTotalQuestions()) {
            System.out.println("Invalid question number.");
            return;
        }

        answers.put(questionNumber, answer);

        System.out.println("Question " + questionNumber
                + " answered with '" + answer + "'.");
    }

    public void submit() {

        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;

        System.out.println("Examination '" + exam.examName
                + "' submitted successfully.");

        evaluateResult();
    }

    private void evaluateResult() {

        int correct = 0;

        for (int i = 0; i < exam.questions.size(); i++) {

            String answer = answers.get(i + 1);

            if (answer != null &&
                    exam.questions.get(i).evaluate(answer)) {
                correct++;
            }
        }

        System.out.println("Result for '" + exam.examName
                + "' attempt: " + correct + "/"
                + exam.getTotalQuestions() + " correct");
    }
}

class Student {
    String studentName;

    HashMap<Examination, Attempt> attempts = new HashMap<>();

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Attempt startExam(Examination exam) {

        Attempt existing = attempts.get(exam);

        if (existing != null) {
            if (existing.submitted) {
                System.out.println("Student already submitted this examination.");
                return null;
            }

            return existing;
        }

        Attempt attempt = new Attempt(this, exam);

        attempts.put(exam, attempt);

        System.out.println("Examination '" + exam.examName
                + "' started by " + studentName + ".");

        return attempt;
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        // Create examination
        Examination exam = new Examination("Math Quiz");

        // Add questions
        exam.addQuestion(
                new MCQQuestion("2 + 2 = ?", "A")
        );

        exam.addQuestion(
                new MCQQuestion("5 + 5 = ?", "B")
        );

        // Create student
        Student student = new Student("John");

        // Start examination
        Attempt attempt = student.startExam(exam);

        if (attempt != null) {

            // Answer questions
            attempt.answerQuestion(1, "A");
            attempt.answerQuestion(2, "C");

            // Submit examination
            attempt.submit();

            // Try changing answer after submission
            attempt.answerQuestion(1, "B");
        }
    }
}