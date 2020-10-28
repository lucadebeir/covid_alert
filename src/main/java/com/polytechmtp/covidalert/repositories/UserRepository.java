package com.polytechmtp.covidalert.repositories;

import com.polytechmtp.covidalert.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

}
