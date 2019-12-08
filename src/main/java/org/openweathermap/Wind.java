package org.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Wind {

	@JsonProperty("deg")
	private int deg;

	@JsonProperty("speed")
	private double speed;

	@JsonProperty("gust")
	private double gust;

	public void setDeg(int deg){
		this.deg = deg;
	}

	public int getDeg(){
		return deg;
	}

	public void setSpeed(double speed){
		this.speed = speed;
	}

	public double getSpeed(){
		return speed;
	}

	public void setGust(double gust){
		this.gust = gust;
	}

	public double getGust(){
		return gust;
	}
	@Override
 	public String toString(){
		return 
			"Wind{" + 
			"deg = '" + deg + '\'' + 
			",speed = '" + speed + '\'' +
			",gust = '" + gust + '\'' +
					"}";
		}
}