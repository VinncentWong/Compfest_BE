package com.demo.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.ItemDto;
import com.demo.service.ItemService;
import com.demo.util.AppResponse;

@RestController
@RequestMapping("/item")
public class ItemController{
	
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping(path = {"/additem"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<AppResponse> createItem(@Valid @ModelAttribute ItemDto item) throws IOException{
		log.info("Masuk ke create item controller");
		log.info("Nilai dari file = " + item.getImage().getName());
		return itemService.createItem(item);
	}
	
	@DeleteMapping("/deleteitem/{id}")
	public ResponseEntity<AppResponse> deleteItem(@PathVariable Long id){
		return itemService.deleteItem(id);
	}
	
	@GetMapping("/getitem")
	public ResponseEntity<AppResponse> getItemByName(@RequestBody String itemName){
		return itemService.findItemByName(itemName);
	}
	
	@GetMapping("/getitems")
	public ResponseEntity<AppResponse> getAllItem(){
		return itemService.findAllItem();
	}
}