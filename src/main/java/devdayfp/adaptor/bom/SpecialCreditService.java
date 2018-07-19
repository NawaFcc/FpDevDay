package devdayfp.adaptor.bom;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import devdayfp.adaptor.redund.RefundCalculationException;

@Service
public class SpecialCreditService {

	public boolean isInSpecialCredir(String bpNumber) 
			throws SpecialCreditQueryException {
		return false;
	}
}
