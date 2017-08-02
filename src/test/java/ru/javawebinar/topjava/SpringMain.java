package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-app-test.xml", "spring-db-test.xml")) {
            System.out.println("Bean definition names: ");
            Arrays.stream(appCtx.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            DbPopulator dbPopulator = appCtx.getBean(DbPopulator.class);
            dbPopulator.execute();

            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExceeded.forEach(System.out::println);


            Meal meal = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 23, 00), "Поздний ужин", 567);
            Meal saved = mealController.create(meal);
            System.out.println("Saved: " + saved);
        }
    }
}
