package Esport_Website.serviceImpl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.UserTransactionDAO;
import Esport_Website.DAO.UsersDAO;
import Esport_Website.dto.Webhook;
import Esport_Website.entity.UserTransaction;
import Esport_Website.entity.Users;
import Esport_Website.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	UserTransactionDAO transDao;
	@Autowired
	UsersDAO udao;

	@Override
	public void handleWebhook(Webhook webhook) {
		
		Pattern pattern = Pattern.compile("NAP\\s+(\\d+)");
		Matcher matcher = pattern.matcher(webhook.getContent());
		
		try {
			if(matcher.find()) {
				if(matcher.group(0).contains("NAP")) {
					
					UserTransaction transaction = transDao.findById(Integer.parseInt(matcher.group(1))).orElse(null);
					
					if(transaction != null) {
						
						transaction.setStatus("completed");
						transDao.save(transaction);
						
						int point = transaction.getTotal() / 100;
						
						Users user = transaction.getUser();
						user.setRemainingPoint(user.getRemainingPoint() + point);
						udao.save(user);
						
						System.out.println("Đã xác nhận thanh toán cho giao dịch số " + matcher.group(1));
						
					}
					
				}
			}
		} catch (Exception e) {
			System.out.println("Đã có lỗi xảy ra: " + e);
		}
		
	}

	@Override
	public List<UserTransaction> getTransaction(Integer userId) {
		List<UserTransaction> trans = transDao.findAllByUser_UserId(userId);
		return trans;
	}

	@Override
	public UserTransaction createNewTrans(Integer total, Integer userId) {
		Users user = udao.findById(userId).orElse(null);
		if(user == null) {
			return null;
		}
		
		UserTransaction trans = UserTransaction.builder().total(total).status("pending").user(user).build();
		
		return transDao.save(trans);
	}
	
	
}
