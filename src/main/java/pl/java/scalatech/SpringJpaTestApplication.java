package pl.java.scalatech;

import static com.google.common.collect.Maps.newHashMap;

import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.bags.UserMap;
import pl.java.scalatech.domain.keys.Travel;
import pl.java.scalatech.domain.keys.Trip;
import pl.java.scalatech.domain.mainPerson.Person;
import pl.java.scalatech.domain.mapkey.basic_collection.BookStore;
import pl.java.scalatech.domain.mapkey.basic_mapKey.Country;
import pl.java.scalatech.domain.mapkey.basic_mapKey.State;
import pl.java.scalatech.domain.mapkey.simple.Book;
import pl.java.scalatech.repository.PersonViewRepo;
import pl.java.scalatech.repository.keys.TravelRepo;
import pl.java.scalatech.repository.keys.TripRepo;
import pl.java.scalatech.repository.mainPerson.PersonRepository;
import pl.java.scalatech.repository.map.basic.CountryRepo;
import pl.java.scalatech.repository.map.collection.BookStoreRepo;
import pl.java.scalatech.repository.map.mapColumn.UserMapRepo;
import pl.java.scalatech.repository.map.simple.BookRepo;

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
    @Autowired
    private BookRepo bookRepo;
    
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private BookStoreRepo bookStoreRepo;
    @Autowired
    private UserMapRepo userMapRepo;


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
        
      Map<String,String> index = newHashMap();
      index.put("exception", "123");
      index.put("mapping", "45");
      index.put("performance", "65");
      index.put("foreword", "1");
      
     // Set<String> reviews = Sets.newHashSet("slawek","tomek","olek","kalina","agnieszka","przodownik");
      Book book = Book.builder().title("jpa in action").indexMap(index)/*.reviews(reviews)*/.build();      
      bookRepo.save(book);
   
      
      countryTest();
      
     // bookStoreTest();
      
      userMapTest();
    }

    private void userMapTest() {
        Map<String,String> phones = Maps.newHashMap();
          phones.put("slawek", "232323");
          phones.put("tomek", "27773");
          UserMap um = UserMap.builder().name("przodownik").phones(phones).build();
          userMapRepo.save(um);
          
          
          
          
    }

    private void countryTest() {
        Map<Integer,State> states = newHashMap();
          states.put(1, State.builder().name("mazowieckie").build());
          states.put(2, State.builder().name("slaskie").build());
          states.put(3, State.builder().name("malopolskie").build());
          Country country = Country.builder().name("Poland").states(states).build();
          countryRepo.save(country);
          states = newHashMap();
          states.put(1, State.builder().name("schlewst").build());
          states.put(2, State.builder().name("bawaria").build());
          country = Country.builder().name("German").states(states).build();
          countryRepo.save(country);
    }

    private void bookStoreTest() {
        Map<pl.java.scalatech.domain.mapkey.basic_collection.Book,Long> invertory = Maps.newHashMap();
          invertory.put(pl.java.scalatech.domain.mapkey.basic_collection.Book.builder().name("quo vadis").isbn("232f").build(), 1l);
          invertory.put(pl.java.scalatech.domain.mapkey.basic_collection.Book.builder().name("lalka").isbn("23244f").build(), 2l);
          BookStore bs = BookStore.builder().address("poznan,pulawska 20").invertory(invertory).build();
          bookStoreRepo.save(bs);
    }

  
}
