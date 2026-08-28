package com.old.silence.mcore.vo;

import lombok.Data;
import java.util.List;

@Data
public class WenYunPageView<T> {
    private List<T> list;
    private Integer page;
    private Integer size;
    private Long total;
}
