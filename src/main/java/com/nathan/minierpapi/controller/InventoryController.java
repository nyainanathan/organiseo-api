package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import com.nathan.minierpapi.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
