package com.gmail.pzalejko.consumer.bar;

public record UserCreatedEvent(String id, String name, String email) {
}