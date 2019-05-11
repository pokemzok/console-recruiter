package pokemzok.consolerecruiter.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AnswerCollection { 

    private final List<Answer> answers;

    public List<CorrectAnswer> getCorrect() {
        return answers.stream()
                .filter(it -> it instanceof CorrectAnswer)
                .map(it -> (CorrectAnswer) it)
                .collect(Collectors.toList());
    }

    public List<WrongAnswer> getWrong() {
        return answers.stream()
                .filter(it -> it instanceof WrongAnswer)
                .map(it -> (WrongAnswer) it)
                .collect(Collectors.toList());
    }

    public List<Answer> getAll(){
        return answers;
    }
}
