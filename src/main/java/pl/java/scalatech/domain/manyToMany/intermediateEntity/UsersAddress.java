package pl.java.scalatech.domain.manyToMany.intermediateEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS_ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@org.hibernate.annotations.Immutable
public class UsersAddress {

    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = -9084005982286633876L;

        @Column(name = "USER_ID")
        protected Long userId;

        @Column(name = "ADDRESS_ID")
        protected Long addressId;

        public Id() {
        }

        public Id(Long userId, Long addressId) {
            this.userId = userId;
            this.addressId = addressId;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return userId.equals(that.userId) && addressId.equals(that.addressId);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return userId.hashCode() + addressId.hashCode();
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @Column(updatable = false)
    @NotNull
    protected String addedBy;

    @Column(updatable = false)
    @NotNull
    protected Date addedOn = new Date();

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID", insertable = false, updatable = false)
    protected Address address;


    public UsersAddress(String addedByUsername, User user, Address address) {

        addedBy = addedByUsername;
        this.user = user;
        this.address = address;

        id.userId = user.getId();
        id.addressId = address.getId();

        // gwarancja integracyjnosc dla dwukierunkowego
        user.getUsersAddress().add(this);
        address.getUsersAddress().add(this);
    }

    public Id getId() {
        return id;
    }

}