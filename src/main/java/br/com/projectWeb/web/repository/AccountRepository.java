package br.com.projectWeb.web.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projectWeb.web.domain.Account;

public interface AccountRepository extends CrudRepository<Account,Long> {
    
    
}
