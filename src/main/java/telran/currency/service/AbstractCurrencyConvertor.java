package telran.currency.service;

import java.util.*;
import java.util.Map.Entry;

public class AbstractCurrencyConvertor implements CurrencyConvertor {
protected Map<String, Double> rates; //key - currency ISO code;
//value - amount of code's units in 1 EUR
	@Override
	public List<String> strongestCurrencies(int amount)
	{
		List<Entry<String,Double>> results=getTopEntrys(amount,false);		
		return outFormat(results);	
	}

	@Override
	public List<String> weakestCurrencies(int amount) 
	{
		List<Entry<String,Double>> results=getTopEntrys(amount,true);		
		return outFormat(results);	
	}

	private List<String> outFormat(List<Entry<String,Double>> cur)
	{
		return cur.stream().map(e->String.format("%s %f", e.getKey(),e.getValue())).toList();	
	}
	private List<Entry<String,Double>> getTopEntrys(int amount, boolean topOrBott) 
	{
	   // topOrBott false for strongest, true for weakest
		List<Entry<String,Double>> results = new ArrayList<>(rates.entrySet());
	    CurrencyComparator comp=new CurrencyComparator();
	    if(topOrBott) Collections.sort(results, comp);
	    else Collections.sort(results, comp.reversed());
	    return results.subList(0, amount);
	}
	
	@Override
	public double convert(String codeFrom, String codeTo, int amount) 
	{
		return amount*rates.get(codeTo)/rates.get(codeFrom);
	}

	@Override
	public HashSet<String> getAllCodes()
	{
		Set<String> rt= rates.keySet();
		HashSet<String> ret=new HashSet<String>();
		ret.addAll(rt);
		return ret;
	}

}
