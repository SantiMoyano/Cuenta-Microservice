* guarda los nulls en la bd

Puerto: 8082

• Registrar monopatín en mantenimiento (debe marcarse como no disponible para su uso)
> PUT /monopatin/mantenimiento/:id
> hay que anotarle los kilometros del service

• Registrar fin de mantenimiento de monopatín
> PUT /monopatin/mantenimiento/:id/listo
> hay que anotarle los kilometros del service


• Agregar monopatín
> POST /monopatin

```
body
 {
        "latitud": 150,
        "longitud": 165,
        "encendido": false,
        "en_taller": false,
        "id_parada": 1,
        "km_ultimo_service": 101
    }
```

• Quitar monopatín
> delete monopatin/:id

• Ubicar monopatín en parada (opcional)
• Generar reporte de uso de monopatines por kilómetros
• Generar reporte de uso de monopatines por tiempo con pausas
• Generar reporte de uso de monopatines por tiempo sin pausas
