package Esport_Website.service;

import java.util.List;

import Esport_Website.dto.Webhook;
import Esport_Website.entity.UserTransaction;

public interface PaymentService {

	void handleWebhook(Webhook webhook);

	List<UserTransaction> getTransaction(Integer userId);

	UserTransaction createNewTrans(Integer total, Integer userId);

	void upgrade(Integer userId);

}
