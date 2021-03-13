package com.vaahano.staffmanager.bean.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TicketPurchasedRequest {
	private String busId;
	private String customerId;
	private float price;
}

