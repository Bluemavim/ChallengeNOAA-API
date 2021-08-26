package ar.com.ada.api.monitor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.monitor.entities.Boya;
import ar.com.ada.api.monitor.repos.BoyaRepository;
import java.util.*;


@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;

    public Boya crearBoya(Double longitudInstalacion, Double latitudInstalacion) {

        Boya boya = new Boya();
        boya.setLatitudInstalacion(latitudInstalacion);
        boya.setLongitudInstalacion(longitudInstalacion);
        boya.setColorBoya(null); // La primera vez está nulo.
        repo.save(boya);
        return boya;   
    }

    public void actualizar(Boya boya) {
        repo.save(boya);
    }


    public Boya buscarPorId(Integer id){

        return repo.findByBoyaId(id);

}

    public List<Boya> listarTotalBoyas() {
        return repo.findAll();
    }


	public List<Boya> listarBoyasPorColor(String color) {
		return repo.findByColor(color);
	}

    public boolean validarBoyaExistente(Integer id) {
        Boya boya = repo.findByBoyaId(id);
        if (boya!= null){
            return true;
        }else
            return false;

    }

    

}







