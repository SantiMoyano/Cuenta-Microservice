package poroto.po.viaje.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_viaje;

    @Column
    private Long id_cuenta;

    @Column
    private String fecha_inicio;

    @Column
    private String fecha_fin;

    @Column
    private String hora_inicio;

    @Column
    private String hora_fin;

    @Column
    private Integer kms;

    @Column
    private Long monopatin;

    public Viaje() {
    }

    public Viaje(Long id_cuenta, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin,
            Integer kms, Long monopatin) {
        this.id_cuenta = id_cuenta;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.kms = kms;
        this.monopatin = monopatin;
    }
    // public Viaje(Long id_cuenta,String fecha_inicio, String hora_inicio, Long monopatin) {
    //     this.id_cuenta = id_cuenta;
    //     this.fecha_inicio = fecha_inicio;
    //     this.fecha_fin = null;
    //     this.hora_inicio = hora_inicio;
    //     this.hora_fin = null;
    //     this.kms = 0;
    //     this.monopatin = monopatin;
    // }

    public Long getId_viaje() {
        return id_viaje;
    }

    public void setId_viaje(Long id_viaje) {
        this.id_viaje = id_viaje;
    }

    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Long getMonopatin() {
        return monopatin;
    }

    public void setMonopatin(Long monopatin) {
        this.monopatin = monopatin;
    }

    @Override
    public String toString() {
        return "Viaje [id_viaje=" + id_viaje + ", id_cuenta=" + id_cuenta + ", fecha_inicio=" + fecha_inicio
                + ", fecha_fin=" + fecha_fin + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", kms="
                + kms + ", monopatin=" + monopatin + "]";
    }

    

}
