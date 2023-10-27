package poroto.po.viaje.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import poroto.po.viaje.entity.Viaje;
import poroto.po.viaje.repsitory.ViajeRepo;
import poroto.po.viaje.service.CuentaService;
import poroto.po.viaje.service.MonopatinService;

@RestController
// @RequestMapping("/viaje")
public class ViajeController {
    @Autowired
    private ViajeRepo viajeRepo;

    @Autowired
    private MonopatinService monoService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/dameViajes")
    public List<Viaje> dameViajes() {
        return viajeRepo.findAll();
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/iniciar")
    public String iniciarViaje(@RequestBody String v) throws JsonMappingException, JsonProcessingException {

        ObjectMapper jsonMap = new ObjectMapper();
        Map<String, Integer> data = jsonMap.readValue(v, Map.class);
        Long idMono = (long) data.get("idMono");
        Long idCuenta = (long) data.get("idCuenta");

        // Float saldo = cuentaService.dameSaldo(idCuenta);
        Float saldo = (float) 4;

        if (saldo > (float) 0) {
            if (monoService.estaListoParaUsar(idMono)) {

                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fecha = fechaActual.format(formatter);
                LocalTime horaActual = LocalTime.now();
                DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                String hora = horaActual.format(formatterHora);
                Viaje viaje = new Viaje(idCuenta, fecha,  "", hora,"", (Integer) 0, idMono);
                this.viajeRepo.save(viaje);
                String r = monoService.encender(idMono);
                return r;
            } else
                return "Monopatin no disponible";
        } else if (saldo == (float) -1)
            return "Cuenta no esta habilitada";
        else {
            return "Saldo insuficiente";
        }
    }

    @PutMapping("/terminar/{idMono}/{kmts}")
    public String terminarViaje(@PathVariable Long idMono,@PathVariable Integer kmts) {
        Viaje v = viajeRepo.dameViajeXMono(idMono);
        if (v == null)
            return "No existe tal viaje";
        String x = monoService.apagar(idMono);
        if (x.equals("se estaciono correctamente")) {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha = fechaActual.format(formatter);
            LocalTime horaActual = LocalTime.now();
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            String hora = horaActual.format(formatterHora);
            v.setFecha_fin(fecha);
            v.setHora_fin(hora);
            v.setKms(kmts);

            this.viajeRepo.save(v);

            // this.viajeRepo.delete(v);
        }
        return x;
    }

}
