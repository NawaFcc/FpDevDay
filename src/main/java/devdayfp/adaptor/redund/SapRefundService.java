package devdayfp.adaptor.redund;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class SapRefundService {
	
	public BigDecimal changePstConditionReturnRefund(String loanNumber, BigDecimal newRate, LocalDate refundDate) 
			throws RefundCalculationException {
		return null;
	}
	
	// Returns document number if success
	public String refundToEarlyPay(String loanNumber, BigDecimal amount) 
			throws RefundException {
		return null;
	}
	
}
