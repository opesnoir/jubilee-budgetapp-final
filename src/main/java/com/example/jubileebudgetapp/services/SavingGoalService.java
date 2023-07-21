package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoal;
import org.springframework.stereotype.Service;

@Service
public class SavingGoalService {


    // helper methodes
    public SavingGoal convertDtoToSavingGoal(SavingGoalDto savingGoalDto){
        SavingGoal savingGoal = new SavingGoal();

        savingGoal.setGoal(savingGoalDto.getGoal());
        savingGoal.setDescription(savingGoalDto.getDescription());
        savingGoal.setCurrentAmount(savingGoalDto.getCurrentAmount());
        savingGoal.setTargetAmount(savingGoalDto.getTargetAmount());
        savingGoal.setStatus(savingGoalDto.getStatus());

        return savingGoal;
    }

    public SavingGoalDto convertSavingGoalToDto(SavingGoal savingGoal){
        SavingGoalDto savingGoalDto = new SavingGoalDto();

        savingGoalDto.setId(savingGoal.getId());
        savingGoalDto.setGoal(savingGoal.getGoal());
        savingGoalDto.setDescription(savingGoal.getDescription());
        savingGoalDto.setCurrentAmount(savingGoal.getCurrentAmount());
        savingGoalDto.setTargetAmount(savingGoal.getTargetAmount());
        savingGoalDto.setStatus(savingGoal.getStatus());

        savingGoalDto.setAccountId(savingGoal.getAccount().getId());

        return savingGoalDto;
    }



}
