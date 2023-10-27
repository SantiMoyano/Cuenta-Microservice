package poroto.po.viaje.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MonopatinService {
    private final String direccion ="http://localhost:8082/monopatin";

    private final RestTemplate rest;

    @Autowired
    public MonopatinService(RestTemplate rest){
        this.rest=rest;
    }

  
    @PutMapping("/apagar")
    public String apagar(Long idMono){

        String r= rest.exchange(direccion+"/apagar/"+idMono, HttpMethod.PUT, null, String.class).getBody();
        System.out.println(r);
        return r;
    }

    @PutMapping("/encender")
    public String encender(Long idMono){

        String r= rest.exchange(direccion+"/encender/"+idMono, HttpMethod.PUT, null, String.class).getBody();
        System.out.println(r);
        return r;
    }


    public Boolean estaListoParaUsar(Long idMono) {
        Boolean l=rest.getForEntity(direccion+"/estaListoParaUsar/"+idMono, Boolean.class).getBody();
        System.out.println("listo: "+ l);
        return l;
        
    }



    
}
