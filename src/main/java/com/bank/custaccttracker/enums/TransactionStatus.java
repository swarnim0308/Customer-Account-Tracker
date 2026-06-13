package com.bank.custaccttracker.enums;

public enum TransactionStatus {
    SUCCESS("success"),
    FAILED("fail"),
    INSUFFICIENT_BALANCE("insufficient_balance"),
    INVALID_CUSTOMER("invalid_customer"),
    SAME_CUSTOMER("same_customer");

    private final String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}

