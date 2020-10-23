package com.polytechmtp.covidalert.repositories;

import com.polytechmtp.covidalert.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
