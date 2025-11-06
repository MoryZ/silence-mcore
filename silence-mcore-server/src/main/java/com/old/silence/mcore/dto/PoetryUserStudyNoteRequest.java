package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * PoetryUserStudyNote命令对象
 */
@Setter
@Getter
public class PoetryUserStudyNoteRequest {
    @NotNull
    private BigInteger userId;
    @NotNull
    private BigInteger contentId;
    @NotBlank
    @Size(max = 65535)
    private String noteContent;
    private String tags;
    private Boolean disclosure;

}