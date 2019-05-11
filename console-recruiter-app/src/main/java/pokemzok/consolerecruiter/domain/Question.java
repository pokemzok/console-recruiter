package pokemzok.consolerecruiter.domain;

import lombok.Getter;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    @Getter
    private String content;
    @Getter
    private List<CorrectAnswer> correctAnswers;
    @Getter
    private List<WrongAnswer> wrongAnswers;

    @Transient
    private AnswerCollection answers;

    public Question(String content, List<CorrectAnswer> correctAnswers, List<WrongAnswer> wrongAnswers) {
        this.content = content;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    public AnswerCollection shuffledAnswers(){
        if( answers == null){
            answers = new AnswerCollection(
                    shuffleAnswers()
            );
        }
        return answers;
    }

    private List<Answer> shuffleAnswers(){
        var answers = new ArrayList<Answer>(correctAnswers);
        answers.addAll(wrongAnswers);
        Collections.shuffle(answers);
        return answers;
    }
}
