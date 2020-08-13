package com.andunker.mercadolibre.services;

import com.andunker.mercadolibre.models.DAO.IItemDao;
import com.andunker.mercadolibre.models.DTO.ChildrenDTO;
import com.andunker.mercadolibre.models.DTO.Health;
import com.andunker.mercadolibre.models.DTO.ItemDTO;
import com.andunker.mercadolibre.models.entities.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private IItemDao itemDAO;

    private  String server = "https://api.mercadolibre.com/";
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper Obj = new ObjectMapper();


    @Override
    @Transactional
    public ItemDTO findItemById(String id) throws JsonProcessingException {


        ItemDTO itemDTO = new ItemDTO();

        Iterable<Item> cacheItem = itemDAO.findAllById(Collections.singleton(id));
        if (cacheItem.iterator().hasNext()){

            String cacheItemValue = cacheItem.iterator().next().getValue();
            return Obj.readValue(cacheItemValue, ItemDTO.class);
        }


        String itemUri = "items/" + id;
        String childrenUri =  "items/" + id + "/children";

        itemDTO = restTemplate.getForObject(server + itemUri, ItemDTO.class);
        ChildrenDTO[] childrens = restTemplate.getForObject(server + childrenUri, ChildrenDTO[].class);

        itemDTO.setChildrens(Arrays.asList(childrens));

        Item item = new Item();
        String jsonStrItem = Obj.writeValueAsString(itemDTO);

        item.setId(id);
        item.setValue(jsonStrItem);
        item.setCreatedAt(new Date());
        item.setModifiedAt(new Date());

        itemDAO.save(item);

        return itemDTO;
    }

    @Override
    public List<Health> getHealth() {
        List<Health> healths = new ArrayList<>();
        return healths;
    }
}
