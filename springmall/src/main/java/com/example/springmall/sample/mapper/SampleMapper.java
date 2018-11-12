package com.example.springmall.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmall.sample.vo.PageMaker;
import com.example.springmall.sample.vo.Sample;

@Mapper
public interface SampleMapper {	//interface란? 추상메서드, 추상클래스, 인터페이스. 자바는 다중상속을 하지 않는다. 그렇지만 인터페이슨 다중상속을 할수 있다.
	//추상메서드는 무조건 public이어야 한다. 그러기에 public을 생략할수 있다. 
	//1.insert
	List<Sample> selectSampleAll(PageMaker pageMaker);	//인터페이스는 추상메서드이기에 public, abstract이 생략이 된다.
	int selectSampleAllCount();
	//2.delete
	int deleteSample(int sampleNo);
	//3.insert
	int insertSample(Sample sample);
	//4.update
	int updateSample(Sample sample);
	
	//5.select one
	Sample selectOne(int sampleNo);
}
