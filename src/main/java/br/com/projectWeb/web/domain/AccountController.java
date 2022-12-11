package br.com.projectWeb.web.domain;

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
import br.com.projectWeb.web.service.AccountService;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    AccountService service;
    @PostMapping("/contabancaria")
    public ResponseEntity<Account> creteAccount(@RequestBody Account account){
       
        Account createAccount = service.createAccount(account);
        return ResponseEntity.ok(createAccount);
    }
    
    @GetMapping("/contabancaria")
    public ResponseEntity<List<Account>> findAll(){
        List<Account> findAll = service.findAll();
        return ResponseEntity.ok(findAll);
    }

    @DeleteMapping("/contabancaria/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("contabancaria/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        Optional<Account> object = service.findById(id);
        return ResponseEntity.ok().body(object.get());
    }

    @PutMapping("/contabancaria/{id}/deposito")
    public Optional<Account> depositeAccount(@RequestBody Account object){
        Optional<Account> depositAccount = service.depositAccount(object);;
        return depositAccount;
    }
    
    @PutMapping("/contabancaria/{id}/saque")
    public Optional<Account> takeAmount(@RequestBody Account object){
        Optional<Account> takeAmount = service.takeAmount(object);;
        return takeAmount;
    }

    @GetMapping("contabancaria/{id}/saldo")
    public ResponseEntity<Account> showAmount(@PathVariable Long id){
        Optional<Account> object = service.showAmount(id);
        return ResponseEntity.ok().body(object.get());
    }
   
}
    
