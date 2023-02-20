package br.com.projectWeb.web.service.impl;

import br.com.projectWeb.web.domain.Account;
import br.com.projectWeb.web.domain.dto.AccountDto;

import java.util.List;

public interface AccountService {

     Account create(AccountDto dto);
     List<Account> findAll();

     void deleteById(Long id);

     Account depositAccount(Long id, Account object);

     Account takeAmount(Long id, Account object);
}
