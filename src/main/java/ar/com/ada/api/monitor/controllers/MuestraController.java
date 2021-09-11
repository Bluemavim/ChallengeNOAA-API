package ar.com.ada.api.monitor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.monitor.entities.*;
import ar.com.ada.api.monitor.models.request.InfoBoyaNueva;
import ar.com.ada.api.monitor.models.request.InfoMuestraNueva;
import ar.com.ada.api.monitor.models.response.GenericResponse;
import ar.com.ada.api.monitor.models.response.ListaMuestraColor;
import ar.com.ada.api.monitor.models.response.MuestraAlturaMinima;
import ar.com.ada.api.monitor.models.response.MuestraTomada;
import ar.com.ada.api.monitor.services.BoyaService;
import ar.com.ada.api.monitor.services.MuestraService;
import ar.com.ada.api.monitor.utils.ColoresUtils;

@RestController
public class MuestraController {

    @Autowired
    MuestraService muestraService;

    @Autowired
    BoyaService boyaService;

    @PostMapping("/api/muestras")
    public ResponseEntity<?> registrarMuestra(@RequestBody InfoMuestraNueva muestraNueva){
        
        if (boyaService.validarBoyaExistente(muestraNueva.boyaId)){
            Boya boya = boyaService.buscarPorId(muestraNueva.boyaId);
            Muestra muestra = muestraService.registrarMuestra(boya, muestraNueva.horario,muestraNueva.matricula,
            muestraNueva.latitud, muestraNueva.longitud, muestraNueva.alturaNivelMar);

            //Falta actualizar el color

            MuestraTomada r = new MuestraTomada();
            r.id = muestra.getMuestraId();
            r.colorBoya = boya.getColorBoya();
            return ResponseEntity.ok(r);

        }
        else {
            GenericResponse respuesta = new GenericResponse();
            respuesta.isOk = false;
            respuesta.message = "No se pudo registrar la muestra.";
            return ResponseEntity.badRequest().body(respuesta);
        }


    }



    @PostMapping("/api/boyas") 
    public ResponseEntity<?> crearBoya(@RequestBody InfoBoyaNueva infoBoya) {
        GenericResponse respuesta = new GenericResponse();
        Boya boyaNueva= boyaService.crearBoya(infoBoya.longitudInstalacion, infoBoya.latitudInstalacion);
        respuesta.isOk = true;
        respuesta.id = boyaNueva.getBoyaId();
        respuesta.message = "BOYA creada exitosamente.";
        return ResponseEntity.ok(respuesta);

    }



    @GetMapping("/api/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> listarMuestrasPorBoya(@PathVariable Integer boyaId){
        if (boyaService.validarBoyaExistente(boyaId)){
            List<Muestra> listaMuestras = boyaService.buscarPorId(boyaId).getMuestras();
            return ResponseEntity.ok(listaMuestras);
        }else{
           return ResponseEntity.badRequest().build(); 
    }
}

    //Reseteara el color de la luz de la boya a “AZUL” a partir de una muestra especifica
    @DeleteMapping("/api/muestras/{id}")
    public ResponseEntity<GenericResponse> resetearBoya(@PathVariable Integer id){
        GenericResponse r = new GenericResponse();
        if (muestraService.validarMuestraExistente(id)){
            Muestra muestra = muestraService.buscarPorId(id);
            boyaService.ResetearColorBoya(muestra.getBoya());
            r.isOk = true;
            r.message = "La boya ha quedado inactiva.";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "No se pudo encontrar dicha muestra.";
            return ResponseEntity.badRequest().body(r);
        }
    }


    //: que devuelva la lista de muestras de un color en formato JSON Array:
    @GetMapping("/api/muestras/colores/{color}")
    public ResponseEntity<List<ListaMuestraColor>> obtenerMuestrasPorColor(@PathVariable String colorBoya){
        return ResponseEntity.ok(muestraService.obtenerMuestrasPorColor(colorBoya));
    }

    //que devuelva la muestra donde la altura nivel del mar sea la minima para una boya 
    //en particular particular en este formato JSON(informar el horario en que ocurrió)
   
    @GetMapping("/api/muestras/minima/{idBoya}")
    public ResponseEntity<MuestraAlturaMinima> obtenerMuestraAlturaMinima(@PathVariable Integer boyaId){
        if (boyaService.validarBoyaExistente(boyaId)){
            Muestra muestra = boyaService.obtenerMuestraAlturaMinima(boyaId);
            MuestraAlturaMinima r = new MuestraAlturaMinima();
            r.color = ColoresUtils.clasificarMuestraPorAltura(muestra);
            r.alturaNivelDelMarMinima = muestra.getAlturaNivelMar();
            r.horario = muestra.getHorarioMuestra();
            return ResponseEntity.ok(r);
        }else{
           return ResponseEntity.badRequest().build(); 
    }

    }

}

