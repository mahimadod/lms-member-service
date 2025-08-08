package com.example.member.service;

import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) throws SQLException {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMember() throws InterruptedException {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> getMemberById(Long id) throws SQLException {
        return memberRepository.findById(id);
    }

    @Override
    public Member updateMember(Long id, Member updatedMember) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setName(updatedMember.getName());
                    member.setEmail(updatedMember.getEmail());
                    member.setPhone(updatedMember.getPhone());
                    return memberRepository.save(member);
                })
                .orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND,"Book not found"));
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
