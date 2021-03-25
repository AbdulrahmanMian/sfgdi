package guru.springframework.sfgdi.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository {
    @Override
    public String sayGreeting() {
        return "Hello Wod - EN";
    }
}
