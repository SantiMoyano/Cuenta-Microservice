package poroto.po.parada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poroto.po.parada.entity.Parada;

public interface ParadaRepo extends JpaRepository<Parada,Long>{
    
}
