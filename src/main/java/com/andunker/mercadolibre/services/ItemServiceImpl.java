package com.andunker.mercadolibre.services;

import com.andunker.mercadolibre.models.DTO.ChildrenDTO;
import com.andunker.mercadolibre.models.DTO.Health;
import com.andunker.mercadolibre.models.DTO.ItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ItemServiceImpl implements IItemService {

    private  String server = "https://api.mercadolibre.com/";
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public ItemDTO findItemById(String id) {

        String itemUri = "items/" + id;
        String childrenUri =  "items/" + id + "/children";


        ItemDTO itemDTO = restTemplate.getForObject(server + itemUri, ItemDTO.class);
        ChildrenDTO[] childrens = restTemplate.getForObject(server + childrenUri, ChildrenDTO[].class);

        itemDTO.setChildrens(Arrays.asList(childrens));

        return itemDTO;
    }

    @Override
    public List<Health> getHealth() {
        return null;
    }
}
