package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.ContractDto;
import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.models.Contract;
import com.example.jubileebudgetapp.models.SavingGoal;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    //helper methodes
    public Contract convertDtoToContract(ContractDto contractDto){
        Contract contract = new Contract();

        contract.setPayee(contractDto.getPayee());
        contract.setType(contractDto.getType());
        contract.setStartDate(contractDto.getStartDate());
        contract.setEndDate(contractDto.getEndDate());
        contract.setAmount(contractDto.getAmount());

        return contract;
    }

    public ContractDto convertContractToDto(Contract contract){
        ContractDto contractDto = new ContractDto();

        contractDto.setId(contract.getId());
        contractDto.setPayee(contract.getPayee());
        contractDto.setType(contract.getType());
        contractDto.setStartDate(contract.getStartDate());
        contractDto.setEndDate(contract.getEndDate());
        contractDto.setAmount(contract.getAmount());

        contractDto.setAccountId(contract.getAccount().getId());

        return contractDto;
    }

    public void updateContractFromDto(Contract existingContract, ContractDto updatedContractDto){
        if (updatedContractDto.getPayee() != null){
            existingContract.setPayee(updatedContractDto.getPayee());
        }
        if (updatedContractDto.getType() != null){
            existingContract.setType(updatedContractDto.getType());
        }
        if (updatedContractDto.getStartDate() != null){
            existingContract.setStartDate(updatedContractDto.getStartDate());
        }
        if (updatedContractDto.getEndDate() != null){
            existingContract.setEndDate(updatedContractDto.getEndDate());
        }
        if (updatedContractDto.getAmount() != null){
            existingContract.setAmount(updatedContractDto.getAmount());
        }
    }

}
