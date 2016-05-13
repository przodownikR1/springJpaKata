package pl.java.scalatech.repository.inheritence.single;

import java.io.Serializable;

import org.codehaus.groovy.ant.Groovy;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.single.GroovyTask;

public interface GroovyTaskRepo extends JpaRepository<GroovyTask, Long>{

}
