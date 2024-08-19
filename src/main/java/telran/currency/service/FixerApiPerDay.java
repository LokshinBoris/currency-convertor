package telran.currency.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.json.JSONObject;

public class FixerApiPerDay extends AbstractCurrencyConvertor
{
   protected String uriString = "http://data.fixer.io/api/latest?access_key=0934a1dd70bd1c4639766cd70f91ed28";
   private LocalDate lastDay=LocalDate.of(2000, 1, 1);

   public FixerApiPerDay()
   {
	   rates = getRates();
   }
protected HashMap<String, Double> getRates() 
{
	HttpClient httpClient = HttpClient.newHttpClient();
	HttpRequest request=null;
	HttpResponse<String> response=null;
	try 
	{
		request = HttpRequest.newBuilder(new URI("http://data.fixer.io/api/latest?access_key=0934a1dd70bd1c4639766cd70f91ed28"))
				  .build();
	}
	catch (URISyntaxException e)
	{
		
		e.printStackTrace();
	}
	
	try 
	{
		response = httpClient.send(request, BodyHandlers.ofString());
	} 
	catch (IOException e)
	{		
		e.printStackTrace();
	} 
	catch (InterruptedException e)
	{	
		e.printStackTrace();
	}
	JSONObject jsonObject = new JSONObject(response.body());
	JSONObject jsonRates = jsonObject.getJSONObject("rates");
	Set<String> keys=jsonRates.keySet();
	HashMap<String, Double> rt=new HashMap<String,Double>();
	keys.forEach(k->rt.put(k,jsonRates.getDouble(k)));
	return rt;
}

@Override
public List<String> strongestCurrencies(int amount) {
	try {
		refresh();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return super.strongestCurrencies(amount);
}
@Override
public List<String> weakestCurrencies(int amount) {
	try {
		refresh();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return super.weakestCurrencies(amount);
}
@Override
public double convert(String codeFrom, String codeTo, int amount) {
	try
	{
		refresh();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return super.convert(codeFrom, codeTo, amount);
}
private void refresh() throws Exception
{
	// TODO Auto-generated method stub
	//checks whether refresh is needed 
	//if so it calls getRates method for updating "rates" map
	//one per day
	if(lastDay.until(LocalDate.now(),ChronoUnit.DAYS)>0)
	{
		rates=this.getRates();
	}
	
}
}
