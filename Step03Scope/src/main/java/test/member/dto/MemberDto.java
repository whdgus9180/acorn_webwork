package test.member.dto;

import java.util.List;

public class MemberDto {
	private int num;
	private String name;
	private String addr;
	//생성자
	public MemberDto() {
		
	}
	
	public MemberDto(int num, String name, String addr) {
		super();
		this.num = num;
		this.name = name;
		this.addr = addr;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public List<MemberDto> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
