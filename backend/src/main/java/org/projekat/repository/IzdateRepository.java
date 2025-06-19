package org.projekat.repository;

import org.projekat.model.Izdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IzdateRepository extends JpaRepository<Izdate,Long> {
    List<Izdate> findByOsoba_User_Id(long id);
}
