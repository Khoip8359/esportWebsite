package Esport_Website.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.UserTransactionDAO;
import Esport_Website.entity.UserTransaction;
import Esport_Website.service.UserTransactionService;

@Service
public class UserTransactionServiceImpl implements UserTransactionService{
	
	@Autowired
	UserTransactionDAO transDao;

	@Override
	public void checkAllBill() {
		List<UserTransaction> trans = transDao.findAll();
		
		Date now = new Date();
		
		try {
			for (UserTransaction transaction : trans) {
				if(transaction.getStatus().equals("pending")) {
					Date createdAt = transaction.getCreatedDate();
					if(createdAt != null) {
						long diffMillis = now.getTime() - createdAt.getTime();

		                if (diffMillis >= 5 * 60 * 1000) {
		                    transaction.setStatus("failed");
		                    transDao.save(transaction);
		                    System.out.println("Đơn #" + transaction.getTransactionId() + " đã quá hạn và FAILED.");
		                }
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Đã có lỗi xảy ra, vui lòng kiểm tra!");
		}
	}
	
}
