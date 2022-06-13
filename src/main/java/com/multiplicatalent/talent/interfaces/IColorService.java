package com.multiplicatalent.talent.interfaces;

import com.multiplicatalent.talent.models.Color;

import java.util.List;
import java.util.Map;

public interface IColorService {

    public List<Color> getColors();
    public Map<String, Object> crearColor(Color color);
    public Color getColorById(Long id);
    public Map<String, Object> actualizarColor(Color colorEncontrado, Color color);
    public void deleteColor(Long id);
}
