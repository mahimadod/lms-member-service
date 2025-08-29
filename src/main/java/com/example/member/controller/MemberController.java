package com.example.member.controller;

import com.example.member.entity.Member;
import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> createBook(@RequestBody Member member) throws SQLException {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllBooks() throws InterruptedException {
        return ResponseEntity.ok(memberService.getAllMember());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id,@RequestHeader("token") String token) throws SQLException {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
