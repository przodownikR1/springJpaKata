package pl.java.scalatech.domain.lock;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Change {

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "diff", nullable = false)
    private String diff;

}