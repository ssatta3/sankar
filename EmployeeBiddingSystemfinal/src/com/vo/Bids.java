package com.vo;

public class Bids 
{
	private String itemName;
	private String description;
	private int points;
	private byte[] itemImage;
	private int itemId;

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public byte[] getItemImage() {
		return itemImage;
	}
	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
