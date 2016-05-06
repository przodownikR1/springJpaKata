package pl.java.scalatech.domain.constructorResult;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@NamedNativeQuery(name = "findWithTodoResultSetMapper", query = "SELECT id, description FROM TODO where description like ?1", resultSetMapping = "TodoResultSetMapper")
@SqlResultSetMapping(name = "TodoResultSetMapper", classes = @ConstructorResult(targetClass = pl.java.scalatech.pojo.TodoDTO.class, columns = {
		@ColumnResult(name = "id", type = Long.class),
		@ColumnResult(name = "description") }))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends AbstractEntity{
    private static final long serialVersionUID = -3401140675132906970L;
    private String summary;
	private String description;
}