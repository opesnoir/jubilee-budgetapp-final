package com.example.jubileebudgetapp.services;


import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.models.SavingGoal;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.SavingGoalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavingGoalService {

    private SavingGoalRepository savingGoalRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public SavingGoalService(SavingGoalRepository savingGoalRepository, AccountRepository accountRepository, AccountService accountService) {
        this.savingGoalRepository = savingGoalRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<SavingGoalDto> getSavingGoal(){
        List<SavingGoal> savingGoals = savingGoalRepository.findAll();
        return convertSavingGoalsToDtos(savingGoals);
    }

    private List<SavingGoalDto> convertSavingGoalsToDtos(List<SavingGoal> savingGoals) {
        List<SavingGoalDto> savingGoalDtoList = new ArrayList<>();

        for (SavingGoal savingGoal : savingGoals){
            SavingGoalDto savingGoalDto = convertSavingGoalToDto(savingGoal);
            if (savingGoal.getAccount() !=null){
                savingGoalDto.setAccountDto(accountService.convertAccountToDto(savingGoal.getAccount()));
            }
            savingGoalDtoList.add(savingGoalDto);
        }
        return savingGoalDtoList;
    }


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

    public void updateSavingGoalFromDto(SavingGoal existingSavingGoal, SavingGoalDto updatedSavingGoalDto){
        if (updatedSavingGoalDto.getGoal() != null){
            existingSavingGoal.setGoal(updatedSavingGoalDto.getGoal());
        }
        if (updatedSavingGoalDto.getDescription()!= null){
            existingSavingGoal.setDescription(updatedSavingGoalDto.getDescription());
        }
        if (updatedSavingGoalDto.getCurrentAmount()!= null){
            existingSavingGoal.setCurrentAmount(updatedSavingGoalDto.getCurrentAmount());
        }
        if (updatedSavingGoalDto.getTargetAmount()!= null){
            existingSavingGoal.setTargetAmount(updatedSavingGoalDto.getTargetAmount());
        }
        if (updatedSavingGoalDto.getStatus()!= null){
            existingSavingGoal.setStatus(updatedSavingGoalDto.getStatus());
        }
    }

}
