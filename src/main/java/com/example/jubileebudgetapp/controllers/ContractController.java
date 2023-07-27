package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.ContractDto;
import com.example.jubileebudgetapp.services.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    public final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getContracts(){
        List<ContractDto> contractDtoList = contractService.getContracts();

        return new ResponseEntity<>(contractDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContract(@PathVariable("id") Long id){
        return ResponseEntity.ok(contractService.getContract(id));
    }

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto contractDto){
        ContractDto createdContractDto = contractService.createContract(contractDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdContractDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdContractDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable Long id){
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<ContractDto> updateContract(@PathVariable Long id, @RequestBody ContractDto updatedContractDto){
        ContractDto updatedContract = contractService.updateContract(id, updatedContractDto);
        return ResponseEntity.ok(updatedContract);
    }

    @PutMapping("/{id}/{accountId}")
    public ResponseEntity<Object> assignAccountToContract(@PathVariable("id") Long id, @PathVariable("accountId") Long accountId){
        contractService.assignAccountToContract(id, accountId);
        return ResponseEntity.noContent().build();
    }

}
