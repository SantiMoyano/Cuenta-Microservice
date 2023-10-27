package poroto.po.monopatin.entity;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_monopatin;

    @Column
    private Float latitud;

    @Column
    private Float longitud;

    private boolean encendido;

    @Column
    private boolean en_taller;

    @Column
    private Long id_parada;

    @Column
    private Integer km_ultimo_service;

    public Monopatin() {
    }

    public Monopatin(Long id_monopatin, Float latitud, Float longitud, boolean encendido, boolean en_taller,
            Long id_parada,
            Integer km_ultimo_service) {
        this.id_monopatin = id_monopatin;
        this.latitud = latitud;
        this.longitud = longitud;
        this.encendido = encendido;
        this.en_taller = en_taller;
        this.id_parada = id_parada;
        this.km_ultimo_service = km_ultimo_service;
    }

    public Long getId_monopatin() {
        return id_monopatin;
    }

    public void setId_monopatin(Long id_monopatin) {
        this.id_monopatin = id_monopatin;
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

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    public boolean isEn_taller() {
        return en_taller;
    }

    public void setEn_taller(boolean en_taller) {
        this.en_taller = en_taller;
    }

    public Long getId_parada() {
        return id_parada;
    }

    public void setId_parada(Long parada) {
        this.id_parada = parada;
    }

    public Integer getKm_ultimo_service() {
        return km_ultimo_service;
    }

    public void setKm_ultimo_service(Integer km_ultimo_service) {
        this.km_ultimo_service = km_ultimo_service;
    }

    public Long estaEnAlguna(List<Parada> paradas) {
        for (Parada parada : paradas) {
            if (this.latitud.equals(parada.getLatitud()) && this.longitud.equals(parada.getLongitud())) {
                return parada.getId();
            }
        }
        return null;
    }

    public boolean estaEstacionado(String paradas) {
        ObjectMapper jsonMap = new ObjectMapper();
        JsonNode array;
        try {
            array = jsonMap.readTree(paradas);
            for (JsonNode nodo : array) {

                Long id = nodo.get("id_parada").asLong();
                Float latitud = (float) nodo.get("latitud").asDouble();
                Float longitud = (float) nodo.get("longitud").asDouble();
                Float distancia = (float) Math.sqrt((Math.pow((latitud - this.latitud), 2))
                        + Math.pow((longitud - this.longitud), 2)) * 100;
                if (distancia==0) return true;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
