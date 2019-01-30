package com.uknown.equity.dto;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Share_information")
public class EquityDTO  implements Serializable{
	
	@Id
	@GenericGenerator(name="pk",strategy="increment")
	@GeneratedValue(generator="pk")
	@Column(name="id")
	private int id;

	@Column(name="symbol")
	private String symbol;
	
	@Column(name="series")
	private String series;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="prev_close")
	private double prevClose;
	
	@Column(name="open_price")
	private double openPrice;
	
	@Column(name="high_price")
	private double highPrice;
	
	@Column(name="low_price")
	private double lowPrice;
	
	@Column(name="last_price")
	private double lastPrice;
	
	@Column(name="close_price")
	private double closePrice;
	
	@Column(name="avg_price")
	private double avgPrice;
	
	@Column(name="ttl_trd_quantity")
	private double ttlTrdQuantity;
	
	@Column(name="turnover_lacs")
	private double turnoverLacs;
	
	@Column(name="no_of_trades")
	private double noOfTrades;
	
	@Column(name="delivery_quantity")
	private double deliveryQuantity;
	
	@Column(name="delivery_percentage")
	private double deliveryPercentage;
	
	public EquityDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(double prevClose) {
		this.prevClose = prevClose;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public double getTtlTrdQuantity() {
		return ttlTrdQuantity;
	}

	public void setTtlTrdQuantity(double ttlTrdQuantity) {
		this.ttlTrdQuantity = ttlTrdQuantity;
	}

	public double getTurnoverLacs() {
		return turnoverLacs;
	}

	public void setTurnoverLacs(double turnoverLacs) {
		this.turnoverLacs = turnoverLacs;
	}

	public double getNoOfTrades() {
		return noOfTrades;
	}

	public void setNoOfTrades(double noOfTrades) {
		this.noOfTrades = noOfTrades;
	}

	public double getDeliveryQuantity() {
		return deliveryQuantity;
	}

	public void setDeliveryQuantity(double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}

	public double getDeliveryPercentage() {
		return deliveryPercentage;
	}

	public void setDeliveryPercentage(double deliveryPercentage) {
		this.deliveryPercentage = deliveryPercentage;
	}
	
	@Override
	public String toString() {
		return "EquityDTO [id=" + id + ", symbol=" + symbol + ", series=" + series + ", date=" + date + ", prevClose="
				+ prevClose + ", openPrice=" + openPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
				+ ", lastPrice=" + lastPrice + ", closePrice=" + closePrice + ", avgPrice=" + avgPrice
				+ ", ttlTrdQuantity=" + ttlTrdQuantity + ", turnoverLacs=" + turnoverLacs + ", noOfTrades=" + noOfTrades
				+ ", deliveryQuantity=" + deliveryQuantity + ", deliveryPercentage=" + deliveryPercentage + "]";
	}

	
	
}
