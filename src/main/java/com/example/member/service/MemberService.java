package com.example.member.service;

import com.example.member.entity.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Member createMember(Member member) throws SQLException;
    public List<Member> getAllMember() throws InterruptedException;
    public Optional<Member> getMemberById(Long id) throws SQLException;
    public Member updateMember(Long id, Member updatedMember);
    public void deleteMember(Long id);


}
