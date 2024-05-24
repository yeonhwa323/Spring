package org.doit.ik.di4;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Component("record1")
public class RecordImpl4 implements Record4 {

	//@Component 자동등록 시 식별자는 맨앞 소문자로 기록됨.
	private int kor;
	private int eng;
	private int mat;
	
	@Override
	public int total() {
		return this.kor + this.eng + this.mat ;
	}

	@Override
	public double avg() {
		return this.total()/3.0;
	}
	
}
