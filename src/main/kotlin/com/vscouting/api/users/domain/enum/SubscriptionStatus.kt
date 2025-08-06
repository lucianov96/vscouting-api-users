package com.vscouting.api.users.domain.enum

enum class SubscriptionStatus(
    val value: String,
    val action: Action,
    val description: String? = null,
    ) {
    ACTIVE (
        value = "ACTIVE",
        action = Action.ACTION,
    ),
    USER_PENDING (
        value = "USER PENDING",
        action = Action.ACTION,
        description = "Final price is <b>30 USD</b>. Do you want to proceed?"
    ),
    ERROR (
        value = "ERROR",
        action = Action.ACTION,
        description = "Please contact your administrator for more details."
    ),
    CANCELED (
        value = "CANCELED",
        action = Action.ACTION,
        description = "Your subscription was canceled"
    ),
    EXPIRED (
        value = "EXPIRED",
        action = Action.ACTION,
        description = "Your subscription has <b>expired</b>. Do you want to <b>renew</b> it?",
    ),
    SUBSCRIPTION_PENDING (
        value = "SUBSCRIPTION PENDING",
        action = Action.SERVER_ACTION,
        description = "We are processing your subscription"
    ),
    PAYMENT_PENDING (
        value = "PAYMENT PENDING",
        action = Action.SERVER_ACTION,
        description = "We are processing your payment"
    );

    enum class Action(val value: String) {
        ACTION ("Action"),
        SERVER_ACTION ("Server action")
    }
}