package com.polytechmtp.covidalert.repositories;

import com.polytechmtp.covidalert.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
