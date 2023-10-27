package poroto.po.cuenta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import poroto.po.cuenta.entity.Cuenta;

public interface CuentaRepo extends JpaRepository<Cuenta,Long>{
    
}
