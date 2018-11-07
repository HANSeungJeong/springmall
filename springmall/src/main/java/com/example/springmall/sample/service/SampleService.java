package com.example.springmall.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.Sample;

@Service
@Transactional
public class SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	//1
	public List<Sample> getSampleAll(HashMap<String, Object> map){
		//페이징 관련 코드
		int pagePerRow=10;
		int startRow = ((int)map.get("currentPage")-1)*pagePerRow; //현재페이지가 1페이지면 0행부터, 2페이지면 10행부터, 3페이지면 20행부터...
		int sampleAllCount=sampleMapper.selectSampleAllCount();
		int lastPage=sampleAllCount/pagePerRow;
		if(sampleAllCount%pagePerRow!=0) {
			lastPage++;
		}
		map.put("pagePerRow", pagePerRow);
		map.put("startRow", startRow);
		map.put("lastPage", lastPage);
		return sampleMapper.selectSampleAll(map);		
		
	}
	//2
	public int removeSample(int sampleNo) {
		
		return sampleMapper.deleteSample(sampleNo);
		
	}
}
