package org.doit.ik.di;

import java.util.Scanner;

import lombok.Data;
import lombok.Setter;

@Data
public class RecordViewImpl implements RecordView {

	// 의존객체 - 외부에서 주입 1)생성자 2)Setter 
	private RecordImpl record = null;
	//private RecordImpl record = new RecordImpl();
	
	// 1)생성자
	public RecordViewImpl() {		
	}
	
	public RecordViewImpl(RecordImpl record) {
		this.record = record;
	}
	
	// 2)Setter - @Data 벌써 생성되어짐.	
	
	
	@Override
	public void input() {
		try ( Scanner scanner = new Scanner(System.in) ){
			System.out.printf("> kor,eng,mat input ?");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void output() {
		System.out.printf("> kor=%d, eng=%d, mat=%d, tot=%d, avg=%.2f\n"
				, this.record.getKor()
				, this.record.getEng()
				, this.record.getMat()
				, this.record.total()
				, this.record.avg()
			);
	}
	
}
