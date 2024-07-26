import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGUI extends JFrame {
    private Quiz quiz;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft;

    public QuizGUI(List<QuizQuestion> questions) {
        quiz = new Quiz(questions);
        initUI();
        loadNextQuestion();
    }

    private void initUI() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener(new OptionButtonListener(i));
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time left: 10");
        add(timerLabel, BorderLayout.SOUTH);
    }

    private void loadNextQuestion() {
        if (!quiz.hasMoreQuestions()) {
            showResult();
            return;
        }

        QuizQuestion question = quiz.getCurrentQuestion();
        questionLabel.setText(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options.get(i));
        }

        startTimer();
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timeLeft = 10;
        timerLabel.setText("Time left: " + timeLeft);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    timeLeft--;
                    timerLabel.setText("Time left: " + timeLeft);
                    if (timeLeft <= 0) {
                        timer.cancel();
                        quiz.nextQuestion();
                        loadNextQuestion();
                    }
                });
            }
        }, 1000, 1000);
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz completed! Your score: " + quiz.getScore());
        System.exit(0);
    }

    private class OptionButtonListener implements ActionListener {
        private int optionIndex;

        public OptionButtonListener(int optionIndex) {
            this.optionIndex = optionIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.cancel();
            quiz.answerCurrentQuestion(optionIndex);
            loadNextQuestion();
        }
    }

    public static void main(String[] args) {
        List<QuizQuestion> questions = List.of(
            new QuizQuestion("What is the capital of France?", List.of("Berlin", "Madrid", "Paris", "Rome"), 2),
            new QuizQuestion("What is 2 + 2?", List.of("3", "4", "5", "6"), 1),
            new QuizQuestion("What is the largest planet?", List.of("Earth", "Jupiter", "Mars", "Saturn"), 1)
        );

        SwingUtilities.invokeLater(() -> {
            QuizGUI quizGUI = new QuizGUI(questions);
            quizGUI.setVisible(true);
        });
    }
}