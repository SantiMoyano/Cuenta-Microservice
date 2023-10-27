package poroto.po.parada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poroto.po.parada.entity.Parada;
import poroto.po.parada.repository.ParadaRepo;

@RestController
// @RequestMapping("/parada")
public class ParadaController {

    @Autowired
    private ParadaRepo repo;

    @GetMapping("/parada")
    public List<Parada> dameParadas(){
     return repo.findAll();
    }

    
    
}
