package com.andunker.mercadolibre.services;

import com.andunker.mercadolibre.models.DTO.Health;
import com.andunker.mercadolibre.models.DTO.ItemDTO;

import java.util.List;

public interface IItemService {
    public ItemDTO findItemById(String id);
    public List<Health> getHealth();
}
