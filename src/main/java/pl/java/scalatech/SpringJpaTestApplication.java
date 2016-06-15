package pl.java.scalatech;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.keys.Travel;
import pl.java.scalatech.domain.keys.Trip;
import pl.java.scalatech.domain.mainPerson.Person;
import pl.java.scalatech.repository.PersonViewRepo;
import pl.java.scalatech.repository.keys.TravelRepo;
import pl.java.scalatech.repository.keys.TripRepo;
import pl.java.scalatech.repository.mainPerson.PersonRepository;

@SpringBootApplication
@Slf4j
public class SpringJpaTestApplication implements CommandLineRunner{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonViewRepo personViewRepo;
    @Autowired
    private TravelRepo travelRepo;
    @Autowired
    private TripRepo tripRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0;i<100;i++){
      personRepository.save(Person.builder().email("przodownikR1"+i+"@gmail.com").firstname("slawek_"+i).disable(true).birthDay(ZonedDateTime.now()).build());
        }
      log.info("{} ",personRepository.findAll());


      log.info("person view : {}",personViewRepo.findAll());
      Travel travel = new Travel();
      travel.setName("lucca");
      travelRepo.save(travel);
      travel = new Travel();
      travel.setName("warsaw");
      travelRepo.save(travel);
      Trip trip = new Trip();
      trip.setName("italy");
      tripRepo.save(trip);
      trip = new Trip();
      trip.setName("german");
      tripRepo.save(trip);



    }
}
