package com.sprout.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull(message = "id不可以为空")
    @Max(value = 10, message = "id 不能大于 10")
    @Min(value = 0, message = "id 至少大于等于 0")
    private Integer id;

    @NotEmpty
    private List<String> str;
}
