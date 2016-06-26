package pl.java.scalatech.domain.bags;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserColl extends AbstractEntity{


    private static final long serialVersionUID = 7977087219237782160L;
    @ElementCollection
    @CollectionTable(
    name = "PHONES",
    joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "PHONE_NUMBER")

   //   @OrderColumn
    private Set<String> phones = Sets.newHashSet();


}
