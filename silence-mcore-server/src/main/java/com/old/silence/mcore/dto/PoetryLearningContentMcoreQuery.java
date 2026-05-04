package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * PoetryLearningContent查询对象
 */
@Getter
@Setter
public class PoetryLearningContentMcoreQuery {
    private String title;
    private String subtitle;
    private Long contentType;
    private BigInteger gradeId;
    private BigInteger categoryId;
    private BigInteger subCategoryId;
    private Long difficulty;
    private String author;
    private String dynasty;
    private String background;
    private String usageExamples;
    private String annotations;
    private String audioUrl;
    private String imageUrl;


}