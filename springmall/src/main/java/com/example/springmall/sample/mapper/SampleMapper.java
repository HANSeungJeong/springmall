package com.example.springmall.sample.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmall.sample.vo.Sample;

@Mapper
public interface SampleMapper {
	//1.insert
	List<Sample> selectSampleAll(HashMap<String, Object> map);
	int selectSampleAllCount();
	//2.select all
	int insertSample(Sample sample);
	//3.delete
	int deleteSample(int sampleNo);
	//4.update
	int updateSample(Sample sample);
}
