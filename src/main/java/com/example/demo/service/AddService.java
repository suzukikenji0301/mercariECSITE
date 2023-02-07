package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Add;
import com.example.demo.repository.AddRepository;

@Service
public class AddService {

	@Autowired
	private AddRepository addRepository;
	
	/**
	 * 商品情報を送信します.
	 * 
	 * @param add 商品情報
	 */
	public void insertAdd(Add add) {
		addRepository.insertAdd(add);
	}
}
