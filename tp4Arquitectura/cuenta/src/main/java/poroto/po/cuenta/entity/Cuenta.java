package poroto.po.cuenta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cuenta;

    private Integer mercado_pago;
    private Float saldo;
    private String fecha_alta;
    private boolean habilitada;
    public Cuenta() {
    }
    public Cuenta(Integer mercado_pago, Float saldo, String fecha_alta) {
        this.mercado_pago = mercado_pago;
        this.saldo = saldo;
        this.fecha_alta = fecha_alta;
        this.habilitada=true;
    }
    public Long getId_cuenta() {
        return id_cuenta;
    }
    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    public Integer getMercado_pago() {
        return mercado_pago;
    }
    public void setMercado_pago(Integer mercado_pago) {
        this.mercado_pago = mercado_pago;
    }
    public Float getSaldo() {
        return saldo;
    }
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
    public String getFecha_alta() {
        return fecha_alta;
    }
    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    public boolean isHabilitada() {
        return habilitada;
    }
    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    
    
}
