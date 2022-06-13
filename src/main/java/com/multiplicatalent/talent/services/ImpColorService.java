package com.multiplicatalent.talent.services;

import com.multiplicatalent.talent.interfaces.IColorService;
import com.multiplicatalent.talent.models.Color;
import com.multiplicatalent.talent.repositories.IColoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImpColorService implements IColorService {

    @Autowired
    private IColoresRepository iColoresRepository;

    @Override
    public List<Color> getColors() {
        return iColoresRepository.findAll();
    }

    @Override
    public Map<String, Object> crearColor(Color color) {
        Color colorNuevo = null;
        Map<String, Object> response = new HashMap<>();
        colorNuevo = iColoresRepository.save(color);
        response.put("color", colorNuevo);
        response.put("mensaje", "El Color se creo Correctamente");
        return response;
    }

    @Override
    public Color getColorById(Long id) {
        return iColoresRepository.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> actualizarColor(Color colorEncontrado, Color color) {
        Map<String, Object> response = new HashMap<>();
        colorEncontrado.setName(color.getName());
        colorEncontrado.setColor(color.getColor());
        colorEncontrado.setPantone(color.getPantone());
        colorEncontrado.setYear(color.getYear());

        Color colorActualizado = iColoresRepository.save(colorEncontrado);
        response.put("color", colorActualizado);
        response.put("mensaje", "Color Actualizado Correctamente");
        return response;
    }

    @Override
    public void deleteColor(Long id) {
         iColoresRepository.deleteById(id);
    }


}
