package com.example.springmall.sample.vo;

public class Sample {
	private int sampleNo;
	private String sampleId;
	private String samplePw;
	private SampleFile sampleFile;
// 생성자---------
	public Sample() {
		super();

	}
	public Sample(int sampleNo, String sampleId, String samplePw) {
		super();
		this.sampleNo = sampleNo;
		this.sampleId = sampleId;
		this.samplePw = samplePw;
	}
//---------생성자 끝
	public int getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(int sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getSamplePw() {
		return samplePw;
	}
	public void setSamplePw(String samplePw) {
		this.samplePw = samplePw;
	}
	@Override
	public String toString() {
		return "sample [sampleNo=" + sampleNo + ", sampleId=" + sampleId + ", samplePw=" + samplePw + "]";
	}
}
