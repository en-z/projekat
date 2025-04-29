package org.projekat.repositorys;

import org.projekat.model.ZavrsniRad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZavrsniRadrepository extends JpaRepository<ZavrsniRad,Long> {
}
