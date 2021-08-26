package ar.com.ada.api.monitor.entities;
import javax.persistence.*;
import java.util.*;


//Posee la información que transmite la boya
@Entity
@Table(name = "Muestra")
public class Muestra {

    //hacer que sea autoincremental
    @Id
    @Column(name = "muestra_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer muestraId;

    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    @Column(name = "horario_muestra")
    private Date horarioMuestra;

    //Embarcacion: string, matrícula de la embarcación, esto solo si hay un barco en
    //la cercanía o el barco está transmitiendo. Por lo tanto este es opcional.
    @Column(name = "matricula_embarcacion")
    private String matriculaEmbarcacion;

    //Coordeanada donde se dejó la boya originalmente
    @Column(name = "latitud")
    private double latitud;
    
    //Coordeanada donde se dejó la boya originalmente
    @Column(name = "longitud")
    private double longitud;
 
    @Column(name = "altura_nivel_mar")
    private double alturaNivelMar;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
    }

    public Date getHorarioMuestra() {
        return horarioMuestra;
    }

    public void setHorarioMuestra(Date horarioMuestra) {
        this.horarioMuestra = horarioMuestra;
    }

    public String getMatriculaEmbarcacion() {
        return matriculaEmbarcacion;
    }

    public void setMatriculaEmbarcacion(String matriculaEmbarcacion) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getAlturaNivelMar() {
        return alturaNivelMar;
    }

    public void setAlturaNivelMar(double alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }


    
}





//Esta posee la información que transmite la boya
//Id de Muestra: number: registro único que identifique a una muestra, autoincremental.
//Id de Boya: number: numero de boya a la que pertenece la muestra
//Horario Muestra: Fecha y hora del horario de la muestra
//Matricula Embarcacion: string, matrícula de la embarcación, esto solo si hay un barco en
//la cercanía o el barco está transmitiendo. Por lo tanto este es opcional.
//Longitud: double, coordenada actual de la boya(no necesariamente es la misma en la quese instaló).
//Latitud: double , coordenada actual de la boya
//Altura al nivel del mar: double, número que especifica a qué nivel del mar está la boya en
//METROS. Ej 0 = significa que está a 0 metros del nivel del mar. -10 metros significa que esta
//“hundida” -10 metros y un valor de 20ms es que esta 20 metros arriba del nivel del mar.
//Nota 1: una boya genera varias muestras, y una muestra corresponde solo a una boya