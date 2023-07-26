package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.ContractDto;
import com.example.jubileebudgetapp.exceptions.ContractNotFoundException;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.exceptions.TransactionNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Contract;
import com.example.jubileebudgetapp.models.Transaction;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private ContractRepository contractRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public ContractService(ContractRepository contractRepository, AccountRepository accountRepository, AccountService accountService) {
        this.contractRepository = contractRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<ContractDto> getContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return convertContractToDtos(contracts);
    }

    private List<ContractDto> convertContractToDtos(List<Contract> contracts) {
        List<ContractDto> contractDtoList = new ArrayList<>();

        for (Contract contract : contracts){
            ContractDto contractDto = convertContractToDto(contract);
            if (contract.getAccount() !=null){
                contractDto.setAccountDto(accountService.convertAccountToDto(contract.getAccount()));
            }
            contractDtoList.add(contractDto);
        }
        return contractDtoList;
    }

    public ContractDto getContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException(id));
        ContractDto contractDto = convertContractToDto(contract);

        if (contract.getAccount() !=null){
            contractDto.setAccountDto(accountService.convertAccountToDto(contract.getAccount()));
        }
        return contractDto;
    }

    public ContractDto createContract(ContractDto contractDto) {
        Contract contract = convertDtoToContract(contractDto);
        contractRepository.save(contract);

        return convertContractToDto(contract);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    public ContractDto updateContract(Long id, ContractDto updatedContractDto) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException(id));

        updateContractFromDto(existingContract, updatedContractDto);
        Contract updatedContract = contractRepository.save(existingContract);

        return convertContractToDto(updatedContract);
    }

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

    public void assignAccountToContract(Long id, Long accountId) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalContract.isPresent() && optionalAccount.isPresent()){
            var contract = optionalContract.get();
            var account = optionalAccount.get();

            contract.setAccount(account);
            contractRepository.save(contract);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
