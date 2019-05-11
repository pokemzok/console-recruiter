package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.CountingPointsStrategy
import pokemzok.consolerecruiter.domain.Recruit
import pokemzok.consolerecruiter.domain.RecruitRate

class MockCountingStrategy implements CountingPointsStrategy{

    @Override
    RecruitRate countFor(Recruit recruit) {
        return new RecruitRate( BigDecimal.ZERO,
                10L, BigDecimal.ZERO,
                this.getClass().getSimpleName()
        )
    }

}
