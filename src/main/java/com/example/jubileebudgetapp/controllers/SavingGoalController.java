package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.services.SavingGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("saving_goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }

    @GetMapping
    public ResponseEntity<List<SavingGoalDto>> getSavingGoals(){
        List<SavingGoalDto> savingGoalDtoList = savingGoalService.getSavingGoals();
        if (savingGoalDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(savingGoalDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavingGoalDto> getSavingGoal(@PathVariable("id") Long id){
        return ResponseEntity.ok(savingGoalService.getSavingGoal(id));
    }

    @PostMapping
    public ResponseEntity<SavingGoalDto> createSavingGoal(@RequestBody SavingGoalDto savingGoalDto){
        SavingGoalDto createdSavingGoalDto = savingGoalService.createSavingGoal(savingGoalDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSavingGoalDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdSavingGoalDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSavingGoal(@PathVariable Long id){
        savingGoalService.deleteSavingGoal(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<SavingGoalDto> updateSavingGoal(@PathVariable Long id, @RequestBody SavingGoalDto updatedSavingGoalDto){
        SavingGoalDto updatedSavingGoal = savingGoalService.updateSavingGoal(id, updatedSavingGoalDto);
        return ResponseEntity.ok(updatedSavingGoal);
    }

    @PutMapping("/{id}/{accountId}")
    public ResponseEntity<Object> assignSavingGoalToAccount(@PathVariable("id") Long id, @PathVariable("accountId") Long accountId){
        savingGoalService.assignSavingGoalToAccount(id, accountId);
        return ResponseEntity.noContent().build();
    }

}
