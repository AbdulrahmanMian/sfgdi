package guru.springframework.sfgdi.config;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

// not needed when using spring boot application properties file @PropertySource("classpath:datasource.properties")
@ImportResource("classpath:sfgdi-config.xml")
    @Configuration
// This annotation tells Spring:This is going to be a Spring configuration class.
//This is going to define  different beans.
public class GreetingServiceConfig {
    @Bean
    FakeDataSource fakeDataSource(SfgConfiguration sfgConfiguration){
        FakeDataSource fakeDataSource=new FakeDataSource();
        fakeDataSource.setUsername(sfgConfiguration.getUsername());
        fakeDataSource.setPassword(sfgConfiguration.getPassword());
        fakeDataSource.setJdbcurl(sfgConfiguration.getJdbcurl());
        return fakeDataSource;
    }
    @Bean
    PetServiceFactory petServiceFactory(){
        return new PetServiceFactory();
    }
    @Bean
    @Profile({"dog", "default"})
    PetService dogPetService(PetServiceFactory petServiceFactory){
       return petServiceFactory.getPetService("dog");
    }
    @Bean
    @Profile("cat")
    PetService catPetservice(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("cat");
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService(){
        return new I18NSpanishService();
    }
   @Bean
    EnglishGreetingRepository englishGreetingRepository(){
      return new EnglishGreetingRepositoryImpl() ;
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository )
    //since bean had a name @Service("i18nService") this method is called after that name
    {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }


    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

  //  @Bean
    ConstructorGreetingService constructorGreetingService(){
        return new ConstructorGreetingService();
    }
    @Bean// default name of the bean is the name of the method that is used
        // here that is (propertyInjectedGreetingService)
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }
    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
//Typically, I would not do this refactoring away from using the stereotype annotation of
// service on this.
//But this is more of a traditional use case for this so would be if I was working with a third
// party component.
//So if I'm using some type of library, a great example would be a Jackson for JSON processing.
}
