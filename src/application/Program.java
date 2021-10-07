package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		File path = new File("c:\\temp\\lambda7.txt");
		
		Scanner sc = new Scanner(System.in);
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] field = line.split(",");
				list.add(new Employee(field[0], field[1], Double.parseDouble(field[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			Double salary = sc.nextDouble();
			
			List<String> people = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.collect(Collectors.toList());
					
			people.forEach(System.out::println);
			
			Double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}

}
