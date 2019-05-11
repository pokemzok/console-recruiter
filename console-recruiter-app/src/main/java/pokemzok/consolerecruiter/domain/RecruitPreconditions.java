package pokemzok.consolerecruiter.domain;

import lombok.NonNull;
import pokemzok.consolerecruiter.precondition.Precondition;

class RecruitPreconditions extends Precondition<Recruit> {

    public void check(@NonNull Recruit recruit) {
        throwIfNull(recruit.getName(), new RuntimeException("Recruit name can not be null"));
        throwIfNull(recruit.getInputInterface(), new RuntimeException("Input interface was not selected for the recruit"));
        throwIfCollectionIsEmpty(recruit.getAssignedQuestions(), new RuntimeException("No question assigned to user. Make sure there is some questions in db."));
    }

}
