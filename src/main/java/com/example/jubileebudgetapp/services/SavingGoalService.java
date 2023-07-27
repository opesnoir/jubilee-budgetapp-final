package com.example.jubileebudgetapp.services;


import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.exceptions.SavingGoalNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoal;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.SavingGoalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<SavingGoalDto> getSavingGoals(){
        List<SavingGoal> savingGoals = savingGoalRepository.findAll();
        return convertSavingGoalsToDtos(savingGoals);
    }

    private List<SavingGoalDto> convertSavingGoalsToDtos(List<SavingGoal> savingGoals) {
        List<SavingGoalDto> savingGoalDtoList = new ArrayList<>();

        for (SavingGoal savingGoal : savingGoals){
            SavingGoalDto savingGoalDto = convertSavingGoalToDto(savingGoal);
/*            if (savingGoal.getAccount() !=null){
                savingGoalDto.setAccountDto(accountService.convertAccountToDto(savingGoal.getAccount()));
            }*/
            savingGoalDtoList.add(savingGoalDto);
        }
        return savingGoalDtoList;
    }

    public SavingGoalDto getSavingGoal(Long id){
        SavingGoal savingGoal = savingGoalRepository.findById(id)
                .orElseThrow(() -> new SavingGoalNotFoundException(id));

/*        if (savingGoal.getGoal() !=null){
            savingGoalDto.setAccountDto(accountService.convertAccountToDto(savingGoal.getAccount()));
        }*/
        return convertSavingGoalToDto(savingGoal);
    }

    public SavingGoalDto createSavingGoal(SavingGoalDto savingGoalDto){
        SavingGoal savingGoal = convertDtoToSavingGoal(savingGoalDto);
        savingGoalRepository.save(savingGoal);

        return convertSavingGoalToDto(savingGoal);
    }

    public void deleteSavingGoal(Long id){
        savingGoalRepository.deleteById(id);
    }

    public SavingGoalDto updateSavingGoal(Long id, SavingGoalDto updatedSavingGoalDto){
        SavingGoal existingSavingGoal = savingGoalRepository.findById(id)
                .orElseThrow(() -> new SavingGoalNotFoundException(id));

        updateSavingGoalFromDto(existingSavingGoal, updatedSavingGoalDto);
        SavingGoal updatedSavingGoal = savingGoalRepository.save(existingSavingGoal);

        return convertSavingGoalToDto(updatedSavingGoal);
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


    public void assignSavingGoalToAccount(Long id, Long accountId) {
        Optional<SavingGoal> optionalSavingGoal = savingGoalRepository.findById(id);
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalSavingGoal.isPresent() && optionalAccount.isPresent()){
            var savingGoal = optionalSavingGoal.get();
            var account = optionalAccount.get();

            savingGoal.setAccount(account);
            savingGoalRepository.save(savingGoal);
        } else {
            throw new RecordNotFoundException();
        }

    }
}
