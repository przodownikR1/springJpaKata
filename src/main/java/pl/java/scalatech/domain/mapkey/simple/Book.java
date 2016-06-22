package pl.java.scalatech.domain.mapkey.simple;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Table(name="BOOK_MAP")
public class Book extends AbstractEntity{

private static final long serialVersionUID = 2939809506799833329L;
String title;
@ElementCollection
@Column(name = "reference")
@MapKeyColumn(name="index")
Map<String, String> indexMap = com.google.common.collect.Maps.newHashMap();

/*@SortNatural
@Column(name = "review")
Set<String> reviews = Sets.newTreeSet();*/


}