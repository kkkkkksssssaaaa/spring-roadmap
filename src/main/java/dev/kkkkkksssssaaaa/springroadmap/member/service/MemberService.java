package dev.kkkkkksssssaaaa.springroadmap.member.service;

import dev.kkkkkksssssaaaa.springroadmap.member.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원 가입
     *
     * Rule: 사용자 이름은 고유해야 한다.
     * */
    public Long join(Member member) {
        validateDuplicateMember(member);

        return repository.save(member).getId();
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        repository
            .findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
}
