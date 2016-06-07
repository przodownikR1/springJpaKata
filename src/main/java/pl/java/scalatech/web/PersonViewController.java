package pl.java.scalatech.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.domain.PersonView;
import pl.java.scalatech.repository.PersonViewRepo;

@RestController
@RequestMapping("/person")
public class PersonViewController extends GenericController {

    @Autowired
    public PersonViewController(PersonViewRepo repo) {
        super(repo);
    }

    @Override
    Class<?> getDomainClass() {
        return PersonView.class;
    }
}
