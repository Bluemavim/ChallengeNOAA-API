package ar.com.ada.api.monitor.services;

import java.util.Date;
import java.util.List;

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
            Integer alturaNivelMar) {
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

    public List<Muestra> obtenerMuestrasPorColor(Integer alturaNivelMar){
        List<Boya> boyas = boyaService.listarTotalBoyas();
        for (Boya boya: boyas){
            List<Muestra> listaMuestras = boyaService.getMuestras();
        }

        //List<Muestra> listaMuestras = new ArrayList<>;
        //newBoyaService.getMuestras()
        //List<Integer> list = new ArrayList<>
        //for (Muestra muestra: muestras) {
            //respuesta.setPregunta(pregunta);
   }

    public boolean validarMuestraExistente(Integer id) {
        Muestra muestra = repo.findByMuestraId(id);
        if (muestra!= null){
            return true;
        }else
            return false;

    }

    public Muestra buscarPorId(Integer id){
        return repo.findByMuestraId(id);

    }

    



}
