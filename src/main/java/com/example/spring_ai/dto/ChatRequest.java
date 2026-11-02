package com.example.spring_ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {


    private String conversationId;

    @NotBlank
    @Size(min = 1, max = 255)
    private String question;

    /***
     *  {    # with every request the
     *  same conversational id should be send.
     *
     *     "conversationId": "java-interview-101",
     *     "question": "Act as an experienced Java interviewer. Ask me one question at a time and evaluate my answers."
     * }
     */

}

