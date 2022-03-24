package com.codegym.dao;

import com.codegym.model.Blog;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class BlogDao implements IBlogDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        String qlString = "select b from Blog as b";
        TypedQuery<Blog> query =  entityManager.createQuery(qlString, Blog.class);
        List<Blog> blogs = query.getResultList();
        return blogs;
    }

    @Override
    public Blog findOne(Long id) {
        if (id == null) return null;
        String qlString = "select b from Blog as b where b.id = :id";
        TypedQuery<Blog> query =  entityManager.createQuery(qlString, Blog.class);
        query.setParameter("id", id);
        Blog blog;
        try {
            blog = query.getSingleResult();
        } catch (NoResultException e){
            blog = null;
        }
        return blog;
    }

    @Override
    public Blog save(Blog blog) {
        if (findOne(blog.getId()) == null){
            entityManager.persist(blog);
        } else {
            entityManager.merge(blog);
        }
        return blog;
    }

    @Override
    public Blog remove(Blog blog) {
        if (findOne(blog.getId()) == null){
            return null;
        }
        entityManager.remove(blog);
        return blog;
    }

    @Override
    public Blog remove(Long id) {
        Blog blog = findOne(id);
        if (blog != null){
            entityManager.remove(blog);
        }
        return blog;
    }
}
