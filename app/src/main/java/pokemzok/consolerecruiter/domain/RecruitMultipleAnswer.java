package pokemzok.consolerecruiter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RecruitMultipleAnswer {

    private Question question;
    private List<CorrectAnswer> correctAnswers;
    private List<WrongAnswer> wrongAnswers;

}
