package pokemzok.consolerecruiter.recruit;

import org.springframework.stereotype.Service;
import pokemzok.consolerecruiter.domain.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class KeyboardInterface implements InputInterface {

    private static final String DELIMITER_REGEX = ",";

    @Override
    public synchronized RecruitMultipleAnswer askForAnswer(Question question) {
        ask(question);
        var selectedAnswers = new AnswerCollection(selectAnswers(getResponse(), question.shuffledAnswers().getAll()));
        return new RecruitMultipleAnswer(
                question,
                selectedAnswers.getCorrect(),
                selectedAnswers.getWrong()
        );
    }

    private void ask(Question question) {
        System.out.println(question.getContent());
        var counter = new Counter();
        question.shuffledAnswers()
                .getAll()
                .forEach(answer -> print(counter.increment(), answer));
    }

    private void print(int answerNumber, Answer answer) {
        System.out.println(answerNumber + ". " + answer.getContent());
    }

    private Collection<String> getResponse() {
        var scan = new Scanner(System.in);
        return splitResponse(scan.nextLine());
    }

    private List<String> splitResponse(String response) {
        if (response != null) {
            return Arrays.asList(response.replaceAll("\\s", "")
                    .split(DELIMITER_REGEX));
        }
        return new ArrayList<>();
    }

    private List<Answer> selectAnswers(Collection<String> response, List<Answer> answers) {
        return response.stream()
                .filter(it -> isIndexable(it, answers.size()))
                .map(it -> answers.get(toIndex(it)))
                .collect(Collectors.toList());
    }

    private boolean isIndexable(String singleAnswer, int maxSize) {
        if (singleAnswer.matches("\\d")) {
            var number = Integer.valueOf(singleAnswer);
            return number > 0 && number <= maxSize;
        }
        return false;
    }

    private int toIndex(String answerNumber) {
        var number = Integer.valueOf(answerNumber);
        return --number;
    }

    private class Counter {

        private int value = 0;

        int increment() {
            return ++value;
        }

    }

}
