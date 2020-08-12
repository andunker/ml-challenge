package com.andunker.mercadolibre.controllers;

import com.andunker.mercadolibre.models.DTO.Health;
import com.andunker.mercadolibre.models.DTO.ItemDTO;
import com.andunker.mercadolibre.services.IItemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ItemController {

    private final IItemService itemService;

    public ItemController(IItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value="/items/{id}",  method= RequestMethod.GET, produces = "application/json")
    public ItemDTO findItemById(@PathVariable(value="id") String id) {
        try {
            return	itemService.findItemById(id);
        }
        catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value="/health",  method= RequestMethod.GET, produces = "application/json")
    public List<Health> getHealth() {

        try {
            return	itemService.getHealth();
        }
        catch (Exception e){
            return null;
        }

    }

}