package ca.jordanfulawka.cruddemo;

import ca.jordanfulawka.cruddemo.dao.AppDAO;
import ca.jordanfulawka.cruddemo.entity.Instructor;
import ca.jordanfulawka.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
		};


	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 4;
		System.out.println("Deleting instructor detail id: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("instructorDetail: " + instructorDetail);
		System.out.println("associated instructor: " + instructorDetail.getInstructor());
		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor");
		System.out.println("instructor: " + instructor);
		System.out.println("the associated instructor details: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

//		Instructor instructor =
//				new Instructor("Chad", "Darby", "darby@gmail.com");
//
//		InstructorDetail instructorDetail =
//				new InstructorDetail(
//						"https://chaddarby.com",
//						"coding");

		Instructor instructor =
				new Instructor("Madhu", "Patel", "madhu@gmail.com");

		InstructorDetail instructorDetail =
				new InstructorDetail(
						"https://patel.com",
						"guitar");

		instructor.setInstructorDetail(instructorDetail);

		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		System.out.println("Saving instructor: " +  instructor);
		appDAO.save(instructor);
		System.out.println("Done");
	}

}
