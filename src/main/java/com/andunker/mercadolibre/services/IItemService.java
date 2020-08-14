package com.andunker.mercadolibre.services;

import com.andunker.mercadolibre.models.DTO.Health;
import com.andunker.mercadolibre.models.DTO.ItemDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface IItemService {
    public ItemDTO findItemById(String id) throws JsonProcessingException;
    public List<Health> getHealth() throws IOException;
}
