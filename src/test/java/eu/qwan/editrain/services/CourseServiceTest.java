package eu.qwan.editrain.services;

import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.repositories.CourseRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @Nested
    class CreatingACourse {
        @Test
        public void createsANewCourseAndSavesItInTheRepository() {
            var createdCourse = courseService.createCourse("name", "description");
            verify(courseRepository).save(createdCourse);
            assertThat(createdCourse.getId(), is(not("")));
        }
    }
    @Nested
    class GettingCourses {
        @Test
        public void createsANewCourseAndSavesItInTheRepository() {
            Course course = new Course("someid", "someName", "someDescription");
            when(courseRepository.findAll()).thenReturn(List.of(course));
            var courses = courseService.findAll();
            assertThat(courses, is(List.of(course)));
        }
    }
}