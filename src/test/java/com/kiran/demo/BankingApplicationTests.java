package com.kiran.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kiran.demo.model.Details;
import com.kiran.demo.model.TransferBalanceRequest;
import com.kiran.demo.repository.AccountRepository;
import com.kiran.demo.repository.TransactionRepository;
import com.kiran.demo.service.AccountService;
import com.kiran.demo.service.CheckActiveService;
import com.kiran.demo.service.addCustomerService;

@SpringBootTest
class BankingApplicationTests {

	@Autowired
	private CheckActiveService check;

	@Autowired
	private addCustomerService add;

	@Autowired
	private TransactionRepository trarepo;

	@Autowired
	private AccountService accservice;
	
	@Autowired
	private AccountRepository accrepo;
	
	// Adding Customer
	@Test
	public void NewCustomer() throws ParseException {
		Details detail = new Details("1254869478", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("1999/24/04") , "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		assertThat(result.equals(detail));
	}

	// Checking Active
	@Test
	public void ActiveCustomer() throws ParseException {
		boolean actual = false;
		boolean expected = true;
		Details detail = new Details("1254869478", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("1999/24/04"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		List<Details> active = check.checkActivity();
		for (Details testObj : active) {
			actual = testObj.isAccountstatus();
		}
		assertEquals(actual, expected);
	}

	//Checking Money Transfer
	@Test
	public void sendMoneyTest() throws ParseException {
		Details account1 = new Details("1254869478", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("1999/24/04"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		Details account2 = new Details("1254869473", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("1999/24/04"), "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);		
		Details result1 = add.addCustomer(account1);
		Details result2 = add.addCustomer(account2);
		TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest(account1.getAccountid(),account2.getAccountid(), new BigDecimal(3000));
		accservice.sendMoney(transferBalanceRequest);
		BigDecimal bd1 = accrepo.findByAccountidEquals(account1.getAccountid()).getAccountbal();
		BigDecimal bd2 = accrepo.findByAccountidEquals(account2.getAccountid()).getAccountbal();
		BigDecimal bd11 = new BigDecimal(47000);
		BigDecimal bd22 = new BigDecimal(5000);
		assertThat(bd1 , Matchers.comparesEqualTo(bd11));
		assertThat(bd2 , Matchers.comparesEqualTo(bd22));
	}
	
	//Checking Return Statement
	@Test
	public void getStatement() throws ParseException {
		Details account1 = new Details("1254869478", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("1999/24/04"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		Details account2 = new Details("1254869473", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("1999/24/04"), "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result1 = add.addCustomer(account1);
		Details result2 = add.addCustomer(account2);
		TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest(account1.getAccountid(),account2.getAccountid(), new BigDecimal(3000));
		accservice.sendMoney(transferBalanceRequest);
		BigDecimal bd1 = accrepo.findByAccountidEquals(account1.getAccountid()).getAccountbal();
		BigDecimal bd11 = new BigDecimal(47000);
		assertThat(bd1, Matchers.comparesEqualTo(bd11));
		accservice.sendMoney(transferBalanceRequest);
		BigDecimal bd3 = accservice.getStatement(account1.getAccountid()).getCurrentBalance();
		BigDecimal bd33 = new BigDecimal(44000);
		assertThat(bd3, Matchers.comparesEqualTo(bd33));
		assertThat(accservice.getStatement(account1.getAccountid()).getTransactionHistory().size()).isEqualTo(2);
	}
	
}
