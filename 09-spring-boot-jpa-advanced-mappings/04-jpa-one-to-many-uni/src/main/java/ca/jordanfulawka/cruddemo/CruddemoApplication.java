package ca.jordanfulawka.cruddemo;

import ca.jordanfulawka.cruddemo.dao.AppDAO;
import ca.jordanfulawka.cruddemo.entity.Course;
import ca.jordanfulawka.cruddemo.entity.Instructor;
import ca.jordanfulawka.cruddemo.entity.InstructorDetail;
import ca.jordanfulawka.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createCourseAndReviews(appDAO);
//			retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};
		



	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		appDAO.deleteCourseById(id);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);
		System.out.println(course);
		System.out.println(course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("Chemistry");
		course.addReview(new Review("Great course"));
		course.addReview(new Review("Cool, well done"));
		course.addReview(new Review("What a dumb course, you are an idiot!"));
		appDAO.save(course);
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		appDAO.deleteCourseById(id);
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseById(id);
		course.setTitle("Enjoy the Simple Things");
		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("updating instructor id: " + id);
		instructor.setLastName("TESTER");
		appDAO.update(instructor);

		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("instructor: " + instructor);
		System.out.println("associated courses" + instructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + id);
        List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println("the associated courses: " + instructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor =
				new Instructor("Susan", "Public", "susan@gmail.com");
		InstructorDetail instructorDetail =
				new InstructorDetail(
						"https://susan.com",
						"video games");

		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Air Guitar");
		Course course2 = new Course("Pinball Masterclass");

		instructor.add(course1);
		instructor.add(course2);

		// This will also save the courses because of CascadeType.PERSIST
		System.out.println("Saving instructor: " + instructor);
		System.out.println("The couress: " + instructor.getCourses());
		appDAO.save(instructor);
		System.out.println("Done");
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
