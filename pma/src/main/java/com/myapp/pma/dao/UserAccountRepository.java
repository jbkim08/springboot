package com.myapp.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.myapp.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
