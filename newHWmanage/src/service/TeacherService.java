package service;

import java.util.List;

import dao.TeacherDao;
import domain.FileQueryTeacher;
import domain.Tea;

public class TeacherService {
	private TeacherDao teacherDao;
	

	
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	public void  addtea(Tea tea){
		this.teacherDao.addTea(tea);
		
		
	}
	public List<Tea> findall() {
		// TODO Auto-generated method stub
		
		return teacherDao.findall();
	}

	public List<FileQueryTeacher> findbyname(String tname) {
		// TODO Auto-generated method stub
		return teacherDao.findbyname(tname);
	}
	public void fabuzuoye(String text,String hname,String name) {
		// TODO Auto-generated method stub
		teacherDao.fabuzuoye(text,hname,name);
	}
	
	

}