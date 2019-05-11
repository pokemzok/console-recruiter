package pokemzok.consolerecruiter.domain;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class RecruitAnswerCollection {

    private final Collection<RecruitMultipleAnswer> recruitMultipleAnswers;

    boolean add(RecruitMultipleAnswer answer){
        return recruitMultipleAnswers.add(answer);
    }

    public void printAnswers(){
        recruitMultipleAnswers.forEach(answer -> {
            log.info("Question: " + answer.getQuestion().getContent());
            log.info("Recruit correct answers: " + answer.getCorrectAnswers()
                    .stream()
                    .map(Answer::getContent)
                    .reduce((answer1, answer2) -> answer1.concat(" | "+answer2)).orElse(""));
            log.info("Recruit wrong answers: " + answer.getWrongAnswers()
                    .stream()
                    .map(Answer::getContent)
                    .reduce((answer1, answer2) -> answer1.concat(" | "+answer2)).orElse(""));
        });
    }
}
