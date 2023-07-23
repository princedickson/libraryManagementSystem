package com.explicit.libraryManagementSystem.Service;

import com.explicit.libraryManagementSystem.Entity.Members;
import com.explicit.libraryManagementSystem.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public Members saveMember(Members members) {
        return memberRepository.save(members);
    }

    @Override
    public List<Members> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Members getMemberById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public Members updateMember(Members members) {
        return memberRepository.save(members);
    }

    @Override
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
