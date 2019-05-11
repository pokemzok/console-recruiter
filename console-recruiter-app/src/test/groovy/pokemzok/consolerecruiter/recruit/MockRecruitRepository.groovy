package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.Recruit
import pokemzok.consolerecruiter.domain.RecruitRepository

class MockRecruitRepository implements RecruitRepository {

    private final List<Recruit> recruits = new ArrayList<>()

    @Override
    <S extends Recruit> S save(S recruit) {
        recruits.add(recruit)
        return recruit
    }

    //Have to add this method because my compiler can not understand that the method above is alright
    //FIXME it appears groovy uses this method instead of the previous, why?
    Object save(Object recruit) {
        recruits.add((Recruit) recruit)
        return recruit
    }

    @Override
    <S extends Recruit> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new RuntimeException("Not supported")
    }

    @Override
    Optional<Recruit> findById(String id) {
        throw new RuntimeException("Not supported")
    }

    @Override
    boolean existsById(String id) {
        throw new RuntimeException("Not supported")
    }

    @Override
    Iterable<Recruit> findAll() {
        throw new RuntimeException("Not supported")
    }

    @Override
    Iterable<Recruit> findAllById(Iterable<String> ids) {
        throw new RuntimeException("Not supported")
    }

    @Override
    long count() {
        throw new RuntimeException("Not supported")
    }

    @Override
    void deleteById(String id) {
        throw new RuntimeException("Not supported")
    }

    @Override
    void delete(Recruit recruit) {
        throw new RuntimeException("Not supported")
    }

    @Override
    void deleteAll(Iterable<? extends Recruit> iterable) {
        throw new RuntimeException("Not supported")
    }

    @Override
    void deleteAll() {
        throw new RuntimeException("Not supported")
    }


}
