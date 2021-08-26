package ar.com.ada.api.monitor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.ada.api.monitor.entities.Muestra;
import java.util.*;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Integer> {

    List<Muestra> findMuestrasByBoyaId(Integer boyaId);
    
}
