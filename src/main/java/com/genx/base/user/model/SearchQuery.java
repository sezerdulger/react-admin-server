package com.genx.base.user.model;

import org.bson.Document;

import lombok.Data;

@Data
public class SearchQuery {
	private int page;
	private int size;
	
	private Document query;
}