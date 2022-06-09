package com.multiplicatalent.talent.controllers;

import com.multiplicatalent.talent.interfaces.IColorService;
import com.multiplicatalent.talent.models.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/apiColores")
public class ColoresController {

    @Autowired
    private IColorService iColorService;

    @GetMapping("/colores")
    public List<Color> getColores(){
        return iColorService.getColor();
    }

    @GetMapping("/color/{id}")
    public Color getColor(){
        return null;
    }

    @PostMapping("/color")
    public Color crearColor(){
        return null;
    }

    @PutMapping("/color/{id}")
    public Color actualizarColor(){
        return null;
    }

    @DeleteMapping("/color/{id}")
    public Color deleteColor(){
        return null;
    }
}
