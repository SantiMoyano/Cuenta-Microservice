package poroto.po.monopatin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
import poroto.po.monopatin.entity.Monopatin;
import poroto.po.monopatin.entity.Parada;
import poroto.po.monopatin.repsitory.MonopatinRepo;
import poroto.po.monopatin.response.Distancia;
import poroto.po.monopatin.servicio.CuentaServicio;
import poroto.po.monopatin.servicio.ParadaServicio;
import poroto.po.monopatin.servicio.ViajeServicio;

@RestController
@RequestMapping("/monopatin")
// @Api(value = "MonopatinVieja", description = "Operaciones con monopatin en MyAPI")
public class MonopatinController {
    @Autowired
    private MonopatinRepo monoRepo;

    @Autowired
    private ParadaServicio servicio;

    @Autowired
    private CuentaServicio cuentaServicio;

    @Autowired
    private ViajeServicio viajeServicio;

    @GetMapping
    public List<Monopatin> dameMonos() {
        return monoRepo.findAll();
    }

    /**
     * @param m lo resibe del post
     * @return Monopatin
     */
    @PostMapping
    // @ApiOperation(value = "Agregarrecurso por ID", response = Monopatin.class)
    public Monopatin registrarMono(@RequestBody Monopatin m) {
        return monoRepo.save(m);
    }


    @PutMapping("/encender/{idMono}")
    public String encender(@PathVariable Long idMono) {

        Monopatin m = monoRepo.findById(idMono).get();
        if (m != null) {
            m.setEncendido(true);
            monoRepo.save(m);
            return "encendido Correctamente con la cuenta: ";
        } else
            return "Hubo un problema";


    }

    @PutMapping("/apagar/{id}")
    public String apagar(@PathVariable Long id) {
        Monopatin m = monoRepo.findById(id).get();

        // harcodeado!!!!!
        // String paradas = servicio.dameParadas().getBody();
        // boolean estacionado = m.estaEstacionado(paradas);
        boolean estacionado = true;

        if (estacionado) {
            m.setEncendido(false);
            monoRepo.save(m);
            return "se estaciono correctamente";
        } else
            return "no esta en un parada";

    }

    @PutMapping("/mantenimiento/{id}")
    public Monopatin ponerEnReparacion(@PathVariable Long id) {
        Monopatin m = monoRepo.findById(id).get();
        m.setEn_taller(true);
        return monoRepo.save(m);

    }

    /**
     * @param id
     * @return Monopatin
     */
    @PutMapping("/mantenimiento/{id}/listo")
    public Monopatin ponerEnLaCalle(@PathVariable Long id) {
        Monopatin m = monoRepo.findById(id).get();
        m.setEn_taller(false);
        return monoRepo.save(m);

    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        monoRepo.deleteById(id);
    }

    @GetMapping("/paradas")
    public List<Monopatin> dameParadas() {
        List<Parada> listaParadas = new ArrayList<Parada>();
        List<Monopatin> resultado = new ArrayList<Monopatin>();
        String body = servicio.dameParadas().getBody();
        ObjectMapper jsonMap = new ObjectMapper();
        JsonNode array;
        try {
            array = jsonMap.readTree(body);
            for (JsonNode nodo : array) {

                Long id = nodo.get("id_parada").asLong();
                Float latitud = (float) nodo.get("latitud").asDouble();
                Float longitud = (float) nodo.get("longitud").asDouble();
                Parada p = new Parada(id, latitud, longitud);

                listaParadas.add(p);
            }
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Monopatin> monos = this.dameMonos();
        for (Monopatin mono : monos) {
            Long parada = mono.estaEnAlguna(listaParadas);
            if (parada != null) {
                mono.setId_parada(parada);
                monoRepo.save(mono);
                resultado.add(mono);
            }

        }
        return resultado;
    }

    @GetMapping("/paradaCercana/{idMono}")
    public List<Distancia> dameParadaCercana(@PathVariable Long idMono) {
        // public List<Monopatin> dameParadas() {
        List<Distancia> resultado = new ArrayList<>();
        Monopatin monopatin = monoRepo.findById(idMono).get();
        String body = servicio.dameParadas().getBody();
        ObjectMapper jsonMap = new ObjectMapper();
        JsonNode array;
        try {
            array = jsonMap.readTree(body);
            for (JsonNode nodo : array) {

                Long id = nodo.get("id_parada").asLong();
                Float latitud = (float) nodo.get("latitud").asDouble();
                Float longitud = (float) nodo.get("longitud").asDouble();
                Float distancia = (float) Math.sqrt((Math.pow((latitud - monopatin.getLatitud()), 2))
                        + Math.pow((longitud - monopatin.getLongitud()), 2)) * 100;

                Distancia resp = new Distancia(idMono, id, distancia);
                resultado.add(resp);
            }
            Comparator<Distancia> comparador = Comparator.comparing(Distancia::getDistancia);
            Collections.sort(resultado, comparador);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultado;
    }

    @GetMapping("/estaListoParaUsar/{id}")
    public Boolean estaLista(@PathVariable Long id){
        Monopatin m=this.monoRepo.getById(id);
        if (m==null) return false;
        return (!m.isEn_taller()&&!m.isEncendido());
    }

}
