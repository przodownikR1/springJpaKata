package pl.java.scalatech.domain.generator;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class GeneratorOne extends IdEntity{

    private String go;
}
