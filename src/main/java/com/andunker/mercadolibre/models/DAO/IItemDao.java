package com.andunker.mercadolibre.models.DAO;

import com.andunker.mercadolibre.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface  IItemDao extends CrudRepository<Item, String>
{
}
