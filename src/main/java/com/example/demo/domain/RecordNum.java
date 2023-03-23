package com.example.demo.domain;

/**
 * ページング機能のページ数を格納するドメイン.
 * 
 * @author kenji.suzuki
 *
 */
public class RecordNum {
	
	/** レコード情報 */
	private Integer recordNum;

	public Integer getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	@Override
	public String toString() {
		return "RecordNum [recordNum=" + recordNum + "]";
	}
	
}
