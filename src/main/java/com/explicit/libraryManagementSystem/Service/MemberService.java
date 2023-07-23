package com.explicit.libraryManagementSystem.Service;


import com.explicit.libraryManagementSystem.Entity.Members;

import java.util.List;

public interface MemberService {
    
    

    Members saveMember(Members members);

    List<Members> getAllMembers();

    Members getMemberById(Long id);

    Members updateMember(Members members);

     void deleteMemberById(Long id);
}
