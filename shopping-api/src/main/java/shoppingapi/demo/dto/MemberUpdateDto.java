package shoppingapi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdateDto {
    private String name;
    MemberUpdateDto(){};
}
