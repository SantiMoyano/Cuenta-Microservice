package poroto.po.parada.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_parada;

    @Column
    private Float latitud;

    @Column
    private Float longitud;

    public Parada() {
    }

    public Parada(Float latitud, Float longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getId_parada() {
        return id_parada;
    }

    public void setId_parada(Long id_parada) {
        this.id_parada = id_parada;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    
    
}
