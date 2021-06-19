package my.hydra.practice.service;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.domains.Member;
import my.hydra.practice.models.member.MemberRegist;
import my.hydra.practice.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceDetailsImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * 로그인할 회원 정보 조회 및 로그인 처리 메서드
     * @param memberId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException(memberId));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(member.getPermission()));
//        if (memberId.equals("hydra12")) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
//        } else {
//            grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
//        }
        return new User(member.getMemberId(), member.getPassword(), grantedAuthorities);
    }
    public void registMember(MemberRegist regist) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encyptPassword = encoder.encode(regist.getPassword());
        Member member = new Member(regist.getUsername(), regist.getMembername(), encyptPassword);
        member.setCreateDate(LocalDateTime.now());
        member.setPermission("ROLE_MEMBER");
        memberRepository.save(member);
    }
}
