package ar.com.ada.api.monitor.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.monitor.entities.Boya;
import ar.com.ada.api.monitor.entities.Muestra;
import ar.com.ada.api.monitor.repos.MuestraRepository;

@Service
public class MuestraService {

    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public Muestra registrarMuestra(Boya boya, Date horario, String matricula, double latitud, double longitud,
            int alturaNivelMar) {
        Muestra muestra = new Muestra();
        muestra.setBoya(boya);
        muestra.setHorarioMuestra(horario);
        muestra.setMatriculaEmbarcacion(matricula);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setAlturaNivelMar(alturaNivelMar);
        repo.save(muestra);
        //Relación bidireccional.
        boya.agregarMuestra(muestra);
        return muestra;
    }

}
