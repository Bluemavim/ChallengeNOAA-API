package ar.com.ada.api.monitor.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.api.monitor.models.request.ColorNuevoBoya;
import ar.com.ada.api.monitor.models.request.InfoBoyaNueva;
import ar.com.ada.api.monitor.models.response.GenericResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ar.com.ada.api.monitor.entities.*;
import ar.com.ada.api.monitor.services.*;
import java.util.*;

@RestController
public class BoyaController {

    @Autowired
    BoyaService boyaService;

    //POST /boyas : que permita la creación boyas
    @PostMapping("/api/boyas") 
    public ResponseEntity<?> crearBoya(@RequestBody InfoBoyaNueva infoBoya) {
        GenericResponse respuesta = new GenericResponse();
        Boya boyaNueva= boyaService.crearBoya(infoBoya.longitudInstalacion, infoBoya.latitudInstalacion);
        respuesta.isOk = true;
        respuesta.id = boyaNueva.getBoyaId();
        respuesta.message = "BOYA creada exitosamente.";
        return ResponseEntity.ok(respuesta);

    }
   //GET /boyas : que devuelva las boyas SIN las muestras.
    @GetMapping("/api/boyas") 
    public ResponseEntity<List<Boya>> listarTotalBoyas(){
        List<Boya> listaBoyas = boyaService.listarTotalBoyas();
        return ResponseEntity.ok(listaBoyas);
    }

    //GET /boyas/{id} : que devuelva la info de una boya en particular(SIN las muestras)
    @GetMapping("/api/boyas/{id}") 
    public ResponseEntity<Boya> getInfoBoya(@PathVariable Integer id){
        Boya boya = boyaService.buscarPorId(id);
        return ResponseEntity.ok(boya);    }


    //PUT /boyas/{id} : que actualice SOLO el color de luz de la boya. El request esperado será:
    @PutMapping("/api/boyas/{id}/color")
    public ResponseEntity<GenericResponse> modificarColor(@PathVariable Integer id, @RequestBody ColorNuevoBoya colorNuevoBoya  ){
        Boya boya = boyaService.buscarPorId(id);//el método buscarPorId devuelve el objeto
        if (boya == null){
            return ResponseEntity.notFound().build();
        }
        else{

            boya.setColorBoya(colorNuevoBoya.color);
            GenericResponse respuesta = new GenericResponse();
            respuesta.isOk = true;
            respuesta.message = "El color de la boya ha sido actualizado";
            return ResponseEntity.ok(respuesta);

        }
        
        }

//POST /boyas : que permita la creación boyas
//RequestBody: {
//“longitudInstalacion”: 34.8877,
//“latitudInstalacion”: 78.230
//}
//GET /boyas : que devuelva las boyas SIN las muestras.
//GET /boyas/{id} : que devuelva la info de una boya en particular(SIN las muestras)
//PUT /boyas/{id} : que actualice SOLO el color de luz de la boya. El request esperado será:
//{
//“color”: “ROJO”

    
}
