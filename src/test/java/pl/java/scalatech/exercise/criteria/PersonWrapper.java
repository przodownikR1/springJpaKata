package pl.java.scalatech.exercise.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
    @AllArgsConstructor
 public   class PersonWrapper{
        private String firstName;
        private Long version;
    }
