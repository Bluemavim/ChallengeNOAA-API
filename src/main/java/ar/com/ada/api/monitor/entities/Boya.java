package ar.com.ada.api.monitor.entities;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;

@Entity
@Table(name = "boya")
public class Boya {

    @Id
    @Column(name = "boya_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boyaId;

    //La primera vez tiene que estar en nulo
    //Nota: el color de la boya es un string con los siguientes valores ROJO, VERDE, AZUL y AMARILLO
    @Column(name = "color_boya")
    private String colorBoya;

    //Coordeanada donde se dejó la boya originalmente
    @Column(name = "longitud_instalacion")
    private double longitudInstalacion;

    //Coordeanada donde se dejó la boya originalmente
    @Column(name = "latitud_instalacion")
    private double latitudInstalacion;

    @JsonIgnore
    @OneToMany(mappedBy= "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Muestra> muestras = new ArrayList<>();
    
    // Getters y Setters

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }


    public String getColorBoya() {
        return colorBoya;
    }

    public void setColorBoya(String colorBoya) {
        this.colorBoya = colorBoya;
    }

    public double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    public void setLongitudInstalacion(double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    public double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    public void setLatitudInstalacion(double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    
    public void agregarMuestra(Muestra muestra) {
        this.muestras.add(muestra);
        muestra.setBoya(this); 
    }
    /*
    public ColorBoyaEnum getColorBoya() {
        return ColorBoyaEnum.parse(colorBoya);
    }

    public void setColorBoya(ColorBoyaEnum colorBoya) {
        this.colorBoya = colorBoya.getValue();


    
    public enum ColorBoyaEnum {

        ROJO(1), AMARILLO(2), VERDE(3), AZUL(4);
    
        private final Integer value;
    
        private ColorBoyaEnum (Integer value) {
            this.value = value;
        }
        public Integer getValue() {
            return value;
         }
        public static ColorBoyaEnum  parse(Integer id) {
            ColorBoyaEnum  status = null; // Default
            for (ColorBoyaEnum  item : ColorBoyaEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
        */

        
        
}








