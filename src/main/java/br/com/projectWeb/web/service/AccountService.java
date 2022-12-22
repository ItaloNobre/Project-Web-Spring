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

    public Optional<Account> depositAccount(Long id, Account object) {
        Optional<Account> account = repository.findById(id);
        double saldoAtual = account.get().getAccountBalance();
        double valorDeposito = object.getAccountBalance();
        account.get().setAccountBalance(saldoAtual + valorDeposito);
        repository.save(account.get());
        return account;
      }

    public Optional<Account> takeAmount(Long id, Account object) {
        Optional<Account> account = findById(id);
        double saldoAtual = account.get().getAccountBalance();
        double valorSaque = object.getAccountBalance();
        
        if(saldoAtual >= 0 && valorSaque <= saldoAtual){
            account.get().setAccountBalance(saldoAtual - valorSaque);
            repository.save(account.get());
        }
        
        return account;
    }
    
    public Optional<Account> showAmount(Long id){
        Optional<Account> object = repository.findById(id);
        object.get().setDescription(null);
        return object;
    }
  


    

    
    

}
