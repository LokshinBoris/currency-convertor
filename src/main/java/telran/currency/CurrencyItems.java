package telran.currency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import telran.currency.service.CurrencyConvertor;
import telran.view.InputOutput;
import telran.view.Item;

public class CurrencyItems 
{
private static CurrencyConvertor currencyConvertor;
public static List<Item> getItems(CurrencyConvertor currencyConvertor) 
{
	CurrencyItems.currencyConvertor = currencyConvertor;
	Item[] items = {
			Item.of("convert currency", CurrencyItems::convert)	,
			Item.of("display strongest currency", CurrencyItems::getStrongest),
			Item.of("display weakest currency", CurrencyItems::getWeakest)
		};
		return new ArrayList<>(List.of(items));
	
}

 private static void convert(InputOutput io)
{
	HashSet<String> rt=currencyConvertor.getAllCodes();
	 String From=io.readStringPredicate("Input currency From", "this currency is not in currency list",
			 str->rt.contains(str));
	 String To=io.readStringPredicate("Input currency To", "this currency is not in currency list",
			 str->rt.contains(str));
	 int amount=io.readInt("How many", "format not integer ");
	 
	 String str=String.format("%d %s = %f %s\n", amount,From,currencyConvertor.convert(From, To, amount),To);
	 io.writeString(str);
}

 private static void getStrongest(InputOutput io)
 {
	int amount=io.readInt("How many strongest", "format not integer ");
	List<String>list=currencyConvertor.strongestCurrencies(amount); 
 	io.writeString(list.toString()+"\n");
 	
 }
 
 private static void getWeakest(InputOutput io)
 {
	int amount=io.readInt("How many weakest", "format not integer ");
	List<String>list=currencyConvertor.weakestCurrencies(amount); 
	io.writeString(list.toString()+"\n");
 }
}
