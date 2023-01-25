package test;

import java.util.Date;

import beans.Employe;
import service.EmployeService;
import service.MachineService;
import beans.Machine;
public class Test {

	public static void main(String[] args) {
		EmployeService es = new EmployeService();
		MachineService ms = new MachineService();
//		es.create(new Employe("Safi3", "Ali3", 10000));
		
		//es.delete(es.findById(3));
		
		
		for(Employe e : es.findAll()) {
			System.out.println(e);
		}
		for(Machine m : ms.findAll()) {
			System.out.println(m);
		}
	}

}
