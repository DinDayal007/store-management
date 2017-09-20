package com.storemanagement.utils;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.services.GroupService;
public class Application {
	public static void main(String[] args) {
		GroupService service = new GroupService();
		MainGroup mainGrp = new MainGroup();
		mainGrp.setName("Main Group");
		SubGroup subGrp = new SubGroup();
		subGrp.setName("Sub Group 1");
		subGrp.setMainGroup(mainGrp);
		SubGroup subGrp2 = new SubGroup();
		subGrp2.setName("Sub Group 2");
		subGrp2.setMainGroup(mainGrp);
		SubGroup subGrp3 = new SubGroup();
		subGrp3.setName("Sub Group 3");
		subGrp3.setMainGroup(mainGrp);
		service.addObject(mainGrp);
		service.addObject(subGrp);
		service.addObject(subGrp2);
		service.addObject(subGrp3);
	}
}
