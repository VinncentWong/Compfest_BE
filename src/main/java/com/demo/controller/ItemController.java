package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ItemDto;
import com.demo.service.ItemService;
import com.demo.util.AppResponse;

@RestController
public class ItemController{
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/additem")
	public ResponseEntity<AppResponse> createItem(@Valid @RequestBody ItemDto item){
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