package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void update(int userId, Meal meal) {
        repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int mealId) {
        checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    @Override
    public Meal get(int userId, int mealId) {
        return checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }
}