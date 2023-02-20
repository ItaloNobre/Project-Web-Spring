package br.com.projectWeb.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.projectWeb.web.domain.dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projectWeb.web.domain.Account;
import br.com.projectWeb.web.service.impl.AccountServiceImpl;

@RestController
@RequestMapping("/contabancaria")
public class AccountController {

    @Autowired
    AccountServiceImpl service;

    @Autowired
    ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        List<AccountDto> accountDto = service.findAll().stream()
                .map(x -> mapper.map(x, AccountDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable Long id){
        Account object = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(object, AccountDto.class));
    }

    @PostMapping("/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(service.create(dto), AccountDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/deposito")
    public AccountDto depositeAccount(@PathVariable Long id, @RequestBody Account object){
        return mapper.map(service.depositAccount(id,object), AccountDto.class);
    }
    
    @PutMapping("/{id}/saque")
    public AccountDto takeAmount(@PathVariable Long id, @RequestBody Account object){
        return mapper.map(service.takeAmount(id, object), AccountDto.class);
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<AccountDto> showAmount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(service.showAmount(id), AccountDto.class));
    }
}
    
