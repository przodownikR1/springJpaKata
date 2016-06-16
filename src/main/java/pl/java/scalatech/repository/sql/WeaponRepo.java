package pl.java.scalatech.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.sql.Weapon;

public interface WeaponRepo extends JpaRepository<Weapon,Long>{

}
