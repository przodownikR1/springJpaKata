package pl.java.scalatech.web;

import static java.util.function.Function.identity;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import static java.util.Optional.of;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.java.scalatech.domain.AbstractEntity;
import pl.java.scalatech.exception.ResourceNotFoundException;

public abstract class GenericController<T extends AbstractEntity> {

    private JpaRepository<T, Long> repo;

    public GenericController(JpaRepository<T, Long> repo){
        this.repo = repo;
    }

    private T verify(Long id) {
        return of(repo.findOne(checkNotNull(id))).map(identity()).orElseThrow(()->new ResourceNotFoundException(getDomainClass().getSimpleName(),id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> getResourceByID(@PathVariable Long id) {
    return verifyAndResponseEntityWrap(id);
    }

    private ResponseEntity<T> verifyAndResponseEntityWrap(Long id) {
        return of(repo.findOne(id)).map(p -> ok(p)).orElseThrow(
                ()->new ResourceNotFoundException(getDomainClass().getSimpleName(),id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Page<T>> getAllResource(Pageable pageable) {
    return ResponseEntity.ok(repo.findAll(pageable));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> createResource(@Valid @RequestBody T resource) {
    T loaded = repo.save(checkNotNull(resource));
    return ResponseEntity.created(fromCurrentRequest().path("/{id}").buildAndExpand(loaded.getId()).toUri()).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateResource(@Valid @RequestBody T resource, @PathVariable Long id) {
    verify(id);
    T loaded = repo.save(checkNotNull(resource));
    return ResponseEntity.ok(loaded);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
    repo.delete(verify(id));
    return ResponseEntity.noContent().build();
    }

    abstract Class<?> getDomainClass();

}
