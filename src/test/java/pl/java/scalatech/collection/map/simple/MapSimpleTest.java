package pl.java.scalatech.collection.map.simple;

import static com.google.common.collect.Maps.newHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.mapkey.entityExample.Company;
import pl.java.scalatech.domain.mapkey.entityExample.Department;
import pl.java.scalatech.domain.mapkey.entityExample.Phone;
import pl.java.scalatech.domain.mapkey.entityExample.Responsibility;
import pl.java.scalatech.domain.mapkey.entityExample.Task;
import pl.java.scalatech.repository.map.entityExample.DepartmentRepo;
import pl.java.scalatech.repository.map.entityExample.PersonDeptRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaMapConfig.class ,JpaLoggerConfig.class})
@ActiveProfiles(value = {"mapExample","logger","dev"})
@Transactional
@Slf4j
public class MapSimpleTest {
    
    @Autowired
    private PersonDeptRepo personDeptRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    /*@Autowired
    private CompanyRepo companyRepo;*/
    @Test
    public void shouldMapMappingWork(){
        
    }
    
    private void mapTest() {
        Map<String,String>  pPhone1 = new HashMap<>();
          pPhone1.put("MOBILE", "888223");
          Map<String,String>  pPhone2 = new HashMap<>();
          pPhone2.put("MOBILE", "56623");
          Map<String,String>  pPhone3 = new HashMap<>();
          pPhone3.put("MOBILE", "6663423");
          pPhone3.put("WORK", "3454353423");
          Map<String,String>  pPhone4 = new HashMap<>();
          pPhone4.put("WORK", "1111");
          Map<String,String>  pPhone5 = new HashMap<>();
          pPhone5.put("WORK", "122");
          pPhone5.put("OFFICE", "123");
          pPhone5.put("MOBILE", "124");
          Map<String,String>  pPhone6 = new HashMap<>();
          
          Map<String, pl.java.scalatech.domain.mapkey.entityExample.Person> people = newHashMap();
          people.put("d_slawek", pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("slawek").age(24).phoneNumbers(pPhone4).build());
          people.put( "d_tolek",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("tolek").phoneNumbers(pPhone5).age(26).build());
          people.put("d_agnieszka", pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("agnieszka").phoneNumbers(pPhone6).age(34).build());
          Map<UUID,Phone> phones = newHashMap();
          phones.put(UUID.randomUUID(), Phone.builder().phoneNumber("343423").build());
          phones.put(UUID.randomUUID(), Phone.builder().phoneNumber("6665").build());
          phones.put(UUID.randomUUID(), Phone.builder().phoneNumber("2222").build());
          Department dept = Department.builder().name("java").ids(2444l).phones(phones).persons(people).build();
          
          people = Maps.newHashMap();
                  people.put("s_pola",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("pola").phoneNumbers(pPhone1).age(26).build());
                  people.put("s_tola",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("tola").age(88).phoneNumbers(pPhone2).build());
                  people.put("s_olek",pl.java.scalatech.domain.mapkey.entityExample.Person.builder().name("olek").phoneNumbers(pPhone3).age(54).build());
          Department dept1 = Department.builder().name("c#").ids(333l).persons(people).build();
          Map<String,String> subDept = newHashMap();
          subDept.put("#j", "java");
          subDept.put("#qa", "quality assurance");
          subDept.put("#test", "testing");
          dept.setSubDepts(subDept);
          Map<Responsibility,Task> tasks = newHashMap();
          tasks.put(Responsibility.builder().priority(1).skill("programming").build(), Task.builder().name("coding").build());
          tasks.put(Responsibility.builder().priority(2).skill("testing").build(), Task.builder().name("checking").build());
          //dept.setTasks(tasks);
          departmentRepo.save(dept);
          departmentRepo.save(dept1);
          Map<Department,pl.java.scalatech.domain.mapkey.entityExample.Person> d1= newHashMap();
          //d1.put(departmentRepo.findAll().get(0),personDeptRepo.findAll().get(0));
          
         // Company c1 = Company.builder().departmentResponsibles(d1).name("scalatech").build();
         // companyRepo.save(c1);
          Map<Department,pl.java.scalatech.domain.mapkey.entityExample.Person> d2= newHashMap();
          //d2.put(departmentRepo.findAll().get(1),personDeptRepo.findAll().get(1));
          Company c2 = Company.builder().departmentResponsibles(d2).name("vavatech").build();
         // companyRepo.save(c2);
    }

}
