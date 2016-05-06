package pl.java.scalatech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TodoDTO {

    private Long id;

    private String description;
}
