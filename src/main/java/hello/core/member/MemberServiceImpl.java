package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // like bean 등록
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 의존성 제거 후
    private final MemberRepository memberRepository;

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
    @Autowired  // 객체 자동 주입. like ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}