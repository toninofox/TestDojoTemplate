import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import  org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

@RunWith(MockitoJUnitRunner.class)
public class FizBuzzTest {

	@Mock
	private Printer printer;
	private FizBuzz fizBuzz;
	
	@Mock
	private MyGreatDatabase database;
	
	@Captor
	public ArgumentCaptor<String> captor;
	
	@Before
	public void setUp() throws Exception {
		fizBuzz = new FizBuzz(printer,database);
	}

	@Test
	public void shouldCallPrinterWithAtLeastOneString() {
		fizBuzz.doIt();
		checkPrinter(atLeast(1));
	}
	

	@Test
	public void shouldCallPrinterWith100Times() {
		fizBuzz.doIt();
		checkPrinter(times(100));
	}
	
	@Test
	public void shouldPrintFizzInsteadOfMultipleOfThree() throws Exception {
		fizBuzz.doIt();
		checkPrinter(times(100));
		List<String> allValues = captor.getAllValues();
		assertThat(allValues.get(2), Matchers.equalTo("Fizz"));
		assertThat(allValues.get(3), Matchers.equalTo("4"));
		assertThat(allValues.get(65), Matchers.equalTo("Fizz"));
	}
	

	@Test
	public void shouldPrintBuzzInsteadOfMultipleOfFiveButNotMultipleOfThree() throws Exception {
		when(database.getFizzNumber()).thenReturn(4);
		fizBuzz.doIt();
		checkPrinter(times(100));
		List<String> allValues = captor.getAllValues();
		assertThat(allValues.get(3), Matchers.equalTo("Fizz"));
	}

	

	@Test
	public void shouldPrintFizzBuzzInsteadOfMultipleOfThreeAndFive() throws Exception {
		fizBuzz.doIt();
		checkPrinter(times(100));
		List<String> allValues = captor.getAllValues();
		assertThat(allValues.get(10), Matchers.equalTo("11"));
		assertThat(allValues.get(14), Matchers.equalTo("FizzBuzz"));
	}
	

	private void checkPrinter(VerificationMode times) {
		verify(printer,times).print(captor.capture());
	}

}
