package pl.java.scalatech.domain.mapIds;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class  UserId  implements  Serializable 
{
  String firstName;
  String lastName;
}