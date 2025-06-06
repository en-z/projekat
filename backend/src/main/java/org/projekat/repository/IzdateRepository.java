package org.projekat.repository.biblioteka;

import org.projekat.model.biblioteka.Izdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IzdateRepository extends JpaRepository<Izdate,Long> {
    List<Izdate> findByOsoba_User_Id(long id);
}
