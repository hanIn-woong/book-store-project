package com.bookstore.domain.detail.service;

public record AiBookSummary(
        String oneLinePitch,
        String detailedSummary,
        String targetReader,
        String purchasePoints
) {}
