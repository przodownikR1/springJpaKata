package pl.java.scalatech.domain.lock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity(name = "repository")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repository extends AbstractEntity{

    private String name;

    public Repository(String name){
        this.name = name;
    }

    @OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commit> commits = new ArrayList<>();
}
