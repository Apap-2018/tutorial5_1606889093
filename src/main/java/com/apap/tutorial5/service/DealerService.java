package com.apap.tutorial5.service;

import java.util.Optional;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.DealerDb;

public interface DealerService {
	Optional<DealerModel> getDealerDetailByID(Long id);
	
	void addDealer(DealerModel dealer);
	void deleteDealer(Long dealerId);
	DealerDb searchAll();

	void dealerUpdate(DealerModel dealer, Long dealerId);
	
}
