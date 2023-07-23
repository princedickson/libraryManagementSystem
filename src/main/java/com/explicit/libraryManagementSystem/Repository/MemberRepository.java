package com.explicit.libraryManagementSystem.Repository;

import com.explicit.libraryManagementSystem.Entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {
    void deleteById(Long id);
}
