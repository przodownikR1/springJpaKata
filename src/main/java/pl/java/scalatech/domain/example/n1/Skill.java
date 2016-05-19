package pl.java.scalatech.domain.example.n1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//view
@NamedEntityGraphs({
@NamedEntityGraph(
name = "candidate",
attributeNodes = {
@NamedAttributeNode("candidate")
}
)
})
@Data
public class Skill extends AbstractEntity
{

    private static final long serialVersionUID = 3076731294612256956L;
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="candidateId")
    JobCandidate candidate;

    @Column(name = "candidateId", insertable = false, updatable = false)
    private Integer candidateId ;

}
