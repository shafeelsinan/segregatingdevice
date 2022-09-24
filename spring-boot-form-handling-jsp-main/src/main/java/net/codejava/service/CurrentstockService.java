package net.codejava.service;

import net.codejava.domain.Currenstock;
import net.codejava.vo.StockInsertVo;

public interface CurrentstockService {
	
	public Currenstock processStockIn(StockInsertVo stockvo);
	public Currenstock processStockOut(StockInsertVo stockvo);

}
