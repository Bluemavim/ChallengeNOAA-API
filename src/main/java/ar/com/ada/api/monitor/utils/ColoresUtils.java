package ar.com.ada.api.monitor.utils;
import ar.com.ada.api.monitor.entities.Muestra;

public class ColoresUtils {

    public static String clasificarMuestraPorAltura(Muestra muestra){
        Double alt = muestra.getAlturaNivelMar();
        if (alt <= -50 || alt >= 50){
            return "AMARILLO";
        }else if (alt <= -100 || alt >= 100){
            return "ROJO";
        }else{
            return "VERDE";
        }     
    

        ////Validar que una boya de color ROJO tenga la ultima muestra en -100 o +100

}
}
