package ar.com.ada.api.monitor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.monitor.entities.Boya;
import ar.com.ada.api.monitor.entities.Muestra;
import ar.com.ada.api.monitor.models.response.ListaMuestraColor;
import ar.com.ada.api.monitor.repos.MuestraRepository;
import ar.com.ada.api.monitor.utils.*;


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

    public List<ListaMuestraColor> obtenerMuestrasPorColor(String color){
        List<ListaMuestraColor> ListaMuestrasPorColor = new ArrayList<>();
        ListaMuestraColor muestraPorColor= new ListaMuestraColor();
        
        for (Muestra muestra : repo.findAll()) {            
            
            String tipoMuestra = ColoresUtils.clasificarMuestraPorAltura(muestra);
            if (tipoMuestra == color){
                muestraPorColor.boyaId = muestra.getBoya().getBoyaId();
               //muestraPorColor.horario= muestra.getHorarioMuestra();
                muestraPorColor.alturaNivelDelMar = muestra.getAlturaNivelMar();                    
                ListaMuestrasPorColor.add(muestraPorColor);
            }
        }
        return ListaMuestrasPorColor;
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


    public HashMap<Muestra, Integer> mapearMuestra(Muestra muestra, Integer tipo){
        HashMap<Muestra, Integer> mapaMuestras = new HashMap<Muestra, Integer>();
        mapaMuestras.put(muestra, tipo);
        return mapaMuestras;        
    }



}



 