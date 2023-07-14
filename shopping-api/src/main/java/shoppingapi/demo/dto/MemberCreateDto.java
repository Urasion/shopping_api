package shoppingapi.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateDto {
    @NotEmpty
    private String name;
    public MemberCreateDto(){}
    public MemberCreateDto(String name) {
        this.name = name;
    }
}
