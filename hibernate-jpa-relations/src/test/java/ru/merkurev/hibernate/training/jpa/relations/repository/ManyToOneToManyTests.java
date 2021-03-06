package ru.merkurev.hibernate.training.jpa.relations.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.relations.entity.Course;
import ru.merkurev.hibernate.training.jpa.relations.entity.Review;
import ru.merkurev.hibernate.training.jpa.relations.entity.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ManyToOneToManyTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void getCourse() {
        Course course = courseRepository.findById(1L);
        List<Review> reviews = course.getReviews(); // without LazyInitializationException because Extended scope
        assertNotNull(course);
        assertFalse(reviews.isEmpty());
    }

    @Test
    void getReviewsNPlus1Problem() {
        List<Review> reviews = reviewRepository.findAll();
        for (Review review : reviews) {
            // many queries to database
            assertNotNull(review.getDescription());
        }
    }
}