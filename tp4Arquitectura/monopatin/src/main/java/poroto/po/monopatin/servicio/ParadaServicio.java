package poroto.po.monopatin.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.client.RestTemplate;

// import poroto.po.viaje.entity.Monopatin;

@Service
// @RestController
// @RequestMapping("/dameUno")
public class ParadaServicio {
    private final String direccion = "http://localhost:8093/parada";

    private final RestTemplate rest;

    @Autowired
    public ParadaServicio(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping
    public ResponseEntity<String> dameParadas() {
        return rest.getForEntity(direccion, String.class);
    }

}
