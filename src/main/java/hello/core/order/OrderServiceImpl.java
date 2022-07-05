package hello.core.order;

import hello.core.annotaion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    final 붙은 필드를 모아서 생성자를 자동으로 생성해준다(생성자 코드 작성안해도 됨)
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 의존성 제거
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired  // 생성자 하나면 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 수정자 주입일 경우 setXXX 을 사용, memberRepository와 discountPolicy는 final이면 안됨.
     * 스프링 빈에 등록되어 있지 않을 때도 사용 가능.
     * 필수값 지정하지 않으려면 @Autowired(required = false) 사용.
     * @Autowired
     *     public void setMemberRepository(MemberRepository memberRepository) {
     *         this.memberRepository = memberRepository;
     *     }
     *     @Autowired
     *     public void setDiscountPolicy(DiscountPolicy discountPolicy) {
     *         this.discountPolicy = discountPolicy;
     *     }
     */

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
