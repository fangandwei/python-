package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.TeacherService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import domain.FileQueryTeacher;
import domain.Stu;
import domain.StuTea;
import domain.Tea;

public class TeacherAction extends ActionSupport implements ModelDriven<Tea>{
    private Tea tea=new Tea();
    private TeacherService teacherService;
	@Override
	public Tea getModel() {
		// TODO Auto-generated method stub
		return tea;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	//实现老师的登陆操作
		public  String  tlogin(){
			//查出数据库中的所有记录，List  然后遍历，如果有和前台传过来的字符串相匹配的
//			就允许登陆，否则不能
		List<Tea> teas=teacherService.findall();
		System.out.println(teas);
		for (Tea Evtea : teas) {
			System.out.println("--------------");
			System.out.println(tea.getName());
			System.out.println(tea.getPassword());
			if(tea.getName().equals(Evtea.getName())&&
			   tea.getPassword().equals(Evtea.getPassword())		
			 ){
				
				ActionContext.getContext().getSession().put("name", tea.getName());
				
				System.out.println(Evtea);
				return SUCCESS;
				
			}
		}
		//上面一旦return  下面就不走了，所以不用担心还会走下面的
	    ValueStack stack = ServletActionContext.getContext().getValueStack();
				stack.set("error", "你的用户名和密码不匹配，请重登");
				return ERROR;
		}
		
		//实现老师的查作业操作
	                public String	Tfindallhbyname(){
	                	List<FileQueryTeacher> findbyname = teacherService.findbyname( (String) ActionContext.getContext().getSession().get("name"));
	                	//将数据放在ActionContext中，用于在前台展示
	            		//???
	            	   Map<String, Object> session = ServletActionContext.getContext().getSession();
	            		session.put("findbyname", findbyname);
	            		
	            		return SUCCESS;
	                	
	                }
	       //老师注册
	        public String zhuce(){
	        	//维护学生与老师的关系
	        	HttpServletRequest request = ServletActionContext.getRequest();
	        	String stuname = request.getParameter("select");
	        	Stu stu=new Stu();
	        	stu.setName(stuname);
	           	
	        	System.out.println("------------------");
        		this.teacherService.addtea(tea);
        		return SUCCESS;
	        }
		//老师发布作业
	      public String fabuzuoye(){
	    	       Tfindallhbyname();
	    	       HttpServletRequest request = ServletActionContext.getRequest();
	    	       String text = request.getParameter("textarea2");
	    	       String hname = request.getParameter("textarea1");
	    	      String name  = (String) ServletActionContext.getContext().getSession().get("name");
	    	  
	    	  teacherService.fabuzuoye(text,hname,name);
	    	  return SUCCESS;
	      }
	      
	        
		
		
		

}