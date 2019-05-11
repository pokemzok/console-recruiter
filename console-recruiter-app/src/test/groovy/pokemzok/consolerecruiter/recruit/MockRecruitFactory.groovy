package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.*

class MockRecruitFactory implements RecruitFactory {

    protected final static String TEST_USER_NAME = "GLaDOS"

    private final RecruitRepository recruitRepository
    private final QuestionRepository questionRepository
    MockRecruitFactory() {
        recruitRepository = new MockRecruitRepository()
        questionRepository = new MockQuestionRepository()
    }

    @Override
    Recruit create() {
        return recruitRepository.save(
                new Recruit(TEST_USER_NAME, new RobotInputInterface(), questionRepository.findAll())
        )
    }

    def createAnEmptyRecruit() {
        return new Recruit(
                null,
                null,
                null
        )
    }

    def createRecruitWithOnlyName() {
        return new Recruit(
                "GLaDOS",
                null,
                null
        )
    }

    def createRecruitWithoutAssignedQuestions() {
        return new Recruit(
                "GLaDOS",
                new RobotInputInterface(),
                null
        )
    }
}
