package dev.kkkkkksssssaaaa.springroadmap.advancedaop.member;

import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.annotation.ClassAop;
import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.annotation.MethodAop;
import org.springframework.stereotype.Service;

@ClassAop
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
