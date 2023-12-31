package poroto.po.cuenta.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cuenta;

    private Integer mercado_pago;
    private Float saldo;
    private LocalDate fecha_alta;
    private boolean habilitada;

//  meter entity de Usuario en la carpeta una vez terminado 
//  @ManyToMany
//  private List<Usuario> usuarios;
    
    public Cuenta() {
    }
    public Cuenta(Integer mercado_pago, Float saldo, LocalDate fecha_alta) {
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

    public LocalDate getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
