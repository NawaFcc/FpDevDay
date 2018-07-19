package devdayfp.adaptor.treasurey;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class RefundService {
	
	public void refundCheque(String address, BigDecimal amount) 
			throws RefundException {
	}
	
	public void refundGiftCard(String address) 
			throws RefundException {
	}
	
}
