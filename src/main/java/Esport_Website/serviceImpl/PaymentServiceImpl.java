package Esport_Website.serviceImpl;

import java.util.List;

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
		String[] parts = webhook.getContent().split(" ");
		
		try {
			if(parts[0].equals("NAP")) {
				
				UserTransaction transaction = transDao.findById(Integer.parseInt(parts[1])).orElse(null);
				
				if(transaction != null) {
					
					transaction.setStatus("complete");
					transDao.save(transaction);
					
					System.out.println("Đã xác nhận thanh toán cho giao dịch số " + parts[1]);
					
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
