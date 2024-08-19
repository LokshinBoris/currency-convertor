package telran.currency.service;

import java.util.Comparator;
import java.util.Map.Entry;

public class CurrencyComparator implements Comparator<Entry<String,Double>>
{
	@Override
	public int compare(Entry<String,Double> o1, Entry<String,Double> o2) 
	{
		int ret=0;
		if(o1.getValue() < o2.getValue()) 
		{
	       ret=1;
	    } 
		else if(o1.getValue() > o2.getValue())
		{
	       ret=-1;
	    }
	    return ret;
	}
}
