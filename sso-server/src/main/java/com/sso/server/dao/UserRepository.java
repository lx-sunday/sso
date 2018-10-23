package com.sso.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sso.server.entity.SSOUser;

public interface UserRepository extends JpaRepository<SSOUser,Long> {

}
