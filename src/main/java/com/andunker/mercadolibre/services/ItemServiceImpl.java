package com.andunker.mercadolibre.services;

import com.andunker.mercadolibre.models.DAO.IItemDao;
import com.andunker.mercadolibre.models.DTO.ChildrenDTO;
import com.andunker.mercadolibre.models.DTO.Health;
import com.andunker.mercadolibre.models.DTO.InfoRequest;
import com.andunker.mercadolibre.models.DTO.ItemDTO;
import com.andunker.mercadolibre.models.entities.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ItemServiceImpl implements IItemService {

    private static JsonParser jsonParser = new JsonParser();
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private IItemDao itemDAO;
    private String server = "https://api.mercadolibre.com/";

    @Override
    @Transactional
    public ItemDTO findItemById(String id) throws JsonProcessingException {


        ItemDTO itemDTO = new ItemDTO();

        Iterable<Item> cacheItem = itemDAO.findAllById(Collections.singleton(id));
        if (cacheItem.iterator().hasNext()) {

            String cacheItemValue = cacheItem.iterator().next().getValue();
            return mapper.readValue(cacheItemValue, ItemDTO.class);
        }


        String itemUri = "items/" + id;
        String childrenUri = "items/" + id + "/children";

        itemDTO = restTemplate.getForObject(server + itemUri, ItemDTO.class);
        ChildrenDTO[] childrens = restTemplate.getForObject(server + childrenUri, ChildrenDTO[].class);

        itemDTO.setChildrens(Arrays.asList(childrens));

        Item item = new Item();
        String jsonStrItem = mapper.writeValueAsString(itemDTO);

        item.setId(id);
        item.setValue(jsonStrItem);
        item.setCreatedAt(new Date());
        item.setModifiedAt(new Date());

        itemDAO.save(item);

        return itemDTO;
    }

    @Override
    public List<Health> getHealth() throws IOException {
        List<Health> healths = new ArrayList<>();

        File file = ResourceUtils.getFile("classpath:json/kquery.json");
        JsonNode body = mapper.readTree(file);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity(body, headers);

        String fullApmResponse = restTemplate.postForEntity("http://localhost:9200/apm-7.2.1-*/_search?filter_path=aggregations.**&pretty", entity, String.class).getBody();

        //
        JsonElement apmResponse = jsonParser.parse(fullApmResponse)
                .getAsJsonObject()
                .get("aggregations")
                .getAsJsonObject()
                .get("range")
                .getAsJsonObject()
                .get("buckets");


        for (int i = 0; i < 3; i++) {

            Health health = new Health();

            if (
                    apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("key").getAsString().length() != 0
            ) {
                health.setDate(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("key").getAsString());
            }

            if (
                    !apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("avg_respnse_time").getAsJsonObject().get("value").isJsonNull()
            ) {
                health.setAvgResponseTime(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("avg_respnse_time").getAsJsonObject().get("value").getAsInt());
            }

            if (
                    apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("doc_count").getAsInt() != 0
            ) {
                health.setTotalRequest(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("doc_count").getAsInt());
            }

            if (
                    !apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("ext").getAsJsonObject().get("avg_respnse_time_api_calls").getAsJsonObject().get("value").isJsonNull()
            ) {
                health.setAvgResponseTimeApiCalls(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("ext").getAsJsonObject().get("avg_respnse_time_api_calls").getAsJsonObject().get("value").getAsInt());
            }

            if (
                    apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("ext").getAsJsonObject().get("doc_count").getAsInt() != 0
            ) {
                health.setTotalCountApiCalls(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("ext").getAsJsonObject().get("doc_count").getAsInt());
            }

            List<InfoRequest> infoRequests = new ArrayList<>();
            for (int j = 0; j < 4; j++) {


                InfoRequest infoRequest = new InfoRequest();

                if (
                        apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("info_requests").getAsJsonObject().get("buckets").getAsJsonArray().get(j).getAsJsonObject().get("from").getAsInt() != 0
                ) {
                    infoRequest.setStatusCode(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("info_requests").getAsJsonObject().get("buckets").getAsJsonArray().get(j).getAsJsonObject().get("from").getAsInt());
                }


                if (
                        apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("info_requests").getAsJsonObject().get("buckets").getAsJsonArray().get(j).getAsJsonObject().get("doc_count").getAsInt() != 0
                ) {
                    infoRequest.setCount(apmResponse.getAsJsonArray().get(i).getAsJsonObject().get("info_requests").getAsJsonObject().get("buckets").getAsJsonArray().get(j).getAsJsonObject().get("doc_count").getAsInt());
                }

                infoRequests.add(infoRequest);


            }
            health.setInfoRequests(infoRequests);
            healths.add(health);
        }


        return healths;
    }
}
