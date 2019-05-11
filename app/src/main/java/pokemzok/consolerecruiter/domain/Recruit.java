package pokemzok.consolerecruiter.domain;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class Recruit {

    @Id
    private String id;
    private String name;
    private List<RecruitMultipleAnswer> answeredQuestions;
    private List<UnansweredQuestion> unansweredQuestions;

    @Getter(AccessLevel.PRIVATE)
    private Collection<RecruitRate> rates;
    private Collection<Question> assignedQuestions;

    @Transient
    private InputInterface inputInterface;

    @Transient
    private RecruitAnswerCollection answerCollection;

    @Transient
    private RecruitRateCollection rateCollection;

    public Recruit(String name, InputInterface inputInterface, Collection<Question> assignedQuestions) {
        this.name = name;
        this.answeredQuestions = new LinkedList<>();
        this.unansweredQuestions = new LinkedList<>();
        this.rates = new LinkedList<>();
        this.inputInterface = inputInterface;
        this.answerCollection = new RecruitAnswerCollection(answeredQuestions);
        this.assignedQuestions = assignedQuestions;
        this.rateCollection = new RecruitRateCollection(rates);
        new RecruitPreconditions().check(this);
    }

    public void answerAssignedQuestions() {
        assignedQuestions.forEach(question ->
                recruitAnswerCollection().add(inputInterface.askForAnswer(question))
        );
    }

    public void stopAnsweringQuestion() {
        var answeredQuestion = answeredQuestions.stream().map(RecruitMultipleAnswer::getQuestion).collect(Collectors.toList());
        var unansweredQuestions = new ArrayList<>(assignedQuestions);
        unansweredQuestions.removeAll(answeredQuestion);
        unansweredQuestions.forEach(question -> this.unansweredQuestions.add(new UnansweredQuestion(question)));
    }

    public RecruitAnswerCollection recruitAnswerCollection() {
        return answerCollection;
    }

    public RecruitRateCollection rateCollection() {
        return rateCollection;
    }

    public RecruitRate rate(CountingPointsStrategy strategy) {
        if (assignedQuestions.size() > (answeredQuestions.size() + unansweredQuestions.size())) {
            throw new RateIsUnavailableException("Recruit still have unanswered questions! Either let him finish answering or stop the answering process before trying to rates");
        }
        var rate = strategy.countFor(this);
        rateCollection.add(rate);
        return rate;
    }
}
