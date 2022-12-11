package br.com.projectWeb.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projectWeb.web.domain.Account;
import br.com.projectWeb.web.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository repository;

    public Account createAccount(Account object){
        
        Account account = repository.save(object);
        return account;
    }

    public List<Account> findAll(){
      Iterable<Account> findAll = repository.findAll();
      return (List<Account>) findAll;
    }

    public void deleteById(Long id){
        repository.deleteById(id);  
    }
    public Optional<Account> findById(Long id){
        Optional<Account> findById = repository.findById(id);
        return findById;
    }

    public Optional<Account> depositAccount(Account object) {
        Optional<Account> account = findById(object.getId());
        account.get().setAccountBalance(account.get().getAccountBalance() + object.getAccountBalance());
        return account;
      }

    public Optional<Account> takeAmount(Account object) {
        Optional<Account> account = findById(object.getId());
        if(account.get().getAccountBalance() >= 0 && object.getAccountBalance() <= account.get().getAccountBalance() ){
        account.get().setAccountBalance(account.get().getAccountBalance() - object.getAccountBalance());
        }
        return account;
    }
    
    public Optional<Account> showAmount(Long id){
        Optional<Account> object = repository.findById(id);
        object.get().setDescription(null);
        return object;
    }
  


    

    
    

}
