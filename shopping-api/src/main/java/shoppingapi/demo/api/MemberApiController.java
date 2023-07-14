package shoppingapi.demo.api;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingapi.demo.dto.MemberCreateDto;
import shoppingapi.demo.dto.MemberDto;
import shoppingapi.demo.dto.MemberUpdateDto;
import shoppingapi.demo.dto.ResponseDto;
import shoppingapi.demo.domain.Member;
import shoppingapi.demo.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /*@PostMapping("/api/vi/members")
    public MemberCreateDto saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new MemberCreateDto(id);

    }*/
    @PostMapping("/api/v2/members")
    public ResponseEntity saveMemberV2(@RequestBody @Valid MemberCreateDto request){
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new ResponseEntity(new ResponseDto(HttpStatus.OK.value(), "회원가입 성공", request), HttpStatus.OK);
    }

    @PutMapping("/api/v2/members/{id}")
    public ResponseEntity updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid MemberUpdateDto memberUpdateDto){
        memberService.update(id,memberUpdateDto.getName());
        log.info(memberUpdateDto.getName());
        Member member = memberService.findOne(id);
        return new ResponseEntity(new ResponseDto(HttpStatus.OK.value(), "유저 업데이트 완료", member.getName()),HttpStatus.OK);

    }
    @GetMapping("api/v1/members")
    public ResponseEntity memberV1(){
        List<Member> members = memberService.findMembers();
        return new ResponseEntity(new ResponseDto(HttpStatus.OK.value(), "전체 유저 조회",members),HttpStatus.OK);
    }
    @GetMapping("api/v2/members")
    public ResponseEntity memberV2(){
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream()
                .map(member -> new MemberDto(member.getName()))
                .collect(Collectors.toList());
        return new ResponseEntity(new ResponseDto(HttpStatus.OK.value(), "전체 리스트 조회", collect),HttpStatus.OK);
    }
}
