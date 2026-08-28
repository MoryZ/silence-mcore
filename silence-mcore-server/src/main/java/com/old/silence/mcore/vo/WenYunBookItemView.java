package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;

@Data
public class WenYunBookItemView {
    private BigInteger id;
    private BigInteger bookId;
    private String title;
    private String author;
    private String category;
    private String content;
    private String dynasty;
}
