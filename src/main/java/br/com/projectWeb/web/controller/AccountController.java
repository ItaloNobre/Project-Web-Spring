package br.com.projectWeb.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projectWeb.web.domain.Account;
import br.com.projectWeb.web.service.AccountService;

@RestController
@RequestMapping("/contabancaria")
public class AccountController {

    @Autowired
    AccountService service;
    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
       
        Account newAccount = service.createAccount(account);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAccount.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Account>> findAll(){
        List<Account> findAll = service.findAll();
        return ResponseEntity.ok(findAll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        Optional<Account> object = service.findById(id);
        return ResponseEntity.ok().body(object.get());
    }

    @PutMapping("/{id}/deposito")
    public Optional<Account> depositeAccount(@PathVariable Long id, @RequestBody Account object){
        Optional<Account> depositAccount = service.depositAccount(id,object);;
        return depositAccount;
    }
    
    @PutMapping("/{id}/saque")
    public Optional<Account> takeAmount(@PathVariable Long id, @RequestBody Account object){
        Optional<Account> takeAmount = service.takeAmount(id, object);;
        return takeAmount;
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Account> showAmount(@PathVariable Long id){
        Optional<Account> object = service.showAmount(id);
        return ResponseEntity.ok().body(object.get());
    }
   
}
    
