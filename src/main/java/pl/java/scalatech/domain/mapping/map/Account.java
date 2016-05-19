package pl.java.scalatech.domain.mapping.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name="MapAccont")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity{

    @ElementCollection
    @JoinTable(name = "account_processed", joinColumns = @JoinColumn(name = "account"))
    @MapKeyColumn(name="topic")
   private Map<String, String> topicMap = new HashMap<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "account_email_address", joinColumns = @JoinColumn(name = "account"))
    @Column(name = "email_address", nullable = false)
    @OrderColumn(name = "address_index")
    private List<String> emailAddresses = new LinkedList<>();
}
