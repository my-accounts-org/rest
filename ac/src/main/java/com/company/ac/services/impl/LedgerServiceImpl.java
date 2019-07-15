package com.company.ac.services.impl;

import java.util.List;

import com.company.ac.dao.LedgersDAO;
import com.company.ac.models.Ledger;
import com.company.ac.services.LedgerService;

public class LedgerServiceImpl implements LedgerService {

	private LedgersDAO dao = new LedgersDAO();
	
	@Override
	public List<Ledger> getLedgerList(long companyId) {		
		return dao.getLedgerList(companyId);
	}
}
