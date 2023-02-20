package br.com.projectWeb.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectWeb.web.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> { }
