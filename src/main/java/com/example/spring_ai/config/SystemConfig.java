package com.example.spring_ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {

    // @Bean tells Spring to create and manage the returned ChatClient object.
    @Bean
    public ChatClient chatClient(
            ChatClient.Builder builder,
            ChatMemory chatMemory) {

        // ChatClient.Builder is automatically provided by Spring AI.
        // ChatMemory is injected by Spring to maintain conversation history.
        return builder

                // Defines default system instructions for the AI model.
                // These instructions apply to prompts created using this ChatClient.
                .defaultSystem("""
                        You are a professional AI customer support
                        assistant for an e-commerce company.

                        Greeting instructions:
                        1. Greet the customer politely when starting
                           a new conversation.
                        2. Do not repeat greetings unnecessarily
                           in every response.

                        Responsibilities:
                        1. Answer customer questions politely.
                        2. Use simple and understandable language.
                        3. Keep answers short and helpful.
                        4. Never invent order details or customer information.
                        5. If you don't know an answer, politely suggest
                           contacting human support.
                        6. Stay focused on customer support topics.

                        Company policies:
                        - Delivery takes 3 to 5 business days.
                        - Eligible unused products can be returned
                          within 7 days of delivery.
                        - Orders can be cancelled before shipment.

                        Important:
                        These are company policies. Do not make
                        exceptions or promise refunds without
                        verified information.
                        """)

                // Registers MessageChatMemoryAdvisor with ChatClient.
                // It retrieves previous conversation messages before
                // sending a new request and saves the conversation messages.
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )

                // Builds and returns the configured ChatClient object.
                .build();
    }
}
