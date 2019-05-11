package pokemzok.consolerecruiter;

import pokemzok.consolerecruiter.domain.CountingPointsStrategy;
import pokemzok.consolerecruiter.precondition.Precondition;

import java.util.Collection;

class StrategiesPrecondition extends Precondition<Collection<CountingPointsStrategy>> {

    @Override
    public void check(Collection<CountingPointsStrategy> collection) {
        throwIfCollectionIsEmpty(collection, new NoStrategySelectedException("At least one strategy should be enabled, please check you properties file."));
    }
}
