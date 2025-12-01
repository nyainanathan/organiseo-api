package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.inventory.FilterInventory;
import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.dto.inventory.PagedInventory;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import com.nathan.minierpapi.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @PostMapping("")
    public ResponseEntity<InventoryMovement> createInventoryMovement(@RequestBody InventoryMovementCreate newMovement) throws SQLException {
        try{
            InventoryMovement movement = service.createInventoryMovement(newMovement, newMovement.getCreatedBy());
            System.out.println("in the controller");
            return new  ResponseEntity<>(movement, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<PagedInventory> getInventoryItems(FilterInventory filters){
        try{
            PagedInventory inventoryItems = service.getInventoryItems(filters);
            return new  ResponseEntity<>(inventoryItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<InventoryMovement> getInventoryMovement(@PathVariable String movementId){
        try{
            InventoryMovement movement = service.getInventoryMovement(movementId);
            return new ResponseEntity<>(movement, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
