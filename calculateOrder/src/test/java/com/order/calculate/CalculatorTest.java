package com.order.calculate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.order.calculate.Interface.Calculator;
import com.order.calculate.impl.CalculatorImpl;
import com.order.calculate.model.Item;
import com.order.calculate.model.Order;
import com.order.calculate.model.OrderLine;

/**
 * Usage : testing all scenarios in {@link Calculator}
 * 
 * @author priya
 */
public class CalculatorTest {
	private Calculator calculator;
	private double resultVal;

	/**
	 * Initialize values;
	 * 
	 * @throws Exception
	 *             not applicable
	 */
	@Before
	public void setUp() throws Exception {
		calculator = new CalculatorImpl();
		resultVal = 0.00;
	}

	/**
	 * reset values.
	 * 
	 * @throws Exception
	 *             not applicable
	 */
	@After
	public void tearDown() throws Exception {
		calculator = null;
		resultVal = 0.00;
	}

	/**
	 * format decimal round to highest value.
	 * 
	 * @throws Exception
	 *             not applicable
	 */
	@Test
	public void testCase1() throws Exception {
		resultVal = calculator.format(15.349);
		assertEquals("assertion failed as value NOT formated", 15.35D, resultVal, 0.00);

	}

	/**
	 * format decimal format to 2 decimal positions.
	 * 
	 * @throws Exception
	 *             not applicable
	 */
	@Test
	public void testCase2() throws Exception {
		resultVal = calculator.format(15.34378945678);
		assertEquals("assertion failed as value NOT formated", 15.35D, resultVal, 0.00);

	}

	/**
	 * format decimal format to 0.000.
	 * 
	 * @throws Exception
	 *             not applicable
	 */
	@Test
	public void testCase3() throws Exception {
		resultVal = calculator.format(0.00);
		assertEquals("assertion failed as value NOT formated", 0.00D, resultVal, 0.00);

	}

	/**
	 * Happy path where result are matching for proper input for three
	 * different inputs.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase4() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("book", (float) 12.49), 1));
		orderObj.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		orderObj.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		orderMap.put("Order 1", orderObj);

		// Create new Order
		orderObj = new Order();

		orderObj.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		orderObj.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 2", orderObj);

		// Create new Order
		orderObj = new Order();

		orderObj.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		orderObj.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		orderObj.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		orderObj.add(new OrderLine(new Item("box of importd chocolates", (float) 11.25), 1));

		orderMap.put("Order 3", orderObj);

		resultVal = calculator.calculate(orderMap);
		System.out.println("Complete Order : "+orderMap);
		assertEquals("assertion failed as value Calculation wrong", 153.81D, resultVal, 0.00);

	}

	/**
	 * Happy path where result are matching for proper input for one
	 * Order.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase5() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("book", (float) 12.49), 1));
		orderObj.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		orderObj.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 28.33D, resultVal, 0.00);

	}

	/**
	 * Happy path where result are matching for proper input for one
	 * Order with Imported.
	 * @throws Exception  throws Exception if Assertion fail.
	 */
	@Test
	public void testCase6() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		orderObj.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 57.5D, resultVal, 0.00);

	}

	/**
	 * Item as null. Expected:Exception must be thrown with message.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase7() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		boolean isExceptionOccured = false;

		try {
			orderObj.add(new OrderLine(null, 1));
			orderObj.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));
			orderMap.put("Order 1", orderObj);
			resultVal = calculator.calculate(orderMap);
		} catch (Exception e) {
			isExceptionOccured = true;
		}
		assertTrue("assertion method must throw exception", isExceptionOccured);
	}

	/**
	 * if In Item Description 'imported' as Upper Case IMPORTED and Mixed
	 * case imPorTed Expected:IMPORTED and Mixed case imPorTed contain values
	 * must be consider as 'Imported' and calculate 5% extra tax.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase8() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("IMPORTED box of chocolate", 10), 1));
		orderObj.add(new OrderLine(new Item("imPorTed bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 57.5D, resultVal, 0.00);

	}

	/**
	 * In Item Description imported as 'import' Expected:'import' contain
	 * values must be consider as 'Imported' and calculate 5% extra tax.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase9() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("IMPORTED box of chocolate", 10), 1));
		orderObj.add(new OrderLine(new Item("imPorTed bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 57.5D, resultVal, 0.00);

	}

	/**
	 * Order as null. 
	 * Expected:Exception must be thrown with message.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase10() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		boolean isExceptionOccured = false;

		try {
			orderObj.add(null);
			orderObj.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));
			orderMap.put("Order 1", orderObj);
			resultVal = calculator.calculate(orderMap);
		} catch (Exception e) {
			isExceptionOccured = true;
		}
		assertTrue("assertion method must throw exception", isExceptionOccured);
	}

	/**
	 * Quantity value zero. 
	 * Expected:Should calculate total price for
	 * remaining items.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase11() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("IMPORTED box of chocolate", 10), 0));
		orderObj.add(new OrderLine(new Item("imPorTed bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 47.5D, resultVal, 0.00);
	}

	/**
	 * Quantity value negative. Expected:Should calculate total price
	 * for remaining items.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase12() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("IMPORTED box of chocolate", 10), -1));
		orderObj.add(new OrderLine(new Item("imPorTed bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 47.5D, resultVal, 0.00);
	}

	/**
	 * With Proper Quantity value. 
	 * Expected:Should calculate total price
	 * for all items considering Quantity.
	 * 
	 * @throws Exception
	 *             throws Exception if Assertion fail.
	 */
	@Test
	public void testCase13() throws Exception {
		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
		Order orderObj = new Order();

		orderObj.add(new OrderLine(new Item("IMPORTED box of chocolate", 10), 2));
		orderObj.add(new OrderLine(new Item("imPorTed bottle of perfume", (float) 47.50), 2));

		orderMap.put("Order 1", orderObj);

		resultVal = calculator.calculate(orderMap);
		assertEquals("assertion failed as value Calculation wrong", 115.0D, resultVal, 0.00);
	}

	

}
