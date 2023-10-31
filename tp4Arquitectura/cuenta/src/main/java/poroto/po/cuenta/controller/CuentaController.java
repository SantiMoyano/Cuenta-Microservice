package poroto.po.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poroto.po.cuenta.entity.Cuenta;
import poroto.po.cuenta.repositorio.CuentaRepo;

@RestController
// @RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaRepo repo;

    @GetMapping
    public List<Cuenta> dameCuentas(){
        return repo.findAll();
    }

    @GetMapping("/tieneSaldo/{id}")
    public Float tieneSaldo(@PathVariable Long id){
    //  return repo.findAll();
        Cuenta cuenta=repo.findById(id).get();
        Float saldo=cuenta.getSaldo();
        if (cuenta.isHabilitada()) return saldo;
        else return (float) -1;

    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return repo.save(cuenta);
    }
    
}
