package com.multiplicatalent.talent.services;

import com.multiplicatalent.talent.interfaces.IColorService;
import com.multiplicatalent.talent.models.Color;
import com.multiplicatalent.talent.repositories.IColoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpColorService implements IColorService {

    @Autowired
    private IColoresRepository iColoresRepository;

    @Override
    public List<Color> getColor() {
        return iColoresRepository.findAll();
    }
}
