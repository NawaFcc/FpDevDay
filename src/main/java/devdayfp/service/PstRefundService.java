package devdayfp.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import devdayfp.adaptor.bom.BpAddressService;
import devdayfp.adaptor.bom.InteractionService;
import devdayfp.adaptor.bom.SpecialCreditService;
import devdayfp.adaptor.loaninfo.LoanBpService;
import devdayfp.adaptor.loaninfo.LoanStatusSevice;
import devdayfp.adaptor.redund.SapRefundService;
import devdayfp.adaptor.treasurey.RefundService;

public class PstRefundService {
	
	@Autowired
	private SapRefundService sapRefundService;
	
	@Autowired
	private LoanBpService loanBpService;
	
	@Autowired
	private LoanStatusSevice loanStatusService;
	
	@Autowired
	private SpecialCreditService specialCreditService;
	
	@Autowired
	private InteractionService interactionService;
	
	@Autowired
	private RefundService refundService;
	
	@Autowired
	private BpAddressService bpAddressService;
	
	public void pstRefund(String loanNumber, BigDecimal newRate, LocalDate refundDate) throws Exception {
		BigDecimal refundAmount = sapRefundService.changePstConditionReturnRefund(loanNumber, newRate, refundDate);
		
		if (refundAmount.compareTo(BigDecimal.ZERO) < 0)
			return;
		
		String bpNumber = loanBpService.getLoanBp(loanNumber);
		boolean isSpecialCredit = specialCreditService.isInSpecialCredir(bpNumber);
		
		if (isSpecialCredit)
			return;
		
		boolean isLoanActive = loanStatusService.isLoanActive(loanNumber);
		if (isLoanActive) {
			sapRefundService.refundToEarlyPay(loanNumber, refundAmount);
			interactionService.addInteraction(bpNumber, "Refund to EarlyPay.");
		} else {
			if (refundAmount.compareTo(new BigDecimal(5)) > 0) {
				String address = bpAddressService.getAddress(bpNumber);
				refundService.refundCheque(address, refundAmount);
				interactionService.addInteraction(bpNumber, "Refund by Cheque.");
				
			} if (refundAmount.compareTo(new BigDecimal(1)) > 0) {
				String address = bpAddressService.getAddress(bpNumber);
				refundService.refundGiftCard(address);
				interactionService.addInteraction(bpNumber, "Refund with gift card.");
				
			} else {
				interactionService.addInteraction(bpNumber, "No refund.");
			}
		}
	}
	
}
