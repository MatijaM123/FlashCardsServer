package com.example.flashcardsserver.DTO;

public class CardGroupOwnerRequest {
    private Long cardGroupId;
    private String ownerUsername;

    // Getters and Setters
    public Long getCardGroupId() {
        return cardGroupId;
    }

    public void setCardGroupId(Long cardGroupId) {
        this.cardGroupId = cardGroupId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
