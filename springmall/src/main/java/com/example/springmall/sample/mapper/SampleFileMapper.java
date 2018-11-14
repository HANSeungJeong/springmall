package com.example.springmall.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmall.sample.vo.SampleFile;

@Mapper
public interface SampleFileMapper {
	//insert
	int insertSampleFile(SampleFile sampleFile);
	//delete
	int deleteSampleFile(int sampleNo);
	//select one
	SampleFile selectOneSampleFile(int sampleNo);
}
