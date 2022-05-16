package com.kiran.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.server.ResponseStatusException;

import com.kiran.demo.model.Details;
import com.kiran.demo.model.TransferBalanceRequest;
import com.kiran.demo.repository.AccountRepository;
import com.kiran.demo.repository.BankRepo;
import com.kiran.demo.repository.TransactionRepository;
import com.kiran.demo.service.AccountService;
import com.kiran.demo.service.AciveandMoreService;
import com.kiran.demo.service.CheckActiveService;
import com.kiran.demo.service.MABService;
import com.kiran.demo.service.addCustomerService;

@SpringBootTest
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BankingApplicationTests {

	@Autowired
	private CheckActiveService check;

	@Autowired
	private AciveandMoreService check1;
	
	@Autowired
	private MABService check2;
	
	@Autowired
	private addCustomerService add;

	@Autowired
	private AccountService accservice;
	
	@Autowired
	private AccountRepository accrepo;
	
	
	// Adding Customer
	@Test
	public void NewCustomer() throws Exception {
		Details detail = new Details("1254869470", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999") , "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		assertThat(result.equals(detail));
	}

	// Adding Multiple Customer
	@Test
	public void NewCustomers() throws Exception {
		Details detail = new Details("1254869470", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999") , "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		Details detail1 = new Details("1254869471", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999") , "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result1 = add.addCustomer(detail1);
		assertEquals(2,add.getAllOrders().size());
	}
	
	// Checking Active
	@Test
	public void ActiveCustomer() throws Exception {
		boolean actual = false;
		boolean expected = true;
		Details detail = new Details("1254869470", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		List<Details> active = check.checkActivity();
		for (Details testObj : active) {
			actual = testObj.isAccountstatus();
		}
		
		assertTrue(expected);
		assertTrue(actual);
		assertEquals(actual, expected);
	}
	
	// Checking Active false
	@Test
	public void ActiveCustomerFalse() throws Exception {
		boolean actual = false;
		boolean expected = true;
		Details detail = new Details("1254869470", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", false , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		List<Details> active = check.checkActivity();
		for (Details testObj : active) {
			actual = testObj.isAccountstatus();
		}
		
		assertTrue(expected);
		assertFalse(actual);
		assertNotSame(actual, expected);
	}
	
	// Checking Active and balance > 0
	@Test
	public void ActiveandBalCustomer() throws Exception {
		Details detail = new Details("1254869470", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		Details detail1 = new Details("1254869471", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(0),"9999999999", "rest@gmail.com", 2500);
		Details result1 = add.addCustomer(detail1);
		Details detail3 = new Details("1254869472", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result3 = add.addCustomer(detail3);
		List<Details> actual1 = check1.checkActivitybal();
		List<Details> expected1 = new ArrayList<Details>();
		expected1.add(new Details("1254869470", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500));
		expected1.add(new Details("1254869472", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500));

		assertEquals(actual1.size(), expected1.size());
	}
	
	// Checking Active and balance > 0 and MAB > 1000
	@Test
	public void ActiveandBalandMABCustomer() throws Exception {
		Details detail = new Details("1254869477", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
		Details result = add.addCustomer(detail);
		Details detail1 = new Details("1254869478", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(0),"9999999999", "rest@gmail.com", 2500);
		Details result1 = add.addCustomer(detail1);
		Details detail3 = new Details("1254869479", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 900);
		Details result3 = add.addCustomer(detail3);
		List<Details> actual2 = check2.checkActivitymab();
		List<Details> expected2 = new ArrayList<Details>();
		expected2.add(new Details("1254869477", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true , new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500));
		
		assertEquals(actual2.size(), expected2.size());
	}
	
	//Checking Money Transfer
	@Test
	public void sendMoneyTest() throws Exception {
		Details account1 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		Details account2 = new Details("1254869411", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);		
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
	public void getStatement() throws Exception {
		Details account1 = new Details("1254869412", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		Details account2 = new Details("1254869413", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(2000),"9999999999", "rest@gmail.com", 2500);
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
	
	// Test cases for all the validations
    @Test
    public void CheckValidations() throws Exception {
    	
    	// Account id is 9 digits
		Details account1 = new Details("125486941", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		assertThrows(TransactionSystemException.class, () -> add.addCustomer(account1));
		
		// Account id with letter
		Details account2 = new Details("125486941a", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		assertThrows(TransactionSystemException.class, () -> add.addCustomer(account2));
		
		// Age is less than 18 years
		Details account3 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/2015"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		assertThrows(ResponseStatusException.class, () -> add.addCustomer(account3));
		
		// Age is greater than 60 years
	    Details account4 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1961"), "savings", true, new BigDecimal(50000),"9999999999", "rest@gmail.com", 2500);
		assertThrows(ResponseStatusException.class, () -> add.addCustomer(account4));
				
		//Phone number is 9 digit
		Details account5 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"999999999", "rest@gmail.com", 2500);
		assertThrows(TransactionSystemException.class, () -> add.addCustomer(account5));
		
		//Phone number is non integer
		Details account6 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"999999999a", "rest@gmail.com", 2500);
		assertThrows(TransactionSystemException.class, () -> add.addCustomer(account6));
				
		//wrong mail id
		Details account7 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"9999999999", "restgmail.com", 2500);
		assertThrows(TransactionSystemException.class, () -> add.addCustomer(account7));
		
		//null mail id
		Details account8 = new Details("1254869410", "Kiran", 102, new SimpleDateFormat("dd/MM/yyyy").parse("24/04/1999"), "savings", true, new BigDecimal(50000),"9999999999", "", 2500);
		assertThrows(TransactionSystemException.class, () -> add.addCustomer(account8));
    }
}
