package pl.java.scalatech.domain.lock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity(name = "Commit")
@Table(name = "commit")
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commit extends AbstractEntity{


    private boolean review;

    @ManyToOne(fetch = FetchType.LAZY)
    private Repository repository;

    @ElementCollection
    @CollectionTable(name = "commit_change", joinColumns=@JoinColumn(name="commit_id"))
    @OrderColumn(name = "index_id")
    private List<Change> changes = new ArrayList<>();


    public Commit(Repository repository){
        this.repository  =repository;
    }
}