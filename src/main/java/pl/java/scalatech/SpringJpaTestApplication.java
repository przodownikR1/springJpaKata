package pl.java.scalatech;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.keys.Travel;
import pl.java.scalatech.domain.keys.Trip;
import pl.java.scalatech.domain.mainPerson.Person;
import pl.java.scalatech.domain.mapkey.entityExample.Company;
import pl.java.scalatech.domain.mapkey.entityExample.Department;
import pl.java.scalatech.domain.mapkey.entityExample.Phone;
import pl.java.scalatech.domain.mapkey.simple.Book;
import pl.java.scalatech.repository.PersonViewRepo;
import pl.java.scalatech.repository.keys.TravelRepo;
import pl.java.scalatech.repository.keys.TripRepo;
import pl.java.scalatech.repository.mainPerson.PersonRepository;
import pl.java.scalatech.repository.map.entityExample.CompanyRepo;
import pl.java.scalatech.repository.map.entityExample.DepartmentRepo;
import pl.java.scalatech.repository.map.entityExample.PersonDeptRepo;
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
    private PersonDeptRepo personDeptRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    /*@Autowired
    private CompanyRepo companyRepo;*/

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
        
      Map<String,String> index = Maps.newHashMap();
      index.put("exception", "123");
      index.put("mapping", "45");
      index.put("performance", "65");
      index.put("foreword", "1");
      
     // Set<String> reviews = Sets.newHashSet("slawek","tomek","olek","kalina","agnieszka","przodownik");
      Book book = Book.builder().title("jpa in action").indexMap(index)/*.reviews(reviews)*/.build();      
      bookRepo.save(book);
      
      Map<String, pl.java.scalatech.domain.mapkey.entityExample.Person> people = Maps.newHashMap();
      people.put("d_slawek", pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("slawek").age(24).build());
      people.put( "d_tolek",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("tolek").age(26).build());
      people.put("d_agnieszka", pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("agnieszka").age(34).build());
      Map<UUID,Phone> phones = Maps.newHashMap();
      phones.put(UUID.randomUUID(), Phone.builder().phoneNumber("343423").build());
      phones.put(UUID.randomUUID(), Phone.builder().phoneNumber("6665").build());
      phones.put(UUID.randomUUID(), Phone.builder().phoneNumber("2222").build());
      Department dept = Department.builder().name("java").ids(2444l).phones(phones).persons(people).build();
      people = Maps.newHashMap();
              people.put("s_pola",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("pola").age(26).build());
              people.put("s_tola",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("tola").age(88).build());
              people.put("s_olek",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("olek").age(54).build());
      Department dept1 = Department.builder().name("c#").ids(333l).persons(people).build();
      Map<String,String> subDept = Maps.newHashMap();
      subDept.put("#j", "java");
      subDept.put("#qa", "quality assurance");
      subDept.put("#test", "testing");
      dept.setSubDepts(subDept);
      departmentRepo.save(dept);
      departmentRepo.save(dept1);
      Map<Department,pl.java.scalatech.domain.mapkey.entityExample.Person> d1= Maps.newHashMap();
      //d1.put(departmentRepo.findAll().get(0),personDeptRepo.findAll().get(0));
      
     // Company c1 = Company.builder().departmentResponsibles(d1).name("scalatech").build();
     // companyRepo.save(c1);
      Map<Department,pl.java.scalatech.domain.mapkey.entityExample.Person> d2= Maps.newHashMap();
      //d2.put(departmentRepo.findAll().get(1),personDeptRepo.findAll().get(1));
      Company c2 = Company.builder().departmentResponsibles(d2).name("vavatech").build();
     // companyRepo.save(c2);

    }
}
