package pl.java.scalatech.domain.ids;

import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
//@NaturalIdCache //Work only if second cache is enabled
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@Data
@Builder
public class Company extends AbstractEntity{
    private static final long serialVersionUID = -5255398081358190770L;
    @NaturalId
    private String nip;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Company other = (Company) obj;
        if (nip == null) {
            if (other.nip != null) {
                return false;
            }
        } else if (!nip.equals(other.nip)) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (nip == null ? 0 : nip.hashCode());
        return result;
    }

}
