package com.example.spring_ai.service;

import com.example.spring_ai.dto.ChatRequest;
import com.example.spring_ai.response.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    // Business method that processes the incoming chat request
    // and returns the AI response as a ChatResponse object.
    public ChatResponse createChat(ChatRequest chatRequest) {

        // Creates a new prompt request.
        return chatClient.prompt()

                // Adds the current user's question to the prompt.
                .user(chatRequest.getQuestion())

                // Configures the memory advisor for this request.
                // The conversation ID tells Spring AI which
                // conversation history it should retrieve and update.
                .advisors(advisorSpec -> advisorSpec.param(
                        ChatMemory.CONVERSATION_ID,
                        chatRequest.getConversationId()
                ))

                // Sends the completed prompt to the AI model
                // and waits for the response.
                .call()

                // Converts the AI response into your ChatResponse DTO.
                .entity(ChatResponse.class);
    }



}
