package org.projekat.repository;

import org.projekat.model.ZavrsniRad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZavrsniRadRepository extends JpaRepository<ZavrsniRad,Long> {
}
