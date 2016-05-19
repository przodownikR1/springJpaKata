package pl.java.scalatech.domain.filter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name="pupil")
@FilterDef(name="pupilFilter", parameters={
		@ParamDef( name="maxAge", type="integer" ),
		@ParamDef( name="minAge", type="integer" ),
		@ParamDef( name="minNumber", type="integer")
})
@Filters( {
    @Filter(name="pupilFilter", condition=":minAge <= age and :maxAge >= age"),
    @Filter(name="pupilFilter", condition=":minNumber <= number")
} )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pupil extends AbstractEntity{


    private static final long serialVersionUID = -3900593686248262939L;
    @Column(name="age")
	private Integer age;
	@Column(name="pupilName")
	private String name;
	@Column(name="pupilNumber")
	private Integer number;

}