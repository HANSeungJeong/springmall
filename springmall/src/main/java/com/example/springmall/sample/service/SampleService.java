package com.example.springmall.sample.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.springmall.sample.mapper.SampleFileMapper;
import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.PageMaker;
import com.example.springmall.sample.vo.Sample;
import com.example.springmall.sample.vo.SampleFile;
import com.example.springmall.sample.vo.SampleRequest;

@Service
@Transactional
public class SampleService {
	@Autowired 
	//@Autowired은 의존관계를 자동설정할 때 사용하며 타입을 이용하여 의존하는 객체를 삽입해 준다. (해당 타입의 빈객체가 존재하지 않거나 또는 2개 이상 존재할 경우 스프링은 예외를 발생시킨다.)주입 

	private SampleMapper sampleMapper;	
	@Autowired 
	private SampleFileMapper sampleFileMapper;
	//4-1
		public Sample getSample(int sampleNo) {
			return sampleMapper.selectOne(sampleNo);
		}
	//4-2
	public int modifySample(Sample sample) {
		return sampleMapper.updateSample(sample);	
	}
		
	//3
	public int addSample(SampleRequest sampleRequest) {
		// 1
		Sample sample = new Sample();
		sample.setSampleId(sampleRequest.getSampleId());
		sample.setSamplePw(sampleRequest.getSamplePw());
		int insertSampleNum = sampleMapper.insertSample(sample); // ai -> sampleNo
		// 2
		SampleFile sampleFile = new SampleFile();
		MultipartFile multipartFile = sampleRequest.getMultipartFile();
		// 1. SampleFileNo : AutoIncrement
		// 2. SampleNo
		sampleFile.setSampleNo(sample.getSampleNo());
		// insertSample(sample)가 실행된 후에 PK값이 sample 매개변수로 채워진다.
		// 3. SampleFilePath
		String sampleFilePath=sampleRequest.getSampleFilePath(); // 복잡한 루틴을 통해서
		sampleFile.setSampleFilePath(sampleFilePath);
		// 4. 확장자
		String originalFileName = multipartFile.getOriginalFilename();
		String sampleFileExt = originalFileName.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1); //
		sampleFile.setSampleFileExt(sampleFileExt);
		// 5. 이름
		String sampleFileName = UUID.randomUUID().toString();
		sampleFile.setSampleFileName(sampleFileName);
		// 6. 타입
		sampleFile.setSampleFileType(multipartFile.getContentType());
		// 7. 크기
		sampleFile.setSampleFileSize(multipartFile.getSize());
		//sampleFilePath와 같은 경로에 디렉토긱가 없다면 디렉토리 생성
		File dir = new File(sampleFilePath);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		System.out.println(sampleFile);
		int insertSampleFileNum = sampleFileMapper.insertSampleFile(sampleFile);
		if(insertSampleFileNum == 1) {
			System.out.println("originalFileName : " + originalFileName + "인 파일 추가 성공");
		} else {
			System.out.println("originalFileName : " + originalFileName + "인 파일 추가 실패");
		}
		try {
			// 내가 원하는 이름의 빈파일 하나 만들어 multipartFile을 빈파일로 복사하자
			multipartFile.transferTo(new File(sampleFilePath + "\\" + sampleFileName + "." + sampleFileExt));
		} catch (IllegalStateException e) {
			System.out.println("IllegalStateException 발생");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException 발생");
			e.printStackTrace();
		}
		/*
		 * SampleRequest --> Sample, SampleFile
		 * 1. multipartfile 파일데이터 -> 저장
		 * 2. multipartfile 정보 -> 새로운정보 추가 -> SampleFile 
		 */
		return insertSampleNum;
	}
	//2
		public int removeSample(int sampleNo) {
			
			return sampleMapper.deleteSample(sampleNo);
			
		}
	
	//1
	public List<Sample> getSampleAll(HashMap<String, Object> map){
		//페이징 관련 코드(set메서드 순서를 지켜야한다)
		//페이징에서 기본적으로 넣어줘야 할 값을 설정
		PageMaker pageMaker=(PageMaker)map.get("pageMaker");
		pageMaker.setRowPerPage(10);
		pageMaker.setPagePerBlock(10);
		pageMaker.setAllCount(sampleMapper.selectSampleAllCount((String)map.get("searchWord")));
		//페이징에 필요한 값을 계산하여 설정
		pageMaker.setStartRow();
		pageMaker.setLastPage();
		pageMaker.setCurrentBlock();
		pageMaker.setLastBlock();
		pageMaker.setStartPage();
		pageMaker.setEndPage();
		//이전 페이지와 다음 페이즈를 컨트롤 하는 조건문
		if(pageMaker.getCurrentPage() > 0 && pageMaker.getCurrentPage() < pageMaker.getPagePerBlock() + 1) {
			pageMaker.setPrevPage(false);
			pageMaker.setNextPage(true);
		} else if(pageMaker.getLastBlock() == pageMaker.getCurrentBlock()) {
			pageMaker.setPrevPage(true);
			pageMaker.setNextPage(false);
		} else {
			pageMaker.setPrevPage(true);
			pageMaker.setNextPage(true);			
	}
		return sampleMapper.selectSampleAll(map);		

	}
}
