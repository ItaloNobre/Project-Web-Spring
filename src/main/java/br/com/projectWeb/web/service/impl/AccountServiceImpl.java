package br.com.projectWeb.web.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.projectWeb.web.domain.dto.AccountDto;
import br.com.projectWeb.web.exception.BadRequestExeption;
import br.com.projectWeb.web.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projectWeb.web.domain.Account;
import br.com.projectWeb.web.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    AccountRepository repository;

    @Autowired
    ModelMapper mapper;

    public Account create(AccountDto dto){
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestExeption("Id deve ser nulo");
        }
        return repository.save(mapper.map(dto, Account.class));
    }

    @Override
    public List<Account> findAll() {
       return (List<Account>) repository.findAll();
    }

    public void deleteById(Long id){
        getAccount(id);
        repository.deleteById(id);
    }
    public Account findById(Long id){
        return getAccount(id);
    }

    public Account depositAccount(Long id, Account object) {
        Optional<Account> account = repository.findById(id);
        account.get().setAccountBalance(account.get().getAccountBalance() + object.getAccountBalance());
        return repository.save(account.get());
      }

    public Account takeAmount(Long id, Account object) {
        Account account = getAccount(id);
        if(account.getAccountBalance() >= 0 && object.getAccountBalance() <= account.getAccountBalance() ){
            account.setAccountBalance(account.getAccountBalance() - object.getAccountBalance());
            repository.save(account);
        }
        return account;
    }
    
    public Account showAmount(Long id){
        return getAccount(id);
    }

    private Account getAccount(Long id) throws NotFoundException {
        Optional<Account> optionalAccount = repository.findById(id);
        if (optionalAccount.isEmpty()){
            throw  new NotFoundException("Conta nao encontrada");
        }
        return optionalAccount.get();
    }
}
