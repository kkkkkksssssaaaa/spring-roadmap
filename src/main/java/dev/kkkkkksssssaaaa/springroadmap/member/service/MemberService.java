package dev.kkkkkksssssaaaa.springroadmap.member.service;

import dev.kkkkkksssssaaaa.springroadmap.member.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.member.repository.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.member.repository.MemoryMemberRepository;

public class MemberService {

    private final MemberRepository repository = new MemoryMemberRepository();

    /**
     * 회원 가입
     *
     * Rule: 사용자 이름은 고유해야 한다.
     * */
    public Long join(Member member) {
        validateDuplicateMember(member);

        return repository.save(member).getId();
    }

    private void validateDuplicateMember(Member member) {
        repository
            .findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalArgumentException("이미 존재하는 회원입니다.");
            });
    }
}
