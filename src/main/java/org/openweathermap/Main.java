package org.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Main {

	@JsonProperty("temp")
	private double temp;

	@JsonProperty("feels_like")
	private double feelsLike;

	@JsonProperty("pressure")
	private int pressure;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("temp_min")
	private double tempMin;

	@JsonProperty("temp_max")
	private int tempMax;



	public void setTemp(double temp){
		this.temp = temp;
	}

	public double getTemp(){
		return temp;
	}

	public double getFeelsLike() { return feelsLike; 	}

	public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

	public void setTempMin(double tempMin){
		this.tempMin = tempMin;
	}

	public double getTempMin(){
		return tempMin;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
		return humidity;
	}

	public void setPressure(int pressure){
		this.pressure = pressure;
	}

	public int getPressure(){
		return pressure;
	}

	public void setTempMax(int tempMax){
		this.tempMax = tempMax;
	}

	public int getTempMax(){
		return tempMax;
	}

	@Override
 	public String toString(){
		return 
			"Main{" + 
			"temp = '" + temp + '\'' + 
			",temp_min = '" + tempMin + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",temp_max = '" + tempMax + '\'' + 
			"}";
		}
}