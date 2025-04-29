package org.projekat.repositorys;

import org.projekat.model.Univerzitet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniverzitetRepository extends JpaRepository<Univerzitet,Long> {
}
