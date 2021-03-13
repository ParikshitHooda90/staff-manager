package com.vaahano.staffmanager.bean.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class TicketPassPurchasedRequest {
	private String passId;
	private String customerId;
}
