package poroto.po.viaje.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


// @Service
@RestController
public class CuentaService {
    private final String direccion ="http://localhost:8083/tieneSaldo";

    private final RestTemplate rest;

    @Autowired
    public CuentaService(RestTemplate rest){
        this.rest=rest;
    }

    @GetMapping
    public Float dameSaldo(Long cuenta){
        return rest.getForEntity(direccion+"/"+cuenta, Float.class).getBody();
       }

    @PutMapping
    public String apagar(Long idMono){

        String r= rest.exchange(direccion+"/apagar/"+idMono, HttpMethod.PUT, null, String.class).getBody();
        System.out.println(r);
        return r;
    }

    
}
