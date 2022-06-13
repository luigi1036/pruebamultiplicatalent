package com.multiplicatalent.talent.controllers;

import com.multiplicatalent.talent.interfaces.IColorService;
import com.multiplicatalent.talent.models.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost: 3501", "*"})
@RestController()
@RequestMapping("/apiColores")
public class ColoresController {

    @Autowired
    private IColorService iColorService;

    //Metodo que recupera todos los colores disponibles
    @GetMapping("/colores")
    public ResponseEntity<List<Color>> getColores(){
        return new ResponseEntity<>(iColorService.getColors(), HttpStatus.OK);
    }

    //Metodo que recupera un color filtrado por Id
    @GetMapping("/color/{id}")
    public ResponseEntity<Map<String, Object>> getColorById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Color colorEncontrado = null;
        try {
             colorEncontrado = iColorService.getColorById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error Al realizar la consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(colorEncontrado != null){
            response.put("color", colorEncontrado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("mensaje", "El color no se encuentra Registrado ");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


    }

    //Metodo para crear un color

    @PostMapping("/color")
    public ResponseEntity<Map<String, Object>> crearColor(@Valid @RequestBody Color color, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        response = validacionErrores(result);
        if(response.size() != 0){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
        }else{
            response = iColorService.crearColor(color);
        }
        return new ResponseEntity<>(response, HttpStatus.OK );
    }

    //Metodo Para actualizar un colo, filtrado por su id

    @PutMapping("/color/{id}")
    public ResponseEntity<Map<String, Object>> actualizarColor(@Valid @RequestBody Color color, BindingResult result,
                                                               @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Color colorEncontrado = null;
        response = validacionErrores(result);
        if(response.size() != 0){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
        }else{
            colorEncontrado = iColorService.getColorById(id);
            if(colorEncontrado == null){
                response.put("mensaje", "No se puede actualizar el Color" );
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                response = iColorService.actualizarColor(colorEncontrado, color);
            }catch (DataAccessException e){
                response.put("mensaje", "Error Al realizar la consulta");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>(response, HttpStatus.OK );
    }

    //Metodo Para Eliminar un colo, filtrado por su id

    @DeleteMapping("/color/{id}")
    public ResponseEntity<Map<String, Object>> deleteColor(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        System.out.println("Entro a eliminar: " + id);
        try {
            iColorService.deleteColor(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error Al Eliminar el Color");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Color Eliminado con Exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Metodo que me sirve para validar si al momento de crear o actualizar un Objeto
    // tiene error.

    private Map<String, Object> validacionErrores(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(error -> "El Campo " + error.getField() + " Presenta Errores")
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return response;
        }

        return response;
    }
}
