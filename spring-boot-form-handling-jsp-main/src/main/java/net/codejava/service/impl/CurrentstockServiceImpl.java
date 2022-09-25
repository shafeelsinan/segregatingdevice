package net.codejava.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.BeanUtils;
import net.codejava.domain.Currenstock;
import net.codejava.domain.StockLedger;
import net.codejava.repository.CurrenstockRepository;
import net.codejava.repository.ProductRepository;
import net.codejava.repository.SellRepository;
import net.codejava.repository.StkLedgerRepository;
import net.codejava.service.CurrentstockService;
import net.codejava.vo.StockInsertVo;

@Service
public class CurrentstockServiceImpl implements CurrentstockService {

	@Autowired
	private CurrenstockRepository currenstockRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SellRepository sellRepository;
	
	@Autowired
	private StkLedgerRepository stkledgerrepository;

	private ModelMapper mapper;

	

	public CurrentstockServiceImpl(CurrenstockRepository currenstockRepository, ProductRepository productRepository,
			SellRepository sellRepository, StkLedgerRepository stkledgerrepository, ModelMapper mapper) {
		this.currenstockRepository = currenstockRepository;
		this.productRepository = productRepository;
		this.sellRepository = sellRepository;
		this.stkledgerrepository = stkledgerrepository;
		this.mapper = mapper;
	}

	@Override
	public Currenstock processStockIn(StockInsertVo stockvo) {
		System.out.println("STOCK IN");
		Currenstock curreststock = new Currenstock();
		
		if (!BeanUtils.isNullOrEmpty(currenstockRepository.findByProductidAndSellingprice(stockvo.getProductid(),
				stockvo.getSellingprice()))) {
			curreststock = currenstockRepository.findByProductidAndSellingprice(stockvo.getProductid(),
					stockvo.getSellingprice()).get(0);
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			String strDate= formatter.format(date); 
			curreststock.setUpdatedtime(strDate);
			curreststock.setCurrenstockqty(stockvo.getStockinqty()+curreststock.getCurrenstockqty());

		}
		else
		{
			curreststock = mapper.map(stockvo, Currenstock.class);
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			String strDate= formatter.format(date); 
			curreststock.setCreatedtime(strDate);
			curreststock.setCurrenstockqty(curreststock.getStockinqty());
		}
		currenstockRepository.save(curreststock);
		StockLedger stkLedger = mapper.map(curreststock, StockLedger.class);
		stkLedger.setStockinqty(stockvo.getStockinqty());
		stkLedger.setCurrentstockid(curreststock.getId());
		stkLedger.setDocid(stockvo.getDocid());
		stkLedger.setDocnum(stockvo.getDocnum());
		stkLedger.setQty(stockvo.getStockinqty());
		stkLedger.setUserid(stockvo.getUserid());
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		String strDate= formatter.format(date); 
		stkLedger.setCreatedtime(strDate);
		stkledgerrepository.save(stkLedger);
		return curreststock;
	}

	@Override
	public Currenstock processStockOut(StockInsertVo stockvo) {
		System.out.println("STOCK OUT");
		return new Currenstock();
	}

}