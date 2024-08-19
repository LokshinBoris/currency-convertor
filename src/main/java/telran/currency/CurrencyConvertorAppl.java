package telran.currency;

import java.util.List;

import telran.currency.service.CurrencyConvertor;
import telran.currency.service.FixerApiPerDay;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;


public class CurrencyConvertorAppl
{

	public static void main(String[] args)
	{
		CurrencyConvertor currencyConvertor=new FixerApiPerDay();
		List<Item> currencyConvertorItems =
				CurrencyItems.getItems(currencyConvertor);
		
		currencyConvertorItems.add(Item.ofExit());
		Menu menu= new Menu("Currency converter", currencyConvertorItems.toArray(Item[]::new));
		menu.perform(new SystemInputOutput());
	}

}
