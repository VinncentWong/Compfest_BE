package com.demo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.ItemDto;
import com.demo.entity.Item;
import com.demo.repository.ItemRepository;
import com.demo.util.AppResponse;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private AppResponse response;
	
	public ResponseEntity<AppResponse> createItem(ItemDto tempItem) throws IOException{
		Item item = new Item();
		item.setCreatedAt(new Date());
		item.setDescription(tempItem.getDescription());
		item.setImage(tempItem.getImage().getBytes());
		item.setName(tempItem.getName());
		item.setPrice(tempItem.getPrice());
		itemRepository.save(item);
		response.getMap().put("data", item);
		response.getMap().put("message", "success add item");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public ResponseEntity<AppResponse> deleteItem(Long id){
		itemRepository.deleteById(id);
		response.getMap().put("data", null);
		response.getMap().put("message", "success delete item");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public ResponseEntity<AppResponse> findItemByName(String name){
		Optional<Item> item = itemRepository.findByName(name);
		if(item.isEmpty()) {
			response.getMap().put("data", null);
			response.getMap().put("message", "item name doesn't exist");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		response.getMap().put("data", item.get());
		response.getMap().put("message", "success find item data");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public ResponseEntity<AppResponse> findAllItem(){
		List<Item> items = itemRepository.findAll();
		response.getMap().put("data", items);
		response.getMap().put("message", "success");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
