package com.apap.tutorial5.service;

import java.util.Optional;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.DealerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {

	@Override
	public Optional<DealerModel> getDealerDetailByID(Long id) {
		// TODO Auto-generated method stub
		return dealerDb.findById(id);
	}

	@Override
	public void addDealer(DealerModel dealer) {
		// TODO Auto-generated method stub
		dealerDb.save(dealer);
		
	}
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public void deleteDealer(Long dealerId) {
		dealerDb.deleteById(dealerId);
	}
	
	@Override
	public DealerDb searchAll() {
		return dealerDb;
	}
	
	@Override
	public void dealerUpdate(DealerModel updateDealer, Long dealerId) {
		DealerModel dataLama = dealerDb.findById(dealerId).get();
		dataLama.setAlamat(updateDealer.getAlamat());
		dataLama.setNoTelp(updateDealer.getNoTelp());
		dealerDb.save(dataLama);
	}
	
}
