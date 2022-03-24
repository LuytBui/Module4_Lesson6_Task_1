package com.codegym.service;

import com.codegym.dao.IBlogDao;
import com.codegym.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    IBlogDao blogDao;

    @Override
    public List<Blog> findAll() {
        return blogDao.findAll();
    }

    @Override
    public Blog findOne(Long id) {
        return blogDao.findOne(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public Blog remove(Blog blog) {
        return blogDao.remove(blog);
    }

    @Override
    public Blog remove(Long id) {
        return blogDao.remove(id);
    }
}
