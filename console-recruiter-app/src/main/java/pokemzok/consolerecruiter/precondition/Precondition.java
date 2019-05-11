package pokemzok.consolerecruiter.precondition;

import java.util.Collection;

public abstract class Precondition<T> {

    public abstract void check(T object);

    protected <P> void throwIfNull(P param, RuntimeException exception) {
        if (param == null) {
            throw exception;
        }
    }

    protected <P> void throwIfCollectionIsEmpty(Collection<P> paramCollection, RuntimeException exception) {
        if (paramCollection == null || paramCollection.isEmpty()) {
            throw exception;
        }
    }
}
