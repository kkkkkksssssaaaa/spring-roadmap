package dev.kkkkkksssssaaaa.springroadmap.domain.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return repository.findById(id);
    }

    public MemberRepository getRepository() {
        return repository;
    }
}
